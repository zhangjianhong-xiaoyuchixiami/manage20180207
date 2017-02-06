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
    public boolean getAllPartnerWeekPaymentRecordAndAddWeekMonthAmount(Integer result)throws Exception;

    /**
     * 统计和插入合作公司每月的付款数据
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean getAllPartnerMonthPaymentRecordAndAddWeekMonthAmount(Integer result)throws Exception;


    /**
     * 统计和插入合作公司每周的收款数据
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean getAllPartnerWeekReceiptRecordAndAddWeekMonthAmount(Integer result)throws Exception;

    /**
     * 统计和插入合作公司每月的收款数据
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean getAllPartnerMonthReceiptRecordAndAddWeekMonthAmount(Integer result)throws Exception;


}
