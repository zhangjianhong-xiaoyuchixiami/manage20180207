package org.qydata.service.impl;


import org.qydata.entity.Customer;
import org.qydata.mapper.mapper1.CustomerDeptMapper;
import org.qydata.mapper.mapper1.CustomerMapper;
import org.qydata.mapper.mapper2.CustomerSelectMapper;
import org.qydata.service.CustomerService;
import org.qydata.tools.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/11/8.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerSelectMapper customerSelectMapper;

    @Autowired
    private CustomerDeptMapper customerDeptMapper;

    @Override
    public Customer findCustomerByAuthId(String authId) {
        return customerSelectMapper.findCustomerByAuthId(authId);
    }

    @Override
    public PageModel<Customer> findAllCustomer(Map<String,Object> map) throws Exception{
        PageModel<Customer> pageModel = new PageModel<Customer>();
        pageModel.setCount(customerSelectMapper.getAllCount(map));
        pageModel.setList(customerSelectMapper.findAllCustomer(map));
        return pageModel;
    }


    @Override
    public List<Integer> findAllCustomerIdByDeptId(List<Integer> deptIdList){
        try {
            return customerSelectMapper.findAllCustomerIdByDeptId(deptIdList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Integer> findAllCustomerId() {
        return customerSelectMapper.findAllCustomerId();
    }

    @Override
    public Map<String,Object> findAllCustomerRequestLog(Map<String, Object> map) {
        Map<String,Object> mapResult = new HashMap<>();
        try {
            mapResult.put("findAllCustomerRequestLog",customerSelectMapper.findAllCustomerRequestLog(map));
            mapResult.put("getCountAllCustomerRequestLog",customerSelectMapper.getCountAllCustomerRequestLog(map));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapResult;
    }

    @Override
    public String findCustomerRequestLogById(Integer id){
        try {
            return customerSelectMapper.findCustomerRequestLogById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
