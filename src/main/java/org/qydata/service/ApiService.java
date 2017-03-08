package org.qydata.service;

import org.qydata.entity.*;

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

    /**
     * 以客户纬度查询产品
     * @param map
     * @return
     */
    public List<CompanyApi> queryApiByCompanyId(Map<String,Object> map);

    /**
     * 查询所有公司
     * @return
     */
    public List<Company> queryCompany();

}
