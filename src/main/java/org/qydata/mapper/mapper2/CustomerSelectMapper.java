package org.qydata.mapper.mapper2;


import org.qydata.entity.Customer;
import org.qydata.entity.CustomerRequestLog;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/11/8.
 */
public interface CustomerSelectMapper {

    /**
     * 根据authId查找客户信息
     * @param authId 要查找客户的authId
     * @return 如果有数据，则数据以Customer对象的形式返回，如果没有返回空；
     */
    public Customer findCustomerByAuthId(String authId);

    /**
     * 查询当前登录着可见的客户信息并分页显示
     * @param map 包含了筛选条件数据
     * @return 如果有数据，则以List集合的形式返回，如果没有返回空（size==0）
     */
    public List<Customer> findAllCustomer(Map<String, Object> map)throws Exception;
    /**
     * 获取总的数据量
     * @param map 包含了筛选条件数据
     * @return
     */
    public Integer getAllCount(Map<String, Object> map)throws Exception;

    /**
     * 根据客户Id查找对应的部门Id
     * @param companyId
     * @return
     * @throws Exception
     */
    public Integer findDeptIdByCompanyId(Integer companyId)throws Exception;

    /**
     * 根据部门编号查找对应的customerId
     * @param deptIdList
     * @return
     * @throws Exception
     */
    public List<Integer> findAllCustomerIdByDeptId(List<Integer> deptIdList)throws Exception;

    /**
     * 查询所有的客户账号Id
     * @return
     */
    public List<Integer> findAllCustomerId();

    /**
     * 查询客户请求的日志
     * @return
     * @throws Exception
     */
    public List<CustomerRequestLog> findAllCustomerRequestLog(Map<String, Object> map)throws Exception;

    /**
     * 统计客户请求日志的数据量
     * @param map
     * @return
     * @throws Exception
     */
    public Integer getCountAllCustomerRequestLog(Map<String, Object> map)throws Exception;


    public String findCustomerRequestLogById(Integer id)throws Exception;

}
