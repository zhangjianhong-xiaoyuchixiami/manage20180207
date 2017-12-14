package org.qydata.mapper.mapper1;


import org.qydata.entity.Customer;
import org.qydata.entity.CustomerRequestLog;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/11/8.
 */
public interface CustomerMapper {

    /**
     * 添加新客户--真实
     * @param vo 包含了要添加数据的Customer类对象
     * @return 插入成功返回true,否则返回false;
     */
    public boolean insertCustomer(Customer vo)throws Exception;
    /**
     * 添加新客户--测试
     * @param vo 包含了要添加数据的Customer类对象
     * @return 插入成功返回true,否则返回false;
     */
    public boolean insertCustomerTest(Customer vo)throws Exception;

}
