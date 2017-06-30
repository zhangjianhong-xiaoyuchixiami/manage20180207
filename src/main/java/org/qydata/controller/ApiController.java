package org.qydata.controller;

import com.google.gson.Gson;
import org.qydata.entity.ApiBan;
import org.qydata.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    /**
     * 查询api最近请求的失败次数
     * @param model
     * @return
     */
    @RequestMapping("/api-monitor")
    public String queryApiMonitor(Model model){
        List<ApiBan> apiBanList = apiService.queryApiMonitor();
        model.addAttribute("apiBanList",apiBanList);
        return "/api/apimonitor";
    }

    /**
     * 禁用产品
     * @return
     */
    @RequestMapping("/ban")
    @ResponseBody
    public String apiBan(HttpServletRequest request){
        Gson gson = new Gson();
        String [] apiId = request.getParameterValues("apiId[]");
        Map<String,Object> mapResu = null;
        try {
            mapResu = apiService.updateApiBan(apiId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gson.toJson(mapResu);
    }

    /**
     * 解禁产品
     * @param request
     * @return
     */
    @RequestMapping("/unban")
    @ResponseBody
    public String apiUnBan(HttpServletRequest request){
        Gson gson = new Gson();
        String [] apiId = request.getParameterValues("apiId[]");
        Map<String,Object> mapResu = null;
        try {
            mapResu = apiService.updateApiUnBan(apiId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gson.toJson(mapResu);
    }


}
