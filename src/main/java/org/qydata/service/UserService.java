package org.qydata.service;

import org.qydata.entity.User;
import org.qydata.tools.PageModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by jonhn on 2016/11/23.
 */

public interface UserService {

    /**
     * 此方法是留给Realm进行用户认证使用的，目的是根据用户名取得密码数据
     * @param username
     * @return
     * @throws Exception
     */
    public User get(String username) throws Exception ;
    /**
     * 此方法是留给Realm实现授权处理的，主要要根据用户ID查询出所有的角色以及所有对应权限
     * @param username
     * @return 返回的数据包含有两个内容：<br>
     * <li>key = allRoles、value = 所有的用户角色；</li>
     * <li>key = allActions、value = 所有的用户权限。</li>
     * @throws Exception
     */
    public Map<String,Object> listAuthByUser(String username) throws Exception ;

    /**
     * 添加管理员
     * @param user
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean addUser(User user)throws Exception;
    /**
     * 根据用户名和旧密码修改密码
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean updatePassword(String username, String password, String newPassword)throws Exception;

    /**
     * 根据用户Id重置密码
     * @param username
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean resetPassword(String username)throws Exception;

    /**
     * 查找全部用户
     * @param map
     * @return
     * @throws Exception
     */
    public PageModel<User> findAllUser(Map<String,Object> map)throws Exception;
    /**
     * 根据用户名查找指定用户信息
     * @param username
     * @return
     * @throws Exception
     */
    public User findUserByUsername(String username)throws Exception;

    /**
     * 启用
     * @param username
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean updateStatusStart(String username)throws Exception;

    /**
     * 禁用
     * @param username
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean updateStatusForbid(String username)throws Exception;


}
