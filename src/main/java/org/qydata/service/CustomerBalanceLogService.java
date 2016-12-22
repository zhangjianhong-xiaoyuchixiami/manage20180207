package org.qydata.service;

import org.qydata.entity.CustomerBalanceLog;
import org.qydata.entity.CustomerBalanceModifyReason;
import org.qydata.tools.PageModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/11/8.
 */
public interface CustomerBalanceLogService {

    /**
     * 查询全部的CustomerBalanceModifyReason
     * @return
     */
    public List<CustomerBalanceModifyReason> findAll(List customerBalanceModifyReasonIdList);

    /**
     * 余额变动
     * @param authId
     * @param amount
     * @param reasonId
     * @return
     */
    @Transactional
    public boolean changeCustomerBalance(String authId, String amount,String reasonId)throws Exception;

    /**
     * 查询指定账号消费和充值记录并分页显示
     * @param map
     * @return
     * @throws Exception
     */
    public PageModel<CustomerBalanceLog> findAllCustomerBalanceLogByCustomerId(Map<String,Object> map)throws Exception;

    /**
     * 查询指定账号的Amount
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerBalanceLog> getAllCustomerBalanceLogAmountByCustomerId(Map<String,Object> map)throws Exception;

}
