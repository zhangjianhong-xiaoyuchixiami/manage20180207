package org.qydata.mapper;

import org.qydata.entity.CustomerCompanyEmail;
import org.qydata.entity.Partner;
import org.qydata.entity.monitor.CompanyBalanceMonitor;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/23.
 */
public interface CompanyBalanceMonitorMapper {


    /**
     * 查询客户余额报警列表
     * @param map
     * @return
     */
    public List<CompanyBalanceMonitor> queryCompanyBalanceMonitor(Map<String,Object> map);

    /**
     * 查找是否已添加余额报警客户
     * @param cid
     * @return
     */
    public CompanyBalanceMonitor queryCompanyBalanceMonitorById(Integer cid);

    /**
     * 插入余额报警客户
     * @param monitor
     * @return
     */
    public boolean insertCompanyBalanceMonitor(CompanyBalanceMonitor monitor);


    /**
     *修改是否预付
     * @param cid
     * @param value
     * @return
     */
    public boolean updatePrepay(Integer cid,Integer value);

    /**
     *修改是否报警
     * @param cid
     * @param value
     * @return
     */
    public boolean updateAlarm(Integer cid,Integer value);

    /**
     *修改是否提醒客户
     * @param cid
     * @param value
     * @return
     */
    public boolean updateRemindCustomer(Integer cid,Integer value);

    /**
     *修改余额可以几天提醒客户
     * @param cid
     * @param value
     * @return
     */
    public boolean updateAhead(Integer cid,Integer value);

    /**
     * 查找客户邮箱
     * @param map
     * @return
     */
    public List<CustomerCompanyEmail> queryEmailById(Map<String,Object> map);

    /**
     * 删除邮箱
     * @param id
     * @param cid
     * @return
     */
    public boolean deleteEmail(Integer id,Integer cid);

    /**
     * 添加邮箱
     * @param email
     * @return
     */
    public boolean addEmail(CustomerCompanyEmail email);

    /**
     * 查询所有合作伙伴
     * @return
     */
    public List<Partner> queryAllPartner();

}
