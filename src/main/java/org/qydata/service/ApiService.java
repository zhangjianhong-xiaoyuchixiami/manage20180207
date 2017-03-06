package org.qydata.service;

import org.qydata.entity.Api;
import org.qydata.entity.ApiType;
import org.qydata.entity.ApiVendor;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/2/28.
 */
public interface ApiService {

    /**
     * 查询产品
     * @return
     */
    public List<Api> queryApi(Map<String,Object> map);

    /**
     * 查询产品类型
     * @return
     */
    public List<ApiType> queryApiType();

    /**
     * 根据产品类型查询产品供应商
     * @return
     */
    public List<ApiVendor> queryApiVendor();

    /**
     * 根据产品类型查询产品供应商
     * @return
     */
    public List<ApiVendor> queryApiVendorByApiTypeId(Integer id);

}
