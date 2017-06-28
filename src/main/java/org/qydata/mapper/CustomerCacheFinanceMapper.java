package org.qydata.mapper;

import org.qydata.dst.CustomerCacheApiTypeConsume;
import org.qydata.dst.CustomerCacheConsume;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/6/26.
 */
public interface CustomerCacheFinanceMapper {


    /**
     * 查询客户缓存调用情况
     * @param map
     * @return
     */
    public List<CustomerCacheConsume> queryCustomerCacheConsume(Map<String,Object> map);

    /**
     * 查询客户当月缓存调用情况
     * @param map
     * @return
     */
    public List<CustomerCacheConsume> queryCustomerCacheConsumeByCurrMonth(Map<String,Object> map);


    /**
     * 查询客户当天缓存调用情况
     * @param map
     * @return
     */
    public List<CustomerCacheConsume> queryCustomerCacheConsumeByCurrDay(Map<String,Object> map);

    /**
     * 查询客户各产品类型缓存调用情况（开通后至昨天）
     * @param map
     * @return
     */
    public List<CustomerCacheConsume> queryCustomerApiTypeCacheConsume(Map<String,Object> map);


    /**
     * 查询客户各产品类型缓存调用情况（今天）
     * @param map
     * @return
     */
    public List<CustomerCacheConsume> queryCustomerApiTypeCacheConsumeCurrDay(Map<String,Object> map);

    /**
     * 统计客户缓存调用总数（开通后至昨天）
     * @param map
     * @return
     */
    public List<CustomerCacheConsume> queryCustomerTotleCacheConsume(Map<String,Object> map);

    /**
     * 加载客户当天各产品类型的缓存数据（点击显示列表）
     * @param map
     * @return
     */
    public List<CustomerCacheApiTypeConsume> queryCustomerCurrDayApiTypeCacheConsume(Map<String,Object> map);


}
