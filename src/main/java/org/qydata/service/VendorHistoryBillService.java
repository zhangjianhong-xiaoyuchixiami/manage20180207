package org.qydata.service;

import java.util.Map;

/**
 * Created by jonhn on 2017/8/30.
 */
public interface VendorHistoryBillService {

    /**
     * 查询供应商历史财务账单
     * @param map
     * @return
     */
    public Map<String,Object> queryVendorHistoryBill(Map<String,Object> map);

    /**
     * 查询供应商历史财务账单明细
     * @param map
     * @return
     */
    public Map<String,Object> queryVendorHistoryBillDetail(Map<String,Object> map);

}
