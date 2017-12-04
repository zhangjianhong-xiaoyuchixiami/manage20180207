package org.qydata.service;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.entity.Company;
import org.qydata.entity.CompanyApi;
import org.qydata.entity.CustomerHistoryBillUpdateLog;
import org.qydata.entity.Partner;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/7/31.
 */
public interface CustomerHistoryBillService {

    /**
     * 查询客户历史消费账单
     * @param map
     * @return
     */
    @SystemServiceLog(description = "查询客户历史消费账单")
    public Map<String,Object> queryCustomerHistoryBill(Map<String,Object> map);

    /**
     * 查询所有客户
     * @return
     */
    @SystemServiceLog(description = "查询所有客户")
    public List<Company> queryAllCompany();

    /**
     * 查询所有合作伙伴
     * @return
     */
    @SystemServiceLog(description = "查询所有合作伙伴")
    public List<Partner> queryAllPartner();

    /**
     * 查询消费的月份
     * @return
     */
    @SystemServiceLog(description = "查询消费月份")
    public List<String> queryAllConsumeTime();

    /**
     * 查询客户历史消费账单明细
     * @param map
     * @return
     */
    @SystemServiceLog(description = "查询客户历史消费账单明细")
    public Map<String,Object> queryCustomerHistoryBillDetail(Map<String,Object> map);

    /**
     * 修改单价
     * @param id
     * @return
     */
    @SystemServiceLog(description = "修改单价")
    public boolean updateCustomerHistoryBillCost(Integer id,Double oldCost,Double newCost,String content);

    /**
     * 修改扣费量
     * @param id
     * @return
     */
    @SystemServiceLog(description = "修改扣费量")
    public boolean updateCustomerHistoryBillAmount(Integer id,Integer oldAmount,Integer newAmount,String content);

    /**
     * 新增历史记录
     * @param cid
     * @return
     */
    @SystemServiceLog(description = "新增历史纪录")
    public boolean addCustomerHistoryBill(Integer cid,String tid,Double cost,Integer amount,String yearMonth);

    /**
     * 删除历史记录
     * @param id
     * @return
     */
    @SystemServiceLog(description = "删除历史纪录")
    public boolean deleteCustomerHistoryBill(String [] id);

    /**
     * 查询客户产品权限
     * @param cid
     * @return
     */
    @SystemServiceLog(description = "查询客户产品权限")
    public List<CompanyApi> queryCompanyApiByCompanyId(Integer cid);

    /**
     * 客户历史账单消费走势加载数据
     * @param map
     * @return
     */
    @SystemServiceLog(description = "客户历史账单消费走势加载数据")
    public Map<String,Object> queryCustomerHistoryBillTrendData(Map<String,Object> map);

    /**
     * 修改锁定/解锁状态
     * @param id
     * @param isLock
     * @return
     */
    @SystemServiceLog(description = "修改锁定/解锁状态")
    public boolean updateCustomerHistoryBillIsLock(String [] id,Integer isLock);

    /**
     * 根据Id查看锁定状态
     * @param id
     * @return
     */
    @SystemServiceLog(description = "根据Id查看锁定状态")
    public Integer queryCustomerHistoryBillLockById(Integer id);

    /**
     * 查看修改日志
     * @param map
     * @return
     */
    @SystemServiceLog(description = "查看修改日志")
    public List<CustomerHistoryBillUpdateLog> queryCustomerHistoryBillDetailUpdateLog(Map<String,Object> map);

}
