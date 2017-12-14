package org.qydata.service.impl;

import org.qydata.entity.Role;
import org.qydata.entity.User;
import org.qydata.entity.UserDept;
import org.qydata.entity.UserRole;
import org.qydata.mapper.mapper1.DeptMapper;
import org.qydata.mapper.mapper1.RoleMapper;
import org.qydata.mapper.mapper1.UserMapper;
import org.qydata.mapper.mapper2.DeptSelectMapper;
import org.qydata.mapper.mapper2.RoleSelectMapper;
import org.qydata.mapper.mapper2.UserSelectMapper;
import org.qydata.service.RoleService;
import org.qydata.tools.IpTool;
import org.qydata.tools.Md5Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/3.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired private UserMapper userMapper;
    @Autowired private UserSelectMapper userSelectMapper;
    @Autowired private RoleMapper roleMapper;
    @Autowired private RoleSelectMapper roleSelectMapper;
    @Autowired private DeptMapper deptMapper;
    @Autowired protected DeptSelectMapper deptSelectMapper;

    @Override
    public List<Role> findAllRole() throws Exception {
        return roleSelectMapper.findAllRole();
    }

    @Override

    public List<UserRole> findAllRoleByUsername(Integer userId) throws Exception {
        return roleSelectMapper.findAllRoleByUsername(userId);
    }

    @Override
    public boolean addRoleUser(Integer userId,String [] roleId) throws Exception {
        if (roleId != null && roleId.length>0) {
            Integer[] temp = IpTool.intArray(roleId);
            Map<String, Object> map = new HashMap();
            map.put("userId", userId);
            map.put("roleId", temp);
            roleMapper.deleteUserRoleByUserId(userId);
            return roleMapper.addRoleUser(map);
        }else {
            return roleMapper.deleteUserRoleByUserId(userId );
        }
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
        String password = Md5Tools.md5(userSelectMapper.findUserByUsername(userId).getEmail()+"123456");
        return userMapper.resetPassword(userId,password);
    }

    @Override
    public boolean updateStatusStart(Integer userId) throws Exception {
        return userMapper.updateStatusStart(userId);
    }

    @Override
    public boolean updateStatusForbid(Integer userId) throws Exception {
        return userMapper.updateStatusforbid(userId);
    }



}
