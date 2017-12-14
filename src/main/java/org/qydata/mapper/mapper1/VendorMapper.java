package org.qydata.mapper.mapper1;

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
     * 修改供应商预付状态
     * @param vid
     * @param preId
     * @return
     */
    public boolean updateVendorPrepay(Integer vid, Integer preId);

    /**
     * 修改供应商预付状态
     * @return
     */
    public boolean insertVendorPrepay(VendorExt vendorExt);

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
