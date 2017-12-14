package org.qydata.service.impl;

import org.qydata.entity.User;
import org.qydata.mapper.mapper1.DeptMapper;
import org.qydata.mapper.mapper1.RoleMapper;
import org.qydata.mapper.mapper1.UserMapper;
import org.qydata.mapper.mapper2.UserSelectMapper;
import org.qydata.service.PowerUserService;
import org.qydata.tools.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by jonhn on 2017/4/14.
 */
@Service
public class PowerServiceImpl implements PowerUserService {

    @Autowired private UserMapper userMapper;
    @Autowired private UserSelectMapper userSelectMapper;
    @Autowired private RoleMapper roleMapper;
    @Autowired private DeptMapper deptMapper;


    @Override
    public PageModel<User> queryAllUser(Map<String, Object> map) throws Exception {
        PageModel<User> pageModel = new PageModel<User>();
        pageModel.setCount(userSelectMapper.queryAllCount(map));
        pageModel.setList(userSelectMapper.queryAllUser(map));
        return pageModel;
    }

    @Override
    public User findUserByUsername(Integer userId) throws Exception {
        return userSelectMapper.findUserByUsername(userId);
    }

    @Override
    public User findUserByEmail(String email) {
        try {
            return userSelectMapper.findUserByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
