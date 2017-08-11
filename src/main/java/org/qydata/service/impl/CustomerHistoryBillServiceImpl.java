package org.qydata.service.impl;

import net.sf.json.JSONArray;
import org.apache.commons.collections.map.HashedMap;
import org.qydata.dst.CustomerHistoryBill;
import org.qydata.dst.CustomerHistoryBillDetail;
import org.qydata.entity.Company;
import org.qydata.entity.CompanyApi;
import org.qydata.entity.Partner;
import org.qydata.mapper.CustomerHistoryBillMapper;
import org.qydata.service.CustomerHistoryBillService;
import org.qydata.tools.RegexUtil;
import org.qydata.tools.chartdate.ChartCalendarUtil;
import org.qydata.tools.date.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jonhn on 2017/7/31.
 */
@Service
public class CustomerHistoryBillServiceImpl implements CustomerHistoryBillService {

    @Autowired
    private CustomerHistoryBillMapper billMapper;

    @Override
    public Map<String, Object> queryCustomerHistoryBill(Map<String, Object> map) {
        Integer [] status = null;
        Integer [] cid = null;
        Integer pid = null;
        Double LCredit = null;
        Double HCredit = null;
        Double LUserAble = null;
        Double HUserAble = null;
        Double LConsu = null;
        Double HConsu = null;
        String [] beg_month = null;
        String end_month = null;
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
            if (me.getKey().equals("LCredit")) {
                LCredit = (Double) me.getValue();
            }
            if (me.getKey().equals("HCredit")) {
                HCredit = (Double) me.getValue();
            }
            if (me.getKey().equals("LUserAble")) {
                LUserAble = (Double) me.getValue();
            }
            if (me.getKey().equals("HUserAble")) {
                HUserAble = (Double) me.getValue();
            }
            if (me.getKey().equals("LConsu")) {
                LConsu = (Double) me.getValue();
            }
            if (me.getKey().equals("HConsu")) {
                HConsu = (Double) me.getValue();
            }
            if (me.getKey().equals("beg_month")) {
                beg_month = (String[]) me.getValue();
            }
            if (me.getKey().equals("end_month")) {
                end_month = (String) me.getValue();
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
            param.put("pid",pid);
        }
        if (beg_month != null && beg_month.length > 0){
            List<String> begList = new ArrayList<>();
            for (int i = 0; i < beg_month.length ; i++) {
                begList.add(beg_month[i]);
            }
            param.put("begList",begList);
        }
        param.put("currDayTime", CalendarUtil.formatCurrTime());
        List<CustomerHistoryBill> billList = billMapper.queryCustomerHistoryBill(param);
        List<CustomerHistoryBill> billChargeList = billMapper.queryCustomerChargeTotle(param);
        List<CustomerHistoryBill> billChargeCurrDayList = billMapper.queryCustomerChargeCurrDay(param);
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
                    if (bill.getBalance() != null){
                        bill.setBalance(bill.getBalance()/100.0);
                    }
                    if (bill.getFloor() != null && bill.getBalance() != null){
                        bill.setUserFloor(bill.getFloor() + bill.getBalance());
                    }
                  /*封装充值总额（至昨天）*/
                    if (billChargeList != null){
                        for (int j = 0; j < billChargeList.size() ; j++) {
                            CustomerHistoryBill billCharge = billChargeList.get(j);
                            if (bill.getCustomerId() == billCharge.getCustomerId()){
                                bill.setChargeAmount(billCharge.getChargeAmount()/100.0);
                            }
                        }
                    }

                    /*封装充值总额（今天）*/
                    if (billChargeCurrDayList != null){
                        for (int j = 0; j < billChargeCurrDayList.size() ; j++) {
                            CustomerHistoryBill billChargeCurrDay = billChargeCurrDayList.get(j);
                            if (bill.getCustomerId() == billChargeCurrDay.getCustomerId()){
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
                }
            }
        }
        Map<String,Object> resu = new HashMap<>();
        resu.put("billDetailList",billDetailList);
        resu.put("conTot",conTot);
        return resu;
    }

    @Override
    public boolean updateCustomerHistoryBillCost(Integer id, Double cost) {
        int result = billMapper.updateCustomerHistoryBillCost(id,(int)(cost * 100));
        if (result == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCustomerHistoryBillAmount(Integer id, Integer amount) {
        int result = billMapper.updateCustomerHistoryBillAmount(id,amount);
        if (result == 1){
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
        Map<String,Object> proMap = new TreeMap<>();
        for (int i = 0; i < 6 ; i++) {
            String yearMonth = ChartCalendarUtil.getCurrentDateAssignYearMonth(-(5-i));
            proMap.put(yearMonth,0);
            if (consumeList != null && consumeList.size() > 0){
                for (int j = 0; j < consumeList.size(); j++) {
                    CustomerHistoryBillDetail consume = consumeList.get(j);
                    if (consume != null){
                        if (yearMonth.equals(consume.getYearMonth())){
                            proMap.put(yearMonth,consume.getAmount());
                            break;
                        }
                    }
                }
            }
        }
        Set<Map.Entry<String, Object>> set = proMap.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> me = it.next();
            xList.add(me.getKey());
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


}