package org.qydata.service;

import org.qydata.dst.ApiWeb;
import org.qydata.entity.ApiVendor;
import org.qydata.entity.Partner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/30.
 */
public interface VendorHistoryBillService {

    /**
     * 查询供应商历史财务账单
     * @param map
     * @return
     */
    public Map<String,Object> queryVendorHistoryBill(Map<String,Object> map);

    /**
     * 查询所有供应商
     * @return
     */
    public List<ApiVendor> queryAllVendor();

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
     * 查询供应商历史财务账单明细
     * @param map
     * @return
     */
    public Map<String,Object> queryVendorHistoryBillDetail(Map<String,Object> map);

    /**
     * 根据供应商Id查询产品
     * @param vid
     * @return
     */
    public List<ApiWeb> queryApiByVendorId(Integer vid);

    /**
     * 修改单价
     * @param id
     * @param newCost
     * @return
     */
    @Transactional
    public boolean updateVendorHistoryBillCost(Integer id,Double oldCost,Double newCost,String content);

    /**
     * 修改扣费量
     * @param id
     * @param oldAmount
     * @return
     */
    @Transactional
    public boolean updateVendorHistoryBillAmount(Integer id,Integer oldAmount,Integer newAmount,String content);

    /**
     * 修改锁定/解锁状态
     * @param id
     * @param isLock
     * @return
     */
    public boolean updateVendorHistoryBillIsLock(String [] id,Integer isLock);

    /**
     * 新增历史记录
     * @param vid
     * @return
     */
    public boolean addVendorHistoryBill(Integer vid,Integer aid,Double cost,Integer amount,String yearMonth);

    /**
     * 删除历史记录
     * @param id
     * @return
     */
    public boolean deleteVendorHistoryBill(String [] id);

    /**
     * 根据Id查看锁定状态
     * @param id
     * @return
     */
    public Integer queryVendorHistoryBillLockById(Integer id);

}
