package org.qydata.service;

import java.util.Map;

/**
 * Created by jonhn on 2017/4/26.
 */
public interface TestService {

    /**
     * dataTable后端分页附带额外查询条件
     * @return
     */
    public Map<String,Object> queryAllUserTest(Map<String,Object> map);

}
