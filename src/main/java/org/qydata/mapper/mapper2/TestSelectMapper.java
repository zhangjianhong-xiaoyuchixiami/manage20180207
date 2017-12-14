package org.qydata.mapper.mapper2;

import org.qydata.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/4/26.
 */
public interface TestSelectMapper {

    /**
     * dataTable后端分页附带额外查询条件
     * @param map
     * @return
     */
    public List<User> queryUserTest(Map<String, Object> map);

    /**
     * dataTable后端分页附带额外查询条件--得到总的数据量
     * @param map
     * @return
     */
    public Integer getAllUserCount(Map<String, Object> map);


    public List<Map<Object, Object>> testMap();

}
