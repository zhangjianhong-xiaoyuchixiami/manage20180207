package org.qydata.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jonhn on 2017/1/6.
 */
public interface PartnerWeekMonthAmountService {

    /**
     * 统计和插入合作公司每周的付款数据
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean addAllPartnerWeekPaymentRecordAndAddWeekMonthAmount(Integer result)throws Exception;

    /**
     * 统计和插入合作公司每月的付款数据
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean addAllPartnerMonthPaymentRecordAndAddWeekMonthAmount(Integer result)throws Exception;


    /**
     * 统计和插入合作公司每周的收款数据
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean addAllPartnerWeekReceiptRecordAndAddWeekMonthAmount(Integer result)throws Exception;

    /**
     * 统计和插入合作公司每月的收款数据
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean addAllPartnerMonthReceiptRecordAndAddWeekMonthAmount(Integer result)throws Exception;


}
