package org.qydata.mapper.mapper2;

import org.qydata.dst.ApiTypeSubType;
import org.qydata.dst.CustomerCompanyPartner;
import org.qydata.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/12/15.
 */
public interface CompanySelectMapper {

    /**
     * 查找客户信息
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerCompanyPartner> findAllCompany(Map<String, Object> map)throws Exception;

    /**
     * 查找全部的合作公司
     * @return
     * @throws Exception
     */
    public List<Partner> findAllPartner()throws Exception;

    /**
     * 查找全部的合作公司通过邮箱
     * @return
     * @throws Exception
     */
    public List<Partner> findPartnerByEmail(String email);

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
     * 根据公司Id查找公司名称-用于禁用/解禁公司的操作提醒
     * @param companyId
     * @return
     * @throws Exception
     */
    public String queryCompanyNameByCompanyId(Integer companyId)throws Exception;

    /**
     * 根据公司Id查找公司已拥有权限的Api
     * @param map
     * @return
     */
    public List<CompanyApi> queryCompanyApiByCompanyId(Map<String, Object> map);

    /**
     * 查询全部产品权限
     * @return
     */
    public List<ApiTypeSubType> queryAllApi();

    /**
     * 根据账号Id查找Ip
     * @param customerId
     * @return
     */
    public List<CustomerIp> queryCustomerIpById(Integer customerId);


    /**
     *通过产品类型查找Id，用于修改产品价格
     * @param name
     * @return
     */
    public Integer queryApiTypeIdByName(String name);

    /**
     *通过产品子类型查找Id，用于修改产品价格
     * @param name
     * @return
     */
    public Integer queryStidByName(String name);

    /**
     * 获取访问api.data的key
     * @param key
     * @return
     */
    public String queryAuthKey(String key);

    /**
     * 根据公司Id查找正式账号Id，用于修改信用额度
     * @param companyId
     * @return
     */
    public Customer queryOfficAuthIdByCompanyId(Integer companyId);

    /**
     * 根据产品类型查找统一产品类型的产品列表
     * @param tid
     * @return
     */
    public List<Api> queryApiByTypeId(Integer tid);

}
