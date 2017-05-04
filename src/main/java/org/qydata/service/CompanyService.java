package org.qydata.service;

import org.qydata.dst.ApiTypeSubType;
import org.qydata.dst.CustomerCompanyPartner;
import org.qydata.entity.CompanyApi;
import org.qydata.entity.CustomerBalanceModifyReason;
import org.qydata.entity.CustomerIp;
import org.qydata.entity.Partner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/12/15.
 */
public interface CompanyService {

    /**
     * 查询全部客户
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerCompanyPartner> findAllCompany(Map<String,Object> map);


    /**
     * 新增客户和账户
     * @param companyName
     * @param authId
     * @param partnerId
     * @param apiTypeId_subId
     * @param price
     * @param begIp
     * @param endIp
     * @return
     */
    @Transactional
    public boolean addCompanyCustomer(String companyName,String authId,String partnerId,String apiTypeId_subId [],String price [],String begIp [],String endIp []);

    /**
     * 查找全部的合作公司
     * @return
     */
    public List<Partner> findAllPartner();

    /**
     * 添加账号
     * @param authId
     * @param companyId
     * @return
     */
    @Transactional
    public boolean addCustomer(String authId,Integer companyId);

    /**
     * 查询充值或扣费理由
     * @param list
     * @return
     */
    public List<CustomerBalanceModifyReason> findBalanceReason(List<Integer> list);

    /**
     * 修改账号余额
     * @param customerId
     * @param reason
     * @param amount
     * @return
     */
    public int updateCustomerBalance(Integer customerId,Integer reason,Long amount);

    /**
     * 禁用账号
     * @param authId
     * @return
     */
    public int updateCustomerBan(String authId);

    /**
     * 解禁账号
     * @param authId
     * @return
     */
    public int updateCustomerUnBan(String authId);

    /**
     * 禁用公司
     * @param companyId
     * @return
     */
    public Map<String,Object> updateCompanyBan(String [] companyId);

    /**
     * 解禁公司
     * @param companyId
     * @return
     */
    public Map<String,Object> updateCompanyUnBan(String [] companyId);

    /**
     * 给正式账号添加Ip
     * @param begIp
     * @param endIp
     * @return
     */
    public int addCustomerIp(Integer customerId,String begIp,String endIp);

    /**
     * 根据公司Id查找公司已拥有权限的Api
     * @param map
     * @return
     */
    public List<CompanyApi> queryCompanyApiByCompanyId(Map<String,Object> map);

    /**
     * 禁用产品权限
     * @param id
     * @return
     */
    public int banCompanyApi(Integer companyId,Integer id);

    /**
     * 解禁产品权限
     * @param id
     * @return
     */
    public boolean unBanCompanyApi(Integer id);

    /**
     * 查询可会未拥有的产品权限列表,用于给可会分配新的权限
     * @param companyId
     * @return
     */
    public List<ApiTypeSubType> queryNotHaveApi(Integer companyId);

    /**
     * 新增保存产品权限
     * @param companyId
     * @param apiTypeId
     * @param price
     * @return
     */
    public int addCompanyApi(Integer companyId,String apiTypeId,String price);

    /**
     * 根据账号Id查找Ip
     * @param customerId
     * @return
     */
    public List<CustomerIp> queryCustomerIpById(Integer customerId);

    /**
     * 根据Id删除Ip
     * @param id
     * @return
     */
    public int deleteIpById(Integer customerId,Integer id) throws Exception;


    /**
     * 查询全部产品权限
     * @return
     */
    public List<ApiTypeSubType> queryAllApi();

}
