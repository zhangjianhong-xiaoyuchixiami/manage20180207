package org.qydata.mapper;

import org.qydata.entity.ApiWeekMonthAmount;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/12.
 */
public interface ApiFinanceMapper {

    /**
     *
     * @param map
     * @return
     * @throws Exception
     */
   public List<ApiWeekMonthAmount> queryApiOverAllFinance(Map<String,Object> map)throws Exception;
}
