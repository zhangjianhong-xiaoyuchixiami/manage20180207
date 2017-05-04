package org.qydata.service.impl;


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
    public boolean addCompanyCustomer(String companyName,String authId,String partnerId,String apiTypeId_subId [],String price [],String begIp [],String endIp []) {

        String uri = "https://192.168.111.147:8989/admin/company/api/put";
        Map<String,Object> map = new HashMap<>();
        map.put("k",123456);
        map.put("comName",companyName);
        map.put("authId",authId);
        if (partnerId != null){
            map.put("partnerId",partnerId);
        }
        if (apiTypeId_subId != null && apiTypeId_subId.length > 0){
            if (price != null && price.length > 0){
                if (apiTypeId_subId.length <= price.length){
                    for ( int i=0 ; i<apiTypeId_subId.length ; i++ ){
                        if (RegexUtil.isPoint(apiTypeId_subId[i])){
                            map.put("tid",apiTypeId_subId[i]);
                        }else {
                            String apiTypeIds [] = apiTypeId_subId[i].split(".");
                            map.put("tid",apiTypeIds[0]);
                            map.put("stid",apiTypeIds[1]);
                        }
                        map.put("price",price[i]);
                    }
                }else {
                    for ( int i=0 ; i<price.length ; i++ ){
                        if (RegexUtil.isPoint(apiTypeId_subId[i])){
                            map.put("tid",apiTypeId_subId[i]);

                        }else {
                            String apiTypeIds [] = apiTypeId_subId[i].split(".");
                            map.put("tid",apiTypeIds[0]);
                            map.put("stid",apiTypeIds[1]);
                        }
                        map.put("price",price[i]);
                    }
                }
            }
        }
        if (begIp != null && begIp.length > 0){
            if (endIp != null && endIp.length > 0){
                if (begIp.length <= endIp.length){
                    for ( int j=0 ; j<begIp.length ; j++ ){
                        map.put("begIp",begIp[j]);
                        map.put("endIp",endIp[j]);
                    }
                }else {
                    for ( int j=0 ; j<endIp.length ; j++ ){
                        map.put("begIp",begIp[j]);
                        map.put("endIp",endIp[j]);
                    }
                }
            }
        }
        int result = 0;
        try {
            result = HttpClientUtil.doGet(uri,map,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
    public boolean addCustomer(String authId, Integer companyId) {
        try {
            Integer deptId = companyMapper.findDeptIdByCompanyId(companyId);
            //向客户表中插入数据
            Customer customerB = new Customer();
            customerB.setAuthId(authId.trim() + "_test");
            customerB.setCompanyId(companyId);
            customerMapper.insertCustomerTest(customerB);

            Customer customerA = new Customer();
            customerA.setAuthId(authId.trim());
            customerA.setCompanyId(companyId);
            customerMapper.insertCustomer(customerA);
            if (deptId != null){
                //向部门客户映射表中插入数据
                CustomerDept customerDeptA = new CustomerDept();
                customerDeptA.setCustomerId(customerA.getId());
                customerDeptA.setDeptId(deptId);
                customerDeptMapper.insertCustomerDept(customerDeptA);

                CustomerDept customerDeptB = new CustomerDept();
                customerDeptB.setCustomerId(customerB.getId());
                customerDeptB.setDeptId(deptId);
                customerDeptMapper.insertCustomerDept(customerDeptB);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
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
    public int updateCustomerBalance(Integer customerId, Integer reason, Long amount) {
        final String uri = "https://192.168.111.147:8989/admin/customer/balance/charge";
        int result = 0;
        Map<String,Object> map = new HashMap<>();
        map.put("k",123456);
        map.put("cid",customerId);
        map.put("rid",reason);
        map.put("amount",amount*100);
        try {
            result = HttpClientUtil.doGet(uri,map,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateCustomerBan(String authId) {
        final String uri = "https://192.168.111.147:8989/admin/customer/ban";
        int result = 0;
        Map<String,Object> map = new HashMap<>();
        map.put("k",123456);
        map.put("authid",authId);
        try {
            result = HttpClientUtil.doGet(uri,map,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateCustomerUnBan(String authId) {
        final String uri = "https://192.168.111.147:8989/admin/customer/unban";
        int result = 0;
        Map<String,Object> map = new HashMap<>();
        map.put("k",123456);
        map.put("authid",authId);
        try {
            result = HttpClientUtil.doGet(uri,map,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Map<String,Object> updateCompanyBan(String [] companyId) {
        final String uri = "https://192.168.111.147:8989/admin/company/ban";
        Map<String,Object> mapResu = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<companyId.length; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("k",123456);
            map.put("cid",companyId[i]);
            try {
                int result = HttpClientUtil.doGet(uri,map,null);
                if (result != 200){

                    sb.append(companyMapper.queryCompanyNameByCompanyId(Integer.parseInt(companyId[i]))+"，");
                    mapResu.put("fail","公司名称是："+sb+"禁用失败其余禁用正常");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapResu;
    }

    @Override
    public Map<String,Object> updateCompanyUnBan(String [] companyId) {
        final String uri = "https://192.168.111.147:8989/admin/company/unban";
        Map<String,Object> mapResu = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<companyId.length; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("k",123456);
            map.put("cid",companyId[i]);
            try {
                int result = HttpClientUtil.doGet(uri,map,null);
                if (result != 200){

                    sb.append(companyMapper.queryCompanyNameByCompanyId(Integer.parseInt(companyId[i]))+"，");
                    mapResu.put("fail","公司名称是："+sb+"启用失败其余启用正常");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapResu;
    }

    @Override
    public List<CompanyApi> queryCompanyApiByCompanyId(Map<String, Object> map) {
        return companyMapper.queryCompanyApiByCompanyId(map);
    }

    @Override
    public int banCompanyApi(Integer companyId,Integer id) {
        final String uri = "https://192.168.111.147:8989/admin/company/api/del";
        int result = 0;
        Map<String,Object> map = new HashMap<>();
        map.put("k",123456);
        map.put("cid",companyId);
        map.put("id",id);
        try {
            result = HttpClientUtil.doGet(uri,map,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean unBanCompanyApi(Integer id) {
        return companyMapper.unBanCompanyApi(id);
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
    public int addCompanyApi(Integer companyId, String apiTypeId, String price) {
        String uri = "https://192.168.111.147:8989/admin/company/api/put";
        Map<String,Object> map = new HashMap<>();
        map.put("k",123456);
        map.put("cid",companyId);
        map.put("price",(int)(Double.parseDouble(price)*100));
        if (RegexUtil.isDot(apiTypeId)){
            map.put("tid",apiTypeId);
        }else {
            String apiTypeIds [] = apiTypeId.split(",");
            map.put("tid",apiTypeIds[0]);
            map.put("stid",apiTypeIds[1]);
        }
        int result = 0;
        try {
            result = HttpClientUtil.doGet(uri,map,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public List<CustomerIp> queryCustomerIpById(Integer customerId) {
        return companyMapper.queryCustomerIpById(customerId);
    }

    @Override
    public int addCustomerIp(Integer customerId,String begIp, String endIp) {
        final String uri = "https://192.168.111.147:8989/admin/customer/ip/add";
        int result = 0;
        System.out.println("customerId------"+customerId);
        System.out.println("begIp------"+begIp);
        System.out.println("endIp------"+endIp);
        Map<String,Object> map = new HashMap<>();
        map.put("k",123456);
        map.put("cid",customerId);
        map.put("bip",begIp);
        map.put("eip",endIp);
        try {
            result = HttpClientUtil.doGet(uri,map,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteIpById(Integer customerId,Integer id) throws Exception {
        String uri = "https://192.168.111.147:8989/admin/customer/ip/del";
        Map<String,Object> map = new HashMap<>();
        map.put("k",123456);
        map.put("cid",customerId);
        map.put("id",id);
        int code = HttpClientUtil.doGet(uri,map,null);
        if (200 == code){
            return code;
        }
        throw new Exception("https请求异常，请求状态码statusCode="+code);
    }

    @Override
    public List<ApiTypeSubType> queryAllApi() {
        return companyMapper.queryAllApi();
    }


}
