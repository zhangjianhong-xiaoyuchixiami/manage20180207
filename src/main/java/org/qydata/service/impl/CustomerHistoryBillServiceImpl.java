package org.qydata.service.impl;

import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.qydata.dst.CustomerHistoryBill;
import org.qydata.dst.CustomerHistoryBillDetail;
import org.qydata.dst.customer.CustomerCurrDayConsume;
import org.qydata.entity.Company;
import org.qydata.entity.CompanyApi;
import org.qydata.entity.CustomerHistoryBillUpdateLog;
import org.qydata.entity.Partner;
import org.qydata.mapper.CustomerHistoryBillMapper;
import org.qydata.service.CustomerFinanceService;
import org.qydata.service.CustomerHistoryBillService;
import org.qydata.tools.CalendarTools;
import org.qydata.tools.DateUtils;
import org.qydata.tools.JsonUtils;
import org.qydata.tools.RegexUtil;
import org.qydata.tools.chartdate.ChartCalendarUtil;

import org.qydata.tools.date.CalendarUtil;
import org.qydata.tools.https.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jonhn on 2017/7/31.
 */
@Service
public class CustomerHistoryBillServiceImpl implements CustomerHistoryBillService {

    @Autowired
    private CustomerHistoryBillMapper billMapper;
    @Autowired
    private CustomerFinanceService financeService;

    @Override
    public Map<String, Object> queryCustomerHistoryBill(Map<String, Object> map) {
        Integer [] status = null;
        Integer [] cid = null;
        Integer pid = null;
        String [] beg_month = null;

        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> me = it.next();
            if (me.getKey().equals("status")) {
                status = (Integer[]) me.getValue();
            }
            if (me.getKey().equals("cid")) {
                cid = (Integer[]) me.getValue();
            }
            if (me.getKey().equals("pid")) {
                pid = (Integer) me.getValue();
            }
            if (me.getKey().equals("beg_month")) {
                beg_month = (String[]) me.getValue();
            }
        }
        Map<String,Object> param = new HashMap<>();
        if (status != null && status.length > 0){
            List<Integer> statusList = new ArrayList<>();
            for (int i = 0; i < status.length ; i++) {
                statusList.add(status[i]);
            }
            param.put("statusList",statusList);
        }
        if (cid != null && cid.length > 0){
            List<Integer> cidList = new ArrayList<>();
            for (int i = 0; i < cid.length ; i++) {
                cidList.add(billMapper.queryCustomerIdByCompanyId(cid[i]));
            }
            param.put("cidList",cidList);
        }
        if (pid != null){
            if (pid == -100 || "-100".equals(pid)){
                param.put("nullPid",pid);
            }else {
                param.put("pid", pid);
            }
        }
        if (beg_month != null && beg_month.length > 0){
            List<String> begList = new ArrayList<>();
            for (int i = 0; i < beg_month.length ; i++) {
                begList.add(beg_month[i]);
            }
            param.put("begList",begList);
        }
        param.put("currDayTime", CalendarUtil.formatCurrTime());
        param.put("currMonthTime", CalendarTools.getCurrentMonthFirstDay() + " " + "00:00:00");
        List<CustomerHistoryBill> billList = billMapper.queryCustomerHistoryBill(param);
        List<CustomerHistoryBill> billChargeList = billMapper.queryCustomerChargeTotle(param);
        List<CustomerHistoryBill> billChargeCurrDayList = billMapper.queryCustomerChargeCurrDay(param);
        List<CustomerHistoryBill> billStaticConsume = billMapper.queryCustomerStaticConsumeAmount();
        List<CustomerHistoryBill> billCurrMonthConsumeList = billMapper.queryCustomerCurrMonthRealTimeConsume(param);
//        List<CustomerHistoryBill> billCurrDayConsumeList = billMapper.queryCustomerCurrDayRealTimeConsume(param);
        Double chargeTot = 0.0;
        Double consumeTot = 0.0;
        if (billList != null){
            for (int i = 0; i < billList.size() ; i++) {
                CustomerHistoryBill bill = billList.get(i);
                if (bill != null){
                    if (bill.getConsumeAmount() != null ){
                        bill.setConsumeAmount(bill.getConsumeAmount()/100.0);
                    }
                    if (bill.getFloor() != null){
                        if (bill.getFloor() == 0){
                            bill.setFloor(0.0);
                        }else {
                            bill.setFloor((-bill.getFloor())/100.0);
                        }
                    }
                  /*封装充值总额（至昨天）*/
                    if (billChargeList != null){
                        for (int j = 0; j < billChargeList.size() ; j++) {
                            CustomerHistoryBill billCharge = billChargeList.get(j);
                            if (bill.getCustomerId() == billCharge.getCustomerId() || bill.getCustomerId().equals(billCharge.getCustomerId())){
                                bill.setChargeAmount(billCharge.getChargeAmount()/100.0);
                            }
                        }
                    }

//                    /*封装充值总额（今天）*/
                    if (billChargeCurrDayList != null){
                        for (int j = 0; j < billChargeCurrDayList.size() ; j++) {
                            CustomerHistoryBill billChargeCurrDay = billChargeCurrDayList.get(j);
                            if (bill.getCustomerId() == billChargeCurrDay.getCustomerId() || bill.getCustomerId().equals(billChargeCurrDay.getCustomerId())){
                                bill.setChargeCurrDayAmount(billChargeCurrDay.getChargeCurrDayAmount()/100.0);
                            }
                        }
                    }

                 /*封装充值总额（昨天 + 今天）*/
                    if (bill.getChargeAmount() != null){
                        if (bill.getChargeCurrDayAmount() != null) {
                            bill.setChargeAmount(bill.getChargeAmount() + bill.getChargeCurrDayAmount());
                        }
                    }else {
                        if (bill.getChargeCurrDayAmount() != null) {
                            bill.setChargeAmount(bill.getChargeCurrDayAmount());
                        }
                    }

                    if (billStaticConsume != null){
                        for (CustomerHistoryBill staticBill : billStaticConsume) {
                            if (bill.getCustomerId() == staticBill.getCustomerId() || bill.getCustomerId().equals(staticBill.getCustomerId())){
                                if (staticBill.getStaticConsumeAmount() != null){
                                    bill.setStaticConsumeAmount(staticBill.getStaticConsumeAmount()/100.0);
                                }
                            }
                        }
                    }

                    if (bill.getChargeAmount() != null && bill.getStaticConsumeAmount() != null){
                        bill.setBalance(bill.getChargeAmount() - bill.getStaticConsumeAmount());
                    }
                    if (bill.getChargeAmount() != null && bill.getStaticConsumeAmount() == null){
                        bill.setBalance(bill.getChargeAmount());
                    }
                    if (bill.getChargeAmount() == null && bill.getStaticConsumeAmount() != null){
                        bill.setBalance(-(bill.getStaticConsumeAmount()));
                    }

                    if (billCurrMonthConsumeList != null){
                        for (CustomerHistoryBill currMonthBill : billCurrMonthConsumeList) {
                            if (bill.getCustomerId() == currMonthBill.getCustomerId() || bill.getCustomerId().equals(currMonthBill.getCustomerId())){
                                if (bill.getBalance() != null && currMonthBill.getCurrMonthRealTimeConsume() != null){
                                    bill.setBalance(bill.getBalance() - (currMonthBill.getCurrMonthRealTimeConsume()/100.0));
                                }
                                if (bill.getBalance() != null && currMonthBill.getCurrMonthRealTimeConsume() == null){
                                    bill.setBalance(bill.getBalance());
                                }
                                if (bill.getBalance() == null && currMonthBill.getCurrMonthRealTimeConsume() != null){
                                    bill.setBalance(-(currMonthBill.getCurrMonthRealTimeConsume()/100.0));
                                }
                            }
                        }
                    }
                    Double currDayConsume = queryCustomerCurrDayConsume(bill.getCustomerId());
                    if (currDayConsume != null){
                        if (bill.getBalance() != null && currDayConsume != null){
                            bill.setBalance(bill.getBalance() - (currDayConsume/100.0));
                        }
                        if (bill.getBalance() != null && currDayConsume == null){
                            bill.setBalance(bill.getBalance());
                        }
                        if (bill.getBalance() == null && currDayConsume != null){
                            bill.setBalance(-(currDayConsume/100.0));
                        }
                    }

                    if (bill.getFloor() != null && bill.getBalance() != null){
                        bill.setUserFloor(bill.getFloor() + bill.getBalance());
                    }
                    if (bill.getFloor() != null && bill.getBalance() == null){
                        bill.setUserFloor(bill.getFloor());
                    }

                    if (bill.getChargeAmount() != null){
                        chargeTot += bill.getChargeAmount();
                    }
                    if (bill.getConsumeAmount() != null){
                        consumeTot += bill.getConsumeAmount();
                    }
                }
            }
        }
        Map<String,Object> resu = new HashMap<>();
        resu.put("billList",billList);
        resu.put("chargeTot",chargeTot);
        resu.put("consumeTot",consumeTot);
        return resu;
    }

    @Override
    public List<Company> queryAllCompany() {
        List<Company> companyList = billMapper.queryAllCompany();
        if (companyList != null){
            for (int i = 0; i < companyList.size() ; i++) {
                Company company = companyList.get(i);
                if (company != null){
                    if (company.getName() != null){
                        if (company.getPartnerName() != null){
                            company.setName(company.getName() +"@"+ company.getPartnerName());
                        }
                    }
                }
            }
        }
        return companyList;
    }

    @Override
    public List<Partner> queryAllPartner() {
        return billMapper.queryAllPartner();
    }

    @Override
    public List<String> queryAllConsumeTime() {
        return billMapper.queryAllConsumeTime();
    }

    @Override
    public Map<String, Object> queryCustomerHistoryBillDetail(Map<String, Object> map) {
        Integer cid = null;
        String [] cyc = null;
        String [] tid = null;
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> me = it.next();
            if (me.getKey().equals("cid")) {
                cid = (Integer) me.getValue();
            }
            if (me.getKey().equals("cyc")) {
                cyc = (String[]) me.getValue();
            }
            if (me.getKey().equals("tid")) {
                tid = (String[]) me.getValue();
            }
        }
        Map<String,Object> param = new HashMap<>();
        if (cid != null){
            param.put("cid",cid);
        }
        if (cyc != null && cyc.length > 0){
            List<String> conList = new ArrayList<>();
            for (int i = 0; i < cyc.length; i++) {
                conList.add(cyc[i]);
            }
            param.put("conList",conList);
        }
        if (tid != null && tid.length > 0){
            List<String> tidList = new ArrayList<>();
            List<String> stidList = new ArrayList<>();
            for (int i = 0; i < tid.length ; i++) {
                if (RegexUtil.isPoint(tid[i])){
                    tidList.add(tid[i]);
                }else {
                    String [] tids = tid[i].split("-");
                    tidList.add(tids[0]);
                    stidList.add(tids[1]);
                }
            }
            param.put("tidList",tidList);
            param.put("stidList",stidList);
        }
        Double conTot = 0.0;
        List<CustomerHistoryBillDetail> billDetailList = billMapper.queryCustomerHistoryBillDetail(param);
        if (billDetailList != null){
            for (int i = 0; i < billDetailList.size() ; i++) {
                CustomerHistoryBillDetail billDetail = billDetailList.get(i);
                if (billDetail != null){
                    if (billDetail.getApiTypeName() != null){
                        if (billDetail.getStidName() != null){
                            billDetail.setApiTypeName(billDetail.getApiTypeName() + "--" + billDetail.getStidName());
                        }
                    }
                    if (billDetail.getCost() != null){
                        if (billDetail.getCost() != 0){
                            billDetail.setCost(billDetail.getCost()/100.0);
                        }
                    }
                    if (billDetail.getCost() != null && billDetail.getAmount() != null){
                        billDetail.setConsumeAmount(billDetail.getCost() * billDetail.getAmount());
                    }
                    if (billDetail.getConsumeAmount() != null){
                        conTot += billDetail.getConsumeAmount();
                    }
                    if (billDetail.getIsLock() != null && billDetail.getIsLock() == 1 || "1".equals(billDetail.getIsLock())){
                        billDetail.setIsLockName("已锁定");
                    }else {
                        billDetail.setIsLockName("未锁定");
                    }
                }
            }
        }
        Map<String,Object> resu = new HashMap<>();
        resu.put("billDetailList",billDetailList);
        resu.put("conTot",conTot);
        return resu;
    }

    @Override
    public boolean updateCustomerHistoryBillCost(Integer id, Double oldCost, Double newCost, String content) {
        boolean flag_1 = billMapper.updateCustomerHistoryBillCost(id, (int) (newCost*100));
        CustomerHistoryBillUpdateLog log = new CustomerHistoryBillUpdateLog();
        log.setCustomerHistoryBillId(id);
        log.setBeforData((oldCost*100));
        log.setAfterData((newCost*100));
        log.setContent(content);
        log.setTypeId(1);
        boolean flag_2 = billMapper.insertCustomerHistoryBillUpdateLog(log);
        if (flag_1 && flag_2){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCustomerHistoryBillAmount(Integer id, Integer oldAmount, Integer newAmount, String content) {
        boolean flag_1 = billMapper.updateCustomerHistoryBillAmount(id, newAmount);
        CustomerHistoryBillUpdateLog log = new CustomerHistoryBillUpdateLog();
        log.setCustomerHistoryBillId(id);
        log.setBeforData((double)oldAmount);
        log.setAfterData((double)newAmount);
        log.setContent(content);
        log.setTypeId(2);
        boolean flag_2 = billMapper.insertCustomerHistoryBillUpdateLog(log);
        if (flag_1 && flag_2){
            return true;
        }
        return false;
    }

    @Override
    public boolean addCustomerHistoryBill(Integer cid, String tid, Double cost, Integer amount, String yearMonth) {
        CustomerHistoryBillDetail bill = new CustomerHistoryBillDetail();
        bill.setCustomerId(cid);
        if (RegexUtil.isPoint(tid)){
            bill.setApiTypeId(Integer.parseInt(tid));
        }else {
            String [] tids = tid.split("-");
            bill.setApiTypeId(Integer.parseInt(tids[0]));
            bill.setStid(Integer.parseInt(tids[1]));
        }
        bill.setCost(cost * 100);
        bill.setAmount(amount);
        String [] yearMonths = yearMonth.split("-");
        bill.setYear(Integer.parseInt(yearMonths[0]));
        bill.setMonth(Integer.parseInt(yearMonths[1]));
        bill.setYearMonth(yearMonth);
        return billMapper.addCustomerHistoryBill(bill);
    }

    @Override
    public boolean deleteCustomerHistoryBill(String[] id) {
        List<String> list = new ArrayList<>();
        if (id != null && id.length > 0){
            for (int i = 0; i < id.length ; i++) {
                list.add(id[i]);
            }
        }
        return billMapper.deleteCustomerHistoryBill(list);
    }

    @Override
    public List<CompanyApi> queryCompanyApiByCompanyId(Integer cid) {
        Map<String,Object> param = new HashMap<>();
        param.put("cid",cid);
        List<CompanyApi> apiList = billMapper.queryCompanyApiByCompanyId(billMapper.queryCompanyIdByCustomerId(param));
        if (apiList != null && apiList.size() > 0){
            for (int i = 0; i < apiList.size() ; i++) {
                CompanyApi api = apiList.get(i);
                if (api != null) {
                    if (api.getApiTypeId() != null){
                        if (api.getSubTypeId() != null){
                            api.setType_stid(api.getApiTypeId() + "-" + api.getSubTypeId());
                        }
                    }
                }
            }
        }
        return apiList;
    }

    @Override
    public Map<String, Object> queryCustomerHistoryBillTrendData(Map<String, Object> map) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        List<String> yearMonthList = new ArrayList<>();
        for (int i = 0; i < 6 ; i++) {
            String yearMonth = ChartCalendarUtil.getCurrentDateAssignYearMonth(-(5 - i));
            yearMonthList.add(yearMonth);
        }
        map.put("yearMonthList",yearMonthList);
        map.put("yearMonth",ChartCalendarUtil.getCurrentDateAssignYearMonth(0));
        List<CustomerHistoryBillDetail> consumeList = billMapper.queryCustomerHistoryBillTrendDataConsume(map);
        List<String> xList = new ArrayList<>();
        List<Integer> proList = new ArrayList<>();
        Map<Date,Object> proMap = new TreeMap<>();
        for (int i = 0; i < 6 ; i++) {
            try {
                String yearMonth = ChartCalendarUtil.getCurrentDateAssignYearMonth(-(5-i));
                proMap.put(sdf.parse(yearMonth),0);
                if (consumeList != null && consumeList.size() > 0){
                    for (int j = 0; j < consumeList.size(); j++) {
                        CustomerHistoryBillDetail consume = consumeList.get(j);
                        if (consume != null){
                            if (yearMonth.equals(consume.getYearMonth())){
                                proMap.put(sdf.parse(yearMonth),consume.getAmount());
                                break;
                            }
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        Set<Map.Entry<Date, Object>> set = proMap.entrySet();
        Iterator<Map.Entry<Date, Object>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<Date, Object> me = it.next();
            xList.add(sdf.format(me.getKey()));
            proList.add((Integer) me.getValue());
        }
        Map mapSeries = new HashedMap();
        mapSeries.put("name","月消费");
        mapSeries.put("data",proList);
        JSONArray jsonArrayX = JSONArray.fromObject(xList);
        JSONArray jsonArrayS = JSONArray.fromObject(mapSeries);
        Map<String,Object> resu = new HashMap();
        resu.put("jsonArrayX",jsonArrayX);
        resu.put("jsonArrayS",jsonArrayS);

        //上月各产品扣费量
        List<CustomerHistoryBillDetail> amountList = billMapper.queryCustomerHistoryBillTrendDataAmount(map);
        List<CompanyApi> apiList = billMapper.queryCompanyApiByCompanyId(billMapper.queryCompanyIdByCustomerId(map));
        Map<String,Object> proMap_2 = new TreeMap<>();
        if (apiList != null && apiList.size() > 0){
            for (int i = 0; i < apiList.size() ; i++) {
                CompanyApi api = apiList.get(i);
                if (api != null){
                    proMap_2.put(api.getType_stid_name(),0);
                }
            }
        }
        if (amountList != null && amountList.size() > 0){
            for (int i = 0; i < amountList.size() ; i++) {
                CustomerHistoryBillDetail amount = amountList.get(i);
                if (amount != null){
                    proMap_2.put(amount.getApiTypeName(),amount.getAmount());
                }
            }
        }
        List<String> xList_2 = new ArrayList<>();
        List<Integer> proList_2 = new ArrayList<>();
        Set<Map.Entry<String, Object>> set_2 = proMap_2.entrySet();
        Iterator<Map.Entry<String, Object>> it_2 = set_2.iterator();
        while (it_2.hasNext()) {
            Map.Entry<String, Object> me = it_2.next();
            xList_2.add(me.getKey());
            proList_2.add((Integer) me.getValue());
        }
        Map mapSeries_2 = new HashedMap();
        mapSeries_2.put("name","扣费量");
        mapSeries_2.put("data",proList_2);
        JSONArray jsonArrayX_2 = JSONArray.fromObject(xList_2);
        JSONArray jsonArrayS_2 = JSONArray.fromObject(mapSeries_2);
        resu.put("jsonArrayX_2",jsonArrayX_2);
        resu.put("jsonArrayS_2",jsonArrayS_2);
        return resu;
    }

    @Override
    public boolean updateCustomerHistoryBillIsLock(String[] id, Integer isLock) {
        Map<String,Object> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (String s : id) {
            list.add(Integer.parseInt(s));
        }
        map.put("isLock",isLock);
        map.put("list",list);
        return billMapper.updateCustomerHistoryBillIsLock(map);
    }

    @Override
    public Integer queryCustomerHistoryBillLockById(Integer id) {
        return billMapper.queryCustomerHistoryBillLockById(id);
    }

    @Override
    public List<CustomerHistoryBillUpdateLog> queryCustomerHistoryBillDetailUpdateLog(Map<String, Object> map) {
        List<CustomerHistoryBillUpdateLog> logList = billMapper.queryCustomerHistoryBillDetailUpdateLog(map);
        if (logList == null){
            return null;
        }
        for (CustomerHistoryBillUpdateLog log : logList) {
            if (log.getContent() == null || log.getContent() == "" || "".equals(log.getContent())){
                log.setContent("无");
            }
            if(log.getBeforData() != null && log.getTypeId() == 1){
                log.setBeforData(log.getBeforData()/100.0);
            }
            if(log.getAfterData() != null && log.getTypeId() == 1){
                log.setAfterData(log.getAfterData()/100.0);
            }
        }
        return logList;
    }

    @Override
    public Double queryCustomerCurrDayConsume(Integer cid) {
        //调用接口获得客户请求的服务和对应的次数
        String result = HttpClientUtil.httpRequest(String.valueOf(cid), String.valueOf(DateUtils.formatCurrentDate()));
        //去掉接口返回值为null,""," ","{}","null","NULL"
        if (StringUtils.isBlank(result) || result.replace(" ","").length() == 2 || result.equalsIgnoreCase("null")){
            return null;
        }
        try {
            //非json格式字符串会报错
            JSONObject.parseObject(result);
        } catch (Exception e) {
            return null;
        }
        //将json转换成map
        Map<String, String> jsonMap = JsonUtils.jsonToMap(result);
        Double totalAmount = 0.0;
        if (jsonMap != null){
            for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
                String[] split = entry.getKey().split("-");
                CustomerCurrDayConsume consume = financeService.getPriceByType(cid, Integer.valueOf(split[0]), Integer.valueOf(split[1]));
                if(consume == null){
                    return null;
                }
                totalAmount += Integer.valueOf(entry.getValue()) * consume.getPrice();

            }
        }
        return totalAmount;
    }


}
