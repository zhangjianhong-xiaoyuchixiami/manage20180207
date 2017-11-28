package org.qydata.mapper;

import org.qydata.dst.ApiFinance;
import org.qydata.dst.vendor.VendorFinance;
import org.qydata.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/12.
 */
public interface VendorFinanceMapper {

 /**
  * 统计供应商消费信息
  * @return
  * @throws Exception
  */
 public List<VendorFinance> queryVendor(Map<String, Object> map);

 /**
  * 查询供应商消费总额（至昨天）
  * @return
  * @throws Exception
  */
 public List<VendorFinance> queryVendorConsume(Map<String, Object> map);

 /**
  * 统计供应商上周消费
  * @return
  * @throws Exception
  */
 public List<VendorFinance> queryVendorLastWeekConsume(Map<String, Object> map);


 /**
  * 统计供应商上月消费
  * @return
  * @throws Exception
  */
 public List<VendorFinance> queryVendorLastMonthConsume(Map<String, Object> map);

 /**
  * 统计供应商当月消费
  * @return
  * @throws Exception
  */
 public List<VendorFinance> queryVendorCurrMonthConsume(Map<String, Object> map);

 /**
  * 统计供应商当天消费
  * @return
  * @throws Exception
  */
 public List<VendorFinance> queryVendorCurrDayConsume(Map<String, Object> map);

 /**
  * 查询APIVendor各类型消费情况
  * @param map
  * @return
  */
 public List<VendorFinance> queryVendorTypeConsume(Map<String,Object> map);

 /**
  * 通过apiTypeId查询供应商类型
  */
 public List<ApiVendor> queryApiVendorName();

}
