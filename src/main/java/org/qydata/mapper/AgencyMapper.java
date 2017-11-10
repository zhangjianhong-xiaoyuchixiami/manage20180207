package org.qydata.mapper;

import org.qydata.entity.CompanyApi;
import org.qydata.entity.agency.AgencyBillDetail;
import org.qydata.entity.agency.AgencyCustomer;
import org.qydata.entity.agency.RebateAgency;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/9/14.
 */
public interface AgencyMapper {

    /**
     * 查询代理人
     * @return
     */
    public List<RebateAgency> queryAgency();

    /**
     * 根据代理人Id查找返佣规则
     * @param id
     * @return
     */
    public String queryRebateRuleById(Integer id);

    /**
     * 查询代理人账单详情
     * @param map
     * @return
     */
    public List<AgencyBillDetail> queryAgencyBillDetailUserOur(Map<String,Object> map);

    /**
     * 查询代理人账单详情
     * @param map
     * @return
     */
    public List<AgencyBillDetail> queryAgencyBillDetailUserOpposite(Map<String,Object> map);

    /**
     *缓存调用
     * @param map
     * @return
     */
    public List<AgencyBillDetail> queryAgencyBillDetailCache(Map<String,Object> map);

    /**
     * 查询代理的客户列表
     * @param map
     * @return
     */
    public List<AgencyCustomer> queryAgencyCustomer(Map<String,Object> map);

    /**
     * 查询周期
     * @return
     */
    public List<String> queryConsumeCycle();

    /**
     * 查询产品列表
     * @return
     */
    public List<CompanyApi> queryConsumeApiType();

    /**
     * 修改扣费量
     * @param id
     * @param amount
     * @return
     */
    public boolean updateRebateBillAmount(Integer id,Integer amount);

    /**
     *修改单价
     * @param id
     * @param cost
     * @return
     */
    public boolean updateRebateBillCost(Integer id,Integer cost);

    /**
     *修改售价
     * @param id
     * @param price
     * @return
     */
    public boolean updateRebateBillPrice(Integer id,Integer price);

    /**
     *修改返佣起始价
     * @param id
     * @param price
     * @return
     */
    public boolean updateRebateBillBeginPrice(Integer id,Integer price);

    /**
     *修改返佣结算价
     * @param id
     * @param price
     * @return
     */
    public boolean updateRebateBillEndPrice(Integer id,Integer price);

    /**
     * 删除记录
     * @param id
     * @return
     */
    public boolean deleteRebateDetail(List<String> id);

    /**
     * 修改缓存售价
     * @param id
     * @param price
     * @return
     */
    public boolean updateCachePrice(Integer id,Integer price);

    /**
     * 修改缓存扣费量
     * @param id
     * @param count
     * @return
     */
    public boolean updateCacheCount(Integer id,Integer count);

    /**
     * 删除缓存记录
     * @param id
     * @return
     */
    public boolean deleteCacheDetail(List<String> id);

}
