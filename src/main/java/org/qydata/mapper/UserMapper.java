package org.qydata.mapper;

import org.qydata.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jonhn on 2016/11/23.
 */
public interface UserMapper {
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
     * 添加用户
     * @param user
     * @return
     * @throws Exception
     */
    public boolean addUser(User user)throws Exception;
    /**
     * Common添加用户
     * @param user
     * @return
     * @throws Exception
     */
    public boolean addUserCommon(User user)throws Exception;
    /**
     * 根据用户名和旧密码修改密码
     * @return
     * @throws Exception
     */
    public boolean updatePassword(Map<String,Object> map)throws Exception;

    /**
     * 根据用户Id重置密码
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean resetPassword(Integer userId,String password)throws Exception;

    /**
     * 查找全部用户
     * @return
     * @throws Exception
     */
    public List<User> queryAllUser(Map<String,Object> map)throws Exception;

    /**
     * 统计数据量
     * @return
     * @throws Exception
     */
    public Integer queryAllCount(Map<String,Object> map)throws Exception;

    /**
     * 根据用户名查找指定用户信息
     * @param userId
     * @return
     * @throws Exception
     */
    public User findUserByUsername(Integer userId)throws Exception;

    /**
     * 启动账号
     * @return
     * @throws Exception
     */
    public boolean updateStatusStart(Integer userId)throws Exception;

    /**
     *禁用账号
     * @return
     * @throws Exception
     */
    public boolean updateStatusforbid(Integer userId)throws Exception;

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
    public List<User> findAllUserTest(Map<String,Object> map);

    /**
     * dataTable后端分页测试
     * @return
     */
    public Integer getAllCountTest();

    public boolean deleteTest(Integer id);


}
