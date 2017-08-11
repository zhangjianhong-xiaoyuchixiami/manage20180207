package org.qydata.service;

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
    public Map<String,Object> queryApiOverAllFinance(Map<String,Object> map);

    /**
     *查询Api消费详情
     * @param map
     * @return
     * @throws Exception
     */
    public Map<String,Object> queryApiDetailById(Map<String,Object> map);

    /**
     * 以APIVendor统计消费信息
     * @return
     */
    public Map<String,Object> queryApiVendor(Map<String,Object> map);

    /**
     * Api充值
     * @return
     */
    @Transactional
    public boolean updateApiVendorChargeLog(Integer vendorIdCharge, String amount, String remark, String chargeDate)throws Exception;

    /**
     * 查询Api类型
     * @return
     */
    public List<ApiType> queryApiType();

    /**
     * 通过apiTypeId查询供应商类型
     * @param map
     * @return
     */
    public List<ApiVendor> queryApiVendorName(Map<String,Object> map);
}
