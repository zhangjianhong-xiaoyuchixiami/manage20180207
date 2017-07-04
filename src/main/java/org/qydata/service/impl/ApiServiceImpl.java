package org.qydata.service.impl;

import org.qydata.config.annotation.DataSourceService;
import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.entity.*;
import org.qydata.mapper.ApiMapper;
import org.qydata.mapper.CompanyMapper;
import org.qydata.service.ApiService;
import org.qydata.tools.date.CalendarUtil;
import org.qydata.tools.finance.ApiTypeMobileOperatorNameUtils;
import org.qydata.tools.https.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jonhn on 2017/2/28.
 */
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired private ApiMapper apiMapper;

    @Autowired private CompanyMapper companyMapper;

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
            //mapValue.put("status",0);
            mapTran.put("queryApi",apiMapper.queryApi(mapValue));
            //mapValueDead.put("status",-1);
            //mapTran.put("queryApiDead",apiMapper.queryApi(mapValueDead));
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
                apiBan.setFailRate(Math.round(((double) apiBan.getFailCount()/(double)apiBan.getTotleCount())*100));
            }
        }
        return apiBanList;
    }

    @Override
    @SystemServiceLog(description = "上游产品禁用")
    public Map<String,Object> updateApiBan(String [] apiId) throws Exception{
        final String uri = "https://api.qydata.org:9000/admin/api/status";
        Map<String,Object> mapResu = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<apiId.length; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("k",companyMapper.queryAuthKey("admin.k"));
            map.put("aid",apiId[i]);
            map.put("s",-1);
            int code = HttpClientUtil.doGet(uri,map,null);
            if (code != 200){
                String apiName = null;
                Api api = apiMapper.queryApiTypeNameStidNameVendorNameByApiId(Integer.parseInt(apiId[i]));
                if (api != null){
                    ApiType apiType = api.getApiType();
                    ApiVendor apiVendor = api.getApiVendor();
                    List<MobileOperator> mobileOperatorList = api.getMobileOperatorList();
                    if (apiType != null && apiType.getName() != null){
                        apiName = apiType.getName();
                        if (mobileOperatorList != null && mobileOperatorList.size() > 0){
                            apiName = apiName + "--";
                            for (int j = 0; j < mobileOperatorList.size() ; j++) {
                                MobileOperator mobileOperator = mobileOperatorList.get(j);
                                if (mobileOperator != null && mobileOperator.getName() != null){
                                    apiName = apiName + mobileOperator.getName() + "，";
                                }
                            }
                            if (apiName.lastIndexOf("，") != -1) {
                                apiName = apiName.substring(0, apiName.lastIndexOf("，"));
                            }
                        }
                        if (apiVendor != null && apiVendor.getName() != null){
                            apiName = apiName + "@" + apiVendor.getName();
                        }
                    }
                }
                sb.append(apiName+"，");
                mapResu.put("fail","产品名称是："+sb+"禁用失败其余禁用正常");
            }else {
                mapResu.put("success","禁用成功");
            }
        }
        return mapResu;
    }

    @Override
    @SystemServiceLog(description = "上游产品解禁")
    public Map<String,Object> updateApiUnBan(String [] apiId) throws Exception{
        final String uri = "https://api.qydata.org:9000/admin/api/status";
        Map<String,Object> mapResu = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<apiId.length; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("k",companyMapper.queryAuthKey("admin.k"));
            map.put("aid",apiId[i]);
            map.put("s",0);
            int code = HttpClientUtil.doGet(uri,map,null);
            if (code != 200){
                String apiName = null;
                Api api = apiMapper.queryApiTypeNameStidNameVendorNameByApiId(Integer.parseInt(apiId[i]));
                if (api != null){
                    ApiType apiType = api.getApiType();
                    ApiVendor apiVendor = api.getApiVendor();
                    List<MobileOperator> mobileOperatorList = api.getMobileOperatorList();
                    if (apiType != null && apiType.getName() != null){
                        apiName = apiType.getName();
                        if (mobileOperatorList != null && mobileOperatorList.size() > 0){
                            apiName = apiName + "--";
                            for (int j = 0; j < mobileOperatorList.size() ; j++) {
                                MobileOperator mobileOperator = mobileOperatorList.get(j);
                                if (mobileOperator != null && mobileOperator.getName() != null){
                                    apiName = apiName + mobileOperator.getName() + "，";
                                }
                            }
                            if (apiName.lastIndexOf("，") != -1) {
                                apiName = apiName.substring(0, apiName.lastIndexOf("，"));
                            }
                        }
                        if (apiVendor != null && apiVendor.getName() != null){
                            apiName = apiName + "@" + apiVendor.getName();
                        }
                    }
                }
                sb.append(apiName+"，");
                mapResu.put("fail","产品名称是："+sb+"启用失败其余启用正常");
            }else {
                mapResu.put("success","启用成功");
            }
        }
        return mapResu;
    }

    @Override
    @SystemServiceLog(description = "修改上游产品价格")
    public int updatePrice(Integer aid, Double pic) throws Exception {
        System.out.println(aid);
        System.out.println(pic);
        /*final String uri = "https://api.qydata.org:9000/admin/";
        Map<String,Object> map = new HashMap<>();
        map.put("k",companyMapper.queryAuthKey("admin.k"));
        map.put("aid",aid);
        map.put("pic",pic*100);
        int  code = HttpClientUtil.doGet(uri,map,null);
        if (200 == code){
            return code;
        }
        throw new Exception("http请求异常，请求状态码statusCode="+code);*/
        return 200;
    }

    @Override
    public List<ApiPriceChanceLog> queryApiPriceChangeLog(Map<String, Object> map) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<ApiPriceChanceLog> apclList = apiMapper.queryApiPriceChangeLog(map);
        if (apclList != null){
            for (int i = 0; i < apclList.size() ; i++) {
                ApiPriceChanceLog apcl = apclList.get(i);
                ApiType apiType = apcl.getApiType();
                if (apiType != null && apiType.getName() != null){
                    String apiType_stidName = ApiTypeMobileOperatorNameUtils.apiTypeMobileOperatorName(apiType.getName(),apcl.getMobileOperatorList());
                    apiType.setName(apiType_stidName);
                    apcl.setApiType(apiType);
                }
                if (apcl.getTimeForce() != null){
                    if (apcl.getTimeDead() != null){
                        apcl.setTimeRange(sdf.format(apcl.getTimeForce()) + "-" + sdf.format(apcl.getTimeDead()));
                    }else {
                        apcl.setTimeRange(sdf.format(apcl.getTimeForce()) + "-至今");
                    }
                }
            }
        }
        return apclList;
    }

    @Override
    public boolean addApiPriceChangeLog(Integer tid, Integer vid, Double pic, String date) throws Exception {
        return false;
    }
}
