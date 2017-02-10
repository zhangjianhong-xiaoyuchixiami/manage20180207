package org.qydata.service;

import org.qydata.entity.CustomerBalanceModifyReason;

import java.util.List;

/**
 * Created by jonhn on 2016/11/8.
 */
public interface CustomerBalanceLogService {

    /**
     * 查询全部的CustomerBalanceModifyReason
     * @return
     */
    public List<CustomerBalanceModifyReason> findAll(List customerBalanceModifyReasonIdList);



}
