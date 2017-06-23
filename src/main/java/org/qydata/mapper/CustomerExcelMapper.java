package org.qydata.mapper;

import org.qydata.entity.CustomerConsumeExcel;

import java.util.Map;

/**
 * Created by jonhn on 2017/6/21.
 */
public interface CustomerExcelMapper {


    /**
     * 根据customerId查询上月消费账单
     * @param map
     * @return
     */
    public CustomerConsumeExcel queryCustomerConsumeExcelByCustomerId(Map<String,Object> map);

}
