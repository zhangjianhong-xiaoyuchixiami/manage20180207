package org.qydata.service;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.entity.CustomerConsumeExcel;

import java.util.Map;

/**
 * Created by jonhn on 2017/6/21.
 */
public interface CustomerExcelService {

    /**
     * 查询客户财务账单excel并且发送邮件
     * @param map
     * @return
     */
    @SystemServiceLog(description = "查询客户财务账单excel并且发送邮件")
    public Map<String,Object> queryCustomerFinanceAccountExcel(Map<String,Object> map);

    /**
     * 根据customerId查询上月消费账单
     * @param map
     * @return
     */
    @SystemServiceLog(description = "根据customerId查询上月消费账单")
    public CustomerConsumeExcel queryCustomerConsumeExcelByCustomerId(Map<String,Object> map);


}
