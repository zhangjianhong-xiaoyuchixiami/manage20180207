package org.qydata.mapper;

import org.apache.ibatis.annotations.Param;
import org.qydata.dst.CustomerApiVendor;
import org.qydata.dst.CustomerFinance;
import org.qydata.dst.CustomerWeekMonthConsume;
import org.qydata.dst.customer.CustomerConsume;
import org.qydata.dst.customer.CustomerCurrDayConsume;
import org.qydata.dst.customer.CustomerCurrDayConsumeDetail;
import org.qydata.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/8.
 */
public interface CustomerFinanceMapper {

    /**
     * 查询客户的财务总览
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerFinance> queryCompanyCustomer(Map<String,Object> map)throws Exception;

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
     * 根据customerId查询公司名称
     * @param id
     * @return
     */
    public String queryCustomerCompanyNameById(Integer id);

    /**
     * 查询客户各产品的消费情况
     * @param map
     * @return
     */
    public List<CustomerFinance> queryCustomerApiTypeConsume(Map<String,Object> map);

    /**
     * 查询客户周月消费总额
     * @param map
     * @return
     */
    public List<CustomerWeekMonthConsume> queryCustomerWeekMonthConsume(Map<String,Object> map);

    /**
     * 查询充值总额（至昨天）
     * @param map
     * @return
     */
    public List<CustomerFinance> queryCustomerChargeTotle(Map<String,Object> map);

    /**
     * 查询当天充值总额
     * @param map
     * @return
     */
    public List<CustomerFinance> queryCustomerChargeCurrDay(Map<String,Object> map);

    /**
     * 查询消费总额（至昨天）
     * @param map
     * @return
     */
    public List<CustomerFinance> queryCustomerConsumeTotle(Map<String,Object> map);

    /**
     * 查询本月消费总额（至昨天）
     * @param map
     * @return
     */
    public List<CustomerFinance> queryCustomerCurrMonthTotle(Map<String,Object> map);

    /**
     * 查询当天消费总额
     * @param map
     * @return
     */
    public List<CustomerFinance> queryCustomerCurrDayTotle(Map<String,Object> map);


    /**
     * 查询客户邮箱，1、用于判断是否存在邮箱，2、发送账单是填写收件人
     * @param map
     * @return
     */
    public List<CustomerCompanyEmail> queryCustomerEmail(Map<String,Object> map);

    /**
     * 查询客户账单，1、用于判断是否存在上月账单
     * @param map
     * @return
     */
    public List<CustomerConsumeExcel> queryCustomerAccountExcel(Map<String,Object> map);

    /**
     * 根据customerId查询companyId
     * @param customerId
     * @return
     */
    public int queryCompanyIdByCustomerId(int customerId);

    /**
     * 查询客户上周充值金额
     * @return
     */
    public List<CustomerConsume> queryCustomerLastWeekCharge();

    /**
     * 查询客户上周消费金额
     * @return
     */
    public List<CustomerConsume> queryCustomerLastWeekConsume();

    /**
     * 查询客户上月充值金额
     * @return
     */
    public List<CustomerConsume> queryCustomerLastMonthCharge();

    /**
     * 查询客户上月消费金额
     * @param lastMonthTime
     * @return
     */
    public List<CustomerConsume> queryCustomerLastMonthConsume(@Param("lastMonthTime")String lastMonthTime);

    /**
     * 查询客户昨日消费金额
     * @return
     */
    public List<CustomerConsume> queryCustomerYesterdayConsume(@Param("yestTime")String yestTime);

    /**
     * 查询近一周消费走势
     * @param cid
     * @return
     */
    public List<CustomerConsume> queryNearlyWeekTrend(Integer cid);




    /**
     * 查询客户的充值记录
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerBalanceLog> queryCompanyCustomerRechargeRecordByCustomerId(Map<String,Object> map)throws Exception;

    /**
     * 统计客户的充值记录金额总计
     * @param map
     * @return
     * @throws Exception
     */
    public Integer getCountCompanyCustomerRechargeRecordByCustomerId(Map<String,Object> map)throws Exception;

//    /**
//     * 查询客户的Api消费记录
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public List<ApiType> queryCompanyCustomerApiConsumeRecordByCustomerId(Map<String,Object> map)throws Exception;
//
//
//    /**
//     * 统计客户的Api消费记录金额总计
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public Integer getCountCompanyCustomerApiConsumeRecordByCustomerId(Map<String,Object> map)throws Exception;
//
//    /**
//     * 查询客户的Api消费明细记录
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public List<CustomerBalanceLog> queryCompanyCustomerApiDetailConsumeRecordByCustomerId(Map<String,Object> map)throws Exception;
//
//
//    /**
//     * 统计客户的Api消费明细记录金额总计
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public Integer getCountCompanyCustomerApiDetailConsumeRecordByCustomerId(Map<String,Object> map)throws Exception;
//
//    /**
//     * 查询客户的周月记录
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public List<WeekMonthAmount> queryCompanyCustomerWeekMonthRecordByCustomerId(Map<String,Object> map)throws Exception;
//
//    /**
//     * 统计客客户的周月记录金额总计
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public Integer getCountCompanyCustomerWeekMonthRecordByCustomerId(Map<String,Object> map)throws Exception;
//
//
//    /**
//     * 查询客户的周月记录年级联菜单
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public List<Integer> queryCompanyCustomerYearsByCustomerId(Map<String,Object> map) throws Exception;
//
//    /**
//     * 查询客户的周月记录月级联菜单
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public List<Integer> queryCompanyCustomerMonthsByCustomerId(Map<String,Object> map) throws Exception;
//
//    /**
//     * 查询客户的周月记录周级联菜单
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public List<Integer> queryCompanyCustomerWeeksByCustomerId(Map<String,Object> map) throws Exception;
//
//    /**
//     * 月账单走势
//     * @param customerId
//     * @param tableId
//     * @param result
//     * @return
//     */
//    public WeekMonthAmount queryMonthChargeConsumeToward(Integer customerId,Integer tableId,Integer result);
//
//    /**
//     * 查询客户各供应商消费情况
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public List<CustomerApiVendor> queryCustomerConsumeByVendor(Map<String,Object> map)throws  Exception;
//
//    /**
//     *查询api类型通过账号Id
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public List<ApiType> queryApiTypeByCustomerId(Map<String,Object> map)throws Exception;
//
//    /**
//     *查询api供应商通过账号Id
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public List<ApiVendor> queryApiVendorByCustomerId(Map<String,Object> map)throws Exception;
}

