package org.qydata.service;

import org.qydata.entity.Company;
import org.qydata.entity.Partner;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/7/31.
 */
public interface CustomerHistoryBillService {

    /**
     * 查询客户历史消费账单
     * @param map
     * @return
     */
    public Map<String,Object> queryCustomerHistoryBill(Map<String,Object> map);

    /**
     * 查询所有客户
     * @return
     */
    public List<Company> queryAllCompany();

    /**
     * 查询所有合作伙伴
     * @return
     */
    public List<Partner> queryAllPartner();

    /**
     * 查询消费的月份
     * @return
     */
    public List<String> queryAllConsumeTime();

    /**
     * 查询客户历史消费账单明细
     * @param map
     * @return
     */
    public Map<String,Object> queryCustomerHistoryBillDetail(Map<String,Object> map);

    /**
     * 客户历史账单消费走势加载数据
     * @param map
     * @return
     */
    public Map<String,Object> queryCustomerHistoryBillTrendData(Map<String,Object> map);

}
