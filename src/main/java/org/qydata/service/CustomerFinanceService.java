package org.qydata.service;

import org.qydata.dst.CustomerApiType;
import org.qydata.dst.CustomerFinance;
import org.qydata.entity.CustomerBalanceLog;
import org.qydata.entity.WeekMonthAmount;

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
     * @throws Exception
     */
    public List<CustomerFinance> queryCompanyCustomerOverAllFinanceByDept(Map<String,Object> map)throws Exception;

    /**
     * 查询客户的财务总览
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerFinance> queryCompanyCustomerOverAllFinance(Map<String,Object> map)throws Exception;
    /**
     * 指定账号余额变动记录
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerBalanceLog> queryCompanyCustomerRechargeRecordByCustomerId(Map<String,Object> map)throws Exception;


    /**
     * 查询客户的Api消费记录
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerApiType> queryCompanyCustomerApiConsumeRecordByCustomerId(Map<String,Object> map)throws Exception;

    /**
     * 查询客户的Api消费明细记录
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerBalanceLog> queryCompanyCustomerApiDetailConsumeRecordByCustomerId(Map<String,Object> map)throws Exception;

    /**
     * 查询客户的周月记录
     * @param map
     * @return
     * @throws Exception
     */
    public List<WeekMonthAmount> queryCompanyCustomerWeekMonthRecordByCustomerId(Map<String,Object> map)throws Exception;

    /**
     * 查询客户的周月记录年级联菜单
     * @param map
     * @return
     * @throws Exception
     */
    public List<Integer> queryCompanyCustomerYearsByCustomerId(Map<String,Object> map) throws Exception;

    /**
     * 查询客户的周月记录月级联菜单
     * @param map
     * @return
     * @throws Exception
     */
    public List<Integer> queryCompanyCustomerMonthsByCustomerId(Map<String,Object> map) throws Exception;

    /**
     * 查询客户的周月记录周级联菜单
     * @param map
     * @return
     * @throws Exception
     */
    public List<Integer> queryCompanyCustomerWeeksByCustomerId(Map<String,Object> map) throws Exception;

    /**
     * 月账单走势
     * @param map
     * @return
     */
    public Map<String,List> monthChargeConsumeToward(Map<String,Object> map);
}

