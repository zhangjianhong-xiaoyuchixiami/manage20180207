package org.qydata.service.impl;

import net.sf.json.JSONArray;
import org.apache.commons.collections.map.HashedMap;
import org.qydata.config.annotation.DataSourceService;
import org.qydata.dst.*;
import org.qydata.dst.customer.CustomerConsume;
import org.qydata.dst.customer.CustomerCurrDayConsume;
import org.qydata.dst.customer.CustomerCurrDayConsumeDetail;
import org.qydata.entity.*;
import org.qydata.mapper.CustomerFinanceMapper;
import org.qydata.service.CustomerFinanceService;
import org.qydata.tools.CalendarTools;
import org.qydata.tools.chartdate.ChartCalendarUtil;
import org.qydata.tools.date.CalendarUtil;
import org.qydata.tools.date.CalendarUtil_2;
import org.qydata.tools.date.CalendarUtil_3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jonhn on 2017/1/8.
 */
@Service
public class CustomerFinanceServiceImpl implements CustomerFinanceService {

    @Autowired
    private CustomerFinanceMapper customerFinanceMapper;

    @Override
    @DataSourceService
    public Map<String,Object> queryCompanyCustomerOverAllFinance(Map<String, Object> map)throws Exception{

        Map<String,Object> mapOverAllParam = new HashMap<>();
        Map<String,Object> mapApiTypeParam = new HashMap<>();

        Map<String,Object> mapTotleParam = new HashMap<>();
        Map<String,Object> mapEmailParam = new HashMap<>();
        Map<String,Object> mapExcelParam = new HashMap<>();
        Set<Map.Entry<String,Object>> set = map.entrySet();
        Iterator<Map.Entry<String,Object>> iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry<String,Object> me = iterator.next();
            if("content".equals(me.getKey())){
                mapOverAllParam.put("content",me.getValue());
            }
            if("partnerId".equals(me.getKey())){
                mapOverAllParam.put("partnerId",me.getValue());
            }
            if("pid".equals(me.getKey())){
                if ((Integer)me.getValue() == -100 || "-100".equals(me.getValue())){
                    mapOverAllParam.put("nullPid",me.getValue());
                }else {
                    mapOverAllParam.put("pid", me.getValue());
                }
            }
            if("beginDate".equals(me.getKey())){
                mapApiTypeParam.put("beginDate",me.getValue());
                mapTotleParam.put("beginDate",me.getValue());
            }
            if("endDate".equals(me.getKey())){
                mapApiTypeParam.put("endDate",me.getValue());
                mapTotleParam.put("endDate",me.getValue());
            }
            if("statusList".equals(me.getKey())){
                mapOverAllParam.put("statusList",me.getValue());
            }
            if("deptIdList".equals(me.getKey())){
                mapOverAllParam.put("deptIdList",me.getValue());
            }
        }
        mapExcelParam.put("years",CalendarTools.getYearMonthCount(1));
        mapExcelParam.put("months",CalendarTools.getMonthCount(1));
        mapApiTypeParam.put("currDayTime",CalendarTools.getCurrentDate()+ " " + "00:00:00");
        mapTotleParam.put("currDayTime",CalendarTools.getCurrentDate()+ " " + "00:00:00");
        mapTotleParam.put("currMonthTime",CalendarTools.getCurrentMonthFirstDay() + " " + "00:00:00");

        //财务账单客户列表
        List<CustomerFinance> list = customerFinanceMapper.queryCompanyCustomerOverAllFinance(mapOverAllParam);
        if (list == null){
            return null;
        }
        //各类型
        List<CustomerFinance> customerFinanceApiTypeConsumeList = customerFinanceMapper.queryCustomerApiTypeConsume(mapApiTypeParam);

        //客户充值（至昨天）
        List<CustomerFinance> customerFinanceChargeList = customerFinanceMapper.queryCustomerChargeTotle(mapTotleParam);
        //客户充值（当天）
        List<CustomerFinance> customerFinanceCurrDayChargeList = customerFinanceMapper.queryCustomerChargeCurrDay(mapTotleParam);
        //客户消费（至昨天）
        List<CustomerFinance> customerFinanceConsumeList = customerFinanceMapper.queryCustomerConsumeTotle(mapTotleParam);
        //客户本月消费（至昨天）
        List<CustomerFinance> customerFinanceCurrMonthList = customerFinanceMapper.queryCustomerCurrMonthTotle(mapTotleParam);
        //客户当天消费
        List<CustomerFinance> customerFinanceCurrDayList = customerFinanceMapper.queryCustomerCurrDayTotle(mapTotleParam);
        //查询客户邮箱
        List<CustomerCompanyEmail> customerCompanyEmailList = customerFinanceMapper.queryCustomerEmail(mapEmailParam);
        //查询客户上月账单
        List<CustomerConsumeExcel> customerConsumeExcelList = customerFinanceMapper.queryCustomerAccountExcel(mapExcelParam);
        //上周充值
        List<CustomerConsume> lastWeekChargeList = customerFinanceMapper.queryCustomerLastWeekCharge();
        //上周消费
        List<CustomerConsume> lastWeekConsumeList = customerFinanceMapper.queryCustomerLastWeekConsume();
        //上月充值
        List<CustomerConsume> lastMonthChargeList = customerFinanceMapper.queryCustomerLastMonthCharge();
        //上月消费
        List<CustomerConsume> lastMonthConsumeList = customerFinanceMapper.queryCustomerLastMonthConsume(CalendarUtil_2.getCurrentLastMonth());
        //昨日消费
        List<CustomerConsume> yesterdayConsumeList = customerFinanceMapper.queryCustomerYesterdayConsume(CalendarUtil_3.getCurrentPreviousTime());
        if (map.get("endDate") == null || CalendarUtil.getCurrTimeAfterDay().equals(map.get("endDate"))) {
            if (customerFinanceApiTypeConsumeList != null){
                for (int i = 0; i < customerFinanceApiTypeConsumeList.size(); i++) {
                    CustomerFinance customerFinanceApiTypeConsume = customerFinanceApiTypeConsumeList.get(i);
                    List<CompanyApi> companyApiList = customerFinanceApiTypeConsume.getCompanyApiList();
                    if (companyApiList != null){
                        for (int j = 0; j < companyApiList.size(); j++) {
                            CompanyApi companyApi = companyApiList.get(j);
                            CompanyApiCount companyApiCount = companyApi.getCompanyApiCount();
                            companyApiCount.setSumAmount(companyApiCount.getSumAmount() + companyApiCount.getCurrDaySumAmount());
                            companyApiCount.setCountTotle(companyApiCount.getCountTotle() + companyApiCount.getCurrDayCountTotle());
                            companyApiCount.setCountSuccess(companyApiCount.getCountSuccess() + companyApiCount.getCurrDayCountSuccess());
                        }
                    }
                }
            }
        }

        double lastWeekChargeTot = 0.0;
        double lastWeekConsumeTot = 0.0;
        double lastMonthChargeTot = 0.0;
        double lastMonthConsumeTot = 0.0;
        double totleChargeAmount = 0.0;
        double totleConsumeAmount = 0.0;
        double currMonthTotleConsumeAmount = 0.0;
        double currDayTotleConsumeAmount = 0.0;
        List<CustomerFinance> customerFinanceList = new ArrayList<>();
        Iterator<CustomerFinance> it = list.iterator();
        while (it.hasNext()) {
            CustomerFinance customerFinance = it.next();
            customerFinance.setBalance(customerFinance.getBalance()/100.0);
            customerFinance.setFloor(-customerFinance.getFloor()/100.0);
            customerFinance.setSurplusFloor(((-customerFinance.getFloor()) + customerFinance.getBalance())/100.0);

            /*封装各类型消费*/
            if (customerFinanceApiTypeConsumeList != null) {
                for (int i = 0; i < customerFinanceApiTypeConsumeList.size(); i++) {
                    CustomerFinance customerFinanceApiTypeConsume = customerFinanceApiTypeConsumeList.get(i);
                    List<CompanyApi> companyApiList = customerFinanceApiTypeConsume.getCompanyApiList();
                    if (customerFinance.getId() == customerFinanceApiTypeConsume.getId() || customerFinance.getId().equals(customerFinanceApiTypeConsume.getId())) {
                        customerFinance.setCompanyApiList(companyApiList);
                    }
                }
            }
            //上周充值
            if (lastWeekChargeList != null){
                for (CustomerConsume consume : lastWeekChargeList) {
                    if (customerFinance.getId() == consume.getCustomerId()
                            || customerFinance.getId().equals(consume.getCustomerId())){
                        customerFinance.setLastWeekCharge(consume.getLastWeekCharge()/100.0);
                    }
                }
            }
            //上周消费
            if (lastWeekConsumeList != null){
                for (CustomerConsume consume : lastWeekConsumeList) {
                    if (customerFinance.getId() == consume.getCustomerId()
                            || customerFinance.getId().equals(consume.getCustomerId())){
                        customerFinance.setLastWeekConsume(-consume.getLastWeekConsume()/100.0);
                    }
                }
            }
            //上月充值
            if (lastMonthChargeList != null){
                for (CustomerConsume consume : lastMonthChargeList) {
                    if (customerFinance.getId() == consume.getCustomerId()
                            || customerFinance.getId().equals(consume.getCustomerId())){
                        customerFinance.setLastMonthCharge(consume.getLastMonthCharge()/100.0);
                    }
                }
            }
            //上月消费
            if (lastMonthConsumeList != null){
                for (CustomerConsume consume : lastMonthConsumeList) {
                    if (customerFinance.getId() == consume.getCustomerId()
                            || customerFinance.getId().equals(consume.getCustomerId())){
                        customerFinance.setLastMonthConsume((consume.getCost() * consume.getAmount())/100.0);
                    }
                }
            }

            //昨天消费
            if (yesterdayConsumeList != null){
                for (CustomerConsume consume : yesterdayConsumeList) {
                    if (customerFinance.getId() == consume.getCustomerId()
                            || customerFinance.getId().equals(consume.getCustomerId())){
                        customerFinance.setYesterdayConsume(-consume.getYesterdayConsume()/100.0);
                    }
                }
            }

                /*封装当天消费总额*/
            if (customerFinanceCurrDayList != null){
                for (int i = 0; i < customerFinanceCurrDayList.size(); i++) {
                    CustomerFinance customerFinanceCurrDay = customerFinanceCurrDayList.get(i);
                    if (customerFinance.getId() == customerFinanceCurrDay.getId() || customerFinance.getId().equals(customerFinanceCurrDay.getId())){
                        customerFinance.setCurrDayAmount(-customerFinanceCurrDay.getCurrDayAmount()/100.0);
                    }
                }
            }

                /*封装当月消费总额（至昨天）*/
            if (customerFinanceCurrMonthList != null){
                for (int i = 0; i < customerFinanceCurrMonthList.size(); i++) {
                    CustomerFinance customerFinanceCurrMonth = customerFinanceCurrMonthList.get(i);
                    if (customerFinance.getId() == customerFinanceCurrMonth.getId() ||customerFinance.getId().equals(customerFinanceCurrMonth.getId())){
                        customerFinance.setCurrMonthAmount(-customerFinanceCurrMonth.getCurrMonthAmount()/100.0);
                    }
                }
            }

                 /*封装当月消费总额（昨天 + 今天）*/
            if (customerFinance.getCurrMonthAmount() != null){
                if (customerFinance.getCurrDayAmount() != null) {
                    customerFinance.setCurrMonthAmount(customerFinance.getCurrMonthAmount() + customerFinance.getCurrDayAmount());
                }
            }else {
                if (customerFinance.getCurrDayAmount() != null) {
                    customerFinance.setCurrMonthAmount(customerFinance.getCurrDayAmount());
                }
            }

                /*封装充值总额（至昨天）*/
            if (customerFinanceChargeList != null){
                for (int i = 0; i < customerFinanceChargeList.size() ; i++) {
                    CustomerFinance customerFinanceCharge = customerFinanceChargeList.get(i);
                    if (customerFinance.getId() == customerFinanceCharge.getId() ||customerFinance.getId().equals(customerFinanceCharge.getId())){
                        customerFinance.setChargeTotleAmount(customerFinanceCharge.getChargeTotleAmount()/100.0);
                    }
                }
            }

                 /*封装充值总额（今天）*/
            if (customerFinanceCurrDayChargeList != null){
                for (int i = 0; i < customerFinanceCurrDayChargeList.size(); i++) {
                    CustomerFinance currDayFinance = customerFinanceCurrDayChargeList.get(i);
                    if (customerFinance.getId() == currDayFinance.getId() || customerFinance.getId().equals(currDayFinance.getId())){
                        customerFinance.setCurrDayChargeAmount(currDayFinance.getCurrDayChargeAmount()/100.0);
                    }
                }
            }

                 /*封装充值总额（昨天 + 今天）*/
            if (customerFinance.getChargeTotleAmount() != null){
                if (customerFinance.getCurrDayChargeAmount() != null) {
                    customerFinance.setChargeTotleAmount(customerFinance.getChargeTotleAmount() + customerFinance.getCurrDayChargeAmount());
                }
            }else {
                if (customerFinance.getCurrDayChargeAmount() != null) {
                    customerFinance.setChargeTotleAmount(customerFinance.getCurrDayChargeAmount());
                }
            }

                /*封装消费总额（至昨天）*/
            if (customerFinanceConsumeList != null){
                for (int i = 0; i < customerFinanceConsumeList.size(); i++) {
                    CustomerFinance customerFinanceConsume = customerFinanceConsumeList.get(i);
                    if (customerFinance.getId() == customerFinanceConsume.getId() || customerFinance.getId().equals(customerFinanceConsume.getId())){
                        customerFinance.setConsumeTotleAmount(-customerFinanceConsume.getConsumeTotleAmount()/100.0);
                    }
                }
            }

                 /*封装消费总额（至昨天 + 今天）*/
            if (map.get("endDate") == null || CalendarUtil.getCurrTimeAfterDay().equals(map.get("endDate")))
            {
                if (customerFinance.getConsumeTotleAmount() != null){
                    if (customerFinance.getCurrDayAmount() != null){
                        customerFinance.setConsumeTotleAmount(customerFinance.getConsumeTotleAmount() + customerFinance.getCurrDayAmount()/100.0);
                    }
                }else {
                    if (customerFinance.getCurrDayAmount() != null){
                        customerFinance.setConsumeTotleAmount(-customerFinance.getCurrDayAmount()/100.0);
                    }
                }
            }

                /*封装邮箱*/
            if (customerCompanyEmailList != null){
                for (int i = 0; i < customerCompanyEmailList.size() ; i++) {
                    CustomerCompanyEmail customerCompanyEmail = customerCompanyEmailList.get(i);
                    if (customerCompanyEmail.getEmail() != null && customerCompanyEmail.getEmail().length() > 0){
                        if (customerFinance.getCompanyId() == customerCompanyEmail.getCompanyId() || customerFinance.getCompanyId().equals(customerCompanyEmail.getCompanyId())){
                            customerFinance.setEmail(customerCompanyEmail.getEmail());
                        }
                    }
                }
            }

                /*封装账单*/
            if(customerConsumeExcelList != null){
                for (int i = 0; i < customerConsumeExcelList.size(); i++) {
                    CustomerConsumeExcel customerConsumeExcel = customerConsumeExcelList.get(i);
                    if (customerFinance.getId() == customerConsumeExcel.getCustomerId() || customerFinance.getId().equals(customerConsumeExcel.getCustomerId())){
                        customerFinance.setConsuTime(customerConsumeExcel.getConsuTime());
                    }
                }
            }



            if (customerFinance.getLastWeekCharge() != null) {
                lastWeekChargeTot += customerFinance.getLastWeekCharge();
            }
            if (customerFinance.getLastWeekConsume() != null) {
                lastWeekConsumeTot += customerFinance.getLastWeekConsume();
            }
            if (customerFinance.getLastMonthCharge() != null) {
                lastMonthChargeTot += customerFinance.getLastMonthCharge();
            }
            if (customerFinance.getLastMonthConsume() != null) {
                lastMonthConsumeTot += customerFinance.getLastMonthConsume();
            }
            if (customerFinance.getChargeTotleAmount() != null) {
                totleChargeAmount += customerFinance.getChargeTotleAmount()/100.0;
            }
            if (customerFinance.getConsumeTotleAmount() != null) {
                totleConsumeAmount += -customerFinance.getConsumeTotleAmount()/100.0;
            }
            if (customerFinance.getCurrMonthAmount() != null) {
                currMonthTotleConsumeAmount += -customerFinance.getCurrMonthAmount()/100.0;
            }
            if (customerFinance.getCurrDayAmount() != null) {
                currDayTotleConsumeAmount += -customerFinance.getCurrDayAmount()/100.0;
            }
            if (customerFinance.getBalance() >= 0) {
                customerFinance.setUsableFloor(customerFinance.getFloor());
            } else {
                customerFinance.setUsableFloor(customerFinance.getFloor() - customerFinance.getBalance());
            }
            customerFinanceList.add(customerFinance);
        }


        Map<String,Object> mapTran = new HashMap<>();
        mapTran.put("lastWeekChargeTot",lastWeekChargeTot);
        mapTran.put("lastWeekConsumeTot",lastWeekConsumeTot);
        mapTran.put("lastMonthChargeTot",lastMonthChargeTot);
        mapTran.put("lastMonthConsumeTot",lastMonthConsumeTot);
        mapTran.put("totleChargeAmount",totleChargeAmount);
        mapTran.put("totleConsumeAmount",totleConsumeAmount);
        mapTran.put("currMonthTotleConsumeAmount",currMonthTotleConsumeAmount);
        mapTran.put("currDayTotleConsumeAmount",currDayTotleConsumeAmount);
        mapTran.put("customerFinanceList",customerFinanceList);
        return mapTran;
    }

    @Override
    public List<CustomerCurrDayConsume> queryCustomerCurrDayApiTypeConsume(Map<String, Object> map) {
        List<CustomerCurrDayConsume> consumeList = customerFinanceMapper.queryCustomerCurrDayApiTypeConsume(map);
        if (consumeList == null) {
            return null;
        }
        for (CustomerCurrDayConsume consume : consumeList) {
            if (consume != null){
                if (consume.getSubTypeName() != null){
                    consume.setApiTypeName(consume.getApiTypeName() + "--" + consume.getSubTypeName());
                }
                if (consume.getSumAmount() != null){
                    consume.setSumAmount(consume.getSumAmount()/100.0);
                }
                if (consume.getPrice() != null){
                    consume.setPrice(consume.getPrice()/100.0);
                }
            }
        }
        return consumeList;
    }

    @Override
    public List<CustomerCurrDayConsumeDetail> queryCustomerCurrDayConsumeDetail(Map<String, Object> map) {
        List<CustomerCurrDayConsumeDetail> detailList = customerFinanceMapper.queryCustomerCurrDayConsumeDetail(map);
        if (detailList != null){
            for (CustomerCurrDayConsumeDetail detail : detailList) {
                if (detail != null){
                    detail.setCacheCount(detail.getResultCount() - detail.getResultCostCount());
                }
            }
        }
        return detailList;
    }

    @Override
    public Map<String, Object> queryNearlyWeekTrend(Integer cid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<CustomerConsume> consumeList = customerFinanceMapper.queryNearlyWeekTrend(cid);
        List<String> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();
        Map<Date,Object> proMap = new TreeMap<>();
        for (int i = 0; i < 7 ; i++) {
            try {
                String yearMonth = ChartCalendarUtil.getCurrentDateAssignYearMonthDay(-(7-i));
                proMap.put(sdf.parse(yearMonth),0.0);
                if (consumeList != null && consumeList.size() > 0){
                    for (CustomerConsume consume : consumeList) {
                        if (yearMonth.equals(consume.getConsuTime())){
                            proMap.put(sdf.parse(yearMonth),-consume.getConsume()/100.0);
                            break;
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        if (proMap != null){
            for (Map.Entry<Date,Object> me : proMap.entrySet()) {
                xList.add(sdf.format(me.getKey()));
                yList.add((Double) me.getValue());
            }
        }

        Map mapSeries = new HashedMap();
        mapSeries.put("name","近一周消费");
        mapSeries.put("data",yList);
        JSONArray jsonArrayX = JSONArray.fromObject(xList);
        JSONArray jsonArrayS = JSONArray.fromObject(mapSeries);
        Map<String,Object> resu = new HashMap();
        resu.put("jsonArrayX",jsonArrayX);
        resu.put("jsonArrayS",jsonArrayS);
        return resu;
    }

    @Override
    public String queryCustomerCompanyNameById(Integer id) {
        return customerFinanceMapper.queryCustomerCompanyNameById(id);
    }

    @Override
    @DataSourceService
    public Map<String,Object> queryCompanyCustomerRechargeRecordByCustomerId(Map<String, Object> map){
        Map<String,Object> mapResult = new HashMap<>();
        try {
            mapResult.put("queryCompanyCustomerRechargeRecordByCustomerId",customerFinanceMapper.queryCompanyCustomerRechargeRecordByCustomerId(map));
            mapResult.put("getCountCompanyCustomerRechargeRecordByCustomerId",customerFinanceMapper.getCountCompanyCustomerRechargeRecordByCustomerId(map)) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapResult;
    }

    @Override
    @DataSourceService
    public Map<String,Object> queryCompanyCustomerApiConsumeRecordByCustomerId(Map<String, Object> map){
        Map<String,Object> mapResult = new HashMap<>();
        try {
            List<ApiType> apiTypeList = customerFinanceMapper.queryCompanyCustomerApiConsumeRecordByCustomerId(map);
            List<CustomerApiType> customerApiTypeList = new ArrayList<>();
            if (apiTypeList != null) {
                for (int i = 0; i < apiTypeList.size(); i++) {
                    ApiType apiType = apiTypeList.get(i);
                    List<CustomerApiVendor> customerApiVendorList = apiType.getCustomerApiVendorList();
                    long totlePrice = 0;
                    for (int j = 0; j < customerApiVendorList.size(); j++) {
                        CustomerApiVendor customerApiVendor = customerApiVendorList.get(j);
                        totlePrice = totlePrice + customerApiVendor.getTotlePrice();
                    }
                    CustomerApiType customerApiType = new CustomerApiType();
                    customerApiType.setApiTypeId(apiType.getId());
                    customerApiType.setApiTypeName(apiType.getName());
                    customerApiType.setTotlePrice(totlePrice);
                    customerApiType.setCustomerApiVendors(apiType.getCustomerApiVendorList());
                    customerApiTypeList.add(customerApiType);
                }
            }
            mapResult.put("queryCompanyCustomerApiConsumeRecordByCustomerId",customerApiTypeList);
            mapResult.put("getCountCompanyCustomerApiConsumeRecordByCustomerId",customerFinanceMapper.getCountCompanyCustomerApiConsumeRecordByCustomerId(map));
        }catch (Exception e){
            e.printStackTrace();
        }
        return mapResult;
    }

    @Override
    @DataSourceService
    public Map<String,Object> queryCompanyCustomerApiDetailConsumeRecordByCustomerId(Map<String, Object> map){
        Map<String,Object> mapTran = new HashMap<>();

        try {
            mapTran.put("queryCompanyCustomerApiDetailConsumeRecordByCustomerId",customerFinanceMapper.queryCompanyCustomerApiDetailConsumeRecordByCustomerId(map));
            mapTran.put("getCountCompanyCustomerApiDetailConsumeRecordByCustomerId",customerFinanceMapper.getCountCompanyCustomerApiDetailConsumeRecordByCustomerId(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapTran;
    }

    @Override
    @DataSourceService
    public Map<String,Object> queryCompanyCustomerWeekMonthRecordByCustomerId(Map<String, Object> map){
        Map<String,Object> mapTran = new HashMap<>();
        try {
            mapTran.put("queryCompanyCustomerWeekMonthRecordByCustomerId",customerFinanceMapper.queryCompanyCustomerWeekMonthRecordByCustomerId(map));
            mapTran.put("getCountCompanyCustomerWeekMonthRecordByCustomerId",customerFinanceMapper.getCountCompanyCustomerWeekMonthRecordByCustomerId(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapTran;
    }

    @Override
    @DataSourceService
    public List<Integer> queryCompanyCustomerYearsByCustomerId(Map<String, Object> map){
        try {
            return customerFinanceMapper.queryCompanyCustomerYearsByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @DataSourceService
    public List<Integer> queryCompanyCustomerMonthsByCustomerId(Map<String, Object> map){
        try {
            return customerFinanceMapper.queryCompanyCustomerMonthsByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @DataSourceService
    public List<Integer> queryCompanyCustomerWeeksByCustomerId(Map<String, Object> map){
        try {
            return customerFinanceMapper.queryCompanyCustomerWeeksByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @DataSourceService
    public Map<String, List> monthChargeConsumeToward(Map<String, Object> map) {
        Set<Map.Entry<String,Object>> set = map.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        Integer customerId = null;
        Integer tableId = null;
        Integer result = null;
        while(it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if(me.getKey().equals("customerId")){
                customerId = (Integer)me.getValue();
            }
            if(me.getKey().equals("tableId") ){
                tableId = (Integer)me.getValue();
            }
            if(me.getKey().equals("result") ){
                result = (Integer)me.getValue();
            }
        }
        List<Long> yList = new ArrayList<>();
        List<String> xList = new ArrayList<>();
        Map<String, List> stringListMap = new HashedMap();

        for (int i = 12; i >0; i--) {
            WeekMonthAmount weekMonthAmount = customerFinanceMapper.queryMonthChargeConsumeToward(customerId, tableId, (result+i));

            if (weekMonthAmount != null) {
                if (weekMonthAmount.getTotleAmount()>0){
                    yList.add((weekMonthAmount.getTotleAmount()/100));
                }else{
                    yList.add(-(weekMonthAmount.getTotleAmount()/100));
                }
                xList.add(weekMonthAmount.getYears() + "年" + weekMonthAmount.getMonths() + "月");
            } else {
                yList.add(0L);
                xList.add(CalendarTools.getYearMonthCount(result+i) + "年" + CalendarTools.getMonthCount(result+i) + "月");
            }
        }
        stringListMap.put("xPort",xList);
        stringListMap.put("type",yList);
        return stringListMap;
    }

    @Override
    @DataSourceService
    public List<CustomerApiVendor> queryCustomerConsumeByVendor(Map<String, Object> map) {
        try {
            return customerFinanceMapper.queryCustomerConsumeByVendor(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @DataSourceService
    public List<ApiType> queryApiTypeByCustomerId(Map<String, Object> map) {
        try {
            return customerFinanceMapper.queryApiTypeByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @DataSourceService
    public List<ApiVendor> queryApiVendorByCustomerId(Map<String, Object> map) {
        try {
            return customerFinanceMapper.queryApiVendorByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
