package org.qydata.service;

import org.qydata.dst.CustomerCacheApiTypeConsume;
import org.qydata.dst.CustomerCacheConsume;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/6/26.
 */
public interface CustomerCacheFinanceService {

    /**
     * 查询客户缓存调用情况
     * @param map
     * @return
     */
    public List<CustomerCacheConsume> queryCustomerCacheConsume(Map<String,Object> map);

    /**
     * 加载客户当天各产品类型的缓存数据
     * @param map
     * @return
     */
    public List<CustomerCacheApiTypeConsume> queryCustomerCurrDayApiTypeCacheConsume(Map<String,Object> map);

}
