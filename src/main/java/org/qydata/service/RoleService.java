package org.qydata.service;

import org.qydata.entity.Role;
import org.qydata.entity.User;
import org.qydata.entity.UserRole;
import org.qydata.tools.PageModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/3.
 */
public interface RoleService {

    /**
     * 查询所有的角色
     * @return
     * @throws Exception
     */
    public List<Role> findAllRole()throws Exception;

    /**
     * 根据用户名匹配所有角色
     * @param userId
     * @return
     * @throws Exception
     */
    public List<UserRole> findAllRoleByUsername(Integer userId)throws Exception;
    /**
     * 分配角色
     * @param userId
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean addRoleUser(Integer userId,String [] roleId )throws Exception;

    /**
     * 添加管理员
     * @param user
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean addUser(User user)throws Exception;
    /**
     * Common添加管理员
     * @param user
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean addUserCommon(User user,String deptId)throws Exception;

    /**
     * 根据用户名和旧密码修改密码
     * @param userId
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean updatePassword(Integer userId,String newPassword)throws Exception;

    /**
     * 根据用户Id重置密码
     * @param userId
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean resetPassword(Integer userId)throws Exception;


    /**
     * 启用
     * @param userId
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean updateStatusStart(Integer userId)throws Exception;

    /**
     * 禁用
     * @param userId
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean updateStatusForbid(Integer userId)throws Exception;



}
