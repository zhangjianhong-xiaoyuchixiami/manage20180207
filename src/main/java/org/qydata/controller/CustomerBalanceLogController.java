package org.qydata.controller;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.qydata.entity.*;
import org.qydata.regex.RegexUtil;
import org.qydata.service.CustomerBalanceLogService;
import org.qydata.service.CustomerService;
import org.qydata.tools.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by jonhn on 2016/11/8.
 */
@Controller
@RequestMapping(value = "/customer-balance")
public class CustomerBalanceLogController {
    private final Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private CustomerBalanceLogService customerBalanceLogService;
    @Autowired
    private CustomerService customerService;

    //余额变动日志View
    @RequestMapping(value = "/customerBalanceChangeView/{authId}")
    public  String customerBalanceChangeView(@PathVariable String authId,Integer reasonId, Model model){
        List customerBalanceModifyReasonIdList = new ArrayList();
        model.addAttribute("authId",authId);
        if (reasonId <0){
            customerBalanceModifyReasonIdList.add(-1);
            customerBalanceModifyReasonIdList.add(-2);
            List<CustomerBalanceModifyReason> list= customerBalanceLogService.findAll(customerBalanceModifyReasonIdList);
            model.addAttribute("customerBalanceModifyReasonList",list);
            return "/customerBalanceLog/customerBalanceChange";
        }else{
            customerBalanceModifyReasonIdList.add(1);
            customerBalanceModifyReasonIdList.add(2);
            customerBalanceModifyReasonIdList.add(3);
            List<CustomerBalanceModifyReason> list= customerBalanceLogService.findAll(customerBalanceModifyReasonIdList);
            model.addAttribute("customerBalanceModifyReasonList",list);
            return "/customerBalanceLog/customerBalanceChange";
        }
    }

    //余额变动日志Action
    @RequestMapping(value = "/customerBalanceChangeAction")
    public String customerBalanceChangeAction(String authId, String amount, String reasonId, RedirectAttributes model){
        if(RegexUtil.isNull(authId)){
            model.addFlashAttribute("CustomerMessageAuthId","请输入账户");
            return "redirect:/customerBalance/customerBalanceChangeView/"+authId;
        }
        if(!RegexUtil.stringCheck(authId)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("CustomerMessageAuthId","账户格式输入不正确");
            return "redirect:/customerBalance/customerBalanceChangeView/"+authId;
        }
        if(RegexUtil.isNull(amount)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("CustomerMessageAmount","请输入金额");
            return "redirect:/customerBalance/customerBalanceChangeView/"+authId;
        }
        if(!RegexUtil.isFloatZero(amount)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("amount",amount);
            model.addFlashAttribute("CustomerMessageAmount","金额格式不正确");
            return "redirect:/customerBalance/customerBalanceChangeView/"+authId;
        }else{
            if(Integer.parseInt(amount)<=0){
                model.addFlashAttribute("authId",authId);
                model.addFlashAttribute("amount",amount);
                model.addFlashAttribute("CustomerMessageAmount","金额必须大于0");
                return "redirect:/customerBalance/customerBalanceChangeView/"+authId;
            }
        }
        if(!RegexUtil.isZhengShuDigits(reasonId)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("amount",amount);
            model.addFlashAttribute("CustomerMessageReasonId","请选择充值理由");
            return "redirect:/customerBalance/customerBalanceChangeView/"+authId;
        }
        try {
            boolean flag = customerBalanceLogService.changeCustomerBalance(authId,amount,reasonId);
            if (!flag){
                model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
                return "redirect:/customerBalance/customerBalanceChangeView/"+authId;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("customerBalanceChangeAction:余额变动异常");
            model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
            return "redirect:/customerBalance/customerBalanceChangeView/"+authId;
        }
        model.addFlashAttribute("success","yes");
        return "redirect:/customerBalance/customerBalanceChangeView/"+authId+"?reasonId="+reasonId;
    }

    //余额变动日志Success
    @RequestMapping(value = "/customerBalanceChangeSuccess/{authId}")
    public String customerBalanceChangeSuccess(@PathVariable String authId,Model model){
        Customer customer = customerService.findByAuthId(authId);
        model.addAttribute("customer",customer);
        return "/customerBalanceLog/customerBalanceChangeSuccess";

    }




    //查找公司财务账单
    @RequestMapping(value = "/find-all-customer")
    public String findAllCustomer(HttpServletRequest request,Model model,String content,String [] customerTypeId){

        PageModel<Customer> pageModel = new PageModel();
        String pageSize= request.getParameter("pageSize");//当前页
        String lineSize = "8";//每页显示条数
        pageModel.setPageSize(pageSize);
        pageModel.setLineSize(lineSize);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("beginIndex",pageModel.getBeginIndex());
        map.put("lineSize",pageModel.getLineSize());
        if(content!=null){
            map.put("content",content);
        }
        List customerTypeIdList = new ArrayList();
        if(customerTypeId != null && customerTypeId.length >0){
            for (int i=0;i<customerTypeId.length;i++){
                customerTypeIdList.add(customerTypeId[i]);
            }
        }
        map.put("customerTypeIdList",customerTypeIdList);
        PageModel<Customer> pageModelOne = null;
        try {
            pageModelOne = customerService.findAllCustomer(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("content",content);
        model.addAttribute("customerTypeIdArray",customerTypeId);
        model.addAttribute("count",pageModelOne.getCount());
        model.addAttribute("customerList",pageModelOne.getList());
        Integer totalPage= null;
        Integer count = pageModelOne.getCount();

        if (count%Integer.parseInt(lineSize) == 0) {
            totalPage = (count/Integer.parseInt(lineSize));
        } else {
            totalPage = (count/Integer.parseInt(lineSize)) + 1;
        }
        model.addAttribute("totlePage",totalPage);
        model.addAttribute("pageSize",pageModel.getPageSize());
        return "/customerBalanceLog/customerFinancialAccount";
    }

    //通过部门编号查找公司财务账单
    @RequestMapping(value = ("/find-all-customer-by-dept-id"))
    public String findAllCustomerByDeptId(HttpServletRequest request,Model model,String content,String [] customerTypeId){
        User user = (User)request.getSession().getAttribute("userInfo");
        List<Dept> deptList = user.getDept();
        List deptIdList = new ArrayList();
        if (deptList.size() > 0) {
            for (int i = 0; i < deptList.size(); i++) {
                deptIdList.add(deptList.get(i).getId());
            }
            PageModel<Customer> pageModel = new PageModel();
            String pageSize = request.getParameter("pageSize");//当前页
            String lineSize = "8";//每页显示条数
            pageModel.setPageSize(pageSize);
            pageModel.setLineSize(lineSize);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("beginIndex", pageModel.getBeginIndex());
            map.put("lineSize", pageModel.getLineSize());
            map.put("deptIdList", deptIdList);
            if (content != null) {
                map.put("content", content);
            }
            List customerTypeIdList = new ArrayList();
            if(customerTypeId != null && customerTypeId.length >0){
                for (int i=0;i<customerTypeId.length;i++){
                    customerTypeIdList.add(customerTypeId[i]);
                }
            }
            map.put("customerTypeIdList", customerTypeIdList);
            PageModel<Customer> pageModelOne = null;
            try {
                pageModelOne = customerService.findAllCustomer(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("deptIdList", deptIdList);
            model.addAttribute("content", content);
            model.addAttribute("customerTypeIdArray",customerTypeId);
            model.addAttribute("count", pageModelOne.getCount());
            model.addAttribute("customerList", pageModelOne.getList());
            Integer totalPage = null;
            Integer count = pageModelOne.getCount();

            if (count % Integer.parseInt(lineSize) == 0) {
                totalPage = (count / Integer.parseInt(lineSize));
            } else {
                totalPage = (count / Integer.parseInt(lineSize)) + 1;
            }
            model.addAttribute("totlePage", totalPage);
            model.addAttribute("pageSize", pageModel.getPageSize());
            return "/customerBalanceLog/customerList";
        }else {
            model.addAttribute("deptIdList", deptIdList);
            model.addAttribute("count", 0);
            model.addAttribute("customerList", null);
            return "/customerBalanceLog/customerFinancialAccount";
        }
    }


    //指定账号消费记录
    @RequestMapping(value = "/find-all-customer/find-all-customer-consume-log-by-customer-id/{customerId}")
    public String findAllCustomerConsumeLogByCustomerId(@PathVariable String customerId,String beginDate,String endDate,String [] reasonId, HttpServletRequest request,Model model){

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
                if (!customerIdList.contains(Integer.parseInt(customerId))){
                    return "../view/unauthUrl";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        PageModel pageModel = new PageModel();
        String pageSize = request.getParameter("pageSize");//当前页
        String lineSize = "12";//每页显示条数
        pageModel.setPageSize(pageSize);
        pageModel.setLineSize(lineSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("customerId", Integer.parseInt(customerId));

        List<Integer> reasonIdList = new ArrayList<>();

        if (reasonId != null && reasonId.length >0) {
            for(int i=0;i<reasonId.length;i++){
                reasonIdList.add(Integer.parseInt(reasonId[i]));
            }
        }else {
            reasonIdList.add(-1);
            reasonIdList.add(-2);
        }
        map.put("reasonIdList", reasonIdList);


        if (beginDate != null && beginDate != "" ) {
            map.put("beginDate", beginDate+" "+"00:00:00");
        }
        if(endDate != null && endDate != ""){
            map.put("endDate", endDate+" "+"23:59:59");
        }
        List<CustomerBalanceLog> customerBalanceLogList = null;
        try {
            customerBalanceLogList = customerBalanceLogService.getAllCustomerBalanceLogAmountByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long totleAmount = 0L;
        if(customerBalanceLogList != null){
            for (int i=0;i<customerBalanceLogList.size();i++){
                totleAmount+=customerBalanceLogList.get(i).getAmount();
            }
        }
        model.addAttribute("totleAmount",(totleAmount/100.0));

        map.put("beginIndex", pageModel.getBeginIndex());
        map.put("lineSize", pageModel.getLineSize());
        PageModel<CustomerBalanceLog> pageModelOne = null;
        try {
            pageModelOne = customerBalanceLogService.findAllCustomerBalanceLogByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long lineSizeTotleAmount = 0L;
        if(pageModelOne != null){
            List<CustomerBalanceLog> customerBalanceLogs = pageModelOne.getList();
            for(int i=0;i<customerBalanceLogs.size();i++){
                lineSizeTotleAmount+=customerBalanceLogs.get(i).getAmount();
            }
        }
        model.addAttribute("lineSizeTotleAmount",(lineSizeTotleAmount/100.0));
        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("reasonIdArray", reasonId);
        model.addAttribute("count", pageModelOne.getCount());
        model.addAttribute("customerBalanceLogList", pageModelOne.getList());
        model.addAttribute("customerId", customerId);
        Integer totalPage = null;
        Integer count = pageModelOne.getCount();
        if (count % Integer.parseInt(lineSize) == 0) {
            totalPage = (count / Integer.parseInt(lineSize));
        } else {
            totalPage = (count / Integer.parseInt(lineSize)) + 1;
        }
        model.addAttribute("totlePage", totalPage);
        model.addAttribute("pageSize", pageModel.getPageSize());
        model.addAttribute("CustomerBalanceModifyReason","扣费");
        return "/customerBalanceLog/customerBalanceLogRecord";
    }

    //指定账号充值记录
    @RequestMapping(value = "/find-all-customer/find-all-customer-recharge-log-by-customer-id/{customerId}")
    public String findAllCustomerRechargeLogByCustomerId(@PathVariable String customerId,String beginDate,String endDate,String [] reasonId, HttpServletRequest request,Model model){
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
                if (!customerIdList.contains(Integer.parseInt(customerId))){
                    return "../view/unauthUrl";
                }
            } catch (Exception e) {
                log.error("findAllRechargeCustomerBalanceLogByCustomerId:授权失败");
                e.printStackTrace();
            }
        }
        PageModel pageModel = new PageModel();
        String pageSize = request.getParameter("pageSize");//当前页
        String lineSize = "12";//每页显示条数
        pageModel.setPageSize(pageSize);
        pageModel.setLineSize(lineSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("customerId", Integer.parseInt(customerId));

        List<Integer> reasonIdList = new ArrayList<>();

        if (reasonId != null && reasonId.length >0) {
            for(int i=0;i<reasonId.length;i++){
                reasonIdList.add(Integer.parseInt(reasonId[i]));
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
        try {
            customerBalanceLogList = customerBalanceLogService.getAllCustomerBalanceLogAmountByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long totleAmount = 0L;
        if(customerBalanceLogList != null){
            for (int i=0;i<customerBalanceLogList.size();i++){
                totleAmount+=customerBalanceLogList.get(i).getAmount();
            }
        }
        model.addAttribute("totleAmount",(totleAmount/100.0));

        map.put("beginIndex", pageModel.getBeginIndex());
        map.put("lineSize", pageModel.getLineSize());
        PageModel<CustomerBalanceLog> pageModelOne = null;
        try {
            pageModelOne = customerBalanceLogService.findAllCustomerBalanceLogByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long lineSizeTotleAmount = 0L;
        if(pageModelOne != null){
            List<CustomerBalanceLog> customerBalanceLogs = pageModelOne.getList();
            for(int i=0;i<customerBalanceLogs.size();i++){
                lineSizeTotleAmount+=customerBalanceLogs.get(i).getAmount();
            }
        }
        model.addAttribute("lineSizeTotleAmount",(lineSizeTotleAmount/100.0));
        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("reasonIdArray", reasonId);
        model.addAttribute("count", pageModelOne.getCount());
        model.addAttribute("customerBalanceLogList", pageModelOne.getList());
        model.addAttribute("customerId", customerId);
        Integer totalPage = null;
        Integer count = pageModelOne.getCount();
        if (count % Integer.parseInt(lineSize) == 0) {
            totalPage = (count / Integer.parseInt(lineSize));
        } else {
            totalPage = (count / Integer.parseInt(lineSize)) + 1;
        }
        model.addAttribute("totlePage", totalPage);
        model.addAttribute("pageSize", pageModel.getPageSize());
        model.addAttribute("CustomerBalanceModifyReason","充值");
        return "/customerBalanceLog/customerBalanceLogRecord";
    }

    //指定账号Api消费记录
    @RequestMapping("/find-all-customer/find-all-customer-api-consume-record-by-customer-id")
    public String findAllApiConsumeRecordByCustomerId(){
        return "/customerBalanceLog/customerApiConsumeRecord";
    }

    //Api消费账单
    @RequestMapping("/find-all-api-record")
    public String findAllApiRecord(){
        return "/customerBalanceLog/apiRecord";
    }

    //Api消费账单-消费明细
    @RequestMapping("/find-all-api-record/detail")
    public String findAllApiDetailRecord(){
        return "/customerBalanceLog/apiDetailRecord";
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
        return "/customerBalanceLog/partnersFinancialAccount";
    }

    //合作伙伴来往账目-收支明细
    @RequestMapping("/find-all-partners-financial-account/receipt-and-paying-record")
    public String findPartnersReceiptAndPayingRecord(){
        return "/customerBalanceLog/partnersReceiptAndPayingRecord";
    }
}
