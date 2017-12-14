package org.qydata.mapper.mapper2;

import org.qydata.dst.customer.BackGroundCustomerBalanceLog;

public interface BackGroundCustomerBalanceLogSelectMapper {


    /**
     * 根据公司Id查找账号Id
     * @param customerId
     * @return
     */
    public Integer queryCustomerIdByCompanyId(Integer customerId);

}
