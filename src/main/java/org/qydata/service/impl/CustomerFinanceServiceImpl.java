package org.qydata.service.impl;

import org.apache.commons.collections.map.HashedMap;
import org.qydata.dst.CustomerApiType;
import org.qydata.dst.CustomerApiVendor;
import org.qydata.dst.CustomerFinance;
import org.qydata.entity.ApiType;
import org.qydata.entity.ApiVendor;
import org.qydata.entity.CustomerBalanceLog;
import org.qydata.entity.WeekMonthAmount;
import org.qydata.mapper.CustomerFinanceMapper;
import org.qydata.service.CustomerFinanceService;
import org.qydata.tools.CalendarTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jonhn on 2017/1/8.
 */
@Service
public class CustomerFinanceServiceImpl implements CustomerFinanceService {

    @Autowired
    private CustomerFinanceMapper customerFinanceMapper;

    @Override
    public List<CustomerFinance> queryCompanyCustomerOverAllFinanceByDept(Map<String, Object> map){
        try {
            return customerFinanceMapper.queryCompanyCustomerOverAllFinanceByDept(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CustomerFinance> queryCompanyCustomerOverAllFinance(Map<String, Object> map){
        try {
            return customerFinanceMapper.queryCompanyCustomerOverAllFinance(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
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
    public List<CustomerBalanceLog> queryCompanyCustomerApiDetailConsumeRecordByCustomerId(Map<String, Object> map){
        try {
            return customerFinanceMapper.queryCompanyCustomerApiDetailConsumeRecordByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<WeekMonthAmount> queryCompanyCustomerWeekMonthRecordByCustomerId(Map<String, Object> map){
        try {
            return customerFinanceMapper.queryCompanyCustomerWeekMonthRecordByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Integer> queryCompanyCustomerYearsByCustomerId(Map<String, Object> map){
        try {
            return customerFinanceMapper.queryCompanyCustomerYearsByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Integer> queryCompanyCustomerMonthsByCustomerId(Map<String, Object> map){
        try {
            return customerFinanceMapper.queryCompanyCustomerMonthsByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Integer> queryCompanyCustomerWeeksByCustomerId(Map<String, Object> map){
        try {
            return customerFinanceMapper.queryCompanyCustomerWeeksByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
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
    public List<CustomerApiVendor> queryCustomerConsumeByVendor(Map<String, Object> map) {
        try {
            return customerFinanceMapper.queryCustomerConsumeByVendor(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ApiType> queryApiTypeByCustomerId(Map<String, Object> map) {
        try {
            return customerFinanceMapper.queryApiTypeByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ApiVendor> queryApiVendorByCustomerId(Map<String, Object> map) {
        try {
            return customerFinanceMapper.queryApiVendorByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
