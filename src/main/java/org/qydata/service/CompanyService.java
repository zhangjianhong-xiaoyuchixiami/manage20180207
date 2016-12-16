package org.qydata.service;

import org.qydata.entity.Api;
import org.qydata.entity.Company;
import org.qydata.entity.Customer;
import org.qydata.entity.CustomerApi;
import org.qydata.tools.PageModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/12/15.
 */
public interface CompanyService {


    /**
     * 根据Id查找指定的客户信息
     * @param id
     * @return
     * @throws Exception
     */
    public Company findById(String id)throws Exception;

    /**
     * 查询全部客户并分页显示
     * @param map
     * @return
     * @throws Exception
     */
    public PageModel<Company> findAllCompany(Map<String,Object> map)throws Exception;

    /**
     * 根据客户公司Id查找全部的账号
     * @return
     * @throws Exception
     */
    public PageModel<Customer> findAllCustomerAccountByCompanyId(Map<String,Object> map)throws Exception;

    /**
     * 新增客户和账户
     * @param name
     * @param authId
     * @param deptId
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean addCompanyAndCustomer(String name,String authId,String deptId)throws Exception;
    /**
     * 查询供应商Api所有数据
     * @return
     */
    public List<Api> findAllApi(String companyId)throws Exception;

    /**
     * 插入
     * @param price
     * @param companyId
     * @param apiId
     * @param enabled
     * @return
     */
    @Transactional
    public boolean insertCustomerApi(String companyId, String price, String apiId, String enabled)throws Exception;

    /**
     * 根据客户Id查找指定客户的所有CustomerApi
     * @param map
     * @return
     */
    public PageModel<CustomerApi> findAllCustomerApiByCompanyId(Map<String,Object> map)throws Exception;
    /**
     * 根据Id查找
     * @param apiId
     * @return
     */
    public CustomerApi findById(String apiId,String companyId)throws Exception;
    /**
     * 根据Id修改
     * @return
     */
    @Transactional
    public boolean updateCustomerApiById(String companyId,String price,String apiId,String enabled)throws Exception;
}
