package org.qydata.mapper.mapper1;

import org.qydata.dst.ApiTypeSubType;
import org.qydata.dst.CustomerCompanyPartner;
import org.qydata.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/12/15.
 */
public interface CompanyMapper {

    /**
     * 新增客户
     * @param company
     * @return
     * @throws Exception
     */
    public boolean addCompany(Company company)throws Exception;

    /**
     * 修改账号余额
     * @param customerId
     * @param amount
     * @return
     * @throws Exception
     */
    public boolean updateCustomerBalance(Integer customerId, Long amount)throws Exception;

    /**
     * 添加余额变动日志
     * @param customerBalanceLog
     * @return
     * @throws Exception
     */
    public boolean addCustomerBalanceLog(CustomerBalanceLog customerBalanceLog)throws Exception;


}
