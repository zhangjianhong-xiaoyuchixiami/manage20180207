package org.qydata.mapper.mapper1;

import org.qydata.dst.ApiWeb;
import org.qydata.dst.VendorHistoryBill;
import org.qydata.dst.VendorHistoryBillDetail;
import org.qydata.entity.ApiVendor;
import org.qydata.entity.Partner;
import org.qydata.entity.VendorHistoryBillUpdateLog;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/30.
 */
public interface VendorHistoryBillMapper {

    /**
     * 修改单价
     * @param id
     * @param cost
     * @return
     */
    public boolean updateVendorHistoryBillCost(Integer id, Integer cost);

    /**
     * 修改扣费量
     * @param id
     * @param amount
     * @return
     */
    public boolean updateVendorHistoryBillAmount(Integer id, Integer amount);

    /**
     * 修改锁定/解锁状态
     * @param map
     * @return
     */
    public boolean updateVendorHistoryBillIsLock(Map<String, Object> map);

    /**
     * 添加修改日志
     * @param log
     * @return
     */
    public boolean insertVendorHistoryBillUpdateLog(VendorHistoryBillUpdateLog log);

    /**
     * 删除历史记录
     * @param list
     * @return
     */
    public boolean deleteVendorHistoryBill(List<Integer> list);

    /**
     * 新增历史记录
     * @param billDetail
     * @return
     */
    public boolean addVendorHistoryBill(VendorHistoryBillDetail billDetail);


}
