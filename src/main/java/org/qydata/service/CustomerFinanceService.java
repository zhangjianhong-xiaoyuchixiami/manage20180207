package org.qydata.service;

import org.qydata.dst.CustomerApiVendor;
import org.qydata.dst.customer.CustomerCurrDayConsume;
import org.qydata.dst.customer.CustomerCurrDayConsumeDetail;
import org.qydata.entity.ApiType;
import org.qydata.entity.ApiVendor;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/8.
 */
public interface CustomerFinanceService {


    /**
     * 查询客户的财务总览
     * @param map
     * @return
     */
    public Map<String,Object> queryCompanyCustomerOverAllFinance(Map<String,Object> map)throws Exception;

    /**
     * 查询客户当天各产品类型的消费情况
     * @param map
     * @return
     */
    public List<CustomerCurrDayConsume> queryCustomerCurrDayApiTypeConsume(Map<String,Object> map);

    /**
     * 查询客户当天各产品类型的消费情况，显示供应商和缓存
     * @param map
     * @return
     */
    public List<CustomerCurrDayConsumeDetail> queryCustomerCurrDayConsumeDetail(Map<String,Object> map);

    /**
     * 查询客户近一周消费走势
     * @param cid
     * @return
     */
    public Map<String,Object> queryNearlyWeekTrend(Integer cid);


    /**
     * 根据customerId查询公司名称
     * @param id
     * @return
     */
    public String queryCustomerCompanyNameById(Integer id);

    /**
     * 指定账号余额变动记录
     * @param map
     * @return
     */
    public Map<String,Object> queryCompanyCustomerRechargeRecordByCustomerId(Map<String,Object> map);

    /**
     * 修改比率
     * @param cid
     * @param rate
     * @return
     */
    public boolean updateRate(Integer cid,Integer rate);

//    /**
//     *
//     * @param customerId
//     * @param apiTypeId
//     * @param subTypeId
//     * @return
//     */
//    public CustomerCurrDayConsume getPriceByType(Integer customerId, Integer apiTypeId, Integer subTypeId);

//    /**
//     * 查询客户的Api消费记录
//     * @param map
//     * @return
//     */
//    public Map<String,Object> queryCompanyCustomerApiConsumeRecordByCustomerId(Map<String,Object> map);
//
//    /**
//     * 查询客户的Api消费明细记录
//     * @param map
//     * @return
//     */
//    public Map<String,Object> queryCompanyCustomerApiDetailConsumeRecordByCustomerId(Map<String,Object> map);
//
//    /**
//     * 查询客户的周月记录
//     * @param map
//     * @return
//     */
//    public Map<String,Object> queryCompanyCustomerWeekMonthRecordByCustomerId(Map<String,Object> map);
//
//    /**
//     * 查询客户的周月记录年级联菜单
//     * @param map
//     * @return
//     */
//    public List<Integer> queryCompanyCustomerYearsByCustomerId(Map<String,Object> map);
//
//    /**
//     * 查询客户的周月记录月级联菜单
//     * @param map
//     * @return
//     */
//    public List<Integer> queryCompanyCustomerMonthsByCustomerId(Map<String,Object> map);
//
//    /**
//     * 查询客户的周月记录周级联菜单
//     * @param map
//     * @return
//     */
//    public List<Integer> queryCompanyCustomerWeeksByCustomerId(Map<String,Object> map);
//
//    /**
//     * 月账单走势
//     * @param map
//     * @return
//     */
//    public Map<String,List> queryMonthChargeConsumeToward(Map<String,Object> map);
//
//    /**
//     * 查询客户各供应商消费情况
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public List<CustomerApiVendor> queryCustomerConsumeByVendor(Map<String,Object> map);
//
//    /**
//     *
//     * @param map
//     * @return
//     */
//    public List<ApiType> queryApiTypeByCustomerId(Map<String,Object> map);
//
//    /**
//     *
//     * @param map
//     * @return
//     */
//    public List<ApiVendor> queryApiVendorByCustomerId(Map<String,Object> map);

}

