package org.qydata.mapper.mapper2;

import org.qydata.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jonhn on 2016/11/23.
 */
public interface UserSelectMapper {
    /**
     * 根据登录用户名查找是否有指定用户
     * @param email
     * @return
     */
    public User findById(String email) throws Exception;

    /**
     * 根据登录用户名匹配角色
     * @param userId
     * @return
     */
    public Set<String> findAllRoleByUser(Integer userId) throws Exception;

    /**
     * 根据登录用户名匹配权限
     * @param userId
     * @return
     */
    public Set<String> findAllActionByUser(Integer userId)throws Exception ;

    /**
     * 查找全部用户
     * @return
     * @throws Exception
     */
    public List<User> queryAllUser(Map<String, Object> map)throws Exception;

    /**
     * 统计数据量
     * @return
     * @throws Exception
     */
    public Integer queryAllCount(Map<String, Object> map)throws Exception;

    /**
     * 根据用户名查找指定用户信息
     * @param userId
     * @return
     * @throws Exception
     */
    public User findUserByUsername(Integer userId)throws Exception;


    /**
     * 根据邮箱查找用户信息
     * @param email
     * @return
     * @throws Exception
     */
    public User findUserByEmail(String email)throws Exception;

    /**
     * dataTable后端分页测试
     * @return
     */
    public List<User> findAllUserTest(Map<String, Object> map);

    /**
     * dataTable后端分页测试
     * @return
     */
    public Integer getAllCountTest();

    public boolean deleteTest(Integer id);


}
