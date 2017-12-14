package org.qydata.mapper.mapper2;

import org.qydata.dst.ApiFinance;
import org.qydata.entity.ApiType;
import org.qydata.entity.ApiVendor;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/12.
 */
public interface ApiFinanceSelectMapper {

 /**
  *Api财务总览
  * @param map
  * @return
  */
 public List<ApiFinance> queryApiFinance(Map<String, Object> map);

 /**
  *查询当月消费（至昨天）
  * @param map
  * @return
  */
 public List<ApiFinance> queryApiCurrMonthConsume(Map<String, Object> map);

 /**
  *查询当天使用量
  * @param map
  * @return
  */
 public List<ApiFinance> queryApiCurrDayUsage(Map<String, Object> map);

 /**
  *查询当天扣费量
  * @param map
  * @return
  */
 public List<ApiFinance> queryApiCurrDayFee(Map<String, Object> map);

 /**
  *查询消费总额（至昨天）
  * @param map
  * @return
  */
 public List<ApiFinance> queryApiConsumeTotle(Map<String, Object> map);

 /**
  *查询上周消费总额
  * @param map
  * @return
  */
 public List<ApiFinance> queryApiLastWeekConsume(Map<String, Object> map);

 /**
  *查询上月消费总额
  * @param map
  * @return
  */
 public List<ApiFinance> queryApiLastMonthConsume(Map<String, Object> map);


 /**
  * 查询Api类型
  * @return
  * @throws Exception
  */
 public List<ApiType> queryApiType()throws Exception;

 /**
  * 通过apiTypeId查询供应商类型
  * @param map
  * @return
  * @throws Exception
  */
 public List<ApiVendor> queryApiVendorName(Map<String, Object> map)throws Exception;

}
