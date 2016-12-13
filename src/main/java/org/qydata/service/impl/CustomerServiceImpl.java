package org.qydata.service.impl;



import org.qydata.mapper.CustomerDeptUserMapper;
import org.qydata.mapper.CustomerMapper;
import org.qydata.entity.*;
import org.qydata.service.CustomerService;
import org.qydata.tools.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by jonhn on 2016/11/8.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerDeptUserMapper customerDeptUserMapper;



    @Override
    public Customer findByAuthId(String authId) {
        return customerMapper.findByAuthId(authId);
    }

    @Override
    public boolean insertCustomer(Map<String,Object> map)throws Exception {
        Set<Map.Entry<String,Object>> set = map.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        Customer customer = null;
        User user = null;
        Dept dept = null;
        while(it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if(me.getKey().equals("customerInfo")){
                customer = (Customer) me.getValue();
            }if(me.getKey().equals("userInfo") ){
                user = (User)me.getValue();
            }if(me.getKey().equals("deptInfo") ){
                dept = (Dept) me.getValue();
            }
        }
        //向客户表中插入数据
        Customer customerA = new Customer();
        customerA.setName(customer.getName().trim());
        customerA.setAuthId(customer.getAuthId().trim());
        customerMapper.insertCustomer(customerA);
        Customer customerB = new Customer();
        customerB.setAuthId(customer.getAuthId().trim() + "_test");
        customerB.setName(customer.getName().trim());
        customerMapper.insertCustomerTest(customerB);


        //向部门客户映射表中插入数据
        CustomerDeptUser customerDeptUser = new CustomerDeptUser();
        customerDeptUser.setCustomerId(customerA.getId());
        customerDeptUser.setDeptId(dept.getId());
        customerDeptUser.setCreateId(user.getId());
        customerDeptUserMapper.insertCustomerDeptUser(customerDeptUser);


        CustomerDeptUser customerDeptUser1 = new CustomerDeptUser();
        customerDeptUser1.setCustomerId(customerB.getId());
        customerDeptUser1.setDeptId(dept.getId());
        customerDeptUser1.setCreateId(user.getId());
        return customerDeptUserMapper.insertCustomerDeptUser(customerDeptUser1);
    }

    @Override
    public PageModel<Customer> findAllCustomer(Map<String,Object> map) {
        PageModel<Customer> pageModel = new PageModel<Customer>();
        pageModel.setCount(customerMapper.getAllCount(map));
        pageModel.setList(customerMapper.findAllCustomer(map));
        return pageModel;
    }
}
