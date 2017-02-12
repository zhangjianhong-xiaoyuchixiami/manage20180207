package org.qydata.mapper;

import org.qydata.entity.PartnerWeekMonthAmount;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/5.
 */
public interface PartnerWeekMonthAmountMapper {

    /**
     * 统计合作公司每周的付款数据
     * @return
     * @throws Exception
     */
    public List<PartnerWeekMonthAmount> getAllPartnerWeekPaymentRecord(Integer result)throws Exception;

    /**
     * 统计合作公司每月的付款数据
     * @return
     * @throws Exception
     */
    public List<PartnerWeekMonthAmount> getAllPartnerMonthPaymentRecord(Integer result)throws Exception;

    /**
     * 插入合作公司每周的数据
     * @param weekAmountList
     * @return
     * @throws Exception
     */
    public boolean addWeekRecord(List<PartnerWeekMonthAmount> weekAmountList)throws Exception;

    /**
     * 插入合作公司每月的数据
     * @param monthAmountList
     * @return
     * @throws Exception
     */
    public boolean addMonthRecord(List<PartnerWeekMonthAmount> monthAmountList)throws Exception;

    /**
     *统计合作公司每周的收款数据
     * @return
     * @throws Exception
     */
    public List<PartnerWeekMonthAmount> getAllPartnerWeekReceiptRecord(Integer result)throws Exception;

    /**
     * 统计合作公司每月的收款数据
     * @return
     * @throws Exception
     */
    public List<PartnerWeekMonthAmount> getAllPartnerMonthReceiptRecord(Integer result)throws Exception;

    /**
     * 删除合作伙伴周的数据
     * @param map
     * @return
     * @throws Exception
     */
    public boolean deletePartnerWeekRecord(Map<String,Object> map)throws Exception;

    /**
     * 删除合作伙伴月的数据
     * @param map
     * @return
     * @throws Exception
     */
    public boolean deletePartnerMonthRecord(Map<String,Object> map)throws Exception;
}
