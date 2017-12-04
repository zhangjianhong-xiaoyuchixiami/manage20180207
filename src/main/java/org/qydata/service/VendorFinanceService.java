package org.qydata.service;

import org.qydata.config.annotation.SystemServiceLog;
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
    @SystemServiceLog(description = "统计供应商消费信息")
    public Map<String,Object> queryVendor(Map<String, Object> map);

    /**
     * 通过apiTypeId查询供应商类型
     * @return
     */

    public List<ApiVendor> queryApiVendorName();

}
