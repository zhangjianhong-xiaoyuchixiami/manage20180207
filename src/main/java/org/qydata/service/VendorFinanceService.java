package org.qydata.service;

import org.qydata.dst.vendor.VendorCurrDayConsume;
import org.qydata.dst.vendor.VendorFinance;
import org.qydata.entity.ApiVendor;

import java.util.List;
import java.util.Map;

public interface VendorFinanceService {

    /**
     * 统计供应商消费信息
     * @return
     * @throws Exception
     */
    public Map<String,Object> queryVendor(Map<String, Object> map);

    /**
     * 通过apiTypeId查询供应商类型
     * @return
     */
    public List<ApiVendor> queryApiVendorName();

    /**
     * 查询供应商当天消费情况
     * @param map
     * @return
     */
    public List<VendorCurrDayConsume> queryVendorCurrdayConsume(Map<String, Object>map);


    public String queryVendorName(Integer vendorId);

    /**
     * 修改比率
     * @param vid
     * @param rate
     * @return
     */
    public boolean updateRate(Integer vid,Integer rate);
}
