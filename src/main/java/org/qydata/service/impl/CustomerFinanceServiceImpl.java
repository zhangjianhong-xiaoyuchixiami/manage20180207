package org.qydata.service.impl;

import org.qydata.dst.CustomerFinance;
import org.qydata.entity.ApiType;
import org.qydata.entity.CustomerBalanceLog;
import org.qydata.mapper.CustomerFinanceMapper;
import org.qydata.service.CustomerFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<ApiType> queryCompanyCustomerApiConsumeRecordByCustomerId(Map<String, Object> map) throws Exception {
        return customerFinanceMapper.queryCompanyCustomerApiConsumeRecordByCustomerId(map);
    }
}
