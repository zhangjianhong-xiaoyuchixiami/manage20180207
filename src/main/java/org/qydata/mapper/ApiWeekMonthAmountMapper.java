package org.qydata.mapper;

import org.qydata.entity.ApiWeekMonthAmount;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/12.
 */
public interface ApiWeekMonthAmountMapper {

    /**
     * 删除Api周的数据
     * @param map
     * @return
     * @throws Exception
     */
    public boolean deleteApiWeekRecord(Map<String,Object> map)throws Exception;

    /**
     * 删除Api月的数据
     * @param map
     * @return
     * @throws Exception
     */
    public boolean deleteApiMonthRecord(Map<String,Object> map)throws Exception;

    /**
     * 插入Api每周的数据
     * @param apiWeekMonthAmountList
     * @return
     * @throws Exception
     */
    public boolean addApiWeekRecord(List<ApiWeekMonthAmount> apiWeekMonthAmountList)throws Exception;

    /**
     * 插入Api每月的数据
     * @param apiWeekMonthAmountList
     * @return
     * @throws Exception
     */
    public boolean addApiMonthRecord(List<ApiWeekMonthAmount> apiWeekMonthAmountList)throws Exception;

    /**
     * 统计Api每周的消费数据
     * @return
     * @throws Exception
     */
    public List<ApiWeekMonthAmount> getAllApiWeekConsumeRecord(Integer result)throws Exception;

    /**
     * 统计Api每月的消费数据
     * @return
     * @throws Exception
     */
    public List<ApiWeekMonthAmount> getAllApiMonthConsumeRecord(Integer result)throws Exception;

}
