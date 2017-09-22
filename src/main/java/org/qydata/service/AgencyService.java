package org.qydata.service;

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

    public boolean updateRebateBillAmount(Integer id,Integer amount);

}
