package org.qydata.service;

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
     * @return
     */
    @Transactional
    public boolean addCompanyCustomer(String companyName,String authId,Integer partnerId);

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
     * 修改账号余额，同时添加日志
     * @param customerId
     * @param reason
     * @param amount
     * @return
     */
    @Transactional
    public boolean updateCustomerBalance(Integer customerId,Integer reason,Long amount);

    /**
     * 给正式账号添加Ip
     * @param begIp
     * @param endIp
     * @return
     */
    public boolean addCustomerIp(Integer customerId,String [] begIp,String [] endIp);

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
    public boolean banCompanyApi(Integer id);

    /**
     * 解禁产品权限
     * @param id
     * @return
     */
    public boolean unBanCompanyApi(Integer id);

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
    public boolean deleteIpById(Integer id);

}
