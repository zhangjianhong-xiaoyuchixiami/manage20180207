package org.qydata.service.impl;

import org.qydata.config.annotation.DataSourceService;
import org.qydata.entity.ApiBan;
import org.qydata.entity.ApiType;
import org.qydata.entity.ApiVendor;
import org.qydata.entity.Company;
import org.qydata.mapper.ApiMapper;
import org.qydata.service.ApiService;
import org.qydata.tools.date.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jonhn on 2017/2/28.
 */
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired private ApiMapper apiMapper;

    @Override
    @DataSourceService
    public Map<String,Object> queryApi(Map<String,Object> map) {
        Map<String,Object> mapValue = new HashMap<>();
        Map<String,Object> mapValueDead = new HashMap<>();
        Map<String,Object> mapTran = new HashMap<>();
        try {
            Set<Map.Entry<String,Object>> set = map.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while (it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if (me.getKey().equals("apiTypeId")){
                    mapValue.put("apiTypeId",me.getValue());
                    mapValueDead.put("apiTypeId",me.getValue());
                }
                if (me.getKey().equals("vendorId")){
                    mapValue.put("vendorId",me.getValue());
                    mapValueDead.put("vendorId",me.getValue());
                }
                if (me.getKey().equals("partnerId")){
                    mapValue.put("partnerId",me.getValue());
                    mapValueDead.put("partnerId",me.getValue());
                }
            }
            mapValue.put("status",0);
            mapTran.put("queryApi",apiMapper.queryApi(mapValue));
            mapValueDead.put("status",-1);
            mapTran.put("queryApiDead",apiMapper.queryApi(mapValueDead));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapTran;
    }

    @Override
    @DataSourceService
    public List<ApiType> queryApiType() {
        try {
            return apiMapper.queryApiType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @DataSourceService
    public List<ApiVendor> queryApiVendor() {
        try {
            return apiMapper.queryApiVendor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @DataSourceService
    public List<ApiVendor> queryApiVendorByApiTypeId(Integer id) {
        try {
            return apiMapper.queryApiVendorByApiTypeId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @DataSourceService
    public Map<String,Object> queryApiByCompanyId(Map<String, Object> map) {
        Map<String,Object> mapValue = new HashMap<>();
        Map<String,Object> mapValueDead = new HashMap<>();
        Map<String,Object> mapTran = new HashMap<>();
        try {
            Set<Map.Entry<String,Object>> set = map.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while (it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if (me.getKey().equals("apiTypeId")){
                    mapValue.put("apiTypeId",me.getValue());
                    mapValueDead.put("apiTypeId",me.getValue());
                }
                if (me.getKey().equals("companyId")){
                    mapValue.put("companyId",me.getValue());
                    mapValueDead.put("companyId",me.getValue());
                }
                if (me.getKey().equals("partnerId")){
                    mapValue.put("partnerId",me.getValue());
                    mapValueDead.put("partnerId",me.getValue());
                }
            }
            mapValue.put("enabled",1);
            mapTran.put("queryApiByCompanyId",apiMapper.queryApiByCompanyId(mapValue));
            mapValueDead.put("enabled",0);
            mapTran.put("queryApiByCompanyIdDead",apiMapper.queryApiByCompanyId(mapValueDead));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapTran;
    }

    @Override
    @DataSourceService
    public List<Company> queryCompany() {
        try {
            return apiMapper.queryCompany();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ApiBan> queryApiMonitor() {
        Map<String,Object> mapParam = new HashMap<>();
        mapParam.put("time", CalendarUtil.getCurrentLastHour());
        List<ApiBan> apiBanList = apiMapper.queryApiMonitor(mapParam);
        if (apiBanList != null){
            for (int i = 0; i < apiBanList.size() ; i++) {
                ApiBan apiBan = apiBanList.get(i);
                apiBan.setFailRate(((double) apiBan.getFailCount()/(double)apiBan.getTotleCount())*100.0);
            }
        }
        return apiBanList;
    }
}
