package org.qydata.mapper.mapper2;

import org.qydata.entity.Api;
import org.qydata.entity.CustomerApi;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/11/8.
 */
public interface CustomerApiSelectMapper {
    /**
     * 查询非mobileApi所有数据
     * @return
     */
    public List<Api> findAllApiNotMobile(Map<String, Object> map)throws Exception;

    /**
     * 查询mobileApi所有数据
     * @param map
     * @return
     * @throws Exception
     */
    public List<Api> findAllApiMobile(Map<String, Object> map)throws Exception;

    /**
     * 根据客户Id查找指定客户的所有CustomerApiNotMobile
     * @param map
     * @return
     */
    public List<CustomerApi> findAllByCustomerIdNotMobile(Map<String, Object> map)throws Exception;
    /**
     * 根据客户Id查找指定客户的所有CustomerApiMobile
     * @param map
     * @return
     */
    public List<CustomerApi> findAllByCustomerIdMobile(Map<String, Object> map)throws Exception;

    /**
     * 根据客户Id统计全部数据量
     * @param map
     * @return
     */
    public Integer getAllCountByCustomerId(Map<String, Object> map)throws Exception;

    /**
     * 根据Id查找NotMobile
     * @param id
     * @return
     */
    public CustomerApi findByIdNotMobile(Integer id)throws Exception;
    /**
     * 根据Id查找Mobile
     * @param id
     * @return
     */
    public CustomerApi findByIdMobile(Integer id)throws Exception;

    public List<Api> apiList();

    public List<Api> apiList1();


}
