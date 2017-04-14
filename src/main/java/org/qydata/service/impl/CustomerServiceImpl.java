package org.qydata.service.impl;


import org.qydata.config.annotation.DataSourceService;
import org.qydata.entity.Customer;
import org.qydata.entity.CustomerDept;
import org.qydata.mapper.CustomerDeptMapper;
import org.qydata.mapper.CustomerMapper;
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
    private CustomerDeptMapper customerDeptMapper;

    @Override
    @DataSourceService
    public Customer findCustomerByAuthId(String authId) {
        return customerMapper.findCustomerByAuthId(authId);
    }

    @Override
    @DataSourceService
    public boolean insertCustomer(String companyId, String authId, String deptId)throws Exception {

        //向客户表中插入数据
        Customer customerB = new Customer();
        customerB.setAuthId(authId.trim() + "_test");
        customerB.setCompanyId(Integer.parseInt(companyId));
        customerMapper.insertCustomerTest(customerB);

        Customer customerA = new Customer();
        customerA.setAuthId(authId.trim());
        customerA.setCompanyId(Integer.parseInt(companyId));
        customerMapper.insertCustomer(customerA);

        //向部门客户映射表中插入数据
        CustomerDept customerDeptA = new CustomerDept();
        customerDeptA.setCustomerId(customerA.getId());
        customerDeptA.setDeptId(Integer.parseInt(deptId.trim()));
        customerDeptMapper.insertCustomerDept(customerDeptA);

        CustomerDept customerDeptB = new CustomerDept();
        customerDeptB.setCustomerId(customerB.getId());
        customerDeptB.setDeptId(Integer.parseInt(deptId.trim()));
        return customerDeptMapper.insertCustomerDept(customerDeptB);
    }

    @Override
    @DataSourceService
    public PageModel<Customer> findAllCustomer(Map<String,Object> map) throws Exception{
        PageModel<Customer> pageModel = new PageModel<Customer>();
        pageModel.setCount(customerMapper.getAllCount(map));
        pageModel.setList(customerMapper.findAllCustomer(map));
        return pageModel;
    }

    @Override
    @DataSourceService
    public boolean insertCustomerAccount(String companyId, String authId) throws Exception {

        Integer deptId = customerMapper.findDeptIdByCompanyId(Integer.parseInt(companyId));
        //向客户表中插入数据
        Customer customerA = new Customer();
        customerA.setAuthId(authId.trim());
        customerA.setCompanyId(Integer.parseInt(companyId));
        customerMapper.insertCustomer(customerA);

        Customer customerB = new Customer();
        customerB.setAuthId(authId.trim() + "_test");
        customerB.setCompanyId(Integer.parseInt(companyId));
        customerMapper.insertCustomerTest(customerB);

        //向部门客户映射表中插入数据
        CustomerDept customerDeptA = new CustomerDept();
        customerDeptA.setCustomerId(customerA.getId());
        customerDeptA.setDeptId(deptId);
        customerDeptMapper.insertCustomerDept(customerDeptA);

        CustomerDept customerDeptB = new CustomerDept();
        customerDeptB.setCustomerId(customerB.getId());
        customerDeptB.setDeptId(deptId);
        return customerDeptMapper.insertCustomerDept(customerDeptB);
    }

    @Override
    @DataSourceService
    public List<Integer> findAllCustomerIdByDeptId(List<Integer> deptIdList){
        try {
            return customerMapper.findAllCustomerIdByDeptId(deptIdList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @DataSourceService
    public List<Integer> findAllCustomerId() {
        return customerMapper.findAllCustomerId();
    }

    @Override
    @DataSourceService
    public Map<String,Object> findAllCustomerRequestLog(Map<String, Object> map) {
        Map<String,Object> mapResult = new HashMap<>();
        try {
            mapResult.put("findAllCustomerRequestLog",customerMapper.findAllCustomerRequestLog(map));
            mapResult.put("getCountAllCustomerRequestLog",customerMapper.getCountAllCustomerRequestLog(map));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapResult;
    }

    @Override
    @DataSourceService
    public String findCustomerRequestLogById(Integer id){
        try {
            return customerMapper.findCustomerRequestLogById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
