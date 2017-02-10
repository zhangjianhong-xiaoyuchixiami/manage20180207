package org.qydata.service.impl;

import org.qydata.entity.Dept;
import org.qydata.mapper.DeptMapper;
import org.qydata.service.DeptService;
import org.qydata.tools.IpTool;
import org.qydata.tools.PageModel;
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
    public List<Dept> findAllDept(){
        try {
            return deptMapper.findAllDept();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertUserDept(String userId, String [] deptId) throws Exception {
        if (deptId != null && deptId.length>0) {
            Integer[] temp = IpTool.intArray(deptId);
            Map<String, Object> map = new HashMap();
            map.put("userId", Integer.parseInt(userId));
            map.put("deptId", temp);
            deptMapper.deleteUserDeptByUserId(Integer.parseInt(userId));
            return deptMapper.insertUserDept(map);
        }else{
            return deptMapper.deleteUserDeptByUserId(Integer.parseInt(userId));
        }
    }

    @Override
    public PageModel<Dept> findAll(Map<String, Object> map) throws Exception {
        PageModel<Dept> pageModel = new PageModel<>();
        pageModel.setList(deptMapper.findAll(map));
        pageModel.setCount(deptMapper.getAllCount(map));
        return pageModel;
    }
}
