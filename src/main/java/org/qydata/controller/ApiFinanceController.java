package org.qydata.controller;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.qydata.dst.ApiFinance;
import org.qydata.entity.ApiRequestLog;
import org.qydata.entity.ApiResponseLog;
import org.qydata.entity.ApiType;
import org.qydata.entity.ApiVendor;
import org.qydata.regex.RegexUtil;
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
    public String findAllApiRecord(Integer vendorId, Integer apiTypeId, Model model){
        List<ApiType> apiTypeList = apiFinanceService.queryApiType();
        System.out.println(apiTypeList);
        Map<String,Object> map = new HashedMap();
        if (vendorId !=null){
            map.put("vendorId",vendorId);
        }
        List<ApiVendor> apiVendorList  = null;
        if (apiTypeId !=null){
            map.put("apiTypeId",apiTypeId);
            apiVendorList  = apiFinanceService.queryApiVendorName(map);
        }
        long weekTotleAmount = 0L;
        long monthTotleAmount = 0L;
        long consumeTotleAmount = 0L;

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
            }
        }
        model.addAttribute("apiFinanceList",apiFinanceList);
        model.addAttribute("apiTypeList",apiTypeList);
        model.addAttribute("apiVendorList",apiVendorList);
        model.addAttribute("vendorId",vendorId);
        model.addAttribute("apiTypeId",apiTypeId);
        model.addAttribute("weekTotleAmount",weekTotleAmount);
        model.addAttribute("monthTotleAmount",monthTotleAmount);
        model.addAttribute("consumeTotleAmount",consumeTotleAmount);
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
        List<ApiVendor> apiVendorList  = apiFinanceService.queryApiVendorName(map);
        JSONArray jsonArray = JSONArray.fromObject(apiVendorList);
        return jsonArray.toString();
    }

    //Api消费总额柱状图
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

    //Api消费账单-消费明细
    @RequestMapping("/find-all-api-record/detail")
    public String findAllApiDetailRecord(Integer apiId,String apiName,String apiTypeName,String vendorName,String beginDate,String endDate,Model model){
        Map<String,Object> map = new HashedMap();
        map.put("apiId",apiId);
        if (beginDate != null && beginDate != "" ) {
            map.put("beginDate", beginDate+" "+"00:00:00");
        }
        if(endDate != null && endDate != ""){
            map.put("endDate", endDate+" "+"23:59:59");
        }
        long totleAmount = 0;
        List<ApiRequestLog> apiRequestLogList = apiFinanceService.queryApiDetailById(map);
        for (int i=0; i<apiRequestLogList.size(); i++){
            ApiRequestLog apiRequestLog = apiRequestLogList.get(i);
            ApiResponseLog apiResponseLog = apiRequestLog.getApiResponseLog();
            totleAmount = totleAmount + apiResponseLog.getCost();
        }
        model.addAttribute("apiRequestLogList",apiRequestLogList);
        model.addAttribute("apiId",apiId);
        model.addAttribute("apiName",apiName);
        model.addAttribute("apiTypeName",apiTypeName);
        model.addAttribute("vendorName",vendorName);
        model.addAttribute("beginDate",beginDate);
        model.addAttribute("endDate",endDate);
        model.addAttribute("totleAmount",totleAmount);
        return "/finance/apiDetailRecord";
    }


    /**
     * 以API类型统计消费信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/find-all-api-vendor-consume")
    public String findAllApiVendorConsume(Integer vendorId,Model model){
        Map<String,Object> map = new HashMap<>();
        List<ApiVendor> apiVendorList  = apiFinanceService.queryApiVendorName(map);
        System.out.println(apiVendorList);
        if(vendorId != null){
            map.put("vendorId",vendorId);
        }
        List<ApiFinance> apiFinanceList = apiFinanceService.queryApiVendor(map);
        long weekTotleAmount = 0L;
        long monthTotleAmount = 0L;
        long consumeTotleAmount = 0L;
        long totleBalance = 0L;
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
        model.addAttribute("apiVendorList",apiVendorList);
        model.addAttribute("vendorId",vendorId);
        model.addAttribute("weekTotleAmount",weekTotleAmount);
        model.addAttribute("monthTotleAmount",monthTotleAmount);
        model.addAttribute("consumeTotleAmount",consumeTotleAmount);
        model.addAttribute("totleBalance",totleBalance);
        return "/finance/apiVendorRecord";
    }

    //Api消费总额柱状图
    @RequestMapping("/find-all-api-vendor-consume/bar-chart")
    @ResponseBody
    public String findAllApiVendorConsumeBarChart(){
        Map<String,Object> map = new HashedMap();
        List xList = new ArrayList();
        List yList = new ArrayList();
        List<ApiFinance> apiFinanceList = apiFinanceService.queryApiVendor(map);
        if (apiFinanceList != null){
            for (int i=0; i<apiFinanceList.size(); i++){
                ApiFinance apiFinance = apiFinanceList.get(i);
                xList.add(apiFinance.getVendorName());
                if(apiFinance.getConsumeTotleAmount() != null){
                    yList.add(apiFinance.getConsumeTotleAmount()/100.0);
                }else {
                    yList.add(0);
                }
            }
        }
        Map mapOne = new HashedMap();
        mapOne.put("name","供应商消费总额");
        mapOne.put("data",yList);
        JSONArray jsonArray = JSONArray.fromObject(xList);
        JSONArray jsonArray1 = JSONArray.fromObject(mapOne);
        JSONObject getObj = new JSONObject();
        getObj.put("xList", jsonArray);
        getObj.put("yList", jsonArray1);
        return getObj.toString();
    }

    //Api充值
    @RequestMapping("/find-all-vendor-record/charge")
    @ResponseBody
    public String chargeApiVendorBalance(Integer vendorIdCharge,String amount,String remark,String chargeDate){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap();
        if(RegexUtil.isNull(amount)){
            map.put("amountMessage","请输入金额");
            return gson.toJson(map);
        }
        if(!RegexUtil.isFloatZero(amount)){
            map.put("amountMessage","金额格式不正确");
            return gson.toJson(map);
        }else {
            if (Integer.parseInt(amount)<=0){
                map.put("amountMessage","金额必须大于0");
                return gson.toJson(map);
            }
        }
        boolean flag = apiFinanceService.apiVendorChargeLog(vendorIdCharge, Long.parseLong(amount), remark, chargeDate);
        if (flag){
            map.put("successMessage","恭喜你，操作成功！");
        }else {
            map.put("errorMessage","操作失败，请检查你的输入");
        }
        return gson.toJson(map);
    }


}
