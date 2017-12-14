package org.qydata.mapper.mapper2;

import org.qydata.entity.User;
import org.qydata.entity.log.Log;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/3/22.
 */
public interface LogSelectMapper {

    /**
     * 查询日志
     * @param map
     * @return
     */
    public List<Log> queryLog(Map<String, Object> map);

    /**
     * 查询用户，用于日志界面搜索操作人
     * @return
     */
    public List<User> queryUser();
}
