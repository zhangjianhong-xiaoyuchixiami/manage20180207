package org.qydata.service;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.entity.ApiVendorBalanceLog;
import org.qydata.entity.Partner;
import org.qydata.entity.VendorExt;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 查找全部合作伙伴
     * @return
     */
    public List<Partner> queryAllPartner();

    /**
     * 修改供应商预付状态
     * @param vid
     * @param preId
     * @return
     */
    public boolean updateVendorPrepay(Integer vid,Integer preId);

    /**
     * 修改供应商余额
     * @param vid
     * @param amount
     * @param date
     * @param remark
     * @return
     */
    @SystemServiceLog(description = "供应商充值")
    @Transactional
    public boolean updateVendorBalance(Integer vid,String amount,String date,String remark) throws Exception;

    /**
     * 查询充值记录
     * @param map
     * @return
     */
    public List<ApiVendorBalanceLog> queryVendorBalanceLog(Map<String,Object> map);

}
