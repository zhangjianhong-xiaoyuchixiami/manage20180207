package org.qydata.mapper;

import org.qydata.dst.customer.BackGroundCustomerBalanceLog;

public interface BackGroundCustomerBalanceLogMapper {


    /**
     * 插入客户余额变动日志
     * @param log
     * @return
     */
    public int insertBackGroundCustomerBalanceLog (BackGroundCustomerBalanceLog log);

    /**
     * 删除客户余额变动日志
     * @param log
     * @return
     */
    public int deleteBackGroundCustomerBalanceLog(BackGroundCustomerBalanceLog log);

    /**
     * 根据公司Id查找账号Id
     * @param customerId
     * @return
     */
    public Integer queryCustomerIdByCompanyId(Integer customerId);

}
