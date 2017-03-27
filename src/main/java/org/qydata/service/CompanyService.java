package org.qydata.service;

import org.qydata.dst.CustomerCompanyPartner;
import org.qydata.entity.CustomerBalanceModifyReason;
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
     * @param deptId
     * @return
     */
    @Transactional
    public boolean addCompanyCustomer(String companyName,String authId,Integer partnerId,Integer deptId);

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



//    /**
//     * 查询供应商Api所有数据
//     * @return
//     */
//    public List<Api> findAllApi(String companyId)throws Exception;
//
//    /**
//     * 插入
//     * @param price
//     * @param companyId
//     * @param apiId
//     * @param enabled
//     * @return
//     */
//    @Transactional
//    public boolean insertCustomerApi(String companyId, String price, String apiId, String enabled)throws Exception;
//
//    /**
//     * 根据客户Id查找指定客户的所有CustomerApi
//     * @param map
//     * @return
//     */
//    public PageModel<CustomerApi> findAllCustomerApiByCompanyIdOne(Map<String,Object> map)throws Exception;
//    /**
//     * 根据Id查找
//     * @param apiId
//     * @return
//     */
//    public CustomerApi findById(String apiId,String companyId)throws Exception;
//    /**
//     * 根据Id修改
//     * @return
//     */
//    @Transactional
//    public boolean updateCustomerApiById(String beforApiId,String companyId,String price,String afterApiId,String enabled)throws Exception;
//
//    /**
//     * 根据companyId查询对应的CustomerApi
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public PageModel<CustomerApiInfo> findAllCustomerApiByCompanyId(Map<String,Object> map) throws Exception;


}
