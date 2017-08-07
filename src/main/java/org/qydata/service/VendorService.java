package org.qydata.service;

import org.qydata.entity.ApiVendorBalanceLog;
import org.qydata.entity.Partner;
import org.qydata.entity.VendorExt;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/7.
 */
public interface VendorService {

    /**
     * 查找全部的供应商
     * @param map
     * @return
     */
    public List<VendorExt> queryAllVendor(Map<String,Object> map);

    public List<Partner> queryAllPartner();

    public boolean updateVendorPrepay(Integer vid,Integer preId);

    public boolean updateVendorBalance(Integer vid,String amount,String date,String remark);

    public List<ApiVendorBalanceLog> queryVendorBalanceLog(Map<String,Object> map);

}
