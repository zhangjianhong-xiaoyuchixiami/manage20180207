package org.qydata.service;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.entity.ApiType;
import org.qydata.entity.ApiVendor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/17.
 */
public interface ApiFinanceService {

    /**
     *Api财务总览
     * @param map
     * @return
     * @throws Exception
     */
    @SystemServiceLog(description = "Api财务总览")
    public Map<String,Object> queryApiFinance(Map<String,Object> map);

    /**
     * 查询Api类型
     * @return
     */
    @SystemServiceLog(description = "查询Api类型")
    public List<ApiType> queryApiType();

    /**
     * 通过apiTypeId查询供应商类型
     * @param map
     * @return
     */
    @SystemServiceLog(description = "通过apiTypeId查询供应商类型")
    public List<ApiVendor> queryApiVendorName(Map<String,Object> map);
}
