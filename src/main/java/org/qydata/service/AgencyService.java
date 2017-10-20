package org.qydata.service;

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
     *
     * @param map
     * @return
     */
    public Map<String,Object> queryAgencyBill(Map<String,Object> map);

    /**
     *
     * @return
     */
    public List<RebateAgency> queryRebateAgency();

    /**
     *
     * @return
     */
    public List<AgencyCustomer> queryAgencyCustomer(Map<String,Object> map);

    /**
     *
     * @return
     */
    public List<String> queryConsumeCycle();


    public Map<String,Object> queryAgencyBillDetail(Map<String,Object> map);

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
    public boolean updateRebateBillCost(Integer id,Double cost);

    /**
     *修改售价
     * @param id
     * @param price
     * @return
     */
    public boolean updateRebateBillPrice(Integer id,Double price);

    /**
     *修改返佣起始价
     * @param id
     * @param price
     * @return
     */
    public boolean updateRebateBillBeginPrice(Integer id,Double price);

    /**
     *修改返佣结算价
     * @param id
     * @param price
     * @return
     */
    public boolean updateRebateBillEndPrice(Integer id,Double price);

    /**
     * 删除记录
     * @param id
     * @return
     */
    public boolean deleteRebateDetail(String [] id);

}
