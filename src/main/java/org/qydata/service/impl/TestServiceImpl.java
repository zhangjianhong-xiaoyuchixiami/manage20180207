package org.qydata.service.impl;

import org.qydata.mapper.TestMapper;
import org.qydata.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jonhn on 2017/4/26.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired private TestMapper testMapper;

    @Override
    public Map<String, Object> queryAllUserTest(Map<String, Object> map) {
        Map<String,Object> mapResu = new HashMap<>();
        mapResu.put("queryUserTest",testMapper.queryUserTest(map));
        mapResu.put("getAllUserCount",testMapper.getAllUserCount(map));
        return mapResu;
    }
}
