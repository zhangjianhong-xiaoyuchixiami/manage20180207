package org.qydata.controller;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.qydata.dst.ApiFinance;
import org.qydata.service.ApiFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/18.
 */
@Controller
@RequestMapping("/api")
public class ApiFinanceController {

    @Autowired private ApiFinanceService apiFinanceService;

    //Api消费账单
    @RequestMapping("/find-all-api-record")
    public String findAllApiRecord(Integer apiId, Integer apiTypeId, Integer vendorId, Model model){
        Map<String,Object> map = new HashedMap();
        if (apiId !=null){
            map.put("apiId",apiId);
        }
        List<ApiFinance> apiVendorApiList  = null;
        if (apiTypeId !=null){
            map.put("apiTypeId",apiTypeId);
            apiVendorApiList  = apiFinanceService.queryApiVendorAndApi(map);
        }
        if (vendorId !=null){
            map.put("vendorId",vendorId);
            apiVendorApiList  = apiFinanceService.queryApiVendorAndApi(map);
        }
        long weekTotleAmount = 0L;
        long monthTotleAmount = 0L;
        long consumeTotleAmount = 0L;
        long totleBalance = 0L;
        List<ApiFinance> apiFinanceList = apiFinanceService.queryApiOverAllFinance(map);
        if (apiFinanceList != null){
            for (int i=0; i<apiFinanceList.size(); i++){
                ApiFinance apiFinance = apiFinanceList.get(i);
                if(apiFinance.getWeekTotleCost() != null){
                    weekTotleAmount = weekTotleAmount +apiFinance.getWeekTotleCost();
                }
                if(apiFinance.getMonthTotleCost() != null){
                    monthTotleAmount = monthTotleAmount +apiFinance.getMonthTotleCost();
                }
                if(apiFinance.getConsumeTotleAmount() != null){
                    consumeTotleAmount = consumeTotleAmount +apiFinance.getConsumeTotleAmount();
                }
                if(apiFinance.getBalance() != null){
                    totleBalance = totleBalance +apiFinance.getBalance();
                }
            }
        }
        model.addAttribute("apiFinanceList",apiFinanceList);
        model.addAttribute("apiVendorApiList",apiVendorApiList);
        model.addAttribute("apiId",apiId);
        model.addAttribute("apiTypeId",apiTypeId);
        model.addAttribute("vendorId",vendorId);
        model.addAttribute("weekTotleAmount",weekTotleAmount);
        model.addAttribute("monthTotleAmount",monthTotleAmount);
        model.addAttribute("consumeTotleAmount",consumeTotleAmount);
        model.addAttribute("totleBalance",totleBalance);
        return "/finance/apiRecord";
    }

    /**
     * apiVendor二级级联
     * @return
     */
    @RequestMapping("/find-api-vendor-by-api-type-id")
    @ResponseBody
    public String findApiVendorByApiTypeId(Integer apiTypeId){
        Map<String, Object> map = new HashedMap();
        map.put("apiTypeId", apiTypeId);
        List<ApiFinance> apiVendorList  = apiFinanceService.queryApiVendorAndApi(map);
        JSONArray jsonArray = JSONArray.fromObject(apiVendorList);
        return jsonArray.toString();
    }

    /**
     * api三级级联
     * @return
     */
    @RequestMapping("/find-api-by-api-type-id")
    @ResponseBody
    public String findApiByApiTypeId(Integer apiTypeId,Integer vendorId){
        Map<String, Object> map = new HashedMap();
        map.put("apiTypeId", apiTypeId);
        map.put("vendorId",vendorId);
        List<ApiFinance> apiList  = apiFinanceService.queryApiVendorAndApi(map);
        JSONArray jsonArray = JSONArray.fromObject(apiList);
        return jsonArray.toString();
    }


    //Api消费账单-消费明细
    @RequestMapping("/find-all-api-record/detail")
    public String findAllApiDetailRecord(){
        return "/finance/apiDetailRecord";
    }

    //Api消费总额饼状图
    @RequestMapping("/find-all-api-record/count-result")
    @ResponseBody
    public String findApiRecordCountResult(){
        Map<String,Object> map = new HashedMap();
        Map<String,Object> map1 = new HashMap<>();
        long consumeTotleAmount = 0L;
        List<ApiFinance> apiFinanceList = apiFinanceService.queryApiOverAllFinance(map);
        if (apiFinanceList != null){
            for (int i=0; i<apiFinanceList.size(); i++){
                ApiFinance apiFinance = apiFinanceList.get(i);
                if (apiFinance.getConsumeTotleAmount() != null){
                    consumeTotleAmount = consumeTotleAmount + apiFinance.getConsumeTotleAmount();
                }
            }
            for (int i=0; i<apiFinanceList.size(); i++){
                ApiFinance apiFinance = apiFinanceList.get(i);
                if(apiFinance.getConsumeTotleAmount() != null){
                    map1.put(apiFinance.getApiName(),apiFinance.getConsumeTotleAmount()/100.0);
                }else {
                    map1.put(apiFinance.getApiName(),0);
                }
            }
        }
        Gson gson = new Gson();
        return gson.toJson(map1);
    }

    //Api消费总额柱状图图
    @RequestMapping("/find-all-api-record/bar-chart")
    @ResponseBody
    public String findApiRecordBarChart(){
        Map<String,Object> map = new HashedMap();
        List xList = new ArrayList();
        List yList = new ArrayList();
        List<ApiFinance> apiFinanceList = apiFinanceService.queryApiOverAllFinance(map);
        if (apiFinanceList != null){
            for (int i=0; i<apiFinanceList.size(); i++){
                ApiFinance apiFinance = apiFinanceList.get(i);
                xList.add(apiFinance.getApiName());
                if(apiFinance.getConsumeTotleAmount() != null){
                    yList.add(apiFinance.getConsumeTotleAmount()/100.0);
                }else {
                    yList.add(0);
                }
            }
        }
        Map mapOne = new HashedMap();
        mapOne.put("name","产品消费总额");
        mapOne.put("data",yList);
        JSONArray jsonArray = JSONArray.fromObject(xList);
        JSONArray jsonArray1 = JSONArray.fromObject(mapOne);
        JSONObject getObj = new JSONObject();
        getObj.put("xList", jsonArray);
        getObj.put("yList", jsonArray1);
        return getObj.toString();
    }
}
