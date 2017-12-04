package org.qydata.service.impl;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.dst.ApiFinance;
import org.qydata.dst.ApiTypeConsume;
import org.qydata.entity.ApiType;
import org.qydata.entity.ApiVendor;
import org.qydata.entity.ApiVendorBalance;
import org.qydata.entity.ApiVendorBalanceLog;
import org.qydata.mapper.ApiFinanceMapper;
import org.qydata.service.ApiFinanceService;
import org.qydata.tools.CalendarTools;
import org.qydata.tools.date.CalendarUtil;
import org.qydata.tools.finance.ApiTypeMobileOperatorNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jonhn on 2017/1/17.
 */
@Service
public class ApiFinanceServiceImpl implements ApiFinanceService {

    @Autowired private ApiFinanceMapper mapper;

    @Override
    @SystemServiceLog(description = "Api财务总览")
    public Map<String,Object> queryApiFinance(Map<String, Object> map){
        Map<String,Object> param = new HashMap<>();
        if (map != null){
            for (Map.Entry<String,Object> me : map.entrySet()) {
                if (me.getKey().equals("vendorId")){
                    param.put("vendorId",me.getValue());
                }
                if (me.getKey().equals("apiTypeId")){
                    param.put("apiTypeId",me.getValue());
                }
                if (me.getKey().equals("beginDate")){
                    param.put("beginDate",me.getValue());
                }
                if (me.getKey().equals("endDate")){
                    param.put("endDate",me.getValue());
                }
                if (me.getKey().equals("statusList")){
                    param.put("statusList",me.getValue());
                }
            }
        }
        param.put("currDayTime", CalendarTools.getCurrentDate()+ " " + "00:00:00");
        param.put("currMonthTime",CalendarTools.getCurrentMonthFirstDay() + " " + "00:00:00");
        /*Api财务总览*/
        List<ApiFinance> financeList = mapper.queryApiFinance(param);
        /*查询当月消费（至昨天）*/
        List<ApiFinance> currMonthList = mapper.queryApiCurrMonthConsume(param);
        /*查询当天使用量*/
        List<ApiFinance> currDayUuserList = mapper.queryApiCurrDayUsage(param);
        /*查询当天扣费量*/
        List<ApiFinance> currDayFeeList = mapper.queryApiCurrDayFee(param);
        /*查询消费总额（至昨天）*/
        List<ApiFinance> consumeList = mapper.queryApiConsumeTotle(param);
        /*查询上周消费总额*/
        List<ApiFinance> lastWeekList = mapper.queryApiLastWeekConsume(param);
        /*查询上月消费总额*/
        List<ApiFinance> lastMonthList = mapper.queryApiLastMonthConsume(param);

        if (financeList == null){
            return null;
        }
        double lastWeekConsume = 0.0;
        double lastMonthConsume = 0.0;
        double currMonthConsume = 0.0;
        double currDayConsume = 0.0;
        double totleConsume = 0.0;
        for (ApiFinance finance : financeList) {
            if (finance != null) {
                  /*封装类型和子类型名称*/
                if (finance.getApiTypeId() != null){
                    String apiTypeName_stidName = ApiTypeMobileOperatorNameUtils.apiTypeMobileOperatorName(finance.getApiTypeName(),finance.getMobileList());
                    finance.setApiTypeName(apiTypeName_stidName);
                }

                     /*封装当天使用量*/
                if (currDayUuserList != null){
                    for (ApiFinance consume : currDayUuserList) {
                        if (finance.getApiId() == consume.getApiId()
                                || finance.getApiId().equals(consume.getApiId()))
                        {
                            if (consume.getCurrDayUserCount() != null){
                                finance.setCurrDayUserCount(consume.getCurrDayUserCount());
                            }
                        }
                    }
                }

                    /*封装当天扣费量和消费金额*/
                if (currDayFeeList != null){
                    for (ApiFinance consume : currDayFeeList) {
                        if (finance.getApiId() == consume.getApiId()
                                || finance.getApiId().equals(consume.getApiId()))
                        {
                            if (consume.getCurrDayFeeCount() != null){
                                finance.setCurrDayFeeCount(consume.getCurrDayFeeCount());
                            }
                            if (consume.getCurrDayConsume() != null){
                                finance.setCurrDayConsume(consume.getCurrDayConsume()/100.0);
                            }
                        }
                    }
                }

                    /*封装当月消费（至昨天）*/
                if (currMonthList != null){
                    for (ApiFinance consume : currMonthList) {
                        if (finance.getApiId() == consume.getApiId()
                                || finance.getApiId().equals(consume.getApiId()))
                        {
                            if (consume.getCurrMonthConsume() != null){
                                finance.setCurrMonthConsume(consume.getCurrMonthConsume()/100.0);
                            }
                        }
                    }
                }

                   /*封装上周消费*/
                if (lastWeekList != null){
                    for (ApiFinance consume : lastWeekList) {
                        if (finance.getApiId() == consume.getApiId()
                                || finance.getApiId().equals(consume.getApiId()))
                        {
                            if (consume.getLastWeekConsume() != null){
                                finance.setLastWeekConsume(consume.getCurrMonthConsume()/100.0);
                            }
                        }
                    }
                }

                    /*封装上月消费*/
                if (lastMonthList != null){
                    for (ApiFinance consume : lastMonthList) {
                        if (finance.getApiId() == consume.getApiId()
                                || finance.getApiId().equals(consume.getApiId()))
                        {
                            if (consume.getLastMonthConsume() != null){
                                finance.setLastMonthConsume(consume.getLastMonthConsume()/100.0);
                            }
                        }
                    }
                }

                     /*封装消费总额（至昨天）*/
                if (consumeList != null){
                    for (ApiFinance consume : consumeList) {
                        if (finance.getApiId() == consume.getApiId()
                                || finance.getApiId().equals(consume.getApiId()))
                        {
                            if (consume.getConsume() != null){
                                finance.setConsume(consume.getConsume()/100.0);
                            }
                            if (consume.getUserCount() != null){
                                finance.setUserCount(consume.getUserCount());
                            }
                            if (consume.getFeeCount() != null){
                                finance.setFeeCount(consume.getFeeCount());
                            }
                        }
                    }
                }

                     /*封装当月消费总额（昨天 + 今天）*/
                if (finance.getCurrMonthConsume() != null){
                    if (finance.getCurrDayConsume() != null) {
                        finance.setCurrMonthConsume(finance.getCurrMonthConsume() + finance.getCurrDayConsume());
                    }
                }else {
                    if (finance.getCurrDayConsume() != null) {
                        finance.setCurrMonthConsume(finance.getCurrDayConsume());
                    }
                }

                    /*封装消费总额（昨天 + 今天）*/
                if (map.get("endDate") == null || CalendarUtil.getCurrTimeAfterDay().equals(map.get("endDate")))
                {
                    if (finance.getConsume() != null){
                        if (finance.getCurrDayConsume() != null){
                            finance.setConsume(finance.getConsume() + finance.getCurrDayConsume());
                        }
                        if (finance.getCurrDayUserCount() != null){
                            finance.setUserCount(finance.getUserCount() + finance.getCurrDayUserCount());
                        }
                        if (finance.getCurrDayFeeCount() != null){
                            finance.setFeeCount(finance.getFeeCount() + finance.getCurrDayFeeCount());
                        }
                    }else {
                        if (finance.getCurrDayConsume() != null){
                            finance.setConsume(finance.getCurrDayConsume());
                        }
                        if (finance.getCurrDayUserCount() != null){
                            finance.setUserCount(finance.getCurrDayUserCount());
                        }
                        if (finance.getCurrDayFeeCount() != null){
                            finance.setFeeCount(finance.getCurrDayFeeCount());
                        }
                    }
                }
                if (finance.getLastWeekConsume() != null){
                    lastWeekConsume += finance.getLastWeekConsume();
                }
                if (finance.getLastMonthConsume() != null){
                    lastMonthConsume += finance.getLastMonthConsume();
                }
                if (finance.getCurrMonthConsume() != null){
                    currMonthConsume += finance.getCurrMonthConsume();
                }
                if (finance.getCurrDayConsume() != null){
                    currDayConsume += finance.getCurrDayConsume();
                }
                if (finance.getCurrDayConsume() != null){
                    totleConsume += finance.getConsume();
                }
            }
        }
        Map<String,Object> resu = new HashMap<>();
        resu.put("financeList",financeList);
        resu.put("lastWeekConsume",lastWeekConsume);
        resu.put("lastMonthConsume",lastMonthConsume);
        resu.put("currMonthConsume",currMonthConsume);
        resu.put("currDayConsume",currDayConsume);
        resu.put("totleConsume",totleConsume);
        return resu;
    }

    @Override
    @SystemServiceLog(description = "查询Api类型")
    public List<ApiType> queryApiType() {
        try {
            return mapper.queryApiType();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @SystemServiceLog(description = "查询供应商类型")
    public List<ApiVendor> queryApiVendorName(Map<String, Object> map) {
        try {
            return mapper.queryApiVendorName(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
