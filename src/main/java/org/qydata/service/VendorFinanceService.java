package org.qydata.service;

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

}
