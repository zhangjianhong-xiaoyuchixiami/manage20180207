package org.qydata.service.impl;

import org.qydata.entity.User;
import org.qydata.mapper.DeptMapper;
import org.qydata.mapper.RoleMapper;
import org.qydata.mapper.UserMapper;
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
    @Autowired private RoleMapper roleMapper;
    @Autowired private DeptMapper deptMapper;


    @Override
    public PageModel<User> queryAllUser(Map<String, Object> map) throws Exception {
        PageModel<User> pageModel = new PageModel<User>();
        pageModel.setCount(userMapper.queryAllCount(map));
        pageModel.setList(userMapper.queryAllUser(map));
        return pageModel;
    }

    @Override
    public User findUserByUsername(Integer userId) throws Exception {
        return userMapper.findUserByUsername(userId);
    }

    @Override
    public User findUserByEmail(String email) {
        try {
            return userMapper.findUserByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
