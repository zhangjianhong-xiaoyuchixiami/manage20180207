package org.qydata.service.impl;

import org.qydata.dst.ApiFinance;
import org.qydata.entity.*;
import org.qydata.mapper.ApiFinanceMapper;
import org.qydata.service.ApiFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/17.
 */
@Service
public class ApiFinanceServiceImpl implements ApiFinanceService {

    @Autowired private ApiFinanceMapper apiFinanceMapper;

    @Override
    public List<ApiFinance> queryApiOverAllFinance(Map<String, Object> map){
        try {
            return apiFinanceMapper.queryApiOverAllFinance(map);
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

    @Override
    public List<ApiRequestLog> queryApiDetailById(Map<String, Object> map) {
        try {
            return apiFinanceMapper.queryApiDetailById(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
    public List<ApiFinance> queryApiVendor(Map<String, Object> map) {
        try {
            return apiFinanceMapper.queryApiVendor(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
