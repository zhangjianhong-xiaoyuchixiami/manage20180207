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
    public User get(String email) throws Exception {
        return this.userMapper.findById(email);
    }
    @Override
    public Map<String, Object> listAuthByUser(Integer userId) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("allRoles", this.userMapper.findAllRoleByUser(userId)) ;
        map.put("allActions", this.userMapper.findAllActionByUser(userId)) ;
        return map ;
    }

    @Override
    public boolean addUser(User user) throws Exception {
        User userA = new User();
        userA.setEmail(user.getEmail());
        userA.setPassword(Md5Tools.md5(user.getEmail().trim()+"123456"));
        userA.setStatus(user.getStatus());
        userA.setTypeId(user.getTypeId());
        try{
            userMapper.addUser(userA);
            if(user.getTypeId() == 1){
                roleMapper.addRoleSuperUser(userA.getId());
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
        userA.setPassword(Md5Tools.md5(user.getEmail().trim()+"123456"));
        userA.setStatus(user.getStatus());
        userMapper.addUserCommon(userA);
        UserDept userDept = new UserDept();
        userDept.setUserId(userA.getId());
        userDept.setDeptId(Integer.parseInt(deptId));
        return deptMapper.addUserDeptById(userDept);
    }

    @Override
    public boolean updatePassword(Integer userId,String newPassword) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("newPassword",newPassword);
        return userMapper.updatePassword(map);
    }

    @Override
    public boolean resetPassword(Integer userId) throws Exception {
        String password = Md5Tools.md5(userMapper.findUserByUsername(userId).getEmail()+"123456");
        return userMapper.resetPassword(userId,password);
    }

    @Override
    public PageModel<User> findAllUser(Map<String, Object> map) throws Exception {
        PageModel<User> pageModel = new PageModel<User>();
        pageModel.setCount(userMapper.getAllCount(map));
        pageModel.setList(userMapper.findAllUser(map));
        return pageModel;
    }

    @Override
    public User findUserByUsername(Integer userId) throws Exception {
        return userMapper.findUserByUsername(userId);
    }

    @Override
    public boolean updateStatusStart(Integer userId) throws Exception {
        return userMapper.updateStatusStart(userId);
    }

    @Override
    public boolean updateStatusForbid(Integer userId) throws Exception {
        return userMapper.updateStatusforbid(userId);
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

    @Override
    public Map<String,Object> findAllUserTest(Map<String,Object> map) {
        Map<String,Object> mapTran = new HashMap();
        mapTran.put("findAllUser",userMapper.findAllUserTest(map));
        mapTran.put("getAllCount",userMapper.getAllCountTest());
        return mapTran;
    }

    @Override
    public boolean deleteTest(Integer id) {
        return userMapper.deleteTest(id);
    }

}
