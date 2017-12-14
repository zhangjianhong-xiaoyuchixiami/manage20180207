package org.qydata.mapper.mapper2;

import org.qydata.dst.ApiTurnoverBill;
import org.qydata.dst.ApiTurnoverBillTrend;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/9/5.
 */
public interface ApiTurnoverBillSelectMapper {

    /**
     * 统计各产品消费上游情况（不包含当月）
     * @param map
     * @return
     */
    public List<ApiTurnoverBill> queryApiConsumeVendor(Map<String, Object> map);

    /**
     * 统计各产品客户消费情况（不包含当月）
     * @param map
     * @return
     */
    public List<ApiTurnoverBill> queryApiCustomerConsume(Map<String, Object> map);

    /**
     * 查询消费的月份
     * @return
     */
    public List<String> queryAllConsumeTime();

    /**
     * 查询供应商消费类型
     * @return
     */
    public List<ApiTurnoverBill> queryVendorConsumeType();

    /**
     * 查询客户消费类型
     * @return
     */
    public List<ApiTurnoverBill> queryCustomerConsumeType();

    /**
     * 根据类型查询供应商消费
     * @param map
     * @return
     */
    public List<ApiTurnoverBillTrend> queryVendorConsumeByTypeId(Map<String, Object> map);

    /**
     * 根据类型查询客户消费
     * @param map
     * @return
     */
    public List<ApiTurnoverBillTrend> queryCustomerConsumeByTypeId(Map<String, Object> map);

    /**
     * 根据类型查询供应商消费
     * @param map
     * @return
     */
    public List<ApiTurnoverBillTrend> queryVendorConsumeByTypeIdData(Map<String, Object> map);

    /**
     * 根据类型查询客户消费
     * @param map
     * @return
     */
    public List<ApiTurnoverBillTrend> queryCustomerConsumeByTypeIdData(Map<String, Object> map);
}
