package org.qydata.service.impl;

import org.qydata.entity.ApiType;
import org.qydata.entity.ApiVendor;
import org.qydata.entity.ApiVendorBalance;
import org.qydata.entity.ApiVendorBalanceLog;
import org.qydata.mapper.ApiFinanceMapper;
import org.qydata.service.ApiFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jonhn on 2017/1/17.
 */
@Service
public class ApiFinanceServiceImpl implements ApiFinanceService {

    @Autowired private ApiFinanceMapper apiFinanceMapper;

    @Override
    public Map<String,Object> queryApiOverAllFinance(Map<String, Object> map){
        Map<String,Object> mapValue = new HashMap<>();
        Map<String,Object> mapValueDead = new HashMap<>();
        Map<String,Object> mapTran = new HashMap<>();
        try {
            Set<Map.Entry<String,Object>> set = map.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while (it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if (me.getKey().equals("vendorId")){
                    mapValue.put("vendorId",me.getValue());
                    mapValueDead.put("vendorId",me.getValue());
                }
                if (me.getKey().equals("apiTypeId")){
                    mapValue.put("apiTypeId",me.getValue());
                    mapValueDead.put("apiTypeId",me.getValue());
                }
            }
            mapValue.put("status",0);
            mapTran.put("queryApiOverAllFinance",apiFinanceMapper.queryApiOverAllFinance(mapValue));
            mapTran.put("getCountApiWeekFinance",apiFinanceMapper.getCountApiWeekFinance(mapValue));
            mapTran.put("getCountApiMonthFinance",apiFinanceMapper.getCountApiMonthFinance(mapValue));
            mapTran.put("getCountApiTotleFinance",apiFinanceMapper.getCountApiTotleFinance(mapValue));
            mapValueDead.put("status",-1);
            mapTran.put("queryApiOverAllFinanceDead",apiFinanceMapper.queryApiOverAllFinance(mapValueDead));
            mapTran.put("getCountApiWeekFinanceDead",apiFinanceMapper.getCountApiWeekFinance(mapValueDead));
            mapTran.put("getCountApiMonthFinanceDead",apiFinanceMapper.getCountApiMonthFinance(mapValueDead));
            mapTran.put("getCountApiTotleFinanceDead",apiFinanceMapper.getCountApiTotleFinance(mapValueDead));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapTran;
    }

    @Override
    public Map<String,Object> queryApiDetailById(Map<String, Object> map) {
        Map<String,Object> mapTran = new HashMap<>();
        try {
            mapTran.put("queryApiDetailById",apiFinanceMapper.queryApiDetailById(map));
            mapTran.put("getCountApiDetailById",apiFinanceMapper.getCountApiDetailById(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapTran;
    }

    @Override
    public Map<String,Object> queryApiVendor(Map<String, Object> map) {
        Map<String,Object> mapTran = new HashMap<>();
        try {
            mapTran.put("queryApiVendor",apiFinanceMapper.queryApiVendor(map));
            mapTran.put("getCountWeekApiVendor",apiFinanceMapper.getCountWeekApiVendor(map));
            mapTran.put("getCountMonthApiVendor",apiFinanceMapper.getCountMonthApiVendor(map));
            mapTran.put("getCountTotleApiVendor",apiFinanceMapper.getCountTotleApiVendor(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapTran;
    }

    @Override
    public boolean apiVendorChargeLog(Integer vendorIdCharge, Long amount, String remark, String chargeDate) {
        boolean flag = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            ApiVendorBalanceLog apiVendorBalanceLog = new ApiVendorBalanceLog();
            apiVendorBalanceLog.setVendorId(vendorIdCharge);
            apiVendorBalanceLog.setAmount(amount*100);
            apiVendorBalanceLog.setRemark(remark);
            if (chargeDate == "" || chargeDate == null){
                apiVendorBalanceLog.setCreateTime(new Date());
            }else {
                apiVendorBalanceLog.setCreateTime(sdf.parse(chargeDate));
            }
            ApiVendorBalance apiVendorBalance = null;
            apiVendorBalance = apiFinanceMapper.queryApiVendorBalance(vendorIdCharge);
            if (apiVendorBalance == null){
                ApiVendorBalance apiVendorBalanceOne = new ApiVendorBalance();
                apiVendorBalanceOne.setVendorId(vendorIdCharge);
                apiFinanceMapper.insertApiVendorBalance(apiVendorBalanceOne);
                apiFinanceMapper.updateApiVendorBalance(vendorIdCharge,((amount*100)));
            }else {
                apiFinanceMapper.updateApiVendorBalance(vendorIdCharge,((amount*100) + apiVendorBalance.getBalance()));
            }
            flag = apiFinanceMapper.insertApiVendorBalanceLog(apiVendorBalanceLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<ApiType> queryApiType() {
        try {
            return apiFinanceMapper.queryApiType();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ApiVendor> queryApiVendorName(Map<String, Object> map) {
        try {
            return apiFinanceMapper.queryApiVendorName(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
