package org.qydata.service.impl;

import org.qydata.config.annotation.SystemServiceLog;
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
    @SystemServiceLog(description = "新增部门")
    public boolean addDept(Dept dept) throws Exception {
        return deptMapper.insertDept(dept);
    }

    @Override
    @SystemServiceLog(description = "查找所有部门")
    public List<Dept> findAllDept(){
        try {
            return deptMapper.findAllDept();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @SystemServiceLog(description = "批量插入用户和部门应设")
    public boolean insertUserDept(Integer userId, String [] deptId) throws Exception {
        if (deptId != null && deptId.length>0) {
            Integer[] temp = IpTool.intArray(deptId);
            Map<String, Object> map = new HashMap();
            map.put("userId", userId);
            map.put("deptId", temp);
            deptMapper.deleteUserDeptByUserId(userId);
            return deptMapper.insertUserDept(map);
        }else{
            return deptMapper.deleteUserDeptByUserId(userId);
        }
    }

    @Override
    public PageModel<Dept> findAll(Map<String, Object> map) throws Exception {
        PageModel<Dept> pageModel = new PageModel<>();
        pageModel.setList(deptMapper.findAll(map));
        pageModel.setCount(deptMapper.getAllCount(map));
        return pageModel;
    }

    @Override
    @SystemServiceLog(description = "查看所属部门")
    public List<Dept> findDeptByUserId(Integer userId) {
        try {
            return deptMapper.findDeptByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
