package org.qydata.service.impl;

import org.qydata.entity.CustomerBalanceModifyReason;
import org.qydata.mapper.CustomerBalanceLogMapper;
import org.qydata.mapper.CustomerMapper;
import org.qydata.entity.Customer;
import org.qydata.entity.CustomerBalanceLog;
import org.qydata.service.CustomerBalanceLogService;
import org.qydata.tools.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/11/8.
 */
@Service
public class CustomerBalanceLogServiceImpl implements CustomerBalanceLogService{
    @Autowired
    private CustomerBalanceLogMapper customerBalanceLogMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<CustomerBalanceModifyReason> findAll(List customerBalanceModifyReasonIdList) {
        return customerBalanceLogMapper.findAll(customerBalanceModifyReasonIdList);
    }

    @Override
    public boolean changeCustomerBalance(String authId, String amount, String reasonId) throws Exception {
        Customer customer = customerMapper.findByAuthId(authId);
        Long balance = customerBalanceLogMapper.findBalanceByAuthId(authId);
        Long totleBalance = null;
        if(reasonId.equals("-1") || reasonId.equals("-2")){
            totleBalance =balance-(Long.parseLong(amount)*100);
        }else{
            totleBalance =balance+(Long.parseLong(amount)*100);
        }

        customerBalanceLogMapper.updateBalanceByAuthId(totleBalance,authId);
        CustomerBalanceLog customerBalanceLog = new CustomerBalanceLog();
        customerBalanceLog.setCustomerId(customer.getId());
        customerBalanceLog.setReasonId(Integer.parseInt(reasonId));
        if(reasonId.equals("-1") || reasonId.equals("-2")) {
            customerBalanceLog.setAmount(0-(Long.parseLong(amount)*100));
        }else{
            customerBalanceLog.setAmount(0+(Long.parseLong(amount)*100));
        }
        return customerBalanceLogMapper.insertCustomerBalanceLog(customerBalanceLog);
    }

    @Override
    public PageModel<CustomerBalanceLog> findAllCustomerBalanceLogByCustomerId(Map<String, Object> map) throws Exception {
        PageModel<CustomerBalanceLog> pageModel = new PageModel<>();
        pageModel.setList(customerBalanceLogMapper.findAllCustomerBalanceLogByCustomerId(map));
        pageModel.setCount(customerBalanceLogMapper.getAllCountCustomerBalanceLogByCustomerId(map));
        return pageModel;
    }

    @Override
    public List<CustomerBalanceLog> getAllCustomerBalanceLogAmountByCustomerId(Map<String, Object> map) throws Exception {
        return customerBalanceLogMapper.getAllCustomerBalanceLogAmountByCustomerId(map);
    }
}
