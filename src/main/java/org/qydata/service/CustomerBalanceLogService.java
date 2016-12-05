package org.qydata.service;

import org.qydata.entity.CustomerBalanceModifyReason;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jonhn on 2016/11/8.
 */
public interface CustomerBalanceLogService {

    /**
     * 查询全部的CustomerBalanceModifyReason
     * @return
     */
    public List<CustomerBalanceModifyReason> findAll();

    /**
     * 余额变动
     * @param authId
     * @param amount
     * @param reasonId
     * @return
     */
    @Transactional
    public boolean changeCustomerBalance(String authId, String amount,String reasonId)throws Exception;
}
