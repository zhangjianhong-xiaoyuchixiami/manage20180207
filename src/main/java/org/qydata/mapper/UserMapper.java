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
     * @param username
     * @return
     */
    public User findById(String username) throws Exception;

    /**
     * 根据登录用户名匹配角色
     * @param username
     * @return
     */
    public Set<String> findAllRoleByUser(String username) throws Exception;

    /**
     * 根据登录用户名匹配权限
     * @param username
     * @return
     */
    public Set<String> findAllActionByUser(String username)throws Exception ;

    /**
     * 添加用户
     * @param user
     * @return
     * @throws Exception
     */
    public boolean addUser(User user)throws Exception;
    /**
     * 根据用户名和旧密码修改密码
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public boolean updatePassword(String username, String password, String newPassword)throws Exception;

    /**
     * 根据用户Id重置密码
     * @param username
     * @return
     * @throws Exception
     */
    public boolean resetPassword(String username)throws Exception;

    /**
     * 查找全部用户
     * @return
     * @throws Exception
     */
    public List<User> findAllUser(Map<String,Object> map)throws Exception;

    /**
     * 统计数据量
     * @return
     * @throws Exception
     */
    public Integer getAllCount(Map<String,Object> map)throws Exception;

    /**
     * 根据用户名查找指定用户信息
     * @param username
     * @return
     * @throws Exception
     */
    public User findUserByUsername(String username)throws Exception;

//
//    /**
//     * 启动账号
//     * @return
//     * @throws Exception
//     */
//    public boolean updateStatusStart(String loginName)throws Exception;
//
//    /**
//     *禁用账号
//     * @return
//     * @throws Exception
//     */
//    public boolean updateStatusforbid(String loginName)throws Exception;
//
//    /**
//     * 模糊搜索
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public List<User> findByColumn(Map<String,Object> map)throws Exception;
//
//    /**
//     * 模糊搜索总数据量
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public Integer getCountColumn(Map<String,Object> map)throws Exception;
//


}
