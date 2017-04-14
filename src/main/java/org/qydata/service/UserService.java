package org.qydata.service;

import org.qydata.entity.User;

import java.util.Map;

/**
 * Created by jonhn on 2016/11/23.
 */

public interface UserService {

    /**
     * 此方法是留给Realm进行用户认证使用的，目的是根据用户名取得密码数据
     * @param email
     * @return
     * @throws Exception
     */
    public User get(String email)  ;
    /**
     * 此方法是留给Realm实现授权处理的，主要要根据用户ID查询出所有的角色以及所有对应权限
     * @param userId
     * @return 返回的数据包含有两个内容：<br>
     * <li>key = allRoles、value = 所有的用户角色；</li>
     * <li>key = allActions、value = 所有的用户权限。</li>
     * @throws Exception
     */
    public Map<String,Object> listAuthByUser(Integer userId)  ;

}
