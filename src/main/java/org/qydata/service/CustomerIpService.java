package org.qydata.service;

import org.qydata.entity.CustomerIp;
import org.qydata.tools.PageModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by jonhn on 2016/11/8.
 */
public interface CustomerIpService {

    /**
     * 插入Ip
     * @param beginIp
     * @param endIp
     * @param customerId
     * @return
     */
    @Transactional
    public boolean insertCustomerIp(String beginIp,String endIp,String customerId)throws Exception;


    /**
     * 查询客户Ip,并分页显示
     * @param map
     * @return
     */
    public PageModel<CustomerIp> findAllIpByCustomerId(Map<String,Object> map);
    /**
     * 根据Id删除Ip
     * @param id
     * @return
     */
    public boolean deleteIpById(Integer id)throws Exception;




}
