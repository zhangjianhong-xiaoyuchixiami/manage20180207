package org.qydata.mapper;

import org.qydata.dst.CustomerFinance;
import org.qydata.entity.CustomerBalanceLog;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/8.
 */
public interface CustomerFinanceMapper {

    /**
     * 查询客户的财务总览
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerFinance> queryCompanyCustomerOverAllFinance(Map<String,Object> map)throws Exception;

    /**
     * 查询客户的充值记录
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerBalanceLog> queryCompanyCustomerRechargeRecordByCustomerId(Map<String,Object> map)throws Exception;

    /**
     * 查询客户的Api消费记录
     * @param map
     * @return
     * @throws Exception
     */
    public List<> queryCompanyCustomerConsumeRecordByCustomerId(Map<String,Object> map)throws Exception;

}
