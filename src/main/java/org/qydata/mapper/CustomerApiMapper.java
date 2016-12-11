package org.qydata.mapper;

import org.qydata.entity.Api;
import org.qydata.entity.CustomerApi;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/11/8.
 */
public interface CustomerApiMapper {
    /**
     * 查询Api所有数据
     * @return
     */
    public List<Api> findAllApi(Map<String,Object> map)throws Exception;

    /**
     * 插入一条客户Api
     * @param customerApi
     * @return
     */
    public boolean insertCustomerApi(CustomerApi customerApi)throws Exception;

    /**
     * 根据客户Id查找指定客户的所有CustomerApi
     * @param map
     * @return
     */
    public List<CustomerApi> findAllByCustomerId(Map<String,Object> map)throws Exception;

    /**
     * 根据客户Id统计全部数据量
     * @param map
     * @return
     */
    public Integer getAllCountByCustomerId(Map<String,Object> map)throws Exception;

    /**
     * 根据Id查找
     * @param id
     * @return
     */
    public CustomerApi findById(Integer id)throws Exception;

    /**
     * 根据Id修改
     * @param api
     * @return
     */
    public boolean updateCustomerApiById(CustomerApi api)throws Exception;


}
