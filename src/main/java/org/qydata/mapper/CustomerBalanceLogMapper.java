package org.qydata.mapper;

import org.qydata.entity.CustomerBalanceLog;
import org.qydata.entity.CustomerBalanceModifyReason;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/11/8.
 */
public interface CustomerBalanceLogMapper {

    /**
     * 查询全部的CustomerBalanceModifyReason
     * @return
     */
    public List<CustomerBalanceModifyReason> findAll(List customerBalanceModifyReasonIdList);

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
    public boolean insertCustomerBalanceLog(CustomerBalanceLog customerBalanceLog)throws Exception;

    /**
     * 指定账号余额变动记录
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerBalanceLog> findAllCustomerBalanceLogByCustomerId(Map<String,Object> map)throws Exception;

    /**
     * 统计指定账号的余额变动记录数据量
     * @param map
     * @return
     * @throws Exception
     */
    public Integer getAllCountCustomerBalanceLogByCustomerId(Map<String,Object> map)throws Exception;

    /**
     * 查询指定账号的余额变动明细Amount
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerBalanceLog> getAllCustomerBalanceLogAmountByCustomerId(Map<String,Object> map)throws Exception;

    /**
     * 指定客户余额变动记录
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerBalanceLog> findAllCustomerBalanceLogByCompanyId(Map<String,Object> map)throws Exception;

    /**
     * 统计指定客户的余额变动记录数据量
     * @param map
     * @return
     * @throws Exception
     */
    public Integer getAllCountCustomerBalanceLogByCompanyId(Map<String,Object> map)throws Exception;

}
