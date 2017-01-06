package org.qydata.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jonhn on 2017/1/6.
 */
public interface WeekMonthAmountService {

    /**
     * 统计和插入客户每周的充值数据
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean getAllCustomerWeekRechargeRecordAndAddWeekMonthAmount()throws Exception;

    /**
     * 统计和插入客户每月的充值数据
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean getAllCustomerMonthRechargeRecordAndAddWeekMonthAmount()throws Exception;

}
