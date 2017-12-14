package org.qydata.mapper.mapper2;

import org.qydata.entity.CustomerIp;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/11/8.
 */
public interface CustomerIpSelectMapper {

    /**
     * 查询客户Ip
     * @param map
     * @return
     */
    public List<CustomerIp> findAllIpByCustomerId(Map<String, Object> map);

    /**
     * 根据客户Id获取总数据量
     * @param map
     * @return
     */
    public Integer getAllCountByCustomerId(Map<String, Object> map);


}
