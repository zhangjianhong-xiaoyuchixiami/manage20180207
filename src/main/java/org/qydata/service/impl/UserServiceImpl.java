package org.qydata.service.impl;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.entity.User;
import org.qydata.mapper.UserMapper;
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


    @Autowired private UserMapper userMapper;

    @Override
    @SystemServiceLog(description = "根据邮箱验证用户是否存在")
    public User get(String email) {
        try {
            return this.userMapper.findById(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @SystemServiceLog(description = "查看用户的角色以及权限")
    public Map<String, Object> listAuthByUser(Integer userId)  {
        Map<String,Object> map = new HashMap<String,Object>() ;
        try {
            map.put("allRoles", this.userMapper.findAllRoleByUser(userId)) ;
            map.put("allActions", this.userMapper.findAllActionByUser(userId)) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map ;
    }

}
