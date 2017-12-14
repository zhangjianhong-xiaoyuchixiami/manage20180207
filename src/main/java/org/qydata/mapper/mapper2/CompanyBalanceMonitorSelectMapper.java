package org.qydata.mapper.mapper2;

import org.qydata.entity.CustomerCompanyEmail;
import org.qydata.entity.Partner;
import org.qydata.entity.monitor.CompanyBalanceMonitor;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/23.
 */
public interface CompanyBalanceMonitorSelectMapper {


    /**
     * 查询客户余额报警列表
     * @param map
     * @return
     */
    public List<CompanyBalanceMonitor> queryCompanyBalanceMonitor(Map<String, Object> map);

    /**
     * 查找是否已添加余额报警客户
     * @param cid
     * @return
     */
    public CompanyBalanceMonitor queryCompanyBalanceMonitorById(Integer cid);

    /**
     * 查找客户邮箱
     * @param map
     * @return
     */
    public List<CustomerCompanyEmail> queryEmailById(Map<String, Object> map);

    /**
     * 查询所有合作伙伴
     * @return
     */
    public List<Partner> queryAllPartner();

}
