package org.qydata.service;

import org.qydata.entity.Role;
import org.qydata.entity.UserRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
