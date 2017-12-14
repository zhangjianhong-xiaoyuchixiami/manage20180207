package org.qydata.mapper.mapper2;

import org.qydata.entity.Role;
import org.qydata.entity.UserRole;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/3.
 */
public interface RoleSelectMapper {

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

}
