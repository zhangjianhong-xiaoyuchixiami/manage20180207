package org.qydata.mapper;

import org.qydata.entity.log.Log;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/3/22.
 */
public interface LogMapper {

    /**
     * 新增日志
     * @param log
     * @return
     */
    public int createLog(Log log);

    /**
     * 修改日志
     * @param log
     * @return
     */
    public int updateLog(Log log);

    /**
     * 查询日志
     * @param map
     * @return
     */
    public List<Log> queryLog(Map<String,Object> map);
}
