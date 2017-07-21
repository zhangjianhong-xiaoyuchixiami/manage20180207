package org.qydata.service.impl;

import org.qydata.config.annotation.DataSourceService;
import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.dst.ApiTypeInfo;
import org.qydata.dst.CustomerApiPartner;
import org.qydata.entity.*;
import org.qydata.mapper.ApiMapper;
import org.qydata.mapper.CompanyMapper;
import org.qydata.service.ApiService;
import org.qydata.tools.RegexUtil;
import org.qydata.tools.date.CalendarUtil;
import org.qydata.tools.finance.ApiTypeMobileOperatorNameUtils;
import org.qydata.tools.https.HttpClientUtil;
import org.qydata.tools.thread.RecoverApiProbThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/2/28.
 */
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired private ApiMapper apiMapper;

    @Autowired private CompanyMapper companyMapper;

    @Override
    @DataSourceService
    public List<Api> queryApi(Map<String,Object> map) {
        List<Api> apiList = apiMapper.queryApi(map);
        if (apiList != null){
            for (int i = 0; i < apiList.size() ; i++) {
                Api api = apiList.get(i);
                if (api != null){
                    ApiType apiType = api.getApiType();
                    if (apiType != null && apiType.getName() != null){
                        String apiType_stidName = ApiTypeMobileOperatorNameUtils.apiTypeMobileOperatorName(apiType.getName(),api.getMobileOperatorList());
                        apiType.setName(apiType_stidName);
                    }
                }
            }
        }
        return apiList;
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
    public List<Partner> queryPartner() {
        return apiMapper.queryPartner();
    }

    @Override
    @DataSourceService
    public List<CustomerApiPartner> queryApiByCompanyId(Map<String, Object> map) {
        List<CustomerApiPartner> customerApiPartnerList =  apiMapper.queryApiByCompanyId(map);
        return customerApiPartnerList;
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
                String vendorName = null;
                Api api = apiMapper.queryApiTypeNameStidNameVendorNameByApiId(Integer.parseInt(apiId[i]));
                if (api != null){
                    ApiType apiType = api.getApiType();
                    ApiVendor apiVendor = api.getApiVendor();
                    if (apiVendor != null){
                        vendorName = apiVendor.getName();
                    }
                    if (apiType != null && apiType.getName() != null){
                        apiName = ApiTypeMobileOperatorNameUtils.apiTypeVendorMobileOperatorName(apiType.getName(),vendorName,api.getMobileOperatorList());
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
                String vendorName = null;
                Api api = apiMapper.queryApiTypeNameStidNameVendorNameByApiId(Integer.parseInt(apiId[i]));
                if (api != null){
                    ApiType apiType = api.getApiType();
                    ApiVendor apiVendor = api.getApiVendor();
                    if (apiVendor != null){
                        vendorName = apiVendor.getName();
                    }
                    if (apiType != null && apiType.getName() != null){
                        apiName = ApiTypeMobileOperatorNameUtils.apiTypeVendorMobileOperatorName(apiType.getName(),vendorName,api.getMobileOperatorList());
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
        ApiFake apiFake = apiMapper.queryApiFakeByApiId(aid);
        if (apiFake == null){
            ApiFake apiFakeParam = new ApiFake();
            apiFakeParam.setApiId(aid);
            apiFakeParam.setFakeV(1);
            apiMapper.addApiFake(apiFakeParam);
        }
        final String uri = "https://api.qydata.org:9000/admin/api/modify-cost";
        Map<String,Object> map = new HashMap<>();
        map.put("k",companyMapper.queryAuthKey("admin.k"));
        map.put("aid",aid);
        map.put("v",pic*100);
        int  code = HttpClientUtil.doGet(uri,map,null);
        if (200 == code){
            return code;
        }
        throw new Exception("http请求异常，请求状态码statusCode="+code);
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
                        apcl.setTimeRange(sdf.format(apcl.getTimeForce()) + "--" + sdf.format(apcl.getTimeDead()));
                    }else {
                        apcl.setTimeRange(sdf.format(apcl.getTimeForce()) + "--至今");
                    }
                }
            }
        }
        return apclList;
    }

    @Override
    @SystemServiceLog(description = "新增上游产品改价记录")
    public boolean addApiPriceChangeLog(Integer aid, Double pic, String date) throws Exception {
        String dateFormat = date + ":00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        ApiPriceChanceLog apiPriceChanceLog = apiMapper.queryApiChangeLogByApiId(aid);
        if (apiPriceChanceLog != null){
            apiMapper.updateApiDeadTimeByApiId(aid,sdf.parse(dateFormat));
        }
        ApiPriceChanceLog apcl = new ApiPriceChanceLog();
        apcl.setApiId(aid);
        apcl.setPrice((int)(pic*100));
        apcl.setTimeForce(sdf.parse(dateFormat));
        boolean flag = apiMapper.addApiPriceChangeLog(apcl);
        return flag;
    }

    @Override
    public List<Api> queryAllApi(Map<String, Object> map) {
        List<Api> apiList = apiMapper.queryAllApi(map);
        if (apiList != null){
            for (int i = 0; i < apiList.size(); i++) {
                Api api = apiList.get(i);
                String apiName = null;
                String vendorName = null;
                if (api != null){
                    ApiType apiType = api.getApiType();
                    ApiVendor apiVendor = api.getApiVendor();
                    if (apiVendor != null){
                        vendorName = apiVendor.getName();
                    }
                    if (apiType != null && apiType.getName() != null){
                        apiName = ApiTypeMobileOperatorNameUtils.apiTypeVendorMobileOperatorName(apiType.getName(),vendorName,api.getMobileOperatorList());
                    }
                }
                api.setName(apiName);
            }
        }
        return apiList;
    }

    @Override
    public List<CompanyApiPriceChangeLog> queryCompanyApiPriceChangeLog(Map<String, Object> map) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<CompanyApiPriceChangeLog> capclList = apiMapper.queryCompanyApiPriceChangeLog(map);
        if (capclList != null){
            for (int i = 0; i < capclList.size() ; i++) {
                CompanyApiPriceChangeLog capcl = capclList.get(i);
                if (capcl.getTimeForce() != null){
                    if (capcl.getTimeDead() != null){
                        capcl.setTimeRange(sdf.format(capcl.getTimeForce()) + "--" + sdf.format(capcl.getTimeDead()));
                    }else {
                        capcl.setTimeRange(sdf.format(capcl.getTimeForce()) + "--至今");
                    }
                }
            }
        }
        return capclList;
    }

    @Override
    public List<ApiTypeInfo> queryCompanyApiByCompanyId(Integer cid) {
        return apiMapper.queryCompanyApiByCompanyId(cid);
    }

    @Override
    @SystemServiceLog(description = "新增客户产品改价记录")
    public boolean addCompanyApiPriceChangeLog(Integer cid, String tid_stid, Double pic, String date) throws Exception {
        String dateFormat = date + ":00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Integer tid = null;
        Integer stid = null;
        if (RegexUtil.isPoint(tid_stid)){
            tid = Integer.parseInt(tid_stid);
        }else {
            String tid_stids [] = tid_stid.split("-");
            tid = Integer.parseInt(tid_stids[0]);
            stid = Integer.parseInt(tid_stids[1]);
        }
        CompanyApiPriceChangeLog companyApiPriceChangeLog = apiMapper.queryCompanyApiChangeLogByCidTidStid(cid,tid,stid);
        if (companyApiPriceChangeLog != null){
            apiMapper.updateCompanyApiDeadTimeByCidTidStid(cid,tid,stid,sdf.parse(dateFormat));
        }
        CompanyApiPriceChangeLog capcl = new CompanyApiPriceChangeLog();
        capcl.setCompanyId(cid);
        capcl.setApiTypeId(tid);
        capcl.setStid(stid);
        capcl.setPrice((int)(pic*100));
        capcl.setTimeForce(sdf.parse(dateFormat));
        boolean flag = apiMapper.addCompanyApiPriceChangeLog(capcl);
        return flag;
    }

    @Override
    @SystemServiceLog(description = "修改客户产品价格")
    public int updateCompanyApiPrice(Integer cid, Integer tid, Integer stid, Double pic) throws Exception {
        String uri = "https://api.qydata.org:9000/admin/company/api/put";
        Map<String,Object> map = new HashMap<>();
        map.put("k",companyMapper.queryAuthKey("admin.k"));
        map.put("cid",cid);
        map.put("price",(int)(pic*100));
        map.put("tid",tid);
        map.put("stid",stid);
        int  code = HttpClientUtil.doGet(uri,map,null);
        if (200 == code){
            return code;
        }
        throw new Exception("http请求异常，请求状态码statusCode="+code);
    }

    @Override
    @SystemServiceLog(description = "客户产品权限禁用")
    public Map<String,Object> banCompanyApi(String [] cid_id) throws Exception {
        final String uri = "https://api.qydata.org:9000/admin/company/api/del";
        Map<String,Object> mapResu = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        if (cid_id != null){
            for (int i = 0; i < cid_id.length ; i++) {
                Map<String,Object> map = new HashMap<>();
                String [] cid_ids = cid_id[i].split("-");
                Integer cid = Integer.parseInt(cid_ids[0]);
                Integer id = Integer.parseInt(cid_ids[1]);
                map.put("k",companyMapper.queryAuthKey("admin.k"));
                map.put("cid",cid);
                map.put("id",id);
                int  code = HttpClientUtil.doGet(uri,map,null);
                if (code != 200){
                    sb.append(cid_id[i]+"，");
                    mapResu.put("fail",sb+"禁用失败其余禁用正常");
                }else {
                    mapResu.put("success","禁用成功");
                }
            }
        }
        return mapResu;
    }

    @Override
    @SystemServiceLog(description = "修改上游产品当前配额")
    public int updateApiCurrProb(Integer aid, Integer prob) throws Exception {
//        final String uri = "https://api.qydata.org:9000/admin/api/modify-prob";
//        Map<String,Object> map = new HashMap<>();
//        map.put("k",companyMapper.queryAuthKey("admin.k"));
//        map.put("aid",aid);
//        map.put("v",prob);
//        int  code = HttpClientUtil.doGet(uri,map,null);
//        if (200 == code){
//            return code;
//        }
        int code = 200;
        return code;
        //throw new Exception("http请求异常，请求状态码statusCode="+code);
    }

    @Override
    @SystemServiceLog(description = "修改上游产品预设配额")
    public boolean updateApiDefProb(Integer aid, Integer prob) throws Exception {
        try {
            ApiExt apiExt =  apiMapper.queryApiDefProb(aid);
            ApiExt apiExtParam = new ApiExt();
            apiExtParam.setApiId(aid);
            apiExtParam.setDefProb(prob);
            int flag = 0;
            if (apiExt != null){
                flag = apiMapper.updateApiDefProb(apiExtParam);
            }else {
                flag = apiMapper.addApiDefProb(apiExtParam);
            }
            if (1 == flag){
                return true;
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @SystemServiceLog(description = "修改上游产品预设比例")
    public boolean updateApiDefProp(Integer aid, Double prop) throws Exception {
        try {
            ApiExt apiExt =  apiMapper.queryApiDefProp(aid);
            ApiExt apiExtParam = new ApiExt();
            apiExtParam.setApiId(aid);
            apiExtParam.setDefProp((int)(prop * 100));
            int flag = 0;
            if (apiExt != null){
                flag = apiMapper.updateApiDefProp(apiExtParam);
            }else {
                flag = apiMapper.addApiDefProp(apiExtParam);
            }
            if (1 == flag){
                return true;
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void updateRecoverApiProb(String[] aid) throws Exception {
        if (aid != null && aid.length > 0){
            for (int i = 0; i < aid.length ; i++) {
                RecoverApiProbThread runnable = new RecoverApiProbThread(Integer.parseInt(aid[i]),apiMapper,companyMapper);
                new Thread(runnable).start();
            }
        }
    }

    @Override
    public Integer queryApiTypeByApiId(Integer aid) {
        try {
            return apiMapper.queryApiTypeByApiId(aid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RecoverProbLog queryDetailLogByApiId(Integer aid) {
        return apiMapper.queryDetailLogByApiId(aid);
    }
}
