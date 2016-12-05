package org.qydata.mapper;

import org.qydata.entity.CustomerBalanceLog;
import org.qydata.entity.CustomerBalanceModifyReason;

import java.util.List;

/**
 * Created by jonhn on 2016/11/8.
 */
public interface CustomerBalanceLogMapper {

    /**
     * 查询全部的CustomerBalanceModifyReason
     * @return
     */
    public List<CustomerBalanceModifyReason> findAll();

    /**
     * 根据账户查询余额
     * @param authId
     * @return
     */
    public Long findBalanceByAuthId(String authId);
    /**
     * 根据账户修改余额
     * @param totleBalance
     * @param authId
     * @return
     */
    public boolean updateBalanceByAuthId(Long totleBalance,String authId)throws Exception;

    /**
     * 余额变动日志
     * @param customerBalanceLog
     * @return
     */
    public boolean insertcustomerBalanceLog(CustomerBalanceLog customerBalanceLog)throws Exception;

}
