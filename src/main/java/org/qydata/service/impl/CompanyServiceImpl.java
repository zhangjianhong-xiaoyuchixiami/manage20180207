package org.qydata.service.impl;


import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.dst.ApiTypeSubType;
import org.qydata.dst.CustomerCompanyPartner;
import org.qydata.entity.*;
import org.qydata.mapper.CompanyMapper;
import org.qydata.mapper.CustomerDeptMapper;
import org.qydata.mapper.CustomerMapper;
import org.qydata.service.CompanyService;
import org.qydata.tools.RegexUtil;
import org.qydata.tools.https.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/12/15.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired private CompanyMapper companyMapper;

    @Autowired  private CustomerDeptMapper customerDeptMapper;

    @Autowired  private CustomerMapper customerMapper;

    @Override
    public List<CustomerCompanyPartner> findAllCompany(Map<String, Object> map) {
        try {
            return companyMapper.findAllCompany(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @SystemServiceLog(description = "新增客户")
    public int addCompanyCustomer(String companyName,String authId,String partnerId,String apiTypeId_subId [],String price [],String begIp [],String endIp [])throws Exception {
        String uri = "https://api.qydata.org:9000/admin/customer/add-package";
        Map<String,Object> map = new HashMap<>();
        map.put("k",companyMapper.queryAuthKey("admin.k"));
        map.put("name",companyName);
        map.put("key",authId);
        if (partnerId != null){
            map.put("pid",partnerId);
        }
        System.out.println(apiTypeId_subId.length);
        System.out.println(price.length);
        System.out.println(begIp.length);
        System.out.println(endIp.length);
        StringBuffer atsArray = new StringBuffer();
        if (apiTypeId_subId != null && apiTypeId_subId.length > 0){
            if (price != null && price.length > 0){
                if (apiTypeId_subId.length <= price.length){
                    for ( int i=0 ; i<apiTypeId_subId.length ; i++ ){
                        if (apiTypeId_subId[i] != "" && price[i] != ""){
                            if (RegexUtil.isPoint(apiTypeId_subId[i])){
                                atsArray.append( apiTypeId_subId[i]+"-0:"+ (int)(Double.parseDouble(price[i])*100)+",") ;
                            }else {
                                atsArray.append(apiTypeId_subId[i]+":"+ (int)(Double.parseDouble(price[i])*100)+",") ;
                            }
                        }
                    }
                }else {
                    for ( int i=0 ; i<price.length ; i++ ){
                        if (apiTypeId_subId[i] != "" && price[i] != "") {
                            if (RegexUtil.isPoint(apiTypeId_subId[i])) {
                                atsArray.append(apiTypeId_subId[i] + "-0:" + (int)(Double.parseDouble(price[i]) * 100) + ",");

                            } else {
                                atsArray.append(apiTypeId_subId[i] + ":" + (int)(Double.parseDouble(price[i]) * 100) + ",");
                            }
                        }
                    }
                }
            }
        }
        if (atsArray != null && atsArray.length() >0){
            String newAtsArray = atsArray.substring(0,atsArray.lastIndexOf(","));
            System.out.println(newAtsArray);
            map.put("ats",newAtsArray);
        }
        StringBuffer ipsArray = new StringBuffer() ;
        if (begIp != null && begIp.length > 0){
            if (endIp != null && endIp.length > 0){
                if (begIp.length <= endIp.length){
                    for ( int j=0 ; j<begIp.length ; j++ ){
                        if (begIp[j] != null && endIp[j] != ""){
                            ipsArray.append(begIp[j]+"-"+endIp[j]+",") ;
                        }
                    }
                }else {
                    for ( int j=0 ; j<endIp.length ; j++ ){
                        if (begIp[j] != null && endIp[j] != "") {
                            ipsArray.append(begIp[j] + "-" + endIp[j] + ",");
                        }
                    }
                }
            }
        }
        if (ipsArray != null && ipsArray.length() >0){
            String newIpsArray = ipsArray.substring(0,ipsArray.lastIndexOf(","));
            System.out.println(newIpsArray);
            map.put("ips",newIpsArray);
        }

        int code = HttpClientUtil.doGet(uri,map,null);
        if (200 == code){
            return code;
        }
        throw new Exception("http请求异常，请求状态码statusCode="+code);

    }

    @Override
    public List<Partner> findAllPartner() {
        try {
            return companyMapper.findAllPartner();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Partner> findPartnerByEmail(String email) {
        return companyMapper.findPartnerByEmail(email);
    }


    @Override
    public List<CustomerBalanceModifyReason> findBalanceReason(List<Integer> list) {
        try {
            return companyMapper.findBalanceReason(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @SystemServiceLog(description = "账号充值/扣费")
    public int updateCustomerBalance(Integer companyId, Integer reason, String amount) throws Exception{
        final String uri = "https://api.qydata.org:9000/admin/customer/balance/charge";
        Map<String,Object> map = new HashMap<>();
        map.put("k",companyMapper.queryAuthKey("admin.k"));
        map.put("cid",companyMapper.queryOfficAuthIdByCompanyId(companyId).getId());
        map.put("rid",reason);
        map.put("amount",(int)(Double.parseDouble(amount)*100));

        int  code = HttpClientUtil.doGet(uri,map,null);
        if (200 == code){
            return code;
        }
        throw new Exception("http请求异常，请求状态码statusCode="+code);
    }

    @Override
    @SystemServiceLog(description = "账号禁用")
    public int updateCustomerBan(String authId) throws Exception{
        final String uri = "https://api.qydata.org:9000/admin/customer/ban";
        Map<String,Object> map = new HashMap<>();
        map.put("k",companyMapper.queryAuthKey("admin.k"));
        map.put("authid",authId);
        int  code = HttpClientUtil.doGet(uri,map,null);
        if (200 == code){
            return code;
        }
        throw new Exception("http请求异常，请求状态码statusCode="+code);
    }

    @Override
    @SystemServiceLog(description = "账号解禁")
    public int updateCustomerUnBan(String authId) throws Exception{
        final String uri = "https://api.qydata.org:9000/admin/customer/unban";
        Map<String,Object> map = new HashMap<>();
        map.put("k",companyMapper.queryAuthKey("admin.k"));
        map.put("authid",authId);
        int  code = HttpClientUtil.doGet(uri,map,null);
        if (200 == code){
            return code;
        }
        throw new Exception("http请求异常，请求状态码statusCode="+code);
    }

    @Override
    @SystemServiceLog(description = "公司禁用")
    public Map<String,Object> updateCompanyBan(String [] companyId) throws Exception{
        final String uri = "https://api.qydata.org:9000/admin/company/ban";
        Map<String,Object> mapResu = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<companyId.length; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("k",companyMapper.queryAuthKey("admin.k"));
            map.put("cid",companyId[i]);
            int code = HttpClientUtil.doGet(uri,map,null);
            if (code != 200){
                sb.append(companyMapper.queryCompanyNameByCompanyId(Integer.parseInt(companyId[i]))+"，");
                mapResu.put("fail","公司名称是："+sb+"禁用失败其余禁用正常");
            }else {
                mapResu.put("success","禁用成功");
            }
        }
        return mapResu;
    }

    @Override
    @SystemServiceLog(description = "公司解禁")
    public Map<String,Object> updateCompanyUnBan(String [] companyId) throws Exception{
        final String uri = "https://api.qydata.org:9000/admin/company/unban";
        Map<String,Object> mapResu = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<companyId.length; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("k",companyMapper.queryAuthKey("admin.k"));
            map.put("cid",companyId[i]);
            int code = HttpClientUtil.doGet(uri,map,null);
            if (code != 200){
                sb.append(companyMapper.queryCompanyNameByCompanyId(Integer.parseInt(companyId[i]))+"，");
                mapResu.put("fail","公司名称是："+sb+"启用失败其余启用正常");
            }else {
                mapResu.put("success","启用成功");
            }
        }
        return mapResu;
    }

    @Override
    public List<CompanyApi> queryCompanyApiByCompanyId(Map<String, Object> map) {
        return companyMapper.queryCompanyApiByCompanyId(map);
    }

    @Override
    @SystemServiceLog(description = "产品权限禁用")
    public int banCompanyApi(Integer companyId,Integer id)throws Exception {
        final String uri = "https://api.qydata.org:9000/admin/company/api/del";
        Map<String,Object> map = new HashMap<>();
        map.put("k",companyMapper.queryAuthKey("admin.k"));
        map.put("cid",companyId);
        map.put("id",id);
        int  code = HttpClientUtil.doGet(uri,map,null);
        if (200 == code){
            return code;
        }
        throw new Exception("http请求异常，请求状态码statusCode="+code);
    }

    @Override
    @SystemServiceLog(description = "产品权限解禁")
    public int unBanCompanyApi(Integer id) {
        companyMapper.unBanCompanyApi(id);
        return 0;
    }

    @Override
    public List<ApiTypeSubType> queryNotHaveApi(Integer companyId) {
        Map<String,Object> map = new HashMap<>();
        map.put("companyId",companyId);
        //全部产品权限列表
        List<ApiTypeSubType> apiTypeSubTypeList = companyMapper.queryAllApi();
        //客户已拥有的权限列表
        List<CompanyApi> companyApiList =  companyMapper.queryCompanyApiByCompanyId(map);
        //去重
        if (apiTypeSubTypeList != null && apiTypeSubTypeList.size() > 0){
            if (companyApiList != null && companyApiList.size() > 0){
                for (int i=0; i<companyApiList.size();i++){
                    CompanyApi companyApi = companyApiList.get(i);
                    for (int j=0;j<apiTypeSubTypeList.size();j++){
                        ApiTypeSubType apiTypeSubType = apiTypeSubTypeList.get(j);
                        if (companyApi.getApiTypeId() == apiTypeSubType.getApiTypeId()){
                            if (apiTypeSubType.getMobileOperatorId() != null){
                                if (companyApi.getSubTypeId() == apiTypeSubType.getMobileOperatorId()){
                                    apiTypeSubTypeList.remove(j);
                                    j=0;
                                }
                            }else {
                                apiTypeSubTypeList.remove(j);
                                j=0;
                            }
                        }
                    }
                }
                return apiTypeSubTypeList;
            }
            return apiTypeSubTypeList;
        }
        return null;
    }

    @Override
    @SystemServiceLog(description = "新增产品权限")
    public int addCompanyApi(Integer companyId, String apiTypeId, String price)throws Exception {
        String uri = "https://api.qydata.org:9000/admin/company/api/put";
        Map<String,Object> map = new HashMap<>();
        map.put("k",companyMapper.queryAuthKey("admin.k"));
        map.put("cid",companyId);
        map.put("price",(int)(Double.parseDouble(price)*100));
        if (RegexUtil.isDot(apiTypeId)){
            map.put("tid",apiTypeId);
        }else {
            String apiTypeIds [] = apiTypeId.split(",");
            map.put("tid",apiTypeIds[0]);
            map.put("stid",apiTypeIds[1]);
        }
        int  code = HttpClientUtil.doGet(uri,map,null);
        if (200 == code){
            return code;
        }
        throw new Exception("http请求异常，请求状态码statusCode="+code);

    }

    @Override
    @SystemServiceLog(description = "修改下游产品价格")
    public int updateCompanyApiPrice(Integer companyId, String apiTypeId, String price) throws Exception{

        String uri = "https://api.qydata.org:9000/admin/company/api/put";
        Map<String,Object> map = new HashMap<>();
        map.put("k",companyMapper.queryAuthKey("admin.k"));
        map.put("cid",companyId);
        map.put("price",(int)(Double.parseDouble(price)*100));
        if (RegexUtil.isTwoUnderLine(apiTypeId)){
            Integer tid = companyMapper.queryApiTypeIdByName(apiTypeId);
            map.put("tid",tid);

        }else {
            String apiTypeIds [] = apiTypeId.split("--");
            Integer tid = companyMapper.queryApiTypeIdByName(apiTypeIds[0]);
            Integer stid = companyMapper.queryStidByName(apiTypeIds[1]);
            map.put("tid",tid);
            map.put("stid",stid);
        }
        int  code = HttpClientUtil.doGet(uri,map,null);
        if (200 == code){
            return code;
        }
        throw new Exception("http请求异常，请求状态码statusCode="+code);

    }

    @Override
    public List<CustomerIp> queryCustomerIpById(Integer companyId) {
        return companyMapper.queryCustomerIpById(companyMapper.queryOfficAuthIdByCompanyId(companyId).getId());
    }

    @Override
    @SystemServiceLog(description = "正式账号添加IP")
    public int addCustomerIp(Integer companyId,String begIp, String endIp) throws Exception{
        final String uri = "https://api.qydata.org:9000/admin/customer/ip/add";
        Map<String,Object> map = new HashMap<>();
        map.put("k",companyMapper.queryAuthKey("admin.k"));
        map.put("cid",companyMapper.queryOfficAuthIdByCompanyId(companyId).getId());
        map.put("bip",begIp);
        map.put("eip",endIp);
        int  code = HttpClientUtil.doGet(uri,map,null);
        if (200 == code){
            return code;
        }
        throw new Exception("http请求异常，请求状态码statusCode="+code);
    }

    @Override
    @SystemServiceLog(description = "正式账号删除Ip")
    public int deleteIpById(Integer companyId,Integer id) throws Exception {
        String uri = "https://api.qydata.org:9000/admin/customer/ip/del";
        Map<String,Object> map = new HashMap<>();
        map.put("k",companyMapper.queryAuthKey("admin.k"));
        map.put("cid",companyMapper.queryOfficAuthIdByCompanyId(companyId).getId());
        map.put("id",id);
        int code = HttpClientUtil.doGet(uri,map,null);
        if (200 == code){
            return code;
        }
        throw new Exception("http请求异常，请求状态码statusCode="+code);
    }

    @Override
    public List<ApiTypeSubType> queryAllApi() {
        return companyMapper.queryAllApi();
    }

    @Override
    @SystemServiceLog(description = "修改信用额度")
    public int updateCredit(Integer companyId, Integer credit) throws Exception {
        final String uri = "https://api.qydata.org:9000/admin/customer/credit/put";
        Map<String,Object> map = new HashMap<>();
        map.put("k",companyMapper.queryAuthKey("admin.k"));
        map.put("cid",companyMapper.queryOfficAuthIdByCompanyId(companyId).getId());
        map.put("amount",credit*100);
        int  code = HttpClientUtil.doGet(uri,map,null);
        if (200 == code){
            return code;
        }
        throw new Exception("http请求异常，请求状态码statusCode="+code);
    }


}
