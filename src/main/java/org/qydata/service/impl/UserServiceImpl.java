package org.qydata.service.impl;

import org.qydata.entity.User;
import org.qydata.mapper.UserMapper;
import org.qydata.service.UserService;
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
    UserMapper userMapper;

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
        return userMapper.addUser(user);
    }

    @Override
    public boolean updatePassword(String username, String password, String newPassword) throws Exception {
        return userMapper.updatePassword(username,password,newPassword);
    }

    @Override
    public boolean resetPassword(String username) throws Exception {
        return userMapper.resetPassword(username);
    }

    @Override
    public PageModel<User> findAllUser(Map<String, Object> map) throws Exception {
        PageModel<User> pageModel = new PageModel<User>();
        pageModel.setCount(userMapper.getAllCount(map));
        pageModel.setList(userMapper.findAllUser(map));
        System.out.println(userMapper.findAllUser(map));
        return pageModel;
    }

    @Override
    public User findUserByUsername(String username) throws Exception {
        return userMapper.findUserByUsername(username);
    }


//
//    @Override
//    public List<Role> findAllRole() throws Exception {
//        return userMapper.findAllRole();
//    }
//
//    @Override
//    public List<Dept> findAllDept() throws Exception {
//        return userMapper.findAllDept();
//    }
//
//    @Override
//    public boolean addAdminAndRole(AdminRoleInfo adminRoleInfo) throws Exception {
//        boolean flag = false;
//        try {
//            userMapper.addAdmin(adminRoleInfo.getUser());
//            RoleUser roleUser = new RoleUser();
//            roleUser.setRoleId(adminRoleInfo.getRole().getId());
//            roleUser.setLoginName(adminRoleInfo.getUser().getLoginName());
//           flag = userMapper.addRoleAdmin(roleUser);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return flag;
//    }
//
//    @Override
//    public PageModel<User> findAllAdmin(Map<String,Object> map) throws Exception {
//        PageModel<User> pageModel = new PageModel<User>();
//        pageModel.setRows(userMapper.getAllCountAdmin());
//        pageModel.setList(userMapper.findAllAdmin(map));
//        return pageModel;
//    }
//
//    @Override
//    public PageModel<User> findAllByColumn(Map<String, Object> map) throws Exception {
//        PageModel<User> pageModel = new PageModel<User>();
//        pageModel.setRows(userMapper.getCountColumn(map));
//        pageModel.setList(userMapper.findByColumn(map));
//        return pageModel;
//    }
//
//    @Override
//    public boolean updateStatusStart(String longinName) throws Exception {
//        return userMapper.updateStatusStart(longinName);
//    }
//
//    @Override
//    public boolean updateStatusForbid(String longinName) throws Exception {
//        return userMapper.updateStatusforbid(longinName);
//    }
//
//    @Override
//    public boolean updatePassword(String loginName, String password, String newPassword) throws Exception {
//        return userMapper.updatePassword(loginName,password,newPassword);
//    }
}
