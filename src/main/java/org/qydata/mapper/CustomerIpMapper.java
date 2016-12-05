package org.qydata.mapper;

import org.qydata.entity.CustomerIp;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/11/8.
 */
public interface CustomerIpMapper {


    /**
     * 插入Ip
     * @param customerIp
     * @return
     */
    public boolean insertCustomerIp(CustomerIp customerIp)throws Exception;

    /**
     * 查询客户Ip
     * @param map
     * @return
     */
    public List<CustomerIp> findAllIpByCustomerId(Map<String,Object> map);

    /**
     * 根据客户Id获取总数据量
     * @param map
     * @return
     */
    public Integer getAllCountByCustomerId(Map<String,Object> map);

    /**
     * 根据Id删除Ip
     * @param id
     * @return
     */
    public boolean deleteIpById(Integer id)throws Exception;

}
