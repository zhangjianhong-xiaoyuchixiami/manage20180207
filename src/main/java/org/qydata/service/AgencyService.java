package org.qydata.service;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.entity.CompanyApi;
import org.qydata.entity.agency.AgencyCustomer;
import org.qydata.entity.agency.RebateAgency;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/9/14.
 */
public interface AgencyService {

    /**
     * 查询代理人
     * @return
     */
    @SystemServiceLog(description = "查询代理人")
    public List<RebateAgency> queryAgency();

    /**
     * 根据代理人Id查找返佣规则
     * @param id
     * @return
     */
    @SystemServiceLog(description = "根据代理人id查询返佣规则")
    public String queryRebateRuleById(Integer id);

    /**
     * 查询代理人详单
     * @param map
     * @return
     */
    @SystemServiceLog(description = "查询代理人详单")
    public Map<String,Object> queryAgencyBill(Map<String,Object> map);


    /**
     *查询代理的客户列表
     * @return
     */
    @SystemServiceLog(description = "查询代理人客户列表")
    public List<AgencyCustomer> queryAgencyCustomer(Map<String,Object> map);

    /**
     *查询周期
     * @return
     */
    @SystemServiceLog(description = "查询周期")
    public List<String> queryConsumeCycle();

    /**
     * 查询产品列表
     * @return
     */
    @SystemServiceLog(description = "查询产品列表")
    public List<CompanyApi> queryConsumeApiType();

    /**
     * 修改扣费量
     * @param id
     * @param amount
     * @return
     */
    @SystemServiceLog(description = "修改扣费量")
    public boolean updateRebateBillAmount(Integer id,Integer amount);

    /**
     *修改单价
     * @param id
     * @param cost
     * @return
     */
    @SystemServiceLog(description = "修改单价")
    public boolean updateRebateBillCost(Integer id,Double cost);

    /**
     *修改售价
     * @param id
     * @param price
     * @return
     */
    @SystemServiceLog(description = "修改售价")
    public boolean updateRebateBillPrice(Integer id,Double price);

    /**
     *修改返佣起始价
     * @param id
     * @param price
     * @return
     */
    @SystemServiceLog(description = "修改返佣起始价")
    public boolean updateRebateBillBeginPrice(Integer id,Double price);

    /**
     *修改返佣结算价
     * @param id
     * @param price
     * @return
     */
    @SystemServiceLog(description = "修改返佣结算价")
    public boolean updateRebateBillEndPrice(Integer id,Double price);

    /**
     * 删除记录
     * @param id
     * @return
     */
    @SystemServiceLog(description = "删除记录")
    public boolean deleteRebateDetail(String [] id);

    /**
     * 修改缓存售价
     * @param id
     * @param price
     * @return
     */
    @SystemServiceLog(description = "修改缓存售价")
    public boolean updateCachePrice(Integer id,Double price);

    /**
     * 修改缓存扣费量
     * @param id
     * @param count
     * @return
     */
    @SystemServiceLog(description = "修改缓存扣费量")
    public boolean updateCacheCount(Integer id,Integer count);

    /**
     * 删除缓存记录
     * @param id
     * @return
     */
    @SystemServiceLog(description = "删除缓存记录")
    public boolean deleteCacheDetail(String [] id);


}
