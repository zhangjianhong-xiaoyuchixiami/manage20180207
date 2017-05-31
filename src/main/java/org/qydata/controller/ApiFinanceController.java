package org.qydata.controller;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.qydata.dst.ApiFinance;
import org.qydata.entity.ApiVendor;
import org.qydata.service.ApiFinanceService;
import org.qydata.tools.CalendarTools;
import org.qydata.tools.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by jonhn on 2017/1/18.
 */
@Controller
@RequestMapping("/api")
public class ApiFinanceController {

    @Autowired private ApiFinanceService apiFinanceService;

    /**
     * Api消费账单
     * @param vendorId
     * @param apiTypeId
     * @param model
     * @return
     */
    @RequestMapping("/find-all-api-record")
    public ModelAndView findAllApiRecord(String export,Integer vendorId, Integer apiTypeId, String beginDate,String endDate,String [] status,Model model){
        Map<String,Object> map = new HashedMap();
        if (vendorId !=null){
            map.put("vendorId",vendorId);
        }
        if (beginDate != null && beginDate != "" ) {
            map.put("beginDate", beginDate+" "+"00:00:00");
            model.addAttribute("beginDate",beginDate);
        }
        if(endDate != null && endDate != ""){
            map.put("endDate", endDate+" "+"23:59:59");
            model.addAttribute("endDate",endDate);
        }
        List<ApiVendor> apiVendorList  = null;
        if (apiTypeId !=null){
            map.put("apiTypeId",apiTypeId);
            apiVendorList  = apiFinanceService.queryApiVendorName(map);
        }
        List<String> statusList = new ArrayList();
        if (status != null && status.length >0) {
            for(int i=0;i<status.length;i++){
                statusList.add(status[i]);
            }
        }else {
            statusList.add("0");
            statusList.add("-1");
        }
        map.put("statusList", statusList);
        Map<String,Object> mapResult = apiFinanceService.queryApiOverAllFinance(map);
        List<ApiFinance> apiFinanceList = null;
        Set<Map.Entry<String,Object>> set = mapResult.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while(it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if (me.getKey().equals("queryApiOverAllFinance")){
                apiFinanceList = (List<ApiFinance>) me.getValue();
            }
        }
        long weekTotleAmount = 0L;
        long monthTotleAmount = 0L;
        long consumeTotleAmount = 0L;
        long currMonthTotleCost = 0L;
        long currDayTotleCost = 0L;
        for (int j=0; j<apiFinanceList.size(); j++){
            ApiFinance apiFinance = apiFinanceList.get(j);
            if (apiFinance.getCurrMonthCost() != null) {
                currMonthTotleCost = currMonthTotleCost + apiFinance.getCurrMonthCost();
            }
            if (apiFinance.getCurrDayCost() != null) {
                currDayTotleCost = currDayTotleCost + apiFinance.getCurrDayCost();
            }
            if (apiFinance.getWeekTotleCost() != null) {
                weekTotleAmount = weekTotleAmount + apiFinance.getWeekTotleCost();
            }
            if (apiFinance.getMonthTotleCost() != null) {
                monthTotleAmount = monthTotleAmount + apiFinance.getMonthTotleCost();
            }
            if (apiFinance.getConsumeTotleAmount() != null) {
                consumeTotleAmount = consumeTotleAmount + apiFinance.getConsumeTotleAmount();
            }
        }
        model.addAttribute("weekTotleAmount",weekTotleAmount);
        model.addAttribute("monthTotleAmount",monthTotleAmount);
        model.addAttribute("consumeTotleAmount",consumeTotleAmount);
        model.addAttribute("apiFinanceList",apiFinanceList);
        model.addAttribute("apiTypeList",apiFinanceService.queryApiType());
        model.addAttribute("apiVendorList",apiVendorList);
        model.addAttribute("vendorId",vendorId);
        model.addAttribute("apiTypeId",apiTypeId);
        model.addAttribute("statusArray",statusList);
        model.addAttribute("currMonthTotleCost",currMonthTotleCost);
        model.addAttribute("currDayTotleCost",currDayTotleCost);
        model.addAttribute("currYear",CalendarTools.getYearMonthCount(0));
        model.addAttribute("currMonth",CalendarTools.getMonthCount(0));
        model.addAttribute("currDay",CalendarTools.getYearMonthDayCount(0));
        model.addAttribute("year", CalendarTools.getYearMonthCount(1));
        model.addAttribute("month",CalendarTools.getMonthCount(1));
        model.addAttribute("week_month",CalendarTools.getWeekMonthCount(1));
        model.addAttribute("week",CalendarTools.getYearWeekCount(1));
        return new ModelAndView("/finance/apiRecord");
    }

    /**
     * Api消费账单-消费明细
     * @param apiId
     * @param mobileOperatorName
     * @param apiTypeName
     * @param vendorName
     * @param beginDate
     * @param endDate
     * @param model
     * @return
     */
    @RequestMapping("/find-all-api-record/detail")
    public ModelAndView findAllApiDetailRecord(String export,Integer apiId,String beginDate,String endDate,String mobileOperatorName,String apiTypeName,String vendorName,Model model){
        Map<String,Object> map = new HashedMap();
        map.put("apiId",apiId);
        if (beginDate != null && beginDate != "" ) {
            map.put("beginDate", beginDate+" "+"00:00:00");
        }
        if(endDate != null && endDate != ""){
            map.put("endDate", endDate+" "+"23:59:59");
        }
        Map<String,Object> mapResult = apiFinanceService.queryApiDetailById(map);
        Set<Map.Entry<String,Object>> set = mapResult.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while(it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if (me.getKey().equals("queryApiDetailById")){
                model.addAttribute("apiRequestLogList",me.getValue());
            }
            if (me.getKey().equals("getCountApiDetailById")){
                model.addAttribute("totleAmount",me.getValue());
            }
        }
        model.addAttribute("apiId",apiId);
        model.addAttribute("mobileOperatorName",mobileOperatorName);
        model.addAttribute("apiTypeName",apiTypeName);
        model.addAttribute("vendorName",vendorName);
        model.addAttribute("beginDate",beginDate);
        model.addAttribute("endDate",endDate);
        return new ModelAndView("/finance/apiDetailRecord");
    }


    /**
     * 以APIVendor统计消费信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/find-all-api-vendor-consume")
    public ModelAndView findAllApiVendorConsume(String export,Integer vendorId,Integer partnerId,String beginDate,String endDate,String [] status,Model model){
        Map<String,Object> map = new HashMap<>();
        if(vendorId != null){
            map.put("vendorId",vendorId);
        }
        if(partnerId != null){
            map.put("partnerId",partnerId);
        }
        if (beginDate != null && beginDate != "" ) {
            map.put("beginDate", beginDate+" "+"00:00:00");
            model.addAttribute("beginDate",beginDate);
        }
        if(endDate != null && endDate != ""){
            map.put("endDate", endDate+" "+"23:59:59");
            model.addAttribute("endDate",endDate);
        }
        List<String> statusList = new ArrayList();
        if (status != null && status.length >0) {
            for(int i=0;i<status.length;i++){
                statusList.add(status[i]);
            }
        }else {
            statusList.add("0");
            statusList.add("-1");
        }
        map.put("statusList", statusList);
        List<ApiFinance> apiFinanceList = null;
        Map<String,Object> mapResult = apiFinanceService.queryApiVendor(map);
        Set<Map.Entry<String,Object>> set = mapResult.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while(it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if (me.getKey().equals("queryApiVendor")){
                apiFinanceList = (List<ApiFinance>) me.getValue();
            }
        }
        long totleBalance = 0L;
        long currMonthTotleCost = 0L;
        long currDayTotleCost = 0L;
        long weekTotleAmount = 0L;
        long monthTotleAmount = 0L;
        long consumeTotleAmount = 0L;
        if (apiFinanceList != null && apiFinanceList.size() >0) {
            for (int j = 0; j < apiFinanceList.size(); j++) {
                ApiFinance apiFinance = apiFinanceList.get(j);
                if (apiFinance.getBalance() != null) {
                    totleBalance = totleBalance + apiFinance.getBalance();
                }
                if (apiFinance.getCurrMonthCost() != null) {
                    currMonthTotleCost = currMonthTotleCost + apiFinance.getCurrMonthCost();
                }
                if (apiFinance.getCurrDayCost() != null) {
                    currDayTotleCost = currDayTotleCost + apiFinance.getCurrDayCost();
                }
                if (apiFinance.getWeekTotleCost() != null) {
                    weekTotleAmount = weekTotleAmount + apiFinance.getWeekTotleCost();
                }
                if (apiFinance.getMonthTotleCost() != null) {
                    monthTotleAmount = monthTotleAmount + apiFinance.getMonthTotleCost();
                }
                if (apiFinance.getConsumeTotleAmount() != null) {
                    consumeTotleAmount = consumeTotleAmount + apiFinance.getConsumeTotleAmount();
                }
            }
        }
        model.addAttribute("weekTotleAmount",weekTotleAmount);
        model.addAttribute("monthTotleAmount",monthTotleAmount);
        model.addAttribute("consumeTotleAmount",consumeTotleAmount);
        model.addAttribute("apiFinanceList",apiFinanceList);
        model.addAttribute("apiVendorList",apiFinanceService.queryApiVendorName(map));
        model.addAttribute("statusArray",statusList);
        model.addAttribute("vendorId",vendorId);
        model.addAttribute("partnerId",partnerId);
        model.addAttribute("totleBalance",totleBalance);
        model.addAttribute("currMonthTotleCost",currMonthTotleCost);
        model.addAttribute("currDayTotleCost",currDayTotleCost);
        model.addAttribute("currYear",CalendarTools.getYearMonthCount(0));
        model.addAttribute("currMonth",CalendarTools.getMonthCount(0));
        model.addAttribute("currDay",CalendarTools.getYearMonthDayCount(0));
        model.addAttribute("year",CalendarTools.getYearMonthCount(1));
        model.addAttribute("month",CalendarTools.getMonthCount(1));
        model.addAttribute("week_month",CalendarTools.getWeekMonthCount(1));
        model.addAttribute("week",CalendarTools.getYearWeekCount(1));
        return new ModelAndView("/finance/apiVendorRecord");
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

    /**
     * Api消费总额柱状图
     * @return
     */
    @RequestMapping("/find-all-api-record/bar-chart")
    @ResponseBody
    public String findApiRecordBarChart(){
        Map<String,Object> map = new HashedMap();
        List xList = new ArrayList();
        List yList = new ArrayList();
        List<ApiFinance> apiFinanceList = null;
        Map<String,Object> mapResult = apiFinanceService.queryApiOverAllFinance(map);
        Set<Map.Entry<String,Object>> set = mapResult.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while(it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if (me.getKey().equals("queryApiOverAllFinance")){
                apiFinanceList = (List<ApiFinance>) me.getValue();
            }
        }
        if (apiFinanceList != null){
            for (int i=0; i<apiFinanceList.size(); i++){
                ApiFinance apiFinance = apiFinanceList.get(i);
              /*  if (apiFinance.getMobileOperatorList() != null && apiFinance.getMobileOperatorList().size() >0){

                    xList.add(apiFinance.getApiTypeName()+"--"+apiFinance.getMobileOperator().getName()+"@"+apiFinance.getVendorName());
                }else {
                    xList.add(apiFinance.getApiTypeName()+"@"+apiFinance.getVendorName());
                }*/
                xList.add(apiFinance.getApiTypeName()+"@"+apiFinance.getVendorName());
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


    /**
     * Api消费总额柱状图
     * @return
     */
    @RequestMapping("/find-all-api-vendor-consume/bar-chart")
    @ResponseBody
    public String findAllApiVendorConsumeBarChart(){
        Map<String,Object> map = new HashedMap();
        List xList = new ArrayList();
        List yList = new ArrayList();
        List<ApiFinance> apiFinanceList = null;
        Map<String,Object> mapResult = apiFinanceService.queryApiVendor(map);
        Set<Map.Entry<String,Object>> set = mapResult.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while(it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if (me.getKey().equals("queryApiVendor")){
                apiFinanceList = (List<ApiFinance>) me.getValue();
            }
        }
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

    /**
     * Api充值
     * @param vendorIdCharge
     * @param amount
     * @param remark
     * @param chargeDate
     * @return
     */
    @RequestMapping("/find-all-vendor-record/charge")
    @ResponseBody
    public String chargeApiVendorBalance(Integer vendorIdCharge,String amount,String remark,String chargeDate,Model model)throws Exception{
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
        boolean flag = false;
        try {
            flag = apiFinanceService.updateApiVendorChargeLog(vendorIdCharge, Long.parseLong(amount), remark, chargeDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag){
            map.put("successMessage","恭喜你，操作成功！");
        }else {
            map.put("errorMessage","操作失败，请检查你的输入");
        }
        return gson.toJson(map);
    }

}
