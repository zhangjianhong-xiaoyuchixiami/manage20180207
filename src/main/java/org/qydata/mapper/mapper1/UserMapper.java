package org.qydata.mapper.mapper1;

import org.qydata.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jonhn on 2016/11/23.
 */
public interface UserMapper {

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
    public boolean updatePassword(Map<String, Object> map)throws Exception;

    /**
     * 根据用户Id重置密码
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean resetPassword(Integer userId, String password)throws Exception;

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

    public boolean deleteTest(Integer id);


}
