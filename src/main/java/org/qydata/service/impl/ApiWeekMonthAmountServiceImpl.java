package org.qydata.service.impl;

import org.apache.commons.collections.map.HashedMap;
import org.qydata.entity.ApiWeekMonthAmount;
import org.qydata.mapper.ApiWeekMonthAmountMapper;
import org.qydata.service.ApiWeekMonthAmountService;
import org.qydata.tools.IpTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/12.
 */
@Service
public class ApiWeekMonthAmountServiceImpl implements ApiWeekMonthAmountService {

    @Autowired
    private ApiWeekMonthAmountMapper apiWeekMonthAmountMapper;

    @Override
    public boolean getAllApiWeekConsumeRecordAndAddApiWeekMonthAmount() throws Exception {
        Map<String,Object> map = new HashedMap();
        map.put("tableId",3);
        map.put("typeId",1);
        apiWeekMonthAmountMapper.deleteApiRecord(map);
        List<ApiWeekMonthAmount> apiWeekAmountList = apiWeekMonthAmountMapper.getAllApiWeekConsumeRecord();
        List<ApiWeekMonthAmount> apiWeekMonthAmounts = new ArrayList<>();
        Iterator<ApiWeekMonthAmount> iterator = apiWeekAmountList.iterator();
        while (iterator.hasNext()){
            ApiWeekMonthAmount apiWeekMonthAmount = iterator.next();
            List<String> temporalList = IpTool.spiltStr(apiWeekMonthAmount.getTemporal());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Integer tableId = 3;
            Integer typeId = 1;
            ApiWeekMonthAmount apiWeekMonthAmountOne = new ApiWeekMonthAmount();
            apiWeekMonthAmountOne.setYears(apiWeekMonthAmount.getYears());
            apiWeekMonthAmountOne.setMonths(apiWeekMonthAmount.getMonths());
            apiWeekMonthAmountOne.setWeeks(apiWeekMonthAmount.getWeeks());
            apiWeekMonthAmountOne.setTotleCost(apiWeekMonthAmount.getTotleCost());
            apiWeekMonthAmountOne.setApiId(apiWeekMonthAmount.getApiId());
            apiWeekMonthAmountOne.setTableId(tableId);
            apiWeekMonthAmountOne.setTypeId(typeId);
            apiWeekMonthAmountOne.setBeginTime(sdf.parse(temporalList.get(0)));
            apiWeekMonthAmountOne.setEndTime(sdf.parse(temporalList.get(temporalList.size()-1)));
            apiWeekMonthAmounts.add(apiWeekMonthAmountOne);
        }
        return apiWeekMonthAmountMapper.addApiWeekRecord(apiWeekMonthAmounts);
    }

    @Override
    public boolean getAllApiMonthConsumeRecordAndAddApiWeekMonthAmount() throws Exception {
        Map<String,Object> map = new HashedMap();
        map.put("tableId",3);
        map.put("typeId",2);
        apiWeekMonthAmountMapper.deleteApiRecord(map);
        List<ApiWeekMonthAmount> apiWeekAmountList = apiWeekMonthAmountMapper.getAllApiMonthConsumeRecord();
        List<ApiWeekMonthAmount> apiWeekMonthAmounts = new ArrayList<>();
        Iterator<ApiWeekMonthAmount> iterator = apiWeekAmountList.iterator();
        while (iterator.hasNext()){
            ApiWeekMonthAmount apiWeekMonthAmount = iterator.next();
            List<String> temporalList = IpTool.spiltStr(apiWeekMonthAmount.getTemporal());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Integer tableId = 3;
            Integer typeId = 2;
            ApiWeekMonthAmount apiWeekMonthAmountOne = new ApiWeekMonthAmount();
            apiWeekMonthAmountOne.setYears(apiWeekMonthAmount.getYears());
            apiWeekMonthAmountOne.setMonths(apiWeekMonthAmount.getMonths());
            apiWeekMonthAmountOne.setTotleCost(apiWeekMonthAmount.getTotleCost());
            apiWeekMonthAmountOne.setApiId(apiWeekMonthAmount.getApiId());
            apiWeekMonthAmountOne.setTableId(tableId);
            apiWeekMonthAmountOne.setTypeId(typeId);
            apiWeekMonthAmountOne.setBeginTime(sdf.parse(temporalList.get(0)));
            apiWeekMonthAmountOne.setEndTime(sdf.parse(temporalList.get(temporalList.size()-1)));
            apiWeekMonthAmounts.add(apiWeekMonthAmountOne);
        }
        return apiWeekMonthAmountMapper.addApiWeekRecord(apiWeekMonthAmounts);
    }
}
