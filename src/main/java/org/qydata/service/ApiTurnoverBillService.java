package org.qydata.service;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.dst.ApiTurnoverBill;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/9/5.
 */
public interface ApiTurnoverBillService {


    /**
     * 查询各产品进出账单
     * @param map
     * @return
     */
    @SystemServiceLog(description = "查询各产品进出帐单")
    public Map<String,Object> queryApiTurnoverBill(Map<String,Object> map);

    /**
     * 查询消费的月份
     * @return
     */
    @SystemServiceLog(description = "查询消费月份")
    public List<String> queryAllConsumeTime();

    /**
     * 查询消费类型
     * @return
     */
    @SystemServiceLog(description = "查询消费类型")
    public List<ApiTurnoverBill> queryConsumeType();

    /**
     * 查询各产品进出账单走势
     * @param map
     * @return
     */
    @SystemServiceLog(description = "查询各产品进出帐单走势")
    public Map<String,Object> queryApiTurnoverBillTrend(Map<String,Object> map);

    /**
     * 查询各产品进出账单走势
     * @param map
     * @return
     */
    @SystemServiceLog(description = "查询各产品进出帐单走势")
    public Map<String,Object> queryApiTurnoverBillTrendData(Map<String,Object> map);

}
