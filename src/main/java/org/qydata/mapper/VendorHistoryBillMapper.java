package org.qydata.mapper;

import org.qydata.dst.VendorHistoryBill;
import org.qydata.dst.VendorHistoryBillDetail;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/30.
 */
public interface VendorHistoryBillMapper {

    /**
     * 查询供应商历史财务账单
     * @param map
     * @return
     */
    public List<VendorHistoryBill> queryVendorHistoryBill(Map<String,Object> map);

    /**
     * 查询供应商历史财务账单明细
     * @param map
     * @return
     */
    public List<VendorHistoryBillDetail> queryVendorHistoryBillDetail(Map<String,Object> map);

}
