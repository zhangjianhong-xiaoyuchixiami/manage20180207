package org.qydata.mapper;

import org.qydata.dst.CustomerCompanyPartner;
import org.qydata.entity.*;

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
     * 查询充值或扣费理由
     * @param list
     * @return
     * @throws Exception
     */
    public List<CustomerBalanceModifyReason> findBalanceReason(List<Integer> list)throws Exception;

    /**
     *根据账号Id查询账号余额
     * @param customerId
     * @return
     * @throws Exception
     */
    public Long findCustomerBalanceByCustomerId(Integer customerId)throws Exception;

    /**
     * 修改账号余额
     * @param customerId
     * @param amount
     * @return
     * @throws Exception
     */
    public boolean updateCustomerBalance(Integer customerId,Long amount)throws Exception;

    /**
     * 添加余额变动日志
     * @param customerBalanceLog
     * @return
     * @throws Exception
     */
    public boolean addCustomerBalanceLog(CustomerBalanceLog customerBalanceLog)throws Exception;

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
