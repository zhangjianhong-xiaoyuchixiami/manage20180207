package org.qydata.mapper;

import org.qydata.entity.Role;
import org.qydata.entity.UserRole;

import java.util.List;
import java.util.Map;

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
     * 根据用户名匹配所有角色
     * @param username
     * @return
     * @throws Exception
     */
    public List<UserRole> findAllRoleByUsername(String username)throws Exception;

    /**
     * 根据用户Id删除用户和角色映射
     * @return
     * @throws Exception
     */
    public Integer deleteUserRoleByUserId(String userId)throws Exception;

    /**
     * 分配角色
     * @param map
     * @return
     * @throws Exception
     */
    public boolean addRoleUser(Map<String, Object> map)throws Exception;
}
