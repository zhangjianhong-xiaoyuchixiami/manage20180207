package org.qydata.service.impl;

import org.apache.commons.collections.map.HashedMap;
import org.qydata.entity.WeekMonthAmount;
import org.qydata.mapper.WeekMonthAmountMapper;
import org.qydata.service.WeekMonthAmountService;
import org.qydata.tools.IpTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/6.
 */
@Service
public class WeekMonthAmountServiceImpl implements WeekMonthAmountService {

    @Autowired
    private WeekMonthAmountMapper weekMonthAmountMapper;

    @Override
    public  boolean getAllCustomerWeekRechargeRecordAndAddWeekMonthAmount() throws Exception {
        Map<String,Object> map = new HashedMap();
        map.put("weekMonthTypeId",1);
        map.put("tableId",1);
        weekMonthAmountMapper.deleteRecord(map);
        List<WeekMonthAmount> weekAmountList = weekMonthAmountMapper.getAllCustomerWeekRechargeRecord();
        List<WeekMonthAmount> weekAmounts = new ArrayList<>();
        Iterator<WeekMonthAmount> iterator = weekAmountList.iterator();
        while (iterator.hasNext()){
            WeekMonthAmount weekMonthAmount = iterator.next();
            List<String> temporalList = IpTool.spiltStr(weekMonthAmount.getTemporal());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Integer tableId = 1;
            Integer weekMonthTypeId = 1;
            WeekMonthAmount weekMonthAmountOne = new WeekMonthAmount();
            weekMonthAmountOne.setYears(weekMonthAmount.getYears());
            weekMonthAmountOne.setMonths(weekMonthAmount.getMonths());
            weekMonthAmountOne.setWeeks(weekMonthAmount.getWeeks());
            weekMonthAmountOne.setTotleAmount(weekMonthAmount.getTotleAmount());
            weekMonthAmountOne.setCustomerId(weekMonthAmount.getCustomerId());
            weekMonthAmountOne.setTableId(tableId);
            weekMonthAmountOne.setWeekMonthTypeId(weekMonthTypeId);
            weekMonthAmountOne.setBeginTime(sdf.parse(temporalList.get(0)));
            weekMonthAmountOne.setEndTime(sdf.parse(temporalList.get(temporalList.size()-1)));
            weekAmounts.add(weekMonthAmountOne);
        }
        return weekMonthAmountMapper.addWeekRecord(weekAmounts);
    }

    @Override
    public boolean getAllCustomerMonthRechargeRecordAndAddWeekMonthAmount() throws Exception {
        Map<String,Object> map = new HashedMap();
        map.put("weekMonthTypeId",2);
        map.put("tableId",1);
        weekMonthAmountMapper.deleteRecord(map);
        List<WeekMonthAmount> monthAmountList = weekMonthAmountMapper.getAllCustomerMonthRechargeRecord();
        List<WeekMonthAmount> monthAmounts = new ArrayList<>();
        Iterator<WeekMonthAmount> iterator = monthAmountList.iterator();
        while (iterator.hasNext()){
            WeekMonthAmount weekMonthAmount = iterator.next();
            List<String> temporalList = IpTool.spiltStr(weekMonthAmount.getTemporal());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Integer tableId = 1;
            Integer weekMonthTypeId = 2;
            WeekMonthAmount weekMonthAmountOne = new WeekMonthAmount();
            weekMonthAmountOne.setYears(weekMonthAmount.getYears());
            weekMonthAmountOne.setMonths(weekMonthAmount.getMonths());
            weekMonthAmountOne.setTotleAmount(weekMonthAmount.getTotleAmount());
            weekMonthAmountOne.setCustomerId(weekMonthAmount.getCustomerId());
            weekMonthAmountOne.setTableId(tableId);
            weekMonthAmountOne.setWeekMonthTypeId(weekMonthTypeId);
            weekMonthAmountOne.setBeginTime(sdf.parse(temporalList.get(0)));
            weekMonthAmountOne.setEndTime(sdf.parse(temporalList.get(temporalList.size()-1)));
            monthAmounts.add(weekMonthAmountOne);
        }
        return weekMonthAmountMapper.addMonthRecord(monthAmounts);
    }

    @Override
    public boolean getAllCustomerApiWeekConsumeRecordAndAddWeekMonthAmount() throws Exception {
        Map<String,Object> map = new HashedMap();
        map.put("weekMonthTypeId",1);
        map.put("tableId",2);
        weekMonthAmountMapper.deleteRecord(map);
        List<WeekMonthAmount> weekAmountList = weekMonthAmountMapper.getAllCustomerApiWeekConsumeRecord();
        List<WeekMonthAmount> weekAmounts = new ArrayList<>();
        Iterator<WeekMonthAmount> iterator = weekAmountList.iterator();
        while (iterator.hasNext()){
            WeekMonthAmount weekMonthAmount = iterator.next();
            List<String> temporalList = IpTool.spiltStr(weekMonthAmount.getTemporal());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Integer tableId = 2;
            Integer weekMonthTypeId = 1;
            WeekMonthAmount weekMonthAmountOne = new WeekMonthAmount();
            weekMonthAmountOne.setYears(weekMonthAmount.getYears());
            weekMonthAmountOne.setMonths(weekMonthAmount.getMonths());
            weekMonthAmountOne.setWeeks(weekMonthAmount.getWeeks());
            weekMonthAmountOne.setTotleAmount(weekMonthAmount.getTotleAmount());
            weekMonthAmountOne.setCustomerId(weekMonthAmount.getCustomerId());
            weekMonthAmountOne.setTableId(tableId);
            weekMonthAmountOne.setWeekMonthTypeId(weekMonthTypeId);
            weekMonthAmountOne.setBeginTime(sdf.parse(temporalList.get(0)));
            weekMonthAmountOne.setEndTime(sdf.parse(temporalList.get(temporalList.size()-1)));
            weekAmounts.add(weekMonthAmountOne);
        }
        return weekMonthAmountMapper.addWeekRecord(weekAmounts);
    }

    @Override
    public boolean getAllCustomerApiMonthConsumeRecordAndAddWeekMonthAmount() throws Exception {
        Map<String,Object> map = new HashedMap();
        map.put("weekMonthTypeId",2);
        map.put("tableId",2);
        weekMonthAmountMapper.deleteRecord(map);
        List<WeekMonthAmount> monthAmountList = weekMonthAmountMapper.getAllCustomerApiMonthConsumeRecord();
        List<WeekMonthAmount> monthAmounts = new ArrayList<>();
        Iterator<WeekMonthAmount> iterator = monthAmountList.iterator();
        while (iterator.hasNext()){
            WeekMonthAmount weekMonthAmount = iterator.next();
            List<String> temporalList = IpTool.spiltStr(weekMonthAmount.getTemporal());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Integer tableId = 2;
            Integer weekMonthTypeId = 2;
            WeekMonthAmount weekMonthAmountOne = new WeekMonthAmount();
            weekMonthAmountOne.setYears(weekMonthAmount.getYears());
            weekMonthAmountOne.setMonths(weekMonthAmount.getMonths());
            weekMonthAmountOne.setTotleAmount(weekMonthAmount.getTotleAmount());
            weekMonthAmountOne.setCustomerId(weekMonthAmount.getCustomerId());
            weekMonthAmountOne.setTableId(tableId);
            weekMonthAmountOne.setWeekMonthTypeId(weekMonthTypeId);
            weekMonthAmountOne.setBeginTime(sdf.parse(temporalList.get(0)));
            weekMonthAmountOne.setEndTime(sdf.parse(temporalList.get(temporalList.size()-1)));
            monthAmounts.add(weekMonthAmountOne);
        }
        return weekMonthAmountMapper.addMonthRecord(monthAmounts);
    }
}
