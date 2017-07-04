package org.qydata.mapper;

import org.qydata.dst.ApiFinance;
import org.qydata.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/12.
 */
public interface ApiFinanceMapper {

 /**
  *Api财务总览
  * @param map
  * @return
  */
 public List<ApiFinance> queryApiOverAllFinance(Map<String,Object> map);

 /**
  *查询当月消费（至昨天）
  * @param map
  * @return
  */
 public List<ApiFinance> queryApiCurrMonthConsume(Map<String,Object> map);

 /**
  *查询当天使用量
  * @param map
  * @return
  */
 public List<ApiFinance> queryApiCurrDayUsage(Map<String,Object> map);

 /**
  *查询当天扣费量
  * @param map
  * @return
  */
 public List<ApiFinance> queryApiCurrDayFee(Map<String,Object> map);

 /**
  *查询消费总额（至昨天）
  * @param map
  * @return
  */
 public List<ApiFinance> queryApiConsumeTotle(Map<String,Object> map);

 /**
  *查询上周消费总额
  * @param map
  * @return
  */
 public List<ApiFinance> queryApiLastWeekConsume(Map<String,Object> map);

 /**
  *查询上月消费总额
  * @param map
  * @return
  */
 public List<ApiFinance> queryApiLastMonthConsume(Map<String,Object> map);


 /**
  *查询Api消费详情
  * @param map
  * @return
  * @throws Exception
  */
 public List<ApiRequestLog> queryApiDetailById(Map<String,Object> map) throws Exception;

 /**
  * 统计Api消费详情记录金额总计
  * @param map
  * @return
  * @throws Exception
  */
 public Integer getCountApiDetailById(Map<String,Object> map) throws Exception;

 /**
  * 以APIVendor统计消费信息
  * @return
  * @throws Exception
  */
 public List<ApiFinance> queryApiVendor(Map<String,Object> map)throws Exception;

 /**
  * 查询APIVendor各类型消费情况
  * @param map
  * @return
  */
 public List<ApiFinance> queryApiVendorType(Map<String,Object> map);


 /**
  * 以APIVendor统计消费信息--周消费总额
  * @param map
  * @return
  * @throws Exception
  */
 public Integer getCountWeekApiVendor(Map<String,Object> map) throws Exception;

 /**
  * 以APIVendor统计消费信息--月消费总额
  * @param map
  * @return
  * @throws Exception
  */
 public Integer getCountMonthApiVendor(Map<String,Object> map) throws Exception;


 /**
  * ApiVendor充值

  * @return
  * @throws Exception
  */
 public boolean updateApiVendorBalance(Integer vendorId,Long balance ) throws Exception;

 /**
  * ApiVendor充值充值记录
  * @return
  * @throws Exception
  */
 public boolean insertApiVendorBalanceLog(ApiVendorBalanceLog apiVendorBalanceLog) throws Exception;

 /**
  * 查询ApiVendor余额
  * @param vendorId
  * @return
  * @throws Exception
  */
 public ApiVendorBalance queryApiVendorBalance(Integer vendorId)throws Exception;

 /**
  * 插入ApiVendorBalance
  * @param apiVendorBalance
  * @return
  * @throws Exception
  */
 public boolean insertApiVendorBalance(ApiVendorBalance apiVendorBalance)throws Exception;


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
 public List<ApiVendor> queryApiVendorName(Map<String,Object> map)throws Exception;



}
