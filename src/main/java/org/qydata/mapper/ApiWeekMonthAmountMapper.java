package org.qydata.mapper;

import org.qydata.entity.ApiWeekMonthAmount;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/12.
 */
public interface ApiWeekMonthAmountMapper {

    /**
     * 删除Api周月的数据
     * @param map
     * @return
     * @throws Exception
     */
    public boolean deleteApiRecord(Map<String,Object> map)throws Exception;

    /**
     * 插入Api每周的数据
     * @param weekAmountList
     * @return
     * @throws Exception
     */
    public boolean addApiWeekRecord(List<ApiWeekMonthAmount> weekAmountList)throws Exception;

    /**
     * 插入Api每月的数据
     * @param monthAmountList
     * @return
     * @throws Exception
     */
    public boolean addApiMonthRecord(List<ApiWeekMonthAmount> monthAmountList)throws Exception;

    /**
     * 统计Api每周的消费数据
     * @return
     * @throws Exception
     */
    public List<ApiWeekMonthAmount> getAllApiWeekConsumeRecord()throws Exception;

    /**
     * 统计Api每月的消费数据
     * @return
     * @throws Exception
     */
    public List<ApiWeekMonthAmount> getAllApiMonthConsumeRecord()throws Exception;

}
