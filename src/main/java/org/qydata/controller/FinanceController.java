package org.qydata.controller;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.qydata.dst.CustomerApiType;
import org.qydata.dst.CustomerApiVendor;
import org.qydata.entity.ApiVendor;
import org.qydata.entity.CustomerBalanceLog;
import org.qydata.entity.User;
import org.qydata.entity.WeekMonthAmount;
import org.qydata.service.ApiService;
import org.qydata.service.CustomerFinanceService;
import org.qydata.service.CustomerService;
import org.qydata.tools.CalendarTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * Created by jonhn on 2017/1/3.
 */
@Controller
@RequestMapping("/finance")
public class FinanceController {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerFinanceService customerFinanceService;
    @Autowired
    private ApiService apiService;
    /**
     * 查找公司财务账单
     * @param model
     * @param content
     * @return
     */
    @RequestMapping(value = "/find-all-customer")
    public String findAllCustomer(Model model, String content,Integer partnerId,String beginDate,String endDate){
        Map<String,Object> map = new HashMap<>();
        if(content != null){
            map.put("content",content);
        }
        if(partnerId != null){
            map.put("partnerId",partnerId);
        }
        if(beginDate != null && beginDate != ""){
            map.put("beginDate", beginDate+" "+"00:00:00");
        }
        if(endDate != null && endDate != ""){
            map.put("endDate", endDate+" "+"23:59:59");
        }
        model.addAttribute("customerFinanceList",customerFinanceService.queryCompanyCustomerOverAllFinance(map));
        model.addAttribute("content",content);
        model.addAttribute("partnerId",partnerId);
        model.addAttribute("beginDate",beginDate);
        model.addAttribute("endDate",endDate);
        model.addAttribute("year",CalendarTools.getYearMonthCount(1));
        model.addAttribute("month",CalendarTools.getMonthCount(1));
        model.addAttribute("week",CalendarTools.getYearWeekCount(1));
        return "/finance/customerFinancialAccount";
    }

    /**
     * 通过部门编号查找公司财务账单
     * @param request
     * @param model
     * @param content
     * @return
     */
    @RequestMapping(value = ("/find-all-customer-by-dept-id"))
    public String findAllCustomerByDeptId(HttpServletRequest request,Model model,String content,Integer partnerId,String beginDate,String endDate){
        User user = (User) request.getSession().getAttribute("userInfo");
        List deptList = new ArrayList();
        Map<String, Object> map = new HashMap<>();
        if (user.getDept().size() > 0) {
            for (int i = 0; i < user.getDept().size(); i++) {
                deptList.add(user.getDept().get(i).getId());
            }
            map.put("deptIdList", deptList);
            if (content != null) {
                map.put("content", content);
            }
            if (partnerId != null) {
                map.put("partnerId", partnerId);
            }
            if(beginDate != null && beginDate != ""){
                map.put("beginDate", beginDate+" "+"00:00:00");
            }
            if(endDate != null && endDate != ""){
                map.put("endDate", endDate+" "+"23:59:59");
            }
            model.addAttribute("customerFinanceList", customerFinanceService.queryCompanyCustomerOverAllFinanceByDept(map));
            model.addAttribute("content", content);
            model.addAttribute("partnerId", partnerId);
            model.addAttribute("beginDate",beginDate);
            model.addAttribute("endDate",endDate);
            model.addAttribute("year",CalendarTools.getYearMonthCount(1));
            model.addAttribute("month",CalendarTools.getMonthCount(1));
            model.addAttribute("week",CalendarTools.getYearWeekCount(1));
            return "/finance/customerFinancialAccount";
        }
        return "/finance/customerFinancialAccount";
    }
    /**
     * 指定账号充值记录
     * @param customerId
     * @param companyName
     * @param beginDate
     * @param endDate
     * @param reasonId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/find-all-customer/find-all-customer-recharge-log-by-customer-id")
    public String findAllCustomerRechargeLogByCustomerId(Integer customerId,String companyName, String beginDate, String endDate, String [] reasonId, HttpServletRequest request, Model model){
        Map<String, Object> map = new HashMap<>();
        map.put("customerId", customerId);
        List<Integer> reasonIdList = new ArrayList<>();
        if (reasonId != null && reasonId.length >0) {
            for(int i=0;i<reasonId.length;i++){
                reasonIdList.add(parseInt(reasonId[i]));
            }
        }else {
            reasonIdList.add(1);
            reasonIdList.add(2);
            reasonIdList.add(3);
        }
        map.put("reasonIdList", reasonIdList);
        if (beginDate != null && beginDate != "" ) {
            map.put("beginDate", beginDate+" "+"00:00:00");
        }
        if(endDate != null && endDate != ""){
            map.put("endDate", endDate+" "+"23:59:59");
        }
        List<CustomerBalanceLog> customerBalanceLogList = customerFinanceService.queryCompanyCustomerRechargeRecordByCustomerId(map);
        long totleAmount = 0;
        if(customerBalanceLogList != null && customerBalanceLogList.size()>0) {
            Iterator<CustomerBalanceLog> iterator = customerBalanceLogList.iterator();
            while (iterator.hasNext()) {
                CustomerBalanceLog customerBalanceLog = iterator.next();
                totleAmount = totleAmount + customerBalanceLog.getAmount();
            }
        }
        model.addAttribute("customerBalanceLogList",customerBalanceLogList);
        model.addAttribute("customerId",customerId);
        model.addAttribute("reasonIdArray",reasonId);
        model.addAttribute("beginDate",beginDate);
        model.addAttribute("endDate",endDate);
        model.addAttribute("companyName",companyName);
        model.addAttribute("totleAmount",totleAmount);
        return "/finance/customerBalanceLogRecord";
    }

    /**
     * 指定账号Api消费记录
     * @return
     */
    @RequestMapping("/find-all-customer/find-all-customer-api-consume-record-by-customer-id")
    public String findAllApiConsumeRecordByCustomerId(Integer customerId,Integer apiTypeId,Integer apiVendorId,String companyName,Model model){
        try {
            Map<String,Object> map = new HashMap();
            map.put("customerId",customerId);
            List<ApiVendor> vendorList = null;
            if(apiTypeId != null){
                map.put("apiTypeId",apiTypeId);
                vendorList = customerFinanceService.queryApiVendorByCustomerId(map);
            }
            if(apiVendorId != null){
                map.put("apiVendorId",apiVendorId);
            }
            List<CustomerApiType> customerApiTypeList = customerFinanceService.queryCompanyCustomerApiConsumeRecordByCustomerId(map);
            long totleAmount =0;
            if (customerApiTypeList != null) {
                for (int i = 0; i < customerApiTypeList.size(); i++) {
                    CustomerApiType customerApiType = customerApiTypeList.get(i);
                    totleAmount = totleAmount + customerApiType.getTotlePrice();
                }
            }
            model.addAttribute("customerApiTypeList", customerApiTypeList);
            model.addAttribute("customerApiTypes",customerFinanceService.queryApiTypeByCustomerId(map));
            model.addAttribute("customerApiVendors",vendorList);
            model.addAttribute("customerId",customerId);
            model.addAttribute("apiTypeId",apiTypeId);
            model.addAttribute("apiVendorId",apiVendorId);
            model.addAttribute("companyName",companyName);
            model.addAttribute("totleAmount",totleAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/finance/customerApiConsumeRecord";
    }

    //客户个供应商消费饼状图
    @RequestMapping("/find-all-vendor-record/count-result")
    @ResponseBody
    public String findVendorRecordCountResult(Integer customerId,Integer apiTypeId){
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("apiTypeId",apiTypeId);
        Map<String,Object> map1 = new HashMap<>();
        long consumeTotleAmount = 0L;
        List<CustomerApiVendor> customerApiVendorList = customerFinanceService.queryCustomerConsumeByVendor(map);
        if (customerApiVendorList != null){
            for (int i=0; i<customerApiVendorList.size(); i++){
                CustomerApiVendor customerApiVendor = customerApiVendorList.get(i);
                if (customerApiVendor.getTotlePrice() != null){
                    consumeTotleAmount = consumeTotleAmount + customerApiVendor.getTotlePrice();
                }
            }
            for (int i=0; i<customerApiVendorList.size(); i++){
                CustomerApiVendor customerApiVendor = customerApiVendorList.get(i);
                if(customerApiVendor.getVendorName() != null){
                    map1.put(customerApiVendor.getVendorName(),((customerApiVendor.getTotlePrice()/100.0)/(consumeTotleAmount)/100.0));
                }else {
                    map1.put("未知供应商",((customerApiVendor.getTotlePrice()/100.0)/(consumeTotleAmount)/100.0));
                }
            }
        }
        Gson gson = new Gson();
        return gson.toJson(map1);
    }

    /**
     * 指定账号Api消费明细记录
     * @param customerId
     * @param apiTypeId
     * @param companyName
     * @param apiTypeName
     * @param model
     * @return
     */
    @RequestMapping("/find-all-customer/find-all-customer-api-consume-record-by-customer-id/detail")
    public String findAllApiConsumeDetailRecordByCustomerId(Integer customerId, Integer apiTypeId, String companyName, String apiTypeName,Integer [] reasonId,String beginDate,String endDate, Model model){
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("apiTypeId",apiTypeId);
        if (beginDate != null && beginDate != "" ) {
            map.put("beginDate", beginDate+" "+"00:00:00");
        }
        if(endDate != null && endDate != ""){
            map.put("endDate", endDate+" "+"23:59:59");
        }
        List<Integer> reasonIdList = new ArrayList<>();
        if (reasonId != null && reasonId.length >0) {
            for(int i=0;i<reasonId.length;i++){
                reasonIdList.add(reasonId[i]);
            }
        }else {
            reasonIdList.add(-1);
            reasonIdList.add(-2);
        }
        map.put("reasonIdList", reasonIdList);
        List<CustomerBalanceLog> customerBalanceLogList = customerFinanceService.queryCompanyCustomerApiDetailConsumeRecordByCustomerId(map);
        long totleAmount = 0;
        if(customerBalanceLogList != null) {
            for (int i = 0; i < customerBalanceLogList.size(); i++) {
                CustomerBalanceLog customerBalanceLog = customerBalanceLogList.get(i);
                totleAmount = totleAmount + customerBalanceLog.getAmount();
            }
        }
        model.addAttribute("customerBalanceLogList",customerBalanceLogList);
        model.addAttribute("companyName",companyName);
        model.addAttribute("apiTypeName",apiTypeName);
        model.addAttribute("apiTypeId",apiTypeId);
        model.addAttribute("customerId",customerId);
        model.addAttribute("totleAmount",totleAmount);
        model.addAttribute("reasonIdArray",reasonId);
        model.addAttribute("beginDate",beginDate);
        model.addAttribute("endDate",endDate);
        return "/finance/customerApiConsumeDetailRecord";
    }

    /**
     * 周历史记录
     * @param customerId
     * @param years
     * @param months
     * @param weeks
     * @param typeId
     * @param companyName
     * @param model
     * @return
     */
    @RequestMapping("/find-all-customer/find-week-record-by-customer-id")
    public String findWeekRecordByCustomerId(Integer customerId,Integer years,Integer months,Integer weeks,Integer typeId,String companyName,Model model){
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("weekMonthTypeId",1);
        List<Integer> tableIdList = new ArrayList();
        tableIdList.add(typeId);
        map.put("tableIdList",tableIdList);
        List<Integer> yearList = customerFinanceService.queryCompanyCustomerYearsByCustomerId(map);
        List<Integer> monthList = null;
        if(years != null){
            map.put("years",years);
            monthList = customerFinanceService.queryCompanyCustomerMonthsByCustomerId(map);
        }
        List<Integer> weekList = null;
        if(months != null){
            map.put("months",months);
            weekList = customerFinanceService.queryCompanyCustomerWeeksByCustomerId(map);
        }
        if(weeks != null){
            map.put("weeks",weeks);
        }
        List<WeekMonthAmount> weekMonthAmountList = customerFinanceService.queryCompanyCustomerWeekMonthRecordByCustomerId(map);
        long totleAmount = 0;
        if(weekMonthAmountList != null){
            for (int i=0; i<weekMonthAmountList.size(); i++){
                WeekMonthAmount weekMonthAmount = weekMonthAmountList.get(i);
                totleAmount = totleAmount + weekMonthAmount.getTotleAmount();
            }
        }
        model.addAttribute("weekMonthAmountList",weekMonthAmountList);
        model.addAttribute("yearList",yearList);
        model.addAttribute("monthList",monthList);
        model.addAttribute("weekList",weekList);
        model.addAttribute("totleAmount",totleAmount);
        model.addAttribute("companyName",companyName);
        model.addAttribute("customerId",customerId);
        model.addAttribute("typeId",typeId);
        model.addAttribute("years",years);
        model.addAttribute("months",months);
        model.addAttribute("weeks",weeks);
        return "/finance/weekRecord";
    }

    /**
     * 月历史记录
     * @param customerId
     * @param years
     * @param months
     * @param typeId
     * @param companyName
     * @param model
     * @return
     */
    @RequestMapping("/find-all-customer/find-month-record-by-customer-id")
    public String findMonthRecordByCustomerId(Integer customerId,Integer years,Integer months, Integer typeId,String companyName,Model model){
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("weekMonthTypeId",2);
        List<Integer> tableIdList = new ArrayList();
        tableIdList.add(typeId);
        map.put("tableIdList",tableIdList);
        List<Integer> yearList = null;
        yearList = customerFinanceService.queryCompanyCustomerYearsByCustomerId(map);
        List<Integer> monthList = null;
        if(years != null){
            map.put("years",years);
            monthList = customerFinanceService.queryCompanyCustomerMonthsByCustomerId(map);
        }
        if(months != null){
            map.put("months",months);
        }
        List<WeekMonthAmount> weekMonthAmountList = customerFinanceService.queryCompanyCustomerWeekMonthRecordByCustomerId(map);
        long totleAmount = 0;
        if(weekMonthAmountList != null){
            for (int i=0; i<weekMonthAmountList.size(); i++){
                WeekMonthAmount weekMonthAmount = weekMonthAmountList.get(i);
                totleAmount = totleAmount + weekMonthAmount.getTotleAmount();
            }
        }
        model.addAttribute("weekMonthAmountList",weekMonthAmountList);
        model.addAttribute("yearList",yearList);
        model.addAttribute("monthList",monthList);
        model.addAttribute("totleAmount",totleAmount);
        model.addAttribute("companyName",companyName);
        model.addAttribute("customerId",customerId);
        model.addAttribute("typeId",typeId);
        model.addAttribute("years",years);
        model.addAttribute("months",months);
        return "/finance/monthRecord";
    }

    /**
     * 周记录数月级联菜单
     * @param request
     * @return
     */
    @RequestMapping("/find-company-customer-week-uplink-months-by-customer-id")
    @ResponseBody
    public String findCompanyCustomerWeekUplinkMonthsByCustomerId(Integer customerId,Integer years,Integer typeId,HttpServletRequest request){
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("weekMonthTypeId",1);
        map.put("years",years);
        List<Integer> tableIdList = new ArrayList();
        tableIdList.add(typeId);
        map.put("tableIdList",tableIdList);
        JSONArray jsonArray = JSONArray.fromObject(customerFinanceService.queryCompanyCustomerMonthsByCustomerId(map));
        return jsonArray.toString();
    }

    /**
     * 周记录数周级联菜单
     * @param request
     * @return
     */
    @RequestMapping("/find-company-customer-weeks-by-customer-id")
    @ResponseBody
    public String findCompanyCustomerWeeksByCustomerId(Integer customerId,Integer years,Integer months,Integer typeId,HttpServletRequest request){
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("weekMonthTypeId",1);
        map.put("years",years);
        map.put("months",months);
        List<Integer> tableIdList = new ArrayList();
        tableIdList.add(typeId);
        map.put("tableIdList",tableIdList);
        JSONArray jsonArray = JSONArray.fromObject(customerFinanceService.queryCompanyCustomerWeeksByCustomerId(map));
        return jsonArray.toString();
    }


    /**
     * 月记录数月级联菜单
     * @param request
     * @return
     */
    @RequestMapping("/find-company-customer-month-uplink-months-by-customer-id")
    @ResponseBody
    public String findCompanyCustomerMonthUplinkMonthsByCustomerId(Integer customerId,Integer years,Integer typeId,HttpServletRequest request){
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("weekMonthTypeId",2);
        map.put("years",years);
        List<Integer> tableIdList = new ArrayList();
        tableIdList.add(typeId);
        map.put("tableIdList",tableIdList);
        JSONArray jsonArray = JSONArray.fromObject(customerFinanceService.queryCompanyCustomerMonthsByCustomerId(map));
        return jsonArray.toString();
    }

    /**
     * 客户月账单充值消费走势图
     * @param customerId
     * @param typeId
     * @return
     */
    @RequestMapping("/months-charge-consume-toward")
    @ResponseBody
    public String monthsChargeConsumeToward(Integer customerId,Integer typeId,Integer years,Integer months){
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("tableId",typeId);
        if(years != null && months == null){
            if (years < CalendarTools.getYearMonthCount(0)){
                Integer result =  (CalendarTools.compareDate(years+"-"+12+"-"+31, null, 1)-1);
                map.put("result",result);
            }else {
                map.put("result",0);
            }
        }
        if(years != null && months != null){
            Integer result =  (CalendarTools.compareDate(years+"-"+months+"-"+31, null, 1)-1);
            map.put("result",result);
        }
        if (years == null && months == null){
            map.put("result",0);
        }
        Map<String,List> stringListMap = customerFinanceService.monthChargeConsumeToward(map);
        Set<Map.Entry<String,List>> set = stringListMap.entrySet();
        Iterator<Map.Entry<String,List>> it = set.iterator();
        List xList= null;
        List yList = null;
        while(it.hasNext()){
            Map.Entry<String,List> me = it.next();
            if(me.getKey().equals("xPort")){
                xList = (List) me.getValue();
            }if(me.getKey().equals("type") ){
                yList = (List) me.getValue();
            }
        }
        Map mapOne = new HashedMap();
        if (typeId ==1){
            mapOne.put("name","充值");
        }
        if (typeId ==2){
            mapOne.put("name","消费");
        }
        mapOne.put("data",yList);
        JSONArray jsonArray = JSONArray.fromObject(xList);
        JSONArray jsonArray1 = JSONArray.fromObject(mapOne);
        JSONObject getObj = new JSONObject();
        getObj.put("xList", jsonArray);
        getObj.put("yList", jsonArray1);
        return getObj.toString();
    }

}
