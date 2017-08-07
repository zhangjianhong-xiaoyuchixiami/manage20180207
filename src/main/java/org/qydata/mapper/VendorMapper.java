package org.qydata.mapper;

import org.qydata.entity.Partner;
import org.qydata.entity.VendorExt;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/7.
 */
public interface VendorMapper {

    public List<VendorExt> queryAllVendor(Map<String,Object> map);

    public List<VendorExt> queryVendorConsume(Map<String,Object> map);

    public List<VendorExt> queryVendorConsumeCurrDay(Map<String,Object> map);

    public List<Partner> queryAllPartner();

}
