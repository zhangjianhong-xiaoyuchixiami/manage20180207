package org.qydata.service.impl;

import org.qydata.entity.Dept;
import org.qydata.mapper.DeptMapper;
import org.qydata.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/3.
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public boolean addDept(Dept dept) throws Exception {
        return deptMapper.insertDept(dept);
    }

    @Override
    public List<Dept> findAllDept() throws Exception {
        return deptMapper.findAllDept();
    }

    @Override
    public boolean insertUserDept(Integer[] userId, Integer[] deptNo) throws Exception {
        Map<String,Object> map = new HashMap();
        Integer usId = null;
        for (int i=0;i<userId.length;i++){
            usId = userId[i];
        }
        map.put("deptNo",deptNo);
        map.put("usId",usId);
        return false;
    }
}
