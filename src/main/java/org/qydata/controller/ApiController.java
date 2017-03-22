package org.qydata.controller;

import org.qydata.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by jonhn on 2017/2/28.
 */
@Controller
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired private ApiService apiService;

    /**
     * 查询产品
     * @param model
     * @param apiTypeId
     * @param vendorId
     * @return
     */
    @RequestMapping(value = "/api-message")
    public String queryApi(Model model,Integer apiTypeId,Integer vendorId,Integer partnerId){
        Map<String,Object> map = new HashMap<>();
        if (apiTypeId != null){
            map.put("apiTypeId",apiTypeId);
        }
        if (vendorId != null){
            map.put("vendorId",vendorId);
        }
        if (partnerId != null){
            map.put("partnerId",partnerId);
        }
        Map<String,Object> mapResult = apiService.queryApi(map);
        Set<Map.Entry<String,Object>> set = mapResult.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while(it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if (me.getKey().equals("queryApi")){
                model.addAttribute("apiList",me.getValue());
            }
            if (me.getKey().equals("queryApiDead")){
                model.addAttribute("apiListDead",me.getValue());
            }
        }
        model.addAttribute("apiTypeList",apiService.queryApiType());
        model.addAttribute("apiVendorList",apiService.queryApiVendor());
        model.addAttribute("apiTypeId",apiTypeId);
        model.addAttribute("vendorId",vendorId);
        return "/api/apiproduct";
    }

    /**
     * 以客户纬度查询产品
     * @param model
     * @param apiTypeId
     * @param companyId
     * @return
     */
    @RequestMapping("/api-message-by-company")
    public String queryApiByCompanyId(Model model,Integer apiTypeId,Integer companyId,Integer partnerId){
        Map<String,Object> map = new HashMap<>();
        if (apiTypeId != null){
            map.put("apiTypeId",apiTypeId);
        }
        if (companyId != null){
            map.put("companyId",companyId);
        }
        if (partnerId != null){
            map.put("partnerId",partnerId);
        }
        Map<String,Object> mapResult = apiService.queryApiByCompanyId(map);
        Set<Map.Entry<String,Object>> set = mapResult.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while(it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if (me.getKey().equals("queryApiByCompanyId")){
                model.addAttribute("companyApiList",me.getValue());
            }
            if (me.getKey().equals("queryApiByCompanyIdDead")){
                model.addAttribute("companyApiListDead",me.getValue());
            }
        }
        model.addAttribute("apiTypeList",apiService.queryApiType());
        model.addAttribute("companyList",apiService.queryCompany());
        model.addAttribute("apiTypeId",apiTypeId);
        model.addAttribute("companyId",companyId);
        return "api/companyapiproduct";
    }


}
