package org.qydata.mapper;

import org.qydata.entity.ApiVendorBalance;
import org.qydata.entity.ApiVendorBalanceLog;
import org.qydata.entity.Partner;
import org.qydata.entity.VendorExt;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/7.
 */
public interface VendorMapper {

    /**
     * 查询全部供应商
     * @param map
     * @return
     */
    public List<VendorExt> queryAllVendor(Map<String,Object> map);

    /**
     *查询供应商消费金额（至昨天）
     * @param map
     * @return
     */
    public List<VendorExt> queryVendorConsume(Map<String,Object> map);

    /**
     *查询供应商消费金额（今天）
     * @param map
     * @return
     */
    public List<VendorExt> queryVendorConsumeCurrDay(Map<String,Object> map);

    /**
     *查找全部合作伙伴
     * @return
     */
    public List<Partner> queryAllPartner();

    /**
     * 查询充值记录
     * @param map
     * @return
     */
    public List<ApiVendorBalanceLog> queryVendorBalanceLog(Map<String,Object> map);

    /**
     * 修改供应商预付状态
     * @param vid
     * @param preId
     * @return
     */
    public boolean updateVendorPrepay(Integer vid,Integer preId);

    /**
     * 修改供应商预付状态
     * @param vid
     * @return
     */
    public VendorExt queryVendorPrepay(Integer vid);

    /**
     * 修改供应商预付状态
     * @return
     */
    public boolean insertVendorPrepay(VendorExt vendorExt);

    /**
     * 充值或扣费之前查询是否有余额记录，如果有直接修改，反之插入
     * @return
     */
    public ApiVendorBalance queryVendorBalance(Integer vid);

    /**
     * 插入余额记录
     * @return
     */
    public boolean insertVendorBalance(ApiVendorBalance balance);

    /**
     * 修改余额记录
     * @return
     */
    public boolean updateVendorBalance(ApiVendorBalance balance);

    /**
     * 插入余额日志
     * @return
     */
    public boolean insertVendorBalanceLog(ApiVendorBalanceLog log);

}
