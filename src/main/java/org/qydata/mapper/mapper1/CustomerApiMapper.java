package org.qydata.mapper.mapper1;

import org.qydata.entity.Api;
import org.qydata.entity.CustomerApi;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/11/8.
 */
public interface CustomerApiMapper {

    /**
     * 插入一条客户Api
     * @param customerApiList
     * @return
     */
    public boolean insertCustomerApi(List<CustomerApi> customerApiList)throws Exception;


    /**
     * 根据Id修改
     * @param api
     * @return
     */
    public boolean updateCustomerApiById(CustomerApi api)throws Exception;

}
