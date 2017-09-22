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

}
