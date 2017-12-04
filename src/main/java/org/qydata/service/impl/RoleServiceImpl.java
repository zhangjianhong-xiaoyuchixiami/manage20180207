package org.qydata.service.impl;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.entity.Role;
import org.qydata.entity.User;
import org.qydata.entity.UserDept;
import org.qydata.entity.UserRole;
import org.qydata.mapper.DeptMapper;
import org.qydata.mapper.RoleMapper;
import org.qydata.mapper.UserMapper;
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
    @Autowired private RoleMapper roleMapper;
    @Autowired private DeptMapper deptMapper;

    @Override
    @SystemServiceLog(description = "查询所有的角色")
    public List<Role> findAllRole() throws Exception {
        return roleMapper.findAllRole();
    }

    @Override
    @SystemServiceLog(description = "根据用户名匹配所有角色")
    public List<UserRole> findAllRoleByUsername(Integer userId) throws Exception {
        return roleMapper.findAllRoleByUsername(userId);
    }

    @Override
    @SystemServiceLog(description = "分配角色")
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
    @SystemServiceLog(description = "添加管理员")
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
    @SystemServiceLog(description = "Common添加管理员")
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
    @SystemServiceLog(description = "修改密码")
    public boolean updatePassword(Integer userId,String newPassword) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("newPassword",newPassword);
        return userMapper.updatePassword(map);
    }

    @Override
    @SystemServiceLog(description = "重置密码")
    public boolean resetPassword(Integer userId) throws Exception {
        String password = Md5Tools.md5(userMapper.findUserByUsername(userId).getEmail()+"123456");
        return userMapper.resetPassword(userId,password);
    }

    @Override
    @SystemServiceLog(description = "启用")
    public boolean updateStatusStart(Integer userId) throws Exception {
        return userMapper.updateStatusStart(userId);
    }

    @Override
    @SystemServiceLog(description = "禁用")
    public boolean updateStatusForbid(Integer userId) throws Exception {
        return userMapper.updateStatusforbid(userId);
    }



}
