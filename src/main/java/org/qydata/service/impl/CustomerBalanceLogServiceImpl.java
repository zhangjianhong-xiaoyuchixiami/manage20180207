package org.qydata.service.impl;

import org.qydata.entity.CustomerBalanceModifyReason;
import org.qydata.mapper.CustomerBalanceLogMapper;
import org.qydata.mapper.CustomerMapper;
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
    public List<CustomerBalanceModifyReason> findAll(List customerBalanceModifyReasonIdList) {
        return customerBalanceLogMapper.findAll(customerBalanceModifyReasonIdList);
    }

}
