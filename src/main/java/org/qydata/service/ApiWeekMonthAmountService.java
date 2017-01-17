package org.qydata.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jonhn on 2017/1/6.
 */
public interface ApiWeekMonthAmountService {

    /**
     * 统计和插入Api每周的消费数据
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean getAllApiWeekConsumeRecordAndAddApiWeekMonthAmount(Integer result)throws Exception;

    /**
     * 统计和插入Api每月的消费数据
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean getAllApiMonthConsumeRecordAndAddApiWeekMonthAmount(Integer result)throws Exception;


}
