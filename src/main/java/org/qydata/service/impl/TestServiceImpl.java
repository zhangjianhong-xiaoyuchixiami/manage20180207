package org.qydata.service.impl;

import org.qydata.entity.User;
import org.qydata.mapper.mapper1.TestMapper;
import org.qydata.mapper.mapper2.TestSelectMapper;
import org.qydata.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/4/26.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired private TestMapper testMapper;
    @Autowired
    private TestSelectMapper testSelectMapper;

    @Override
    public Map<String, Object> queryAllUserTest(Map<String, Object> map) {
        Map<String,Object> mapResu = new HashMap<>();
        mapResu.put("queryUserTest",testSelectMapper.queryUserTest(map));
        mapResu.put("getAllUserCount",testSelectMapper.getAllUserCount(map));
        return mapResu;
    }

    @Override
    public List<Map<Object, Object>> testMap() {
        List<Map<Object,Object>> mapList = testSelectMapper.testMap();
        List<Map<Object,Object>> listResu = new ArrayList<>();
        for (int i=0; i<mapList.size(); i++){
            Map<Object,Object> mapTran = mapList.get(i);
            Map<Object,Object> mapResu = new HashMap<>();
            User user = new User();
            user.setEmail((String) mapTran.get("email"));
            user.setStatus((int)mapTran.get("status"));
            user.setTypeId((int)mapTran.get("typeId"));
            mapResu.put(mapTran.get("id"),user);
            listResu.add(mapResu);
        }
        return listResu;
    }
}
