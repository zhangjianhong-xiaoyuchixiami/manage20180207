package org.qydata.mapper;

import org.qydata.entity.agency.AgencyBill;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/9/14.
 */
public interface AgencyMapper {

    public List<AgencyBill> queryAgencyBill(Map<String,Object> map);

}
