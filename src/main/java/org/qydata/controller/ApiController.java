package org.qydata.controller;

import net.sf.json.JSONArray;
import org.qydata.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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
    public String queryApi(Model model,Integer apiTypeId,Integer vendorId){
        Map<String,Object> map = new HashMap<>();
        if (apiTypeId != null){
            map.put("apiTypeId",apiTypeId);
            model.addAttribute("apiVendorList",apiService.queryApiVendorByApiTypeId(apiTypeId));
        }
        if (vendorId != null){
            map.put("vendorId",vendorId);
        }
        model.addAttribute("apiList",apiService.queryApi(map));
        model.addAttribute("apiTypeList",apiService.queryApiType());
        model.addAttribute("apiTypeId",apiTypeId);
        model.addAttribute("vendorId",vendorId);
        return "/api/apiproduct";
    }

    /**
     * apiVendor级联
     * @param apiTypeId
     * @return
     */
    @RequestMapping(value = "/api-vendor")
    @ResponseBody
    public String queryApiType(Integer apiTypeId){
        return JSONArray.fromObject(apiService.queryApiVendorByApiTypeId(apiTypeId)).toString();
    }

}
