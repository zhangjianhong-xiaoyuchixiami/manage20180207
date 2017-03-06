package org.qydata.mapper;

import org.qydata.entity.Api;
import org.qydata.entity.ApiType;
import org.qydata.entity.ApiVendor;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/2/28.
 */
public interface ApiMapper {

    /**
     * 查询产品
     * @return
     * @throws Exception
     */
    public List<Api> queryApi(Map<String,Object> map)throws Exception;

    /**
     * 查询产品类型
     * @return
     * @throws Exception
     */
    public List<ApiType> queryApiType()throws Exception;

    /**
     * 查询产品供应商
     * @return
     * @throws Exception
     */
    public List<ApiVendor> queryApiVendor()throws Exception;

    /**
     * 根据产品类型查询产品供应商
     * @return
     * @throws Exception
     */
    public List<ApiVendor> queryApiVendorByApiTypeId(Integer id)throws Exception;

}
