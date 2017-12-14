package org.qydata.mapper.mapper1;

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
     * 根据Id删除Ip
     * @param id
     * @return
     */
    public boolean deleteIpById(Integer id)throws Exception;

}
