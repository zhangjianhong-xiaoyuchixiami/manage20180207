package org.qydata.service.impl;

import org.qydata.config.annotation.DataSourceService;
import org.qydata.entity.log.Log;
import org.qydata.mapper.LogMapper;
import org.qydata.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/3/22.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired private LogMapper logMapper;

    @Override
    @DataSourceService
    @Transactional
    public boolean createLog(Log log) {
        int result = logMapper.createLog(log);
        if (result != 1){
            return false;
        }
        return true;
    }

    @Override
    @DataSourceService
    @Transactional
    public boolean updateLog(Log log) {
       int result = logMapper.updateLog(log);
       if (result != 1){
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           return false;
       }
        return true;
    }

    @Override
    @DataSourceService
    public List<Log> queryLog(Map<String, Object> map) {
        return logMapper.queryLog(map);
    }
}
