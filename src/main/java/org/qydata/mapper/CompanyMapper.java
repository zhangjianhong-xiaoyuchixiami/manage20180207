package org.qydata.mapper;

import org.qydata.dst.CustomerApiInfo;
import org.qydata.dst.CustomerCompanyPartner;
import org.qydata.entity.Api;
import org.qydata.entity.Company;
import org.qydata.entity.CustomerApi;
import org.qydata.entity.Partner;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/12/15.
 */
public interface CompanyMapper {

    /**
     * 查找客户信息
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerCompanyPartner> findAllCompany(Map<String,Object> map)throws Exception;

    /**
     * 查找客户信息
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerCompanyPartner> findAllCompanyByDeptId(Map<String,Object> map)throws Exception;

    /**
     * 新增客户
     * @param company
     * @return
     * @throws Exception
     */
    public boolean addCompany(Company company)throws Exception;

    /**
     * 查找全部的合作公司
     * @return
     * @throws Exception
     */
    public List<Partner> findAllPartner()throws Exception;

    /**
     * 根据公司Id查找部门编号
     * @param companyId
     * @return
     * @throws Exception
     */
    public Integer findDeptIdByCompanyId(Integer companyId)throws Exception;







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
     * 根据apiId和customerId修改
     * @param map
     * @return
     */
    public boolean updateCustomerApiById(Map<String,Object> map)throws Exception;

    /**
     * 根据apiId和customerId批量删除CustomerApi数据
     * @param map
     * @return
     * @throws Exception
     */
    public boolean removeCustomerApiByApiIdAndCustomerId(Map<String,Object> map)throws Exception;

    /**
     * 根据companyId查询对应的CustomerApi
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerApiInfo> findAllCustomerApiByCompanyId(Map<String,Object> map) throws Exception;

    /**
     * 根据companyId统计对应的CustomerApi的数据量
     * @param map
     * @return
     * @throws Exception
     */
    public Integer getAllCustomerApiCountByCompanyId(Map<String,Object> map)throws Exception;
}
