package org.qydata.service;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.entity.User;
import org.qydata.entity.log.Log;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/3/22.
 */
public interface LogService {

    /**
     * 新增日志
     * @param log
     * @return
     */
    @SystemServiceLog(description = "新增日志")
    public boolean createLog(Log log);

    /**
     * 修改日志
     * @param log
     * @return
     */
    @SystemServiceLog(description = "修改日志")
    public boolean updateLog(Log log);

    /**
     * 查询日志
     * @param map
     * @return
     */
    @SystemServiceLog(description = "查询日志")
    public List<Log> queryLog(Map<String,Object> map);

    /**
     * 查询用户，用于日志界面搜索操作人
     * @return
     */
    @SystemServiceLog(description = "日志里搜索操作人")
    public List<User> queryUser();

}
