package org.qydata.service;


import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.entity.Customer;
import org.qydata.tools.PageModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/11/8.
 */
public interface CustomerService {

    /**
     * 根据authId查找客户信息
     * @param authId 要查找客户的authId
     * @return 如果有数据，则数据以Customer对象的形式返回，如果没有返回空；
     */
    @SystemServiceLog(description = "根据authId查找客户信息")
    public Customer findCustomerByAuthId(String authId);

  /**
     * 查询当前登录着可见的客户信息并分页显示
     * @map 包含了要使用的数据
     * @return 如果有数据，则以List集合的形式返回，如果没有返回空（size==0）
     */
  @SystemServiceLog(description = "查询当前登陆者可见的客户信息")
    public PageModel<Customer> findAllCustomer(Map<String,Object> map)throws Exception;

    /**
     * 根据部门编号查找对应的customerId
     * @param deptIdList
     * @return
     */
    @SystemServiceLog(description = "根据部门编号查找对应的customerId")
    public List<Integer> findAllCustomerIdByDeptId(List<Integer> deptIdList);


    /**
     * 查询所有的客户账号Id
     * @return
     */
    @SystemServiceLog(description = "查询所有客户的账号id")
    public List<Integer> findAllCustomerId();

    /**
     * 查询客户请求的日志
     * @return
     */
    @SystemServiceLog(description = "查询客户请求的日志")
    public Map<String,Object> findAllCustomerRequestLog(Map<String,Object> map);

    /**
     * 根据Id查询日志
     * @param id
     * @return
     */
    @SystemServiceLog(description = "根据id查询日志")
    public String findCustomerRequestLogById(Integer id);

}
