package org.qydata.mapper.mapper1;

import org.qydata.dst.CustomerHistoryBill;
import org.qydata.dst.CustomerHistoryBillDetail;
import org.qydata.entity.Company;
import org.qydata.entity.CompanyApi;
import org.qydata.entity.CustomerHistoryBillUpdateLog;
import org.qydata.entity.Partner;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/7/31.
 */
public interface CustomerHistoryBillMapper {

    /**
     * 修改单价
     * @param id
     * @param cost
     * @return
     */
    public boolean updateCustomerHistoryBillCost(Integer id, Integer cost);

    /**
     * 修改扣费量
     * @param id
     * @param amount
     * @return
     */
    public boolean updateCustomerHistoryBillAmount(Integer id, Integer amount);

    /**
     * 新增历史记录
     * @param bill
     * @return
     */
    public boolean addCustomerHistoryBill(CustomerHistoryBillDetail bill);

    /**
     * 删除历史记录
     * @param list
     * @return
     */
    public boolean deleteCustomerHistoryBill(List<String> list);

    /**
     * 修改锁定/解锁状态
     * @param map
     * @return
     */
    public boolean updateCustomerHistoryBillIsLock(Map<String, Object> map);

    /**
     * 添加修改日志
     * @param log
     * @return
     */
    public boolean insertCustomerHistoryBillUpdateLog(CustomerHistoryBillUpdateLog log);


}
