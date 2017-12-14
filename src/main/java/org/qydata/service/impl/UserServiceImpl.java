package org.qydata.service.impl;

import org.qydata.entity.User;
import org.qydata.mapper.mapper1.UserMapper;
import org.qydata.mapper.mapper2.UserSelectMapper;
import org.qydata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jonhn on 2016/11/23.
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserSelectMapper userSelectMapper;

    @Override
    public User get(String email) {
        try {
            return this.userSelectMapper.findById(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Object> listAuthByUser(Integer userId)  {
        Map<String,Object> map = new HashMap<String,Object>() ;
        try {
            map.put("allRoles", this.userSelectMapper.findAllRoleByUser(userId)) ;
            map.put("allActions", this.userSelectMapper.findAllActionByUser(userId)) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map ;
    }

}
