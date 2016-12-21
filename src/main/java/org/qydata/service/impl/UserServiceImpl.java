package org.qydata.service.impl;

import org.qydata.entity.User;
import org.qydata.entity.UserDept;
import org.qydata.mapper.DeptMapper;
import org.qydata.mapper.RoleMapper;
import org.qydata.mapper.UserMapper;
import org.qydata.service.UserService;
import org.qydata.tools.Md5Tools;
import org.qydata.tools.PageModel;
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
    private RoleMapper roleMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public User get(String username) throws Exception {
        return this.userMapper.findById(username);
    }
    @Override
    public Map<String, Object> listAuthByUser(String username) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("allRoles", this.userMapper.findAllRoleByUser(username)) ;
        map.put("allActions", this.userMapper.findAllActionByUser(username)) ;
        return map ;
    }

    @Override
    public boolean addUser(User user) throws Exception {
        User userA = new User();
        userA.setPassword(Md5Tools.md5(user.getUsername().trim()+"123456"));
        userA.setUsername(user.getUsername().trim());
        userA.setName(user.getName());
        userA.setTel(user.getTel());
        userA.setStatus(user.getStatus());
        userA.setTypeId(user.getTypeId());
        try{
            userMapper.addUser(userA);
            if(user.getTypeId() == 1){
                roleMapper.addRoleSuperUser(user.getUsername());
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean addUserCommon(User user,String deptId) throws Exception {
        User userA = new User();
        userA.setPassword(Md5Tools.md5(user.getUsername().trim()+"123456"));
        userA.setUsername(user.getUsername().trim());
        userA.setName(user.getName());
        userA.setTel(user.getTel());
        userA.setStatus(user.getStatus());
        userMapper.addUserCommon(userA);
        UserDept userDept = new UserDept();
        userDept.setUserId(userA.getId());
        userDept.setDeptId(Integer.parseInt(deptId));
        return deptMapper.addUserDeptById(userDept);
    }

    @Override
    public boolean updatePassword(String username, String password, String newPassword) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        map.put("newPassword",newPassword);
        return userMapper.updatePassword(map);
    }

    @Override
    public boolean resetPassword(String username) throws Exception {
        String password = Md5Tools.md5(username+"123456");
        return userMapper.resetPassword(username,password);
    }

    @Override
    public PageModel<User> findAllUser(Map<String, Object> map) throws Exception {
        PageModel<User> pageModel = new PageModel<User>();
        pageModel.setCount(userMapper.getAllCount(map));
        pageModel.setList(userMapper.findAllUser(map));
        return pageModel;
    }

    @Override
    public User findUserByUsername(String username) throws Exception {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public boolean updateStatusStart(String username) throws Exception {
        return userMapper.updateStatusStart(username);
    }

    @Override
    public boolean updateStatusForbid(String username) throws Exception {
        return userMapper.updateStatusforbid(username);
    }

}
