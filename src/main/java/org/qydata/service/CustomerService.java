package org.qydata.service;


import org.qydata.entity.Customer;
import org.qydata.tools.PageModel;
import org.springframework.transaction.annotation.Transactional;

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
    public Customer findByAuthId(String authId);

    /**
     * 添加新客户
     * @param map 包含了要添加的数据
     * @return 插入成功返回true,否则返回false;
     */
    @Transactional
    public boolean insertCustomer(Map<String,Object> map)throws Exception ;
    /**
     * 查询当前登录着可见的客户信息并分页显示
     * @map 包含了要使用的数据
     * @return 如果有数据，则以List集合的形式返回，如果没有返回空（size==0）
     */
    public PageModel<Customer> findAllCustomer(Map<String,Object> map);



}
