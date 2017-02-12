package org.qydata.mapper;

import org.qydata.entity.WeekMonthAmount;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/5.
 */
public interface WeekMonthAmountMapper {

    /**
     * 统计客户每周的充值数据
     * @return
     * @throws Exception
     */
    public List<WeekMonthAmount> getAllCustomerWeekRechargeRecord(Integer result)throws Exception;

    /**
     * 统计客户每月的充值数据
     * @return
     * @throws Exception
     */
    public List<WeekMonthAmount> getAllCustomerMonthRechargeRecord(Integer result)throws Exception;

    /**
     * 删除客户周的数据
     * @param map
     * @return
     * @throws Exception
     */
    public boolean deleteWeekRecord(Map<String,Object> map)throws Exception;

    /**
     * 删除客户月的数据
     * @param map
     * @return
     * @throws Exception
     */
    public boolean deleteMonthRecord(Map<String,Object> map)throws Exception;

    /**
     * 插入客户每周的数据
     * @param weekAmountList
     * @return
     * @throws Exception
     */
    public boolean addWeekRecord(List<WeekMonthAmount> weekAmountList)throws Exception;

    /**
     * 插入客户每月的数据
     * @param monthAmountList
     * @return
     * @throws Exception
     */
    public boolean addMonthRecord(List<WeekMonthAmount> monthAmountList)throws Exception;

    /**
     * 统计客户每周的Api消费数据
     * @return
     * @throws Exception
     */
    public List<WeekMonthAmount> getAllCustomerApiWeekConsumeRecord(Integer result)throws Exception;

    /**
     * 统计客户每月的Api消费数据
     * @return
     * @throws Exception
     */
    public List<WeekMonthAmount> getAllCustomerApiMonthConsumeRecord(Integer result)throws Exception;




}
