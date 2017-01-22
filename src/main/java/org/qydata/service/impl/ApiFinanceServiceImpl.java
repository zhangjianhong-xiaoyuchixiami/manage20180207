package org.qydata.service.impl;

import org.qydata.dst.ApiFinance;
import org.qydata.entity.ApiVendorBalanceLog;
import org.qydata.entity.ApiRequestLog;
import org.qydata.entity.ApiType;
import org.qydata.entity.ApiVendor;
import org.qydata.mapper.ApiFinanceMapper;
import org.qydata.service.ApiFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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
        Long balance = null;
        try {
            balance = apiFinanceMapper.queryApiVendorBalance(vendorIdCharge);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        ApiVendorBalanceLog apiVendorBalanceLog = new ApiVendorBalanceLog();
        apiVendorBalanceLog.setVendorId(vendorIdCharge);
        apiVendorBalanceLog.setAmount(amount*100);
        apiVendorBalanceLog.setRemark(remark);
        System.out.println("****************"+chargeDate);
        if (chargeDate == "" || chargeDate == null){
          apiVendorBalanceLog.setCreateTime(new Date());
        }else {
            try {
                apiVendorBalanceLog.setCreateTime(sdf.parse(chargeDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        boolean flag = false;
        try {
            apiFinanceMapper.updateApiVendorBalance(vendorIdCharge,((amount*100) + balance));
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
