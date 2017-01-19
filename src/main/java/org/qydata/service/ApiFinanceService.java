package org.qydata.service;

import org.qydata.dst.ApiFinance;
import org.qydata.entity.ApiRequestLog;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/17.
 */
public interface ApiFinanceService {

    /**
     *Api财务总览
     * @param map
     * @return
     * @throws Exception
     */
    public List<ApiFinance> queryApiOverAllFinance(Map<String,Object> map);

    /**
     * 查询供应商和api
     * @param map
     * @return
     * @throws Exception
     */
    public List<ApiFinance> queryApiVendorAndApi(Map<String,Object> map);

    /**
     *查询Api消费详情
     * @param map
     * @return
     * @throws Exception
     */
    public List<ApiRequestLog> queryApiDetailById(Map<String,Object> map);
}
