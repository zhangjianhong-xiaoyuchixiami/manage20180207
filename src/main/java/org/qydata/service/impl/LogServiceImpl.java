package org.qydata.service.impl;

import org.qydata.entity.log.Log;
import org.qydata.mapper.LogMapper;
import org.qydata.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/3/22.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired private LogMapper logMapper;

    @Override
    public boolean createLog(Log log) {
        return logMapper.createLog(log);
    }

    @Override
    public boolean updateLog(Log log) {
        return logMapper.updateLog(log);
    }

    @Override
    public List<Log> queryLog(Map<String, Object> map) {
        return logMapper.queryLog(map);
    }
}
