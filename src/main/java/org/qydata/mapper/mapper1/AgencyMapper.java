package org.qydata.mapper.mapper1;

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
     * 修改扣费量
     * @param id
     * @param amount
     * @return
     */
    public boolean updateRebateBillAmount(Integer id, Integer amount);

    /**
     *修改单价
     * @param id
     * @param cost
     * @return
     */
    public boolean updateRebateBillCost(Integer id, Integer cost);

    /**
     *修改售价
     * @param id
     * @param price
     * @return
     */
    public boolean updateRebateBillPrice(Integer id, Integer price);

    /**
     *修改返佣起始价
     * @param id
     * @param price
     * @return
     */
    public boolean updateRebateBillBeginPrice(Integer id, Integer price);

    /**
     *修改返佣结算价
     * @param id
     * @param price
     * @return
     */
    public boolean updateRebateBillEndPrice(Integer id, Integer price);

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
    public boolean updateCachePrice(Integer id, Integer price);

    /**
     * 修改缓存扣费量
     * @param id
     * @param count
     * @return
     */
    public boolean updateCacheCount(Integer id, Integer count);

    /**
     * 删除缓存记录
     * @param id
     * @return
     */
    public boolean deleteCacheDetail(List<String> id);

}
