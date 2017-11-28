package org.qydata.controller;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.qydata.dst.ApiFinance;
import org.qydata.dst.VendorHistoryBill;
import org.qydata.entity.ApiVendor;
import org.qydata.service.ApiFinanceService;
import org.qydata.service.VendorHistoryBillService;
import org.qydata.tools.CalendarTools;
import org.qydata.tools.RegexUtil;
import org.qydata.tools.date.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jonhn on 2017/1/18.
 */
@Controller
@RequestMapping("/api")
public class ApiFinanceController {

    @Autowired
    private ApiFinanceService service;

    /**
     * Api消费账单
     */
    @RequestMapping("/find-all-api-record")
    public ModelAndView findAllApiRecord(String export,Integer vendorId, Integer apiTypeId, String beginDate,String endDate,String [] status,Model model) throws Exception {
        Map<String,Object> map = new HashedMap();
        if (vendorId !=null){
            map.put("vendorId",vendorId);
            model.addAttribute("vendorId",vendorId);
        }
        if(beginDate != null && beginDate != ""){
            map.put("beginDate", CalendarUtil.getTranByInputTime(beginDate));
            model.addAttribute("beginDate",beginDate);
        }
        if(endDate != null && endDate != ""){
            map.put("endDate", CalendarUtil.getAfterDayByInputTime(endDate));
            model.addAttribute("endDate",endDate);
        }
        if (apiTypeId !=null){
            map.put("apiTypeId",apiTypeId);
            model.addAttribute("vendorId",vendorId);
            model.addAttribute("apiVendorList",service.queryApiVendorName(map));
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
        model.addAttribute("statusArray",statusList);
        Map<String,Object> mapResult = service.queryApiFinance(map);
        Set<Map.Entry<String,Object>> set = mapResult.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while(it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if (me.getKey().equals("financeList")){
                model.addAttribute("financeList",me.getValue());
            }
            if (me.getKey().equals("lastWeekConsume")){
                model.addAttribute("lastWeekConsume",me.getValue());
            }
            if (me.getKey().equals("lastMonthConsume")){
                model.addAttribute("lastMonthConsume",me.getValue());
            }
            if (me.getKey().equals("currMonthConsume")){
                model.addAttribute("currMonthConsume",me.getValue());
            }
            if (me.getKey().equals("currDayConsume")){
                model.addAttribute("currDayConsume",me.getValue());
            }
            if (me.getKey().equals("totleConsume")){
                model.addAttribute("totleConsume",me.getValue());
            }
        }
        model.addAttribute("apiTypeList",service.queryApiType());
        return new ModelAndView("/finance/api_finance");
    }

    /**
     * apiVendor二级级联
     */
    @RequestMapping("/find-api-vendor-by-api-type-id")
    @ResponseBody
    public String findApiVendorByApiTypeId(Integer apiTypeId){
        Map<String, Object> map = new HashedMap();
        map.put("apiTypeId", apiTypeId);
        List<ApiVendor> apiVendorList  = service.queryApiVendorName(map);
        JSONArray jsonArray = JSONArray.fromObject(apiVendorList);
        return jsonArray.toString();
    }

}
