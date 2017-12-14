package org.qydata.service.impl;

import org.qydata.dst.CustomerCacheApiTypeConsume;
import org.qydata.dst.CustomerCacheConsume;
import org.qydata.mapper.mapper1.CustomerCacheFinanceMapper;
import org.qydata.mapper.mapper2.CustomerCacheFinanceSelectMapper;
import org.qydata.service.CustomerCacheFinanceService;
import org.qydata.tools.CalendarTools;
import org.qydata.tools.date.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/6/26.
 */
@Service
public class CustomerCacheFinanceServiceImpl implements CustomerCacheFinanceService {

    @Autowired
    private CustomerCacheFinanceSelectMapper customerCacheFinanceSelectMapper;

    @Override
    public List<CustomerCacheConsume> queryCustomerCacheConsume(Map<String, Object> map) {
        Map<String,Object> mapCurrMonth = new HashMap<>();
        Map<String,Object> mapCurrDay = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mapCurrMonth.put("time",CalendarTools.getCurrentMonthFirstDay() + " " + "00:00:00");
        mapCurrDay.put("time",sdf.format(new Date()) + " " + "00:00:00");
        //查询客户使用缓存列表
        List<CustomerCacheConsume> customerCacheConsumeList = customerCacheFinanceSelectMapper.queryCustomerCacheConsume(map);
        //查询客户各产品类型缓存调用情况（开通后至昨天）
        List<CustomerCacheConsume> customerCacheApiTypeConsumeList = customerCacheFinanceSelectMapper.queryCustomerApiTypeCacheConsume(map);
        //查询客户各产品类型缓存调用情况（今天）
        //List<CustomerCacheConsume> customerCacheApiTypeConsumeListCurrDay = customerCacheFinanceSelectMapper.queryCustomerApiTypeCacheConsumeCurrDay(map);
        //统计客户缓存调用总数（开通后至昨天）
        List<CustomerCacheConsume> customerTotleCacheConsumeList = customerCacheFinanceSelectMapper.queryCustomerTotleCacheConsume(map);
        //查询客户当月缓存调用情况
        List<CustomerCacheConsume> customerCacheConsumeListCurrMonth = customerCacheFinanceSelectMapper.queryCustomerCacheConsumeByCurrMonth(mapCurrMonth);
        //查询客户当天缓存调用情况
        List<CustomerCacheConsume> customerCacheConsumeListCurrDay = customerCacheFinanceSelectMapper.queryCustomerCacheConsumeByCurrDay(mapCurrDay);

        if (customerCacheConsumeList != null){
            for (int i = 0; i < customerCacheConsumeList.size() ; i++) {
                CustomerCacheConsume customerCacheConsume = customerCacheConsumeList.get(i);

                //当天
                if (customerCacheConsumeListCurrDay != null){
                    for (int j = 0; j < customerCacheConsumeListCurrDay.size() ; j++) {
                        CustomerCacheConsume customerCacheConsumeCurrDay = customerCacheConsumeListCurrDay.get(j);
                        if (customerCacheConsume.getCustomerId() == customerCacheConsumeCurrDay.getCustomerId()){
                            customerCacheConsume.setCurrDayCacheCount(customerCacheConsumeCurrDay.getCostCount() - customerCacheConsumeCurrDay.getCostUpCount());
                        }
                    }
                }

                //当月(到昨天)
                if (customerCacheConsumeListCurrMonth != null){
                    for (int j = 0; j < customerCacheConsumeListCurrMonth.size() ; j++) {
                        CustomerCacheConsume customerCacheConsumeCurrMonth = customerCacheConsumeListCurrMonth.get(j);
                        if (customerCacheConsume.getCustomerId() == customerCacheConsumeCurrMonth.getCustomerId()){
                            customerCacheConsume.setCurrMonthCacheCount(customerCacheConsumeCurrMonth.getCostCount() - customerCacheConsumeCurrMonth.getCostUpCount());
                        }
                    }
                }

                //当月(到昨天)+当天
                if (customerCacheConsume.getCurrMonthCacheCount() != null ) {
                    if (customerCacheConsume.getCurrDayCacheCount() !=null){
                        customerCacheConsume.setCurrMonthCacheCount(customerCacheConsume.getCurrMonthCacheCount() + customerCacheConsume.getCurrDayCacheCount());
                    }
                }else {
                    if (customerCacheConsume.getCurrDayCacheCount() !=null){
                        customerCacheConsume.setCurrMonthCacheCount(customerCacheConsume.getCurrDayCacheCount());
                    }
                }

                //缓存使用量（到昨天）
                if (customerTotleCacheConsumeList != null) {
                    for (int j = 0; j < customerTotleCacheConsumeList.size(); j++) {
                        CustomerCacheConsume customerTotleCacheConsume = customerTotleCacheConsumeList.get(j);
                        if (customerCacheConsume.getCustomerId() == customerTotleCacheConsume.getCustomerId()) {
                            customerCacheConsume.setCacheCount(customerTotleCacheConsume.getCostCount() - customerTotleCacheConsume.getCostUpCount());
                        }
                    }
                }

                //缓存使用量（到昨天）+ 当天
                if (map.get("endDate") == null || CalendarUtil.getCurrTimeAfterDay().equals(map.get("endDate")))
                {
                    if (customerCacheConsume.getCacheCount() != null){
                        if (customerCacheConsume.getCurrDayCacheCount() != null){
                            customerCacheConsume.setCacheCount(customerCacheConsume.getCacheCount() + customerCacheConsume.getCurrDayCacheCount());
                        }
                    }else {
                        if (customerCacheConsume.getCurrDayCacheCount() != null){
                            customerCacheConsume.setCacheCount(customerCacheConsume.getCurrDayCacheCount());
                        }
                    }
                }

                //封装各产品类型使用缓存量
                if (customerCacheApiTypeConsumeList != null){
                    for (int j = 0; j < customerCacheApiTypeConsumeList.size(); j++) {
                        CustomerCacheConsume customerApiTypeCacheConsume = customerCacheApiTypeConsumeList.get(j);
                        if (customerCacheConsume.getCustomerId() == customerApiTypeCacheConsume.getCustomerId()){
                            customerCacheConsume.setCustomerCacheApiTypeConsumeList(customerApiTypeCacheConsume.getCustomerCacheApiTypeConsumeList());
                        }
                    }
                }
            }
        }
        return customerCacheConsumeList;
    }

    @Override
    public List<CustomerCacheApiTypeConsume> queryCustomerCurrDayApiTypeCacheConsume(Map<String, Object> map) {
        return customerCacheFinanceSelectMapper.queryCustomerCurrDayApiTypeCacheConsume(map);
    }
}
