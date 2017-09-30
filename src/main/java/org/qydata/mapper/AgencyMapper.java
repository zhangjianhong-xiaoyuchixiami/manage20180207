package org.qydata.mapper;

import org.qydata.entity.agency.AgencyBill;
import org.qydata.entity.agency.AgencyBillDetail;
import org.qydata.entity.agency.AgencyCustomer;
import org.qydata.entity.agency.RebateAgency;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/9/14.
 */
public interface AgencyMapper {

    public List<AgencyBill> queryAgencyBill(Map<String,Object> map);

    public List<RebateAgency> queryRebateAgency();

    public List<AgencyCustomer> queryAgencyCustomer(Map<String,Object> map);

    public List<String> queryConsumeCycle();

    public List<AgencyBillDetail> queryAgencyBillDetail(Map<String,Object> map);

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
    public boolean deleteRebateDetail(String [] id);
}
