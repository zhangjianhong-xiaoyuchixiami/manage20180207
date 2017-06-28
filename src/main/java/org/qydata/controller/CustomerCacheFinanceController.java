package org.qydata.controller;

import com.google.gson.Gson;
import org.qydata.dst.CustomerCacheApiTypeConsume;
import org.qydata.dst.CustomerCacheConsume;
import org.qydata.service.CustomerCacheFinanceService;
import org.qydata.service.CustomerFinanceService;
import org.qydata.tools.CalendarTools;
import org.qydata.tools.date.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/6/26.
 */
@Controller
@RequestMapping("/cache")
public class CustomerCacheFinanceController {

    @Autowired
    private CustomerCacheFinanceService customerCacheFinanceService;

    @Autowired
    CustomerFinanceService customerFinanceService;

    /**
     * 查询客户缓存调用情况
     * @param model
     * @return
     */
    @RequestMapping("/find-all-customer")
    public String queryCustomerCacheConsume(String companyName,String beginDate,String endDate,String partnerId,Model model) throws ParseException {
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,Object> mapParam = new HashMap<>();
        if(companyName != null){
            mapParam.put("companyName",companyName);
            model.addAttribute("companyName",companyName);
        }
        if(partnerId != null && beginDate != ""){
            mapParam.put("partnerId",partnerId);
        }
        if(beginDate != null && beginDate != ""){
            mapParam.put("beginDate", sdf.format(sdfInput.parse(beginDate))+" "+"00:00:00");
            model.addAttribute("beginDate",beginDate);
        }
        if(endDate != null && endDate != ""){
            mapParam.put("endDate", CalendarUtil.getAfterDayByInputTime(endDate));
            model.addAttribute("endDate",endDate);
        }
        List<CustomerCacheConsume> customerCacheConsumeList = customerCacheFinanceService.queryCustomerCacheConsume(mapParam);
        model.addAttribute("currYear", CalendarTools.getYearMonthCount(0));
        model.addAttribute("currMonth",CalendarTools.getMonthCount(0));
        model.addAttribute("currDay",CalendarTools.getYearMonthDayCount(0));
        model.addAttribute("customerCacheConsumeList",customerCacheConsumeList);
        return "/finance/customerCacheFinancialAccount";
    }

    /**
     * 加载客户当天各产品类型的缓存数据
     * @param customerId
     * @return
     * @throws InterruptedException
     */
    @RequestMapping(value = "/find-all-customer/curr-day-api-type-consume")
    @ResponseBody
    public String queryCustomerCurrDayApiTypeConsume(Integer customerId) throws InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Map<String,Object> map = new HashMap<>();
        map.put("customerId",customerId);
        map.put("time",sdf.format(new Date()));
        List<CustomerCacheApiTypeConsume> customerCacheApiTypeConsumeList = customerCacheFinanceService.queryCustomerCurrDayApiTypeCacheConsume(map);
        Map<String,Object> mapJson = new HashMap<>();
        mapJson.put("customerCacheApiTypeConsumeList",customerCacheApiTypeConsumeList);
        return new Gson().toJson(mapJson);
    }

    /**
     * 客户当天各产品类型的缓存数据弹框的标题显示
     * @param customerId
     * @return
     * @throws InterruptedException
     */
    @RequestMapping(value = "/find-all-customer/company-name")
    @ResponseBody
    public String queryCustomerCompanyName(Integer customerId) throws InterruptedException {
        String companyName = customerFinanceService.queryCustomerCompanyNameById(customerId);
        Map<String,Object> mapJson = new HashMap<>();
        mapJson.put("companyName",companyName);
        return new Gson().toJson(mapJson);
    }


}
