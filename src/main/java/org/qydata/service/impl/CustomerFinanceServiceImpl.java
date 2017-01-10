package org.qydata.service.impl;

import org.qydata.dst.CustomerApiType;
import org.qydata.dst.CustomerApiVendor;
import org.qydata.dst.CustomerFinance;
import org.qydata.entity.ApiType;
import org.qydata.entity.CustomerBalanceLog;
import org.qydata.entity.WeekMonthAmount;
import org.qydata.mapper.CustomerFinanceMapper;
import org.qydata.service.CustomerFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/8.
 */
@Service
public class CustomerFinanceServiceImpl implements CustomerFinanceService {

    @Autowired
    private CustomerFinanceMapper customerFinanceMapper;

    @Override
    public List<CustomerFinance> queryCompanyCustomerOverAllFinance(Map<String, Object> map) throws Exception {
        return customerFinanceMapper.queryCompanyCustomerOverAllFinance(map);
    }

    @Override
    public List<CustomerBalanceLog> queryCompanyCustomerRechargeRecordByCustomerId(Map<String, Object> map) throws Exception {
        return customerFinanceMapper.queryCompanyCustomerRechargeRecordByCustomerId(map);
    }

    @Override
    public List<CustomerApiType> queryCompanyCustomerApiConsumeRecordByCustomerId(Map<String, Object> map) throws Exception {
        List<ApiType> apiTypeList = customerFinanceMapper.queryCompanyCustomerApiConsumeRecordByCustomerId(map);
        List<CustomerApiType> customerApiTypeList = new ArrayList<>();
        for (int i=0;i<apiTypeList.size();i++){
            ApiType apiType = apiTypeList.get(i);
            List<CustomerApiVendor> customerApiVendorList = apiType.getCustomerApiVendorList();
            long totlePrice = 0;
            for (int j=0;j<customerApiVendorList.size();j++){
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

        return customerApiTypeList;
    }

    @Override
    public List<CustomerApiVendor> queryCompanyCustomerApiDetailConsumeRecordByCustomerId(Map<String, Object> map) throws Exception {
        return customerFinanceMapper.queryCompanyCustomerApiDetailConsumeRecordByCustomerId(map);
    }

    @Override
    public List<WeekMonthAmount> queryCompanyCustomerWeekMonthRecordByCustomerId(Map<String, Object> map) throws Exception {
        return customerFinanceMapper.queryCompanyCustomerWeekMonthRecordByCustomerId(map);
    }

    @Override
    public List<Integer> queryCompanyCustomerYearsByCustomerId(Map<String, Object> map) throws Exception {
        return customerFinanceMapper.queryCompanyCustomerYearsByCustomerId(map);
    }

    @Override
    public List<Integer> queryCompanyCustomerMonthsByCustomerId(Map<String, Object> map) throws Exception {
        return customerFinanceMapper.queryCompanyCustomerMonthsByCustomerId(map);
    }

    @Override
    public List<Integer> queryCompanyCustomerWeeksByCustomerId(Map<String, Object> map) throws Exception {
        return customerFinanceMapper.queryCompanyCustomerWeeksByCustomerId(map);
    }
}
