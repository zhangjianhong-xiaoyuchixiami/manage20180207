package org.qydata.service.impl;

import org.qydata.dst.CustomerCompanyPartner;
import org.qydata.entity.*;
import org.qydata.mapper.CompanyMapper;
import org.qydata.mapper.CustomerDeptMapper;
import org.qydata.mapper.CustomerMapper;
import org.qydata.service.CompanyService;
import org.qydata.tools.HttpClient;
import org.qydata.tools.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/12/15.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired private CompanyMapper companyMapper;

    @Autowired  private CustomerDeptMapper customerDeptMapper;

    @Autowired  private CustomerMapper customerMapper;

    @Override
    public List<CustomerCompanyPartner> findAllCompany(Map<String, Object> map) {
        try {
            return companyMapper.findAllCompany(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addCompanyCustomer(String companyName,String authId,Integer partnerId) {
        try {
            Company company = new Company();
            company.setName(companyName.trim());
            company.setPartnerId(partnerId);
            companyMapper.addCompany(company);

            //向客户表中插入数据
            Customer customerB = new Customer();
            customerB.setAuthId(authId.trim() + "_test");
            customerB.setCompanyId(company.getId());
            customerMapper.insertCustomerTest(customerB);

            Customer customerA = new Customer();
            customerA.setAuthId(authId.trim());
            customerA.setCompanyId(company.getId());
            customerMapper.insertCustomer(customerA);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Partner> findAllPartner() {
        try {
            return companyMapper.findAllPartner();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addCustomer(String authId, Integer companyId) {
        try {
            Integer deptId = companyMapper.findDeptIdByCompanyId(companyId);
            //向客户表中插入数据
            Customer customerB = new Customer();
            customerB.setAuthId(authId.trim() + "_test");
            customerB.setCompanyId(companyId);
            customerMapper.insertCustomerTest(customerB);

            Customer customerA = new Customer();
            customerA.setAuthId(authId.trim());
            customerA.setCompanyId(companyId);
            customerMapper.insertCustomer(customerA);
            if (deptId != null){
                //向部门客户映射表中插入数据
                CustomerDept customerDeptA = new CustomerDept();
                customerDeptA.setCustomerId(customerA.getId());
                customerDeptA.setDeptId(deptId);
                customerDeptMapper.insertCustomerDept(customerDeptA);

                CustomerDept customerDeptB = new CustomerDept();
                customerDeptB.setCustomerId(customerB.getId());
                customerDeptB.setDeptId(deptId);
                customerDeptMapper.insertCustomerDept(customerDeptB);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CustomerBalanceModifyReason> findBalanceReason(List<Integer> list) {
        try {
            return companyMapper.findBalanceReason(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateCustomerBalance(Integer customerId, Integer reason, Long amount) {
        try {
            CustomerBalanceLog customerBalanceLog = new CustomerBalanceLog();
            customerBalanceLog.setCustomerId(customerId);
            customerBalanceLog.setReasonId(reason);
            Long balance = companyMapper.findCustomerBalanceByCustomerId(customerId);
            if (reason < 0){
                companyMapper.updateCustomerBalance(customerId, (balance-(amount*100)));
                customerBalanceLog.setAmount((0-amount*100));
                companyMapper.addCustomerBalanceLog(customerBalanceLog);
            }else {
                companyMapper.updateCustomerBalance(customerId, (balance+(amount*100)));
                customerBalanceLog.setAmount(amount*100);
                companyMapper.addCustomerBalanceLog(customerBalanceLog);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addCustomerIp(Integer customerId,String[] begIp, String[] endIp) {
        try {
            final String uri = "https://api.qydata.org:9000/customer/ip/add";
            for (int i = 0; i < begIp.length; i++){
                System.out.println(i+"------"+customerId);
                System.out.println(i+"------"+begIp[i]);
                System.out.println(i+"------"+endIp[i]);
                if (RegexUtil.isIp(begIp[i]) && RegexUtil.isIp(endIp[i])){
                    String json = "{'customerId':"+customerId+",'begIp':"+begIp[i]+",'endIp':"+endIp[i]+"}";
                    HttpClient.doPostSSL(uri, json);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CompanyApi> queryCompanyApiByCompanyId(Map<String, Object> map) {
        return companyMapper.queryCompanyApiByCompanyId(map);
    }

    @Override
    public boolean banCompanyApi(Integer id) {
        return companyMapper.banCompanyApi(id);
    }

    @Override
    public boolean unBanCompanyApi(Integer id) {
        return companyMapper.unBanCompanyApi(id);
    }

    @Override
    public List<CustomerIp> queryCustomerIpById(Integer customerId) {
        return companyMapper.queryCustomerIpById(customerId);
    }

    @Override
    public boolean deleteIpById(Integer id) {
        return companyMapper.deleteIpById(id);
    }

}
