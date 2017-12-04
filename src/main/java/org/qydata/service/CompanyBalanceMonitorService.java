package org.qydata.service;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.entity.CustomerCompanyEmail;
import org.qydata.entity.Partner;
import org.qydata.entity.monitor.CompanyBalanceMonitor;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/22.
 */
public interface CompanyBalanceMonitorService {

    /**
     * 查询客户余额报警列表
     * @param map
     * @return
     */
    @SystemServiceLog(description = "查询客户余额报警列表")
    public List<CompanyBalanceMonitor> queryCompanyBalanceMonitor(Map<String,Object> map);

    /**
     *修改是否预付
     * @param cid
     * @param value
     * @return
     */
    @SystemServiceLog(description = "修改是否预付")
    public boolean updatePrepay(Integer cid,Integer value);

    /**
     *修改是否报警
     * @param cid
     * @param value
     * @return
     */
    @SystemServiceLog(description = "修改是否报警")
    public boolean updateAlarm(Integer cid,Integer value);

    /**
     *修改是否提醒客户
     * @param cid
     * @param value
     * @return
     */
    @SystemServiceLog(description = "修改是否提醒客户")
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
    @SystemServiceLog(description = "查询客户邮箱")
    public List<CustomerCompanyEmail> queryEmailById(Map<String,Object> map);

    /**
     * 删除邮箱
     * @param id
     * @param cid
     * @return
     */
    @SystemServiceLog(description = "删除客户邮箱")
    public boolean deleteEmail(Integer id,Integer cid);

    /**
     * 添加邮箱
     * @param cid
     * @param email
     * @return
     */
    @SystemServiceLog(description = "添加客户邮箱")
    public boolean addEmail(Integer cid,String email);

    /**
     * 查询所有合作伙伴
     * @return
     */
    @SystemServiceLog(description = "查询所有合作伙伴")
    public List<Partner> queryAllPartner();

}
