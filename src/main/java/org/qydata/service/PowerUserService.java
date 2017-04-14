package org.qydata.service;

import org.qydata.entity.User;
import org.qydata.tools.PageModel;

import java.util.Map;

/**
 * Created by jonhn on 2017/4/14.
 */
public interface PowerUserService {

    /**
     * 查找全部用户
     * @param map
     * @return
     * @throws Exception
     */
    public PageModel<User> queryAllUser(Map<String,Object> map)throws Exception;
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
     */
    public User findUserByEmail(String email);

}
