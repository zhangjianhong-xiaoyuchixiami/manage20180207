package org.qydata.service;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.dst.customer.CustomerReqLog;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/11/16.
 */
public interface SearchService {

    /**
     * 查询客户请求log
     * @param map
     * @return
     */
    @SystemServiceLog(description = "查询客户请求log")
    public List<CustomerReqLog> queryCustomerReqLog(Map<String,Object> map);



}
