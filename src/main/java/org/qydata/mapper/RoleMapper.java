package org.qydata.mapper;

import org.qydata.entity.Role;
import org.qydata.entity.RoleUser;

import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */
public interface RoleMapper {

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
    public boolean addRoleUser(RoleUser roleUser)throws Exception;
}
