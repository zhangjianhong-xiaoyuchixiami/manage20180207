package org.qydata.mapper;

import org.qydata.entity.CustomerApi;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/5.
 */
public interface CustomerRequestLogMapper {

    /**
     * 根据账号Id统计消费Api的价格
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerApi> findCustomerApiPriceByCustomerIdAndApiTypeId(Map<String,Object> map)throws Exception;


}
