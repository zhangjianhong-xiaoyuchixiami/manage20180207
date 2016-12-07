package org.qydata.service.impl;

import org.qydata.dst.AllotDept;
import org.qydata.entity.Dept;
import org.qydata.entity.User;
import org.qydata.mapper.DeptMapper;
import org.qydata.mapper.UserMapper;
import org.qydata.service.DeptService;
import org.qydata.tools.IpTool;
import org.qydata.tools.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Dept> findAllDept(String username) throws Exception {

        return deptMapper.findAllDept();
    }

    @Override
    public boolean insertUserDept(String userId, String [] deptNo) throws Exception {
        Integer[] temp = IpTool.intArray(deptNo);
        Map<String,Object> map = new HashMap();
        map.put("userId",Integer.parseInt(userId));
        map.put("deptNo",temp);
        deptMapper.deleteUserDeptByUserId(Integer.parseInt(userId));
        return deptMapper.insertUserDept(map);
    }

    @Override
    public PageModel<Dept> findAll(Map<String, Object> map) throws Exception {
        PageModel<Dept> pageModel = new PageModel<>();
        pageModel.setList(deptMapper.findAll(map));
        pageModel.setCount(deptMapper.getAllCount(map));
        return pageModel;
    }
}
