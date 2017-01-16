package org.qydata.controller;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.qydata.dst.CustomerApiType;
import org.qydata.dst.CustomerApiVendor;
import org.qydata.dst.CustomerFinance;
import org.qydata.entity.CustomerBalanceLog;
import org.qydata.entity.Dept;
import org.qydata.entity.User;
import org.qydata.entity.WeekMonthAmount;
import org.qydata.service.CustomerBalanceLogService;
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
    private CustomerBalanceLogService customerBalanceLogService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerFinanceService customerFinanceService;

    /**
     * 查找公司财务账单
     * @param request
     * @param model
     * @param content
     * @return
     */
    @RequestMapping(value = "/find-all-customer")
    public String findAllCustomer(HttpServletRequest request, Model model, String content){

        try {
            Map<String,Object> map = new HashMap<String,Object>();
            List customerTypeIdList = new ArrayList();
            if(content!=null){
                map.put("content",content);
            }
            customerTypeIdList.add(1);
            map.put("customerTypeIdList",customerTypeIdList);

            List<CustomerFinance> customerFinanceList = customerFinanceService.queryCompanyCustomerOverAllFinance(map);
            model.addAttribute("customerFinanceList",customerFinanceList);
            model.addAttribute("content",content);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public String findAllCustomerByDeptId(HttpServletRequest request,Model model,String content){
        User user = (User)request.getSession().getAttribute("userInfo");
        List<Dept> deptList = user.getDept();
        List deptIdList = new ArrayList();
        System.out.println(deptList.size());
        if (deptList.size() > 0) {
            for (int i = 0; i < deptList.size(); i++) {
                deptIdList.add(deptList.get(i).getId());
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deptIdList", deptIdList);
            if (content != null) {
                map.put("content", content);
            }
            List customerTypeIdList = new ArrayList();
            customerTypeIdList.add(1);
            map.put("customerTypeIdList", customerTypeIdList);
            List<CustomerFinance> customerFinanceList = null;
            try {
                customerFinanceList = customerFinanceService.queryCompanyCustomerOverAllFinance(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("customerFinanceList",customerFinanceList);
            model.addAttribute("deptIdList", deptIdList);
            model.addAttribute("content",content);
            return "/finance/customerFinancialAccount";
        }else {
            model.addAttribute("deptIdList", deptIdList);
            model.addAttribute("customerFinanceList",null);
            return "/finance/customerFinancialAccount";
        }
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
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("sell")){
            User user = (User)request.getSession().getAttribute("userInfo");
            List<Dept> deptList = user.getDept();
            List deptIdList = new ArrayList();
            for(int i =0;i<deptList.size();i++){
                deptIdList.add(deptList.get(i).getId());
            }
            List customerIdList = null;
            try {
                customerIdList = customerService.findAllCustomerIdByDeptId(deptIdList);
                if (!customerIdList.contains(customerId)){
                    return "../view/unauthUrl";
                }
            } catch (Exception e) {
                log.error("findAllRechargeCustomerBalanceLogByCustomerId:授权失败");
                e.printStackTrace();
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
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
        List<CustomerBalanceLog> customerBalanceLogList = null;
        long totleAmount = 0;
        try {
            customerBalanceLogList = customerFinanceService.queryCompanyCustomerRechargeRecordByCustomerId(map);
            if(customerBalanceLogList != null && customerBalanceLogList.size()>0) {
                Iterator<CustomerBalanceLog> iterator = customerBalanceLogList.iterator();
                while (iterator.hasNext()) {
                    CustomerBalanceLog customerBalanceLog = iterator.next();
                    totleAmount = totleAmount + customerBalanceLog.getAmount();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        List<CustomerApiType> customerApiTypes = null;
        try {
            customerApiTypes = customerFinanceService.queryCompanyCustomerApiConsumeRecordByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<CustomerApiType> customerApiTypes1 = null;
        List<CustomerApiVendor> customerApiVendors = null;
        if(apiTypeId != null){
            map.put("apiTypeId",apiTypeId);
            try {
                customerApiTypes1 = customerFinanceService.queryCompanyCustomerApiConsumeRecordByCustomerId(map);
                if (customerApiTypes1 != null) {
                    for (int i = 0; i < customerApiTypes1.size(); i++) {
                        CustomerApiType customerApiType = customerApiTypes1.get(i);
                        customerApiVendors = customerApiType.getCustomerApiVendors();
                        if (customerApiVendors != null){
                            for (int j=0; j<customerApiVendors.size(); j++){
                                CustomerApiVendor customerApiVendor = customerApiVendors.get(j);
                                if (customerApiVendor.getVendorName() == null){
                                    customerApiVendors.remove(j);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(apiVendorId != null){
            map.put("apiVendorId",apiVendorId);
        }
        List<CustomerApiType> customerApiTypeList = null;
        long totleAmount =0;
        try {
            customerApiTypeList = customerFinanceService.queryCompanyCustomerApiConsumeRecordByCustomerId(map);
            if (customerApiTypeList != null) {
                for (int i = 0; i < customerApiTypeList.size(); i++) {
                    CustomerApiType customerApiType = customerApiTypeList.get(i);
                    totleAmount = totleAmount + customerApiType.getTotlePrice();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("customerApiTypeList", customerApiTypeList);
        model.addAttribute("customerApiTypes",customerApiTypes);
        model.addAttribute("customerApiVendors",customerApiVendors);
        model.addAttribute("customerId",customerId);
        model.addAttribute("apiTypeId",apiTypeId);
        model.addAttribute("apiVendorId",apiVendorId);
        model.addAttribute("companyName",companyName);
        model.addAttribute("totleAmount",totleAmount);
        return "/finance/customerApiConsumeRecord";
    }

    /**
     * 二级级联
     * @param request
     * @return
     */
    @RequestMapping("/find-api-vendor-by-api-type-id")
    @ResponseBody
    public String findApiVendorByApiTypeId(Integer apiTypeId,Integer customerId,HttpServletRequest request){
        Map<String, Object> map = new HashedMap();
        map.put("customerId", customerId);
        map.put("apiTypeId", apiTypeId);
        List<CustomerApiType> customerApiTypeList = null;
        CustomerApiType customerApiType = null;
        List<CustomerApiVendor> customerApiVendorList = null;
        try {
            customerApiTypeList = customerFinanceService.queryCompanyCustomerApiConsumeRecordByCustomerId(map);
            if(customerApiTypeList != null) {
                for (int i = 0; i < customerApiTypeList.size(); i++) {
                    customerApiType = customerApiTypeList.get(i);
                    customerApiVendorList = customerApiType.getCustomerApiVendors();
                    if (customerApiVendorList != null){
                        for (int j=0; j<customerApiVendorList.size(); j++){
                            CustomerApiVendor customerApiVendor = customerApiVendorList.get(j);
                            if (customerApiVendor.getVendorName() == null){
                                customerApiVendorList.remove(j);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = JSONArray.fromObject(customerApiVendorList);
        return jsonArray.toString();
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
    public String findAllApiConsumeDetailRecordByCustomerId(Integer customerId, Integer apiTypeId, String companyName, String apiTypeName,Integer [] reasonId, Model model){
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("apiTypeId",apiTypeId);
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
        List<CustomerApiVendor> customerApiVendorList = null;
        long totleAmount = 0;
        try {
            customerApiVendorList = customerFinanceService.queryCompanyCustomerApiDetailConsumeRecordByCustomerId(map);
            if(customerApiVendorList != null) {
                for (int i = 0; i < customerApiVendorList.size(); i++) {
                    CustomerApiVendor customerApiVendor = customerApiVendorList.get(i);
                    totleAmount = totleAmount + customerApiVendor.getTotlePrice();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("customerApiVendorList",customerApiVendorList);
        model.addAttribute("companyName",companyName);
        model.addAttribute("apiTypeName",apiTypeName);
        model.addAttribute("apiTypeId",apiTypeId);
        model.addAttribute("customerId",customerId);
        model.addAttribute("totleAmount",totleAmount);
        model.addAttribute("reasonIdArray",reasonId);
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
        List<Integer> monthList = null;
        if(years != null){
            map.put("years",years);
            try {
                monthList = customerFinanceService.queryCompanyCustomerMonthsByCustomerId(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<Integer> weekList = null;
        if(months != null){
            map.put("months",months);
            try {
                weekList = customerFinanceService.queryCompanyCustomerWeeksByCustomerId(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(weeks != null){
            map.put("weeks",weeks);
        }
        List<WeekMonthAmount> weekMonthAmountList = null;
        List<Integer> yearList = null;
        long totleAmount = 0;
        try {
            yearList = customerFinanceService.queryCompanyCustomerYearsByCustomerId(map);
            weekMonthAmountList = customerFinanceService.queryCompanyCustomerWeekMonthRecordByCustomerId(map);
            if(weekMonthAmountList != null){
                for (int i=0; i<weekMonthAmountList.size(); i++){
                    WeekMonthAmount weekMonthAmount = weekMonthAmountList.get(i);
                    totleAmount = totleAmount + weekMonthAmount.getTotleAmount();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        List<Integer> monthList = null;
        if(years != null){
            map.put("years",years);
            try {
                monthList = customerFinanceService.queryCompanyCustomerMonthsByCustomerId(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(months != null){
            map.put("months",months);

        }
        List<WeekMonthAmount> weekMonthAmountList = null;
        List<Integer> yearList = null;
        long totleAmount = 0;
        try {
            yearList = customerFinanceService.queryCompanyCustomerYearsByCustomerId(map);
            weekMonthAmountList = customerFinanceService.queryCompanyCustomerWeekMonthRecordByCustomerId(map);
            if(weekMonthAmountList != null){
                for (int i=0; i<weekMonthAmountList.size(); i++){
                    WeekMonthAmount weekMonthAmount = weekMonthAmountList.get(i);
                    totleAmount = totleAmount + weekMonthAmount.getTotleAmount();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        List<Integer> monthList = null;
        try {
            monthList = customerFinanceService.queryCompanyCustomerMonthsByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = JSONArray.fromObject(monthList);
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
        List<Integer> weekList = null;
        try {
            weekList = customerFinanceService.queryCompanyCustomerWeeksByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = JSONArray.fromObject(weekList);
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
        List<Integer> monthList = null;
        try {
            monthList = customerFinanceService.queryCompanyCustomerMonthsByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = JSONArray.fromObject(monthList);
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
        System.out.println(customerId);
        System.out.println(typeId);
        System.out.println(years);
        System.out.println(months);
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("tableId",typeId);
        if(years != null && months == null){
            if (years < CalendarTools.getYearMonthCount(0)){
                Integer result =  (CalendarTools.compareDate(years+"-"+12+"-"+31, null, 1)-1);
                map.put("result",result);
                System.out.println(result);
            }else {
                map.put("result",0);
            }

        }
        if(years != null && months != null){
            Integer result =  (CalendarTools.compareDate(years+"-"+months+"-"+31, null, 1)-1);
            map.put("result",result);
            System.out.println(result);
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








    //Api消费账单
    @RequestMapping("/find-all-api-record")
    public String findAllApiRecord(){
        return "/finance/apiRecord";
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
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        map.put("张建宏",12);
        map.put("韩立志",23);
        map.put("王永鹏",15);
        map.put("王鸿年",10);
        map.put("张悦",20);
        map.put("张诗雨",20);
        return gson.toJson(map);
    }

    //合作伙伴来往账目
    @RequestMapping("/find-all-partners-financial-account")
    public String findPartnersFinancialAccount(){
        return "/finance/partnersFinancialAccount";
    }

    //合作伙伴来往账目-收支明细
    @RequestMapping("/find-all-partners-financial-account/receipt-and-paying-record")
    public String findPartnersReceiptAndPayingRecord(){
        return "/finance/partnersReceiptAndPayingRecord";
    }
}
