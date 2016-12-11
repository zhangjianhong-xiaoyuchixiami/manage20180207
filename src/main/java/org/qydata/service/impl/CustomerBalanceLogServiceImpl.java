package org.qydata.service.impl;

import org.qydata.entity.CustomerBalanceModifyReason;
import org.qydata.mapper.CustomerBalanceLogMapper;
import org.qydata.mapper.CustomerMapper;
import org.qydata.entity.Customer;
import org.qydata.entity.CustomerBalanceLog;
import org.qydata.service.CustomerBalanceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<CustomerBalanceModifyReason> findAll() {
        return customerBalanceLogMapper.findAll();
    }

    @Override
    public boolean changeCustomerBalance(String authId, String amount, String reasonId) throws Exception {
        Customer customer = customerMapper.findByAuthId(authId);
        Long balance = customerBalanceLogMapper.findBalanceByAuthId(authId);
        Long totleBalance = null;
        if(reasonId.equals("-1") || reasonId.equals("-2")){
            totleBalance =balance-Long.parseLong(amount);
        }else{
            totleBalance =balance+Long.parseLong(amount);
        }

        customerBalanceLogMapper.updateBalanceByAuthId(totleBalance,authId);
        CustomerBalanceLog customerBalanceLog = new CustomerBalanceLog();
        customerBalanceLog.setCustomerId(customer.getId());
        customerBalanceLog.setAmount(Long.parseLong(amount));
        customerBalanceLog.setReasonId(Integer.parseInt(reasonId));
        return customerBalanceLogMapper.insertcustomerBalanceLog(customerBalanceLog);
    }
}
