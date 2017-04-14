package org.qydata.service.impl;

import org.qydata.config.annotation.DataSourceService;
import org.qydata.entity.ApiWeekMonthAmount;
import org.qydata.mapper.ApiWeekMonthAmountMapper;
import org.qydata.service.ApiWeekMonthAmountService;
import org.qydata.tools.CalendarTools;
import org.qydata.tools.IpTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jonhn on 2017/1/12.
 */
@Service
public class ApiWeekMonthAmountServiceImpl implements ApiWeekMonthAmountService {

    @Autowired
    private ApiWeekMonthAmountMapper apiWeekMonthAmountMapper;

    @Override
    @DataSourceService
    public boolean addAllApiWeekConsumeRecordAndAddApiWeekMonthAmount(Integer result) throws Exception {
        Map<String,Object> map = new HashMap();
        map.put("year",CalendarTools.getYearCount(result));
        map.put("month",CalendarTools.getMonthWeekCount(result));
        map.put("week",CalendarTools.getYearWeekCount(result));
        map.put("tableId",3);
        map.put("typeId",1);
        apiWeekMonthAmountMapper.deleteApiWeekRecord(map);
        List<ApiWeekMonthAmount> apiWeekMonthAmountList = apiWeekMonthAmountMapper.getAllApiWeekConsumeRecord(result);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<ApiWeekMonthAmount> apiWeekMonthAmounts = new ArrayList<>();
        Iterator<ApiWeekMonthAmount> iterator = apiWeekMonthAmountList.iterator();
        while (iterator.hasNext()) {
            ApiWeekMonthAmount apiWeekMonthAmount = iterator.next();
            Integer tableId = 3;
            Integer typeId = 1;
            ApiWeekMonthAmount apiWeekMonthAmountOne = new ApiWeekMonthAmount();
            apiWeekMonthAmountOne.setApiId(apiWeekMonthAmount.getApiId());
            apiWeekMonthAmountOne.setTableId(tableId);
            apiWeekMonthAmountOne.setTypeId(typeId);
            if(apiWeekMonthAmount.getYears() == null){
                apiWeekMonthAmountOne.setYears(CalendarTools.getYearCount(result));
                apiWeekMonthAmountOne.setMonths(CalendarTools.getMonthWeekCount(result));
                apiWeekMonthAmountOne.setWeeks(CalendarTools.getYearWeekCount(result));
                apiWeekMonthAmountOne.setTotleCost(0L);
                apiWeekMonthAmountOne.setBeginTime(CalendarTools.getYearWeekFirstDay(CalendarTools.getYearCount(result),CalendarTools.getYearWeekCount(result)));
                apiWeekMonthAmountOne.setEndTime(CalendarTools.getYearWeekEndDay(CalendarTools.getYearCount(result),CalendarTools.getYearWeekCount(result)));
            }else{
                List<String> temporalList = IpTool.spiltStr(apiWeekMonthAmount.getTemporal());
                apiWeekMonthAmountOne.setYears(apiWeekMonthAmount.getYears());
                apiWeekMonthAmountOne.setMonths(CalendarTools.getMonthWeekCount(result));
                apiWeekMonthAmountOne.setWeeks(apiWeekMonthAmount.getWeeks());
                apiWeekMonthAmountOne.setTotleCost(apiWeekMonthAmount.getTotleCost());
                apiWeekMonthAmountOne.setBeginTime(sdf.parse(temporalList.get(0)));
                apiWeekMonthAmountOne.setEndTime(sdf.parse(temporalList.get(temporalList.size()-1)));
            }
            apiWeekMonthAmounts.add(apiWeekMonthAmountOne);
        }
        return apiWeekMonthAmountMapper.addApiWeekRecord(apiWeekMonthAmounts);
    }

    @Override
    @DataSourceService
    public boolean addAllApiMonthConsumeRecordAndAddApiWeekMonthAmount(Integer result) throws Exception {
        Map<String,Object> map = new HashMap();
        map.put("year",CalendarTools.getYearMonthCount(result));
        map.put("month",CalendarTools.getMonthCount(result));
        map.put("tableId",3);
        map.put("typeId",2);
        apiWeekMonthAmountMapper.deleteApiMonthRecord(map);
        List<ApiWeekMonthAmount> apiWeekMonthAmountList = apiWeekMonthAmountMapper.getAllApiMonthConsumeRecord(result);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<ApiWeekMonthAmount> apiWeekMonthAmounts = new ArrayList<>();
        Iterator<ApiWeekMonthAmount> iterator = apiWeekMonthAmountList.iterator();
        while (iterator.hasNext()){
            ApiWeekMonthAmount apiWeekMonthAmount = iterator.next();
            Integer tableId = 3;
            Integer typeId = 2;
            ApiWeekMonthAmount apiWeekMonthAmountOne = new ApiWeekMonthAmount();
            apiWeekMonthAmountOne.setApiId(apiWeekMonthAmount.getApiId());
            apiWeekMonthAmountOne.setTableId(tableId);
            apiWeekMonthAmountOne.setTypeId(typeId);
            if(apiWeekMonthAmount.getYears() == null){
                apiWeekMonthAmountOne.setYears(CalendarTools.getYearMonthCount(result));
                apiWeekMonthAmountOne.setMonths(CalendarTools.getMonthCount(result));
                apiWeekMonthAmountOne.setTotleCost(0L);
                apiWeekMonthAmountOne.setBeginTime(CalendarTools.getYearMonthFirstDay(CalendarTools.getYearMonthCount(result),CalendarTools.getMonthCount(result)));
                apiWeekMonthAmountOne.setEndTime(CalendarTools.getYearMonthEndDay(CalendarTools.getYearMonthCount(result),CalendarTools.getMonthCount(result)));
            }else{
                List<String> temporalList = IpTool.spiltStr(apiWeekMonthAmount.getTemporal());
                apiWeekMonthAmountOne.setYears(apiWeekMonthAmount.getYears());
                apiWeekMonthAmountOne.setMonths(apiWeekMonthAmount.getMonths());
                apiWeekMonthAmountOne.setTotleCost(apiWeekMonthAmount.getTotleCost());
                apiWeekMonthAmountOne.setBeginTime(sdf.parse(temporalList.get(0)));
                apiWeekMonthAmountOne.setEndTime(sdf.parse(temporalList.get(temporalList.size()-1)));
            }
            apiWeekMonthAmounts.add(apiWeekMonthAmountOne);
        }
        return apiWeekMonthAmountMapper.addApiMonthRecord(apiWeekMonthAmounts);
    }
}
