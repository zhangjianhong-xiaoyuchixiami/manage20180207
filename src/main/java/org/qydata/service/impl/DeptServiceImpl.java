package org.qydata.service.impl;

import org.qydata.entity.Dept;
import org.qydata.mapper.DeptMapper;
import org.qydata.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
