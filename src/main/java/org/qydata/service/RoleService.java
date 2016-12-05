package org.qydata.service;

import org.qydata.entity.Role;
import org.qydata.entity.RoleUser;
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
     * 分配角色
     * @param roleUser
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean addRoleUser(RoleUser roleUser)throws Exception;
}
