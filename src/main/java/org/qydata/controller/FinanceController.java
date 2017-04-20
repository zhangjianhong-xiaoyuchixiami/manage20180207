package org.qydata.controller;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.qydata.dst.CustomerApiVendor;
import org.qydata.entity.ApiVendor;
import org.qydata.entity.User;
import org.qydata.service.CustomerFinanceService;
import org.qydata.service.PowerUserService;
import org.qydata.service.RoleService;
import org.qydata.tools.CalendarTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * Created by jonhn on 2017/1/3.
 */
@Controller
@RequestMapping("/finance")
public class FinanceController {

    @Autowired private CustomerFinanceService customerFinanceService;
    @Autowired private RoleService roleService;
    @Autowired private PowerUserService powerUserService;
    /**
     * 查找公司财务账单
     * @param model
     * @param content
     * @return
     */
    @RequestMapping(value = "/find-all-customer")
    public ModelAndView queryAllCustomer(String export, String content, Integer partnerId, String beginDate, String endDate,String [] status, Model model)throws Exception{
        Map<String,Object> map = new HashMap<>();
        if(content != null){
            map.put("content",content);
        }
        if(partnerId != null){
            map.put("partnerId",partnerId);
        }
        if(beginDate != null && beginDate != ""){
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
        Map<String,Object> mapResult = customerFinanceService.queryCompanyCustomerOverAllFinance(map);
        Set<Map.Entry<String,Object>> set = mapResult.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while (it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if (me.getKey().equals("weekChargeAmount")){
                model.addAttribute("weekChargeAmount",me.getValue());
            }
            if (me.getKey().equals("weekConsumeAmount")){
                model.addAttribute("weekConsumeAmount",me.getValue());
            }
            if (me.getKey().equals("monthChargeAmount")){
                model.addAttribute("monthChargeAmount",me.getValue());
            }
            if (me.getKey().equals("monthConsumeAmount")){
                model.addAttribute("monthConsumeAmount",me.getValue());
            }
            if (me.getKey().equals("totleChargeAmount")){
                model.addAttribute("totleChargeAmount",me.getValue());
            }
            if (me.getKey().equals("totleConsumeAmount")){
                model.addAttribute("totleConsumeAmount",me.getValue());
            }
            if (me.getKey().equals("currMonthTotleConsumeAmount")){
                model.addAttribute("currMonthTotleConsumeAmount",me.getValue());
            }
            if (me.getKey().equals("currDayTotleConsumeAmount")){
                model.addAttribute("currDayTotleConsumeAmount",me.getValue());
            }
            if (me.getKey().equals("customerFinanceList")){
                model.addAttribute("customerFinanceList",me.getValue());
            }
        }
        model.addAttribute("content",content);
        model.addAttribute("partnerId",partnerId);
        model.addAttribute("statusArray",statusList);
        model.addAttribute("currYear",CalendarTools.getYearMonthCount(0));
        model.addAttribute("currMonth",CalendarTools.getMonthCount(0));
        model.addAttribute("currDay",CalendarTools.getYearMonthDayCount(0));
        model.addAttribute("year",CalendarTools.getYearMonthCount(1));
        model.addAttribute("month",CalendarTools.getMonthCount(1));
        model.addAttribute("week",CalendarTools.getYearWeekCount(1));
        return new ModelAndView("/finance/customerFinancialAccount");
    }

    /**
     * 通过部门编号查找公司财务账单
     * @param model
     * @param content
     * @return
     */
    @RequestMapping(value = ("/find-all-customer-by-dept-id"))
    public ModelAndView queryAllCustomerByDeptId(String export,String content,Integer partnerId,String beginDate,String endDate,String [] status, Model model)throws Exception{
        User user = powerUserService.findUserByEmail((String) SecurityUtils.getSubject().getPrincipal());
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
            Map<String,Object> mapResult = customerFinanceService.queryCompanyCustomerOverAllFinance(map);
            Set<Map.Entry<String,Object>> set = mapResult.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while (it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if (me.getKey().equals("weekChargeAmount")){
                    model.addAttribute("weekChargeAmount",me.getValue());
                }
                if (me.getKey().equals("weekConsumeAmount")){
                    model.addAttribute("weekConsumeAmount",me.getValue());
                }
                if (me.getKey().equals("monthChargeAmount")){
                    model.addAttribute("monthChargeAmount",me.getValue());
                }
                if (me.getKey().equals("monthConsumeAmount")){
                    model.addAttribute("monthConsumeAmount",me.getValue());
                }
                if (me.getKey().equals("totleChargeAmount")){
                    model.addAttribute("totleChargeAmount",me.getValue());
                }
                if (me.getKey().equals("totleConsumeAmount")){
                    model.addAttribute("totleConsumeAmount",me.getValue());
                }
                if (me.getKey().equals("currMonthTotleConsumeAmount")){
                    model.addAttribute("currMonthTotleConsumeAmount",me.getValue());
                }
                if (me.getKey().equals("currDayTotleConsumeAmount")){
                    model.addAttribute("currDayTotleConsumeAmount",me.getValue());
                }
                if (me.getKey().equals("customerFinanceList")){
                    model.addAttribute("customerFinanceList",me.getValue());
                }
            }
            model.addAttribute("content", content);
            model.addAttribute("partnerId", partnerId);
            model.addAttribute("statusArray",statusList);
            model.addAttribute("currYear",CalendarTools.getYearMonthCount(0));
            model.addAttribute("currMonth",CalendarTools.getMonthCount(0));
            model.addAttribute("currDay",CalendarTools.getYearMonthDayCount(0));
            model.addAttribute("year",CalendarTools.getYearMonthCount(1));
            model.addAttribute("month",CalendarTools.getMonthCount(1));
            model.addAttribute("week",CalendarTools.getYearWeekCount(1));
            return new ModelAndView("/finance/customerFinancialAccount");
        }
        return new ModelAndView("/finance/customerFinancialAccount");
    }


    /**
     * 指定账号充值记录
     * @param customerId
     * @param companyName
     * @param beginDate
     * @param endDate
     * @param reasonId
     * @param model
     * @return
     */
    @RequestMapping(value = "/find-all-customer/find-all-customer-recharge-log-by-customer-id")
    public ModelAndView findAllCustomerRechargeLogByCustomerId(Integer customerId,String export,String companyName, String beginDate, String endDate, String [] reasonId, Model model){
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
        Map<String,Object> mapResult = customerFinanceService.queryCompanyCustomerRechargeRecordByCustomerId(map);
        Set<Map.Entry<String,Object>> set = mapResult.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while(it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if(me.getKey().equals("getCountCompanyCustomerRechargeRecordByCustomerId")){
                model.addAttribute("totleAmount", me.getValue());
            }
            if(me.getKey().equals("queryCompanyCustomerRechargeRecordByCustomerId") ){
                model.addAttribute("customerBalanceLogList",me.getValue());
            }
        }
        model.addAttribute("customerId",customerId);
        model.addAttribute("reasonIdArray",reasonId);
        model.addAttribute("beginDate",beginDate);
        model.addAttribute("endDate",endDate);
        model.addAttribute("companyName",companyName);
        return new ModelAndView("/finance/customerBalanceLogRecord");
    }

    /**
     * 指定账号Api消费记录
     * @return
     */
    @RequestMapping("/find-all-customer/find-all-customer-api-consume-record-by-customer-id")
    public ModelAndView findAllApiConsumeRecordByCustomerId(Integer customerId,String export,Integer apiTypeId,Integer apiVendorId,String companyName,Model model){
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
        Map<String,Object> mapResult = customerFinanceService.queryCompanyCustomerApiConsumeRecordByCustomerId(map);
        Set<Map.Entry<String,Object>> set = mapResult.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while(it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if(me.getKey().equals("getCountCompanyCustomerApiConsumeRecordByCustomerId")){
                model.addAttribute("totleAmount", me.getValue());
            }
            if(me.getKey().equals("queryCompanyCustomerApiConsumeRecordByCustomerId") ){
                model.addAttribute("customerApiTypeList",me.getValue());
            }
        }
        model.addAttribute("customerApiTypes",customerFinanceService.queryApiTypeByCustomerId(map));
        model.addAttribute("customerApiVendors",vendorList);
        model.addAttribute("customerId",customerId);
        model.addAttribute("apiTypeId",apiTypeId);
        model.addAttribute("apiVendorId",apiVendorId);
        model.addAttribute("companyName",companyName);
        return new ModelAndView("/finance/customerApiConsumeRecord");
    }

    /**
     * 二级级联
     * @return
     */
    @RequestMapping("/find-api-vendor-by-api-type-id")
    @ResponseBody
    public String findApiVendorByApiTypeId(Integer apiTypeId,Integer customerId){
        Map<String, Object> map = new HashedMap();
        map.put("customerId", customerId);
        map.put("apiTypeId", apiTypeId);
        List<ApiVendor> vendorList = customerFinanceService.queryApiVendorByCustomerId(map);
        JSONArray jsonArray = JSONArray.fromObject(vendorList);
        return jsonArray.toString();
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
    public ModelAndView findAllApiConsumeDetailRecordByCustomerId(Integer customerId,String export, Integer apiTypeId, String companyName, String apiTypeName,Integer [] reasonId,String beginDate,String endDate, Model model){
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
        }else{
            reasonIdList.add(-1);
            reasonIdList.add(-2);
            reasonIdList.add(-3);
        }
        map.put("reasonIdList", reasonIdList);
        Map<String,Object> mapResult = customerFinanceService.queryCompanyCustomerApiDetailConsumeRecordByCustomerId(map);
        Set<Map.Entry<String,Object>> set = mapResult.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while(it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if(me.getKey().equals("getCountCompanyCustomerApiDetailConsumeRecordByCustomerId")){
                model.addAttribute("totleAmount", me.getValue());
            }
            if(me.getKey().equals("queryCompanyCustomerApiDetailConsumeRecordByCustomerId") ){
                model.addAttribute("customerBalanceLogList",me.getValue());
            }
        }
        model.addAttribute("companyName",companyName);
        model.addAttribute("apiTypeName",apiTypeName);
        model.addAttribute("apiTypeId",apiTypeId);
        model.addAttribute("customerId",customerId);
        model.addAttribute("reasonIdArray",reasonId);
        model.addAttribute("beginDate",beginDate);
        model.addAttribute("endDate",endDate);
        return new ModelAndView("/finance/customerApiConsumeDetailRecord");
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
    public ModelAndView findWeekRecordByCustomerId(Integer customerId,String export,Integer years,Integer months,Integer weeks,Integer typeId,String companyName,Model model){
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
        Map<String,Object> mapResult = customerFinanceService.queryCompanyCustomerWeekMonthRecordByCustomerId(map);
        Set<Map.Entry<String,Object>> set = mapResult.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while(it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if(me.getKey().equals("getCountCompanyCustomerWeekMonthRecordByCustomerId")){
                model.addAttribute("totleAmount", me.getValue());
            }
            if(me.getKey().equals("queryCompanyCustomerWeekMonthRecordByCustomerId") ){
                model.addAttribute("weekMonthAmountList",me.getValue());
            }
        }
        model.addAttribute("yearList",yearList);
        model.addAttribute("monthList",monthList);
        model.addAttribute("weekList",weekList);
        model.addAttribute("companyName",companyName);
        model.addAttribute("customerId",customerId);
        model.addAttribute("typeId",typeId);
        model.addAttribute("years",years);
        model.addAttribute("months",months);
        model.addAttribute("weeks",weeks);
        return new ModelAndView("/finance/weekRecord");
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
    public ModelAndView findMonthRecordByCustomerId(Integer customerId,String export,Integer years,Integer months, Integer typeId,String companyName,Model model){
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("weekMonthTypeId",2);
        List<Integer> tableIdList = new ArrayList();
        tableIdList.add(typeId);
        map.put("tableIdList",tableIdList);
        List<Integer> yearList = customerFinanceService.queryCompanyCustomerYearsByCustomerId(map);
        List<Integer> monthList = null;
        if(years != null){
            map.put("years",years);
            monthList = customerFinanceService.queryCompanyCustomerMonthsByCustomerId(map);
        }
        if(months != null){
            map.put("months",months);
        }
        Map<String,Object> mapResult = customerFinanceService.queryCompanyCustomerWeekMonthRecordByCustomerId(map);
        Set<Map.Entry<String,Object>> set = mapResult.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while(it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if(me.getKey().equals("queryCompanyCustomerWeekMonthRecordByCustomerId") ){
                model.addAttribute("weekMonthAmountList",me.getValue());
            }
            if(me.getKey().equals("getCountCompanyCustomerWeekMonthRecordByCustomerId")){
                model.addAttribute("totleAmount", me.getValue());
            }
        }
        model.addAttribute("yearList",yearList);
        model.addAttribute("monthList",monthList);
        model.addAttribute("companyName",companyName);
        model.addAttribute("customerId",customerId);
        model.addAttribute("typeId",typeId);
        model.addAttribute("years",years);
        model.addAttribute("months",months);
        return new ModelAndView("/finance/monthRecord");
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
    public String findMonthsChargeConsumeToward(Integer customerId,Integer typeId,Integer years,Integer months){
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
