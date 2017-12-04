package org.qydata.service.impl;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.entity.User;
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
    @Transactional
    @SystemServiceLog(description = "新增日志")
    public boolean createLog(Log log) {
        int result = logMapper.createLog(log);
        if (result != 1){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    @SystemServiceLog(description = "修改日志")
    public boolean updateLog(Log log) {
       int result = logMapper.updateLog(log);
       if (result != 1){
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           return false;
       }
        return true;
    }

    @Override
    @SystemServiceLog(description = "查询日志")
    public List<Log> queryLog(Map<String, Object> map) {
        return logMapper.queryLog(map);
    }

    @Override
    @SystemServiceLog(description = "日志里搜索操作人")
    public List<User> queryUser() {
        return logMapper.queryUser();
    }
}
