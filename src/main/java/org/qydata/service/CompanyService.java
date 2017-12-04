package org.qydata.service;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.dst.ApiTypeSubType;
import org.qydata.dst.CustomerCompanyPartner;
import org.qydata.entity.*;

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
    @SystemServiceLog(description = "查询全部客户")
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
    @SystemServiceLog(description = "新增客户")
    public int addCompanyCustomer(String companyName,String authId,String partnerId,String apiTypeId_subId [],String price [],String begIp [],String endIp [])throws Exception;

    /**
     * 查找全部的合作公司
     * @return
     */
    @SystemServiceLog(description = "查找全部合作公司")
    public List<Partner> findAllPartner();

    /**
     * 查找全部的合作公司通过邮箱
     * @return
     * @throws Exception
     */
    @SystemServiceLog(description = "通过邮箱查找全部合作公司")
    public List<Partner> findPartnerByEmail(String email);


    /**
     * 查询充值或扣费理由
     * @param list
     * @return
     */
    @SystemServiceLog(description = "查询充值或扣费的理由")
    public List<CustomerBalanceModifyReason> findBalanceReason(List<Integer> list);

    /**
     * 修改账号余额
     * @param companyId
     * @param reason
     * @param amount
     * @return
     */
    @SystemServiceLog(description = "账号充值/扣费")
    public int updateCustomerBalance(Integer companyId,Integer reason,String amount)throws Exception;

    /**
     * 禁用账号
     * @param authId
     * @return
     */
    @SystemServiceLog(description = "账号禁用")
    public int updateCustomerBan(String authId)throws Exception;

    /**
     * 解禁账号
     * @param authId
     * @return
     */
    @SystemServiceLog(description = "账号解禁")
    public int updateCustomerUnBan(String authId)throws Exception;

    /**
     * 禁用公司
     * @param companyId
     * @return
     */
    @SystemServiceLog(description = "公司禁用")
    public Map<String,Object> updateCompanyBan(String [] companyId)throws Exception;

    /**
     * 解禁公司
     * @param companyId
     * @return
     */
    @SystemServiceLog(description = "公司解禁")
    public Map<String,Object> updateCompanyUnBan(String [] companyId)throws Exception;

    /**
     * 给正式账号添加Ip
     * @param begIp
     * @param endIp
     * @return
     */
    @SystemServiceLog(description = "正式账号添加IP")
    public int addCustomerIp(Integer companyId,String begIp,String endIp)throws Exception;

    /**
     * 根据公司Id查找公司已拥有权限的Api
     * @param map
     * @return
     */
    @SystemServiceLog(description = "根据公司Id查找公司已拥有权限的Api")
    public List<CompanyApi> queryCompanyApiByCompanyId(Map<String,Object> map);

    /**
     * 禁用产品权限
     * @param id
     * @return
     */
    @SystemServiceLog(description = "客户产品权限禁用")
    public int banCompanyApi(Integer companyId,Integer id)throws Exception;

    /**
     * 解禁产品权限
     * @param id
     * @return
     */
    @SystemServiceLog(description = "客户产品权限解禁")
    public int unBanCompanyApi(Integer companyId,Integer id)throws Exception;


    /**
     * 查询客户未拥有的产品权限列表,用于给客户分配新的权限
     * @param companyId
     * @return
     */
    @SystemServiceLog(description = "查询客户未拥有的产品权限列表")
    public List<ApiTypeSubType> queryNotHaveApi(Integer companyId);

    /**
     * 保存产品权限
     * @param companyId
     * @param apiTypeId
     * @param price
     * @return
     */
    @SystemServiceLog(description = "新增产品权限")
    public int addCompanyApi(Integer companyId,String apiTypeId,String price,String aid)throws Exception;

    /**
     * 修改下游产品价格
     * @return
     */
    @SystemServiceLog(description = "修改客户产品价格")
    public int updateCompanyApiPrice(Integer companyId,Integer tid,Integer stid,String pic)throws Exception;

    /**
     * 根据账号Id查找Ip
     * @param companyId
     * @return
     */
    @SystemServiceLog(description = "根据账号Id查找Ip")
    public List<CustomerIp> queryCustomerIpById(Integer companyId);

    /**
     * 根据Id删除Ip
     * @param id
     * @return
     */
    @SystemServiceLog(description = "根据账号删除Ip")
    public int deleteIpById(Integer companyId,Integer id) throws Exception;


    /**
     * 查询全部产品权限
     * @return
     */
    @SystemServiceLog(description = "查询全部产品权限")
    public List<ApiTypeSubType> queryAllApi();

    /**
     * 修改信用额度
     * @param companyId
     * @param credit
     * @return
     * @throws Exception
     */
    @SystemServiceLog(description = "修改信用额度")
    public int updateCredit(Integer companyId,Integer credit) throws Exception;

    /**
     * 根据产品类型查找统一产品类型的产品列表
     * @param tid_stid
     * @return
     */
    @SystemServiceLog(description = "根据产品类型查找统一产品类型的产品列表")
    public List<Api> queryApiByTypeId(String tid_stid);

    /**
     * 修改指定产品
     * @return
     */
    @SystemServiceLog(description = "修改指定产品")
    public int updateCompanyApiAppointApi(Integer companyId,Integer tid,Integer stid,String pic,Integer aid)throws Exception;

}
