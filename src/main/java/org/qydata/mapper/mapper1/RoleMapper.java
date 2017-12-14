package org.qydata.mapper.mapper1;

import org.qydata.entity.Role;
import org.qydata.entity.UserRole;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/3.
 */
public interface RoleMapper {

    /**
     * 根据用户Id删除用户和角色映射
     * @return
     * @throws Exception
     */
    public boolean deleteUserRoleByUserId(Integer userId)throws Exception;

    /**
     * 分配角色
     * @param map
     * @return
     * @throws Exception
     */
    public boolean addRoleUser(Map<String, Object> map)throws Exception;

    /**
     * 给super默认添加角色
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean addRoleSuperUser(Integer userId)throws Exception;
}
