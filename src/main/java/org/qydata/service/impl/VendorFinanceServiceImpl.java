package org.qydata.service.impl;

import org.qydata.dst.vendor.VendorFinance;
import org.qydata.dst.vendor.VendorTypeConsume;
import org.qydata.entity.ApiVendor;
import org.qydata.mapper.VendorFinanceMapper;
import org.qydata.service.SearchService;
import org.qydata.service.VendorFinanceService;
import org.qydata.tools.CalendarTools;
import org.qydata.tools.finance.ApiTypeMobileOperatorNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class VendorFinanceServiceImpl implements VendorFinanceService {

    @Autowired
    private VendorFinanceMapper mapper;

    @Override
    public Map<String, Object> queryVendor(Map<String, Object> map) {
        Map<String,Object> param = new HashMap<>();
        if (map != null){
            for (Map.Entry<String,Object> me : map.entrySet()) {
                if (me.getKey().equals("vendorId")){
                    param.put("vendorId",me.getValue());
                }
                if (me.getKey().equals("partnerId")){
                    param.put("partnerId",me.getValue());
                }
                if("beginDate".equals(me.getKey())){
                    param.put("beginDate",me.getValue());
                }
                if("endDate".equals(me.getKey())){
                    param.put("beginDate",me.getValue());
                }
                if("statusList".equals(me.getKey())){
                    param.put("statusList",me.getValue());
                }
            }
        }
        param.put("currDayTime", CalendarTools.getCurrentDate()+ " " + "00:00:00");
        param.put("currMonthTime",CalendarTools.getCurrentMonthFirstDay() + " " + "00:00:00");
        List<VendorFinance> financeList = mapper.queryVendor(param);
        List<VendorFinance> consumeList = mapper.queryVendorConsume(param);
        List<VendorFinance> weekList = mapper.queryVendorLastWeekConsume(param);
        List<VendorFinance> monthList = mapper.queryVendorLastMonthConsume(param);
        List<VendorFinance> currMonthList = mapper.queryVendorCurrMonthConsume(param);
        List<VendorFinance> currDayList = mapper.queryVendorCurrDayConsume(param);
        List<VendorFinance> typeList = mapper.queryVendorTypeConsume(param);
        if (financeList == null){
            return null;
        }
        double lastWeekConsume = 0.0;
        double lastMonthConsume = 0.0;
        double currMonthConsume = 0.0;
        double currDayConsume = 0.0;
        double totleConsume = 0.0;
        for (VendorFinance finance : financeList) {
            if (finance == null){
                continue;
            }
            if (finance.getCharge() != null){
                finance.setCharge(finance.getCharge()/100.0);
            }
            if (consumeList != null){
                for (VendorFinance consume : consumeList) {
                    if (finance.getVendorId() == consume.getVendorId()
                            || finance.getVendorId().equals(consume.getVendorId()))
                    {
                        if (consume.getConsume() != null){
                            finance.setConsume(consume.getConsume()/100.0);
                        }
                    }
                }
            }
            if (weekList != null){
                for (VendorFinance consume : weekList) {
                    if (finance.getVendorId() == consume.getVendorId()
                            || finance.getVendorId().equals(consume.getVendorId()))
                    {
                        if (consume.getLastWeekConsume() != null){
                            finance.setLastWeekConsume(consume.getLastWeekConsume()/100.0);
                        }
                    }
                }
            }
            if (monthList != null){
                for (VendorFinance consume : monthList) {
                    if (finance.getVendorId() == consume.getVendorId()
                            || finance.getVendorId().equals(consume.getVendorId()))
                    {
                        if (consume.getLastMonthConsume() != null){
                            finance.setLastMonthConsume(consume.getLastMonthConsume()/100.0);
                        }
                    }
                }
            }
            if (currMonthList != null){
                for (VendorFinance consume : currMonthList) {
                    if (finance.getVendorId() == consume.getVendorId()
                            || finance.getVendorId().equals(consume.getVendorId()))
                    {
                        if (consume.getCurrMonthConsume() != null){
                            finance.setCurrMonthConsume(consume.getCurrMonthConsume()/100.0);
                        }
                    }
                }
            }
            if (monthList != null){
                for (VendorFinance consume : currDayList) {
                    if (finance.getVendorId() == consume.getVendorId()
                            || finance.getVendorId().equals(consume.getVendorId()))
                    {
                        if (consume.getCurrDayConsume() != null){
                            finance.setCurrDayConsume(consume.getCurrDayConsume()/100.0);
                        }
                    }
                }
            }
            if (typeList != null){
                for (VendorFinance type : typeList) {
                    if (finance.getVendorId() == type.getVendorId()
                            || finance.getVendorId().equals(type.getVendorId()))
                    {
                        if (type.getTypeConsumeList() != null){
                            List<VendorTypeConsume> typeConsumeList = type.getTypeConsumeList();
                            if (typeConsumeList != null){
                                for (VendorTypeConsume consume : typeConsumeList) {
                                    if (consume != null){
                                        String type_stid_name = ApiTypeMobileOperatorNameUtils.apiTypeMobileOperatorName(consume.getApiTypeName(),consume.getMobileList());
                                        consume.setApiTypeName(type_stid_name);
                                    }
                                    if (consume.getCost() != null){
                                        consume.setCost(consume.getCost()/100.0);
                                    }
                                    if (consume.getConsume() != null){
                                        consume.setConsume(consume.getConsume()/100.0);
                                    }
                                }
                            }
                        }
                        finance.setTypeConsumeList(type.getTypeConsumeList());
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
    public List<ApiVendor> queryApiVendorName() {
            return mapper.queryApiVendorName();
    }

}
