package org.qydata.service.impl;

import org.qydata.config.annotation.DataSourceService;
import org.qydata.dst.ApiFinance;
import org.qydata.dst.ApiTypeConsume;
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
    @DataSourceService
    public Map<String,Object> queryApiOverAllFinance(Map<String, Object> map){
        Map<String,Object> mapValue = new HashMap<>();
        Map<String,Object> mapTran = new HashMap<>();
        try {
            Set<Map.Entry<String,Object>> set = map.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while (it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if (me.getKey().equals("vendorId")){
                    mapValue.put("vendorId",me.getValue());
                }
                if (me.getKey().equals("apiTypeId")){
                    mapValue.put("apiTypeId",me.getValue());
                }
                if (me.getKey().equals("beginDate")){
                    mapValue.put("beginDate",me.getValue());
                }
                if (me.getKey().equals("endDate")){
                    mapValue.put("endDate",me.getValue());
                }
                if (me.getKey().equals("statusList")){
                    mapValue.put("statusList",me.getValue());
                }
            }
            mapTran.put("queryApiOverAllFinance",apiFinanceMapper.queryApiOverAllFinance(mapValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapTran;
    }

    @Override
    @DataSourceService
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
    @DataSourceService
    public Map<String,Object> queryApiVendor(Map<String, Object> map) {
        Map<String,Object> mapValue = new HashMap<>();
        Map<String,Object> mapTran = new HashMap<>();
        try {
            Set<Map.Entry<String,Object>> set = map.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while (it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if (me.getKey().equals("vendorId")){
                    mapValue.put("vendorId",me.getValue());
                }
                if (me.getKey().equals("partnerId")){
                    mapValue.put("partnerId",me.getValue());
                }
                if (me.getKey().equals("beginDate")){
                    mapValue.put("beginDate",me.getValue());
                }
                if (me.getKey().equals("endDate")){
                    mapValue.put("endDate",me.getValue());
                }
                if (me.getKey().equals("statusList")){
                    mapValue.put("statusList",me.getValue());
                }
            }
            List<ApiFinance> apiFinanceList = apiFinanceMapper.queryApiVendor(mapValue);
            List<ApiFinance> apiFinanceTypeList = apiFinanceMapper.queryApiVendorType(mapValue);
            if (apiFinanceTypeList != null && apiFinanceTypeList.size() > 0){
                for (int i=0; i<apiFinanceTypeList.size(); i++){
                    ApiFinance apiFinance = apiFinanceTypeList.get(i);
                    List<ApiTypeConsume> apiTypeConsumeList = apiFinance.getApiTypeConsumeList();
                    if (map.get("endDate") == null || new SimpleDateFormat("yyyy/MM/dd 23:59:59").format(new Date()).equals(map.get("endDate"))) {
                        if (apiTypeConsumeList != null && apiTypeConsumeList.size() >0) {
                            for (int j = 0; j < apiTypeConsumeList.size(); j++) {
                                ApiTypeConsume apiTypeConsume = apiTypeConsumeList.get(j);
                                apiTypeConsume.setApiTypeConsumeTotleAmount(apiTypeConsume.getApiTypeConsumeTotleAmount() + apiTypeConsume.getTypeCurrDayCost());
                                apiTypeConsume.setApiTypeUsageAmount(apiTypeConsume.getApiTypeUsageAmount() + apiTypeConsume.getTypeCurrDayUsageAmount());
                                apiTypeConsume.setApiTypefeeAmount(apiTypeConsume.getApiTypefeeAmount() + apiTypeConsume.getTypeFeeCurrDayAmount());
                            }
                        }
                    }
                }
            }
            if (apiFinanceList != null && apiFinanceList.size() >0) {
                for (int i = 0; i < apiFinanceList.size(); i++) {
                    ApiFinance apiFinance = apiFinanceList.get(i);
                    if (map.get("endDate") == null || new SimpleDateFormat("yyyy/MM/dd 23:59:59").format(new Date()).equals(map.get("endDate"))) {
                        apiFinance.setConsumeTotleAmount(apiFinance.getConsumeTotleAmount() + apiFinance.getCurrDayCost());
                    }
                    if (apiFinanceTypeList != null && apiFinanceTypeList.size() >0) {
                        for (int r = 0; r < apiFinanceTypeList.size(); r++) {
                            ApiFinance apiFinanceType = apiFinanceTypeList.get(r);
                            if (apiFinance.getVendorId() == apiFinanceType.getVendorId()) {
                                apiFinance.setApiTypeConsumeList(apiFinanceType.getApiTypeConsumeList());
                            }
                        }
                    }
                }
                mapTran.put("queryApiVendor", apiFinanceList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapTran;
    }

    @Override
    @DataSourceService
    public boolean updateApiVendorChargeLog(Integer vendorIdCharge, Long amount, String remark, String chargeDate)throws Exception{

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
        return apiFinanceMapper.insertApiVendorBalanceLog(apiVendorBalanceLog);

    }

    @Override
    @DataSourceService
    public List<ApiType> queryApiType() {
        try {
            return apiFinanceMapper.queryApiType();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @DataSourceService
    public List<ApiVendor> queryApiVendorName(Map<String, Object> map) {
        try {
            return apiFinanceMapper.queryApiVendorName(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
