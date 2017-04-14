package org.qydata.service.impl;

import org.qydata.config.annotation.DataSourceService;
import org.qydata.entity.PartnerWeekMonthAmount;
import org.qydata.mapper.PartnerWeekMonthAmountMapper;
import org.qydata.service.PartnerWeekMonthAmountService;
import org.qydata.tools.CalendarTools;
import org.qydata.tools.IpTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jonhn on 2017/1/6.
 */
@Service
public class PartnerWeekMonthAmountServiceImpl implements PartnerWeekMonthAmountService {

@Autowired private PartnerWeekMonthAmountMapper partnerWeekMonthAmountMapper;

    @Override
    @DataSourceService
    public boolean addAllPartnerWeekPaymentRecordAndAddWeekMonthAmount(Integer result) throws Exception {
        Map<String,Object> map = new HashMap();
        map.put("year",CalendarTools.getYearCount(result));
        map.put("month",CalendarTools.getMonthWeekCount(result));
        map.put("week",CalendarTools.getYearWeekCount(result));
        map.put("typeId",1);
        map.put("reasonId",2);
        partnerWeekMonthAmountMapper.deletePartnerWeekRecord(map);
        List<PartnerWeekMonthAmount> partnerWeekMonthAmountList = partnerWeekMonthAmountMapper.getAllPartnerWeekPaymentRecord(result);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<PartnerWeekMonthAmount> partnerWeekMonthAmounts = new ArrayList<>();
        Iterator<PartnerWeekMonthAmount> iterator = partnerWeekMonthAmountList.iterator();
        while (iterator.hasNext()) {
            PartnerWeekMonthAmount partnerWeekMonthAmount = iterator.next();
            Integer typeId = 1;
            Integer reasonId = 2;
            PartnerWeekMonthAmount partnerWeekMonthAmountOne = new PartnerWeekMonthAmount();
            partnerWeekMonthAmountOne.setPartnerId(partnerWeekMonthAmount.getPartnerId());
            partnerWeekMonthAmountOne.setTypeId(typeId);
            partnerWeekMonthAmountOne.setReasonId(reasonId);
            if(partnerWeekMonthAmount.getYears() == null){
                partnerWeekMonthAmountOne.setYears(CalendarTools.getYearCount(result));
                partnerWeekMonthAmountOne.setMonths(CalendarTools.getMonthWeekCount(result));
                partnerWeekMonthAmountOne.setWeeks(CalendarTools.getYearWeekCount(result));
                partnerWeekMonthAmountOne.setTotleAmount(0L);
                partnerWeekMonthAmountOne.setBeginTime(CalendarTools.getYearWeekFirstDay(CalendarTools.getYearCount(result),CalendarTools.getYearWeekCount(result)));
                partnerWeekMonthAmountOne.setEndTime(CalendarTools.getYearWeekEndDay(CalendarTools.getYearCount(result),CalendarTools.getYearWeekCount(result)));
            }else{
                List<String> temporalList = IpTool.spiltStr(partnerWeekMonthAmount.getTemporal());
                partnerWeekMonthAmountOne.setYears(partnerWeekMonthAmount.getYears());
                partnerWeekMonthAmountOne.setMonths(CalendarTools.getMonthWeekCount(result));
                partnerWeekMonthAmountOne.setWeeks(partnerWeekMonthAmount.getWeeks());
                partnerWeekMonthAmountOne.setTotleAmount(partnerWeekMonthAmount.getTotleAmount());
                partnerWeekMonthAmountOne.setBeginTime(sdf.parse(temporalList.get(0)));
                partnerWeekMonthAmountOne.setEndTime(sdf.parse(temporalList.get(temporalList.size()-1)));
            }
            partnerWeekMonthAmounts.add(partnerWeekMonthAmountOne);
        }
        return partnerWeekMonthAmountMapper.addWeekRecord(partnerWeekMonthAmounts);
    }

    @Override
    @DataSourceService
    public boolean addAllPartnerMonthPaymentRecordAndAddWeekMonthAmount(Integer result) throws Exception {
        Map<String,Object> map = new HashMap();
        map.put("year",CalendarTools.getYearMonthCount(result));
        map.put("month",CalendarTools.getMonthCount(result));
        map.put("typeId",2);
        map.put("reasonId",2);
        partnerWeekMonthAmountMapper.deletePartnerMonthRecord(map);
        List<PartnerWeekMonthAmount> partnerWeekMonthAmountList = partnerWeekMonthAmountMapper.getAllPartnerMonthPaymentRecord(result);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<PartnerWeekMonthAmount> partnerWeekMonthAmounts = new ArrayList<>();
        Iterator<PartnerWeekMonthAmount> iterator = partnerWeekMonthAmountList.iterator();
        while (iterator.hasNext()) {
            PartnerWeekMonthAmount partnerWeekMonthAmount = iterator.next();
            Integer typeId = 2;
            Integer reasonId = 2;
            PartnerWeekMonthAmount partnerWeekMonthAmountOne = new PartnerWeekMonthAmount();
            partnerWeekMonthAmountOne.setPartnerId(partnerWeekMonthAmount.getPartnerId());
            partnerWeekMonthAmountOne.setTypeId(typeId);
            partnerWeekMonthAmountOne.setReasonId(reasonId);
            if(partnerWeekMonthAmount.getYears() == null){
                partnerWeekMonthAmountOne.setYears(CalendarTools.getYearMonthCount(result));
                partnerWeekMonthAmountOne.setMonths(CalendarTools.getMonthCount(result));
                partnerWeekMonthAmountOne.setTotleAmount(0L);
                partnerWeekMonthAmountOne.setBeginTime(CalendarTools.getYearMonthFirstDay(CalendarTools.getYearMonthCount(result),CalendarTools.getMonthCount(result)));
                partnerWeekMonthAmountOne.setEndTime(CalendarTools.getYearMonthEndDay(CalendarTools.getYearMonthCount(result),CalendarTools.getMonthCount(result)));
            }else{
                List<String> temporalList = IpTool.spiltStr(partnerWeekMonthAmount.getTemporal());
                partnerWeekMonthAmountOne.setYears(partnerWeekMonthAmount.getYears());
                partnerWeekMonthAmountOne.setMonths(partnerWeekMonthAmount.getMonths());
                partnerWeekMonthAmountOne.setTotleAmount(partnerWeekMonthAmount.getTotleAmount());
                partnerWeekMonthAmountOne.setBeginTime(sdf.parse(temporalList.get(0)));
                partnerWeekMonthAmountOne.setEndTime(sdf.parse(temporalList.get(temporalList.size()-1)));
            }
            partnerWeekMonthAmounts.add(partnerWeekMonthAmountOne);
        }
        return partnerWeekMonthAmountMapper.addMonthRecord(partnerWeekMonthAmounts);
    }

    @Override
    @DataSourceService
    public boolean addAllPartnerWeekReceiptRecordAndAddWeekMonthAmount(Integer result) throws Exception {
        Map<String,Object> map = new HashMap();
        map.put("year",CalendarTools.getYearCount(result));
        map.put("month",CalendarTools.getMonthWeekCount(result));
        map.put("week",CalendarTools.getYearWeekCount(result));
        map.put("typeId",1);
        map.put("reasonId",1);
        partnerWeekMonthAmountMapper.deletePartnerWeekRecord(map);
        List<PartnerWeekMonthAmount> partnerWeekMonthAmountList = partnerWeekMonthAmountMapper.getAllPartnerWeekReceiptRecord(result);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<PartnerWeekMonthAmount> partnerWeekMonthAmounts = new ArrayList<>();
        Iterator<PartnerWeekMonthAmount> iterator = partnerWeekMonthAmountList.iterator();
        while (iterator.hasNext()) {
            PartnerWeekMonthAmount partnerWeekMonthAmount = iterator.next();
            Integer typeId = 1;
            Integer reasonId = 1;
            PartnerWeekMonthAmount partnerWeekMonthAmountOne = new PartnerWeekMonthAmount();
            partnerWeekMonthAmountOne.setPartnerId(partnerWeekMonthAmount.getPartnerId());
            partnerWeekMonthAmountOne.setTypeId(typeId);
            partnerWeekMonthAmountOne.setReasonId(reasonId);
            if(partnerWeekMonthAmount.getYears() == null){
                partnerWeekMonthAmountOne.setYears(CalendarTools.getYearCount(result));
                partnerWeekMonthAmountOne.setMonths(CalendarTools.getMonthWeekCount(result));
                partnerWeekMonthAmountOne.setWeeks(CalendarTools.getYearWeekCount(result));
                partnerWeekMonthAmountOne.setTotleAmount(0L);
                partnerWeekMonthAmountOne.setBeginTime(CalendarTools.getYearWeekFirstDay(CalendarTools.getYearCount(result),CalendarTools.getYearWeekCount(result)));
                partnerWeekMonthAmountOne.setEndTime(CalendarTools.getYearWeekEndDay(CalendarTools.getYearCount(result),CalendarTools.getYearWeekCount(result)));
            }else{
                List<String> temporalList = IpTool.spiltStr(partnerWeekMonthAmount.getTemporal());
                partnerWeekMonthAmountOne.setYears(partnerWeekMonthAmount.getYears());
                partnerWeekMonthAmountOne.setMonths(CalendarTools.getMonthWeekCount(result));
                partnerWeekMonthAmountOne.setWeeks(partnerWeekMonthAmount.getWeeks());
                partnerWeekMonthAmountOne.setTotleAmount(partnerWeekMonthAmount.getTotleAmount());
                partnerWeekMonthAmountOne.setBeginTime(sdf.parse(temporalList.get(0)));
                partnerWeekMonthAmountOne.setEndTime(sdf.parse(temporalList.get(temporalList.size()-1)));
            }
            partnerWeekMonthAmounts.add(partnerWeekMonthAmountOne);
        }
        return partnerWeekMonthAmountMapper.addWeekRecord(partnerWeekMonthAmounts);
    }

    @Override
    @DataSourceService
    public boolean addAllPartnerMonthReceiptRecordAndAddWeekMonthAmount(Integer result) throws Exception {
        Map<String,Object> map = new HashMap();
        map.put("year",CalendarTools.getYearMonthCount(result));
        map.put("month",CalendarTools.getMonthCount(result));
        map.put("typeId",2);
        map.put("reasonId",1);
        partnerWeekMonthAmountMapper.deletePartnerMonthRecord(map);
        List<PartnerWeekMonthAmount> partnerWeekMonthAmountList = partnerWeekMonthAmountMapper.getAllPartnerMonthReceiptRecord(result);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<PartnerWeekMonthAmount> partnerWeekMonthAmounts = new ArrayList<>();
        Iterator<PartnerWeekMonthAmount> iterator = partnerWeekMonthAmountList.iterator();
        while (iterator.hasNext()) {
            PartnerWeekMonthAmount partnerWeekMonthAmount = iterator.next();
            Integer typeId = 2;
            Integer reasonId = 1;
            PartnerWeekMonthAmount partnerWeekMonthAmountOne = new PartnerWeekMonthAmount();
            partnerWeekMonthAmountOne.setPartnerId(partnerWeekMonthAmount.getPartnerId());
            partnerWeekMonthAmountOne.setTypeId(typeId);
            partnerWeekMonthAmountOne.setReasonId(reasonId);
            if(partnerWeekMonthAmount.getYears() == null){
                partnerWeekMonthAmountOne.setYears(CalendarTools.getYearMonthCount(result));
                partnerWeekMonthAmountOne.setMonths(CalendarTools.getMonthCount(result));
                partnerWeekMonthAmountOne.setTotleAmount(0L);
                partnerWeekMonthAmountOne.setBeginTime(CalendarTools.getYearMonthFirstDay(CalendarTools.getYearMonthCount(result),CalendarTools.getMonthCount(result)));
                partnerWeekMonthAmountOne.setEndTime(CalendarTools.getYearMonthEndDay(CalendarTools.getYearMonthCount(result),CalendarTools.getMonthCount(result)));
            }else{
                List<String> temporalList = IpTool.spiltStr(partnerWeekMonthAmount.getTemporal());
                partnerWeekMonthAmountOne.setYears(partnerWeekMonthAmount.getYears());
                partnerWeekMonthAmountOne.setMonths(partnerWeekMonthAmount.getMonths());
                partnerWeekMonthAmountOne.setTotleAmount(partnerWeekMonthAmount.getTotleAmount());
                partnerWeekMonthAmountOne.setBeginTime(sdf.parse(temporalList.get(0)));
                partnerWeekMonthAmountOne.setEndTime(sdf.parse(temporalList.get(temporalList.size()-1)));
            }
            partnerWeekMonthAmounts.add(partnerWeekMonthAmountOne);
        }
        return partnerWeekMonthAmountMapper.addMonthRecord(partnerWeekMonthAmounts);
    }
}
