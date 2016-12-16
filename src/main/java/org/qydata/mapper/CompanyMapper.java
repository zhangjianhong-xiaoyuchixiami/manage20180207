package org.qydata.mapper;

import org.qydata.entity.Api;
import org.qydata.entity.Company;
import org.qydata.entity.Customer;
import org.qydata.entity.CustomerApi;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/12/15.
 */
public interface CompanyMapper {
    /**
     * 新增客户
     * @param company
     * @return
     * @throws Exception
     */
    public boolean addCompany(Company company)throws Exception;

    /**
     * 根据Id查找指定客户信息
     * @param id
     * @return
     * @throws Exception
     */
    public Company findById(Integer id)throws Exception;

    /**
     * 根据条件查找客户信息
     * @param map
     * @return
     * @throws Exception
     */
    public List<Company> findAllCompany(Map<String,Object> map)throws Exception;

    /**
     * 根据条件统计数据量
     * @param map
     * @return
     * @throws Exception
     */
    public Integer getAllCountCompany(Map<String,Object> map)throws Exception;

    /**
     * 根据客户公司Id查找全部的账号
     * @param companyId
     * @return
     * @throws Exception
     */
    public List<Customer> findAllCustomerByCompanyId(Integer companyId)throws Exception;

    /**
     * 根据客户公司Id查找全部的账号
     * @param map
     * @return
     * @throws Exception
     */
    public List<Customer> findAllCustomerAccountByCompanyId(Map<String,Object> map)throws Exception;

    /**
     * 根据客户公司Id统计全部的账号的数据量
     * @param map
     * @return
     * @throws Exception
     */
    public Integer getAllCustomerAccountByCompanyId(Map<String,Object> map)throws Exception;

    /**
     * 查询非mobileApi所有数据
     * @return
     */
    public List<Api> findAllApiNotMobile(Map<String,Object> map)throws Exception;

    /**
     * 查询mobileApi所有数据
     * @param map
     * @return
     * @throws Exception
     */
    public List<Api> findAllApiMobile(Map<String,Object> map)throws Exception;

    /**
     * 根据客户Id查找指定客户的所有CustomerApiNotMobile
     * @param map
     * @return
     */
    public List<CustomerApi> findAllByCustomerIdNotMobile(Map<String,Object> map)throws Exception;
    /**
     * 根据客户Id查找指定客户的所有CustomerApiMobile
     * @param map
     * @return
     */
    public List<CustomerApi> findAllByCustomerIdMobile(Map<String,Object> map)throws Exception;

    /**
     * 根据客户Id统计全部数据量
     * @param map
     * @return
     */
    public Integer getAllCountByCustomerId(Map<String,Object> map)throws Exception;

    /**
     * 根据账号Id查找指定账号的所有CustomerApi
     * @param customerId
     * @return
     */
    public List<Integer> findAllCustomerApiByOnlyOneCustomerId(Integer customerId)throws Exception;


    /**
     * 根据Id查找NotMobile
     * @param map
     * @return
     */
    public List<CustomerApi> findByIdNotMobile(Map<String,Object> map)throws Exception;
    /**
     * 根据Id查找Mobile
     * @param map
     * @return
     */
    public List<CustomerApi> findByIdMobile(Map<String,Object> map)throws Exception;

    /**
     * 根据Id修改
     * @param map
     * @return
     */
    public boolean updateCustomerApiById(Map<String,Object> map)throws Exception;
}
