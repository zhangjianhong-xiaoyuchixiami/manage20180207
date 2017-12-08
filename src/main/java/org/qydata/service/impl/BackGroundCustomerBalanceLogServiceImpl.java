package org.qydata.service.impl;

import org.qydata.dst.customer.BackGroundCustomerBalanceLog;
import org.qydata.mapper.BackGroundCustomerBalanceLogMapper;
import org.qydata.service.BackGroundCustomerBalanceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackGroundCustomerBalanceLogServiceImpl implements BackGroundCustomerBalanceLogService {

    @Autowired
    private BackGroundCustomerBalanceLogMapper mapper;

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
        return mapper.queryCustomerIdByCompanyId(customerId);
    }
}
