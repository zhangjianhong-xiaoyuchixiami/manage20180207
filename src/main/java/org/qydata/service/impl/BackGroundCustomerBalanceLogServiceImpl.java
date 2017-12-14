package org.qydata.service.impl;

import org.qydata.dst.customer.BackGroundCustomerBalanceLog;
import org.qydata.mapper.mapper1.BackGroundCustomerBalanceLogMapper;
import org.qydata.mapper.mapper2.BackGroundCustomerBalanceLogSelectMapper;
import org.qydata.service.BackGroundCustomerBalanceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackGroundCustomerBalanceLogServiceImpl implements BackGroundCustomerBalanceLogService {

    @Autowired
    private BackGroundCustomerBalanceLogMapper mapper;
    @Autowired
    private BackGroundCustomerBalanceLogSelectMapper selectMapper;

    @Override
    public int insertBackGroundCustomerBalanceLog(BackGroundCustomerBalanceLog log) {
        return mapper.insertBackGroundCustomerBalanceLog(log);
    }

    @Override
    public int deleteBackGroundCustomerBalanceLog(BackGroundCustomerBalanceLog log) {
        return mapper.deleteBackGroundCustomerBalanceLog(log);
    }

    @Override
    public Integer queryCustomerIdByCompanyId(Integer customerId) {
        return selectMapper.queryCustomerIdByCompanyId(customerId);
    }
}
