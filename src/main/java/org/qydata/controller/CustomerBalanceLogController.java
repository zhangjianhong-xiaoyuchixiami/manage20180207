package org.qydata.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.qydata.entity.Customer;
import org.qydata.entity.CustomerBalanceLog;
import org.qydata.entity.CustomerBalanceModifyReason;
import org.qydata.regex.RegexUtil;
import org.qydata.service.CustomerBalanceLogService;
import org.qydata.service.CustomerService;
import org.qydata.tools.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping(value = "/customerBalance")
public class CustomerBalanceLogController {
    private final Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private CustomerBalanceLogService customerBalanceLogService;
    @Autowired
    private CustomerService customerService;

    //余额变动日志View
    @RequestMapping(value = "/customerBalanceChangeView/{authId}")
    public  String customerBalanceChangeView(@PathVariable String authId, Model model){
        List<CustomerBalanceModifyReason> list= customerBalanceLogService.findAll();
        model.addAttribute("customerBalanceModifyReasonList",list);
        model.addAttribute("authId",authId);
        return "/customerBalanceLog/customerBalanceChange";
    }

    //余额变动日志Action
    @RequestMapping(value = "/customerBalanceChangeAction")
    public String customerBalanceChangeAction(String authId, String amount, String reasonId, RedirectAttributes model){
        if(RegexUtil.isNull(authId)){
            model.addFlashAttribute("CustomerMessageAuthId","请输入账户");
            return "redirect:/customerBalance/customerBalanceChangeView";
        }
        if(!RegexUtil.stringCheck(authId)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("CustomerMessageAuthId","账户格式输入不正确");
            return "redirect:/customerBalance/customerBalanceChangeView";
        }
        if(RegexUtil.isNull(amount)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("CustomerMessageAmount","请输入金额");
            return "redirect:/customerBalance/customerBalanceChangeView";
        }
        if(!RegexUtil.isFloatZero(amount)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("amount",amount);
            model.addFlashAttribute("CustomerMessageAmount","金额格式不正确");
            return "redirect:/customerBalance/customerBalanceChangeView";
        }else{
            if(Integer.parseInt(amount)<=0){
                model.addFlashAttribute("authId",authId);
                model.addFlashAttribute("amount",amount);
                model.addFlashAttribute("CustomerMessageAmount","金额必须大于0");
                return "redirect:/customerBalance/customerBalanceChangeView";
            }
        }
        if(!RegexUtil.isZhengShuDigits(reasonId)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("amount",amount);
            model.addFlashAttribute("CustomerMessageReasonId","请选择充值理由");
            return "redirect:/customerBalance/customerBalanceChangeView";
        }
        try {
            boolean flag = customerBalanceLogService.changeCustomerBalance(authId,amount,reasonId);
            if (!flag){
                model.addFlashAttribute("msg","操作失败！");
                return "redirect:/customerBalance/customerBalanceChangeView";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("customerBalanceChangeAction:余额变动异常");
            model.addFlashAttribute("msg","操作失败！");
            return "redirect:/customerBalance/customerBalanceChangeView";
        }
        return "redirect:/customerBalance/customerBalanceChangeSuccess/"+authId;
    }

    //余额变动日志Success
    @RequestMapping(value = "/customerBalanceChangeSuccess/{authId}")
    public String customerBalanceChangeSuccess(@PathVariable String authId,Model model){
        Customer customer = customerService.findByAuthId(authId);
        model.addAttribute("customer",customer);
        return "/customerBalanceLog/customerBalanceChangeSuccess";

    }

    //指定账号消费记录
    @RequestMapping(value = "/findAllCustomerBalanceLogByCustomerId/{customerId}")
    public String findAllCustomerBalanceLogByCustomerId(@PathVariable String customerId,String beginDate,String endDate,String reasonId1,String reasonId2, HttpServletRequest request,Model model){

        System.out.println("customerId="+customerId);
        System.out.println("beginDate="+beginDate+" "+"00:00:00");
        System.out.println("endDate="+endDate+" "+"23:59:59");
        System.out.println("reasonId1=" + reasonId1);
        System.out.println("reasonId2=" + reasonId2);

        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.hasRole("backAdmin"));

        PageModel pageModel = new PageModel();
        String pageSize = request.getParameter("pageSize");//当前页
        String lineSize = "12";//每页显示条数
        pageModel.setPageSize(pageSize);
        pageModel.setLineSize(lineSize);
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("customerId", Integer.parseInt(customerId));
        List<Integer> reasonIdList = new ArrayList<>();
        if (reasonId1 != null && reasonId1 != "") {
            reasonIdList.add(Integer.parseInt(reasonId1));
        }
        if(reasonId2 != null && reasonId2 != ""){
            reasonIdList.add(Integer.parseInt(reasonId2));
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
        if (beginDate != null && beginDate != "") {
            model.addAttribute("beginDate", beginDate);
        }
        if(endDate != null && endDate != ""){
            model.addAttribute("endDate", endDate);
        }
        if (reasonId1 != null && reasonId1 != "") {
            model.addAttribute("reasonId1", reasonId1);
        }
        if (reasonId2 != null && reasonId2 != "") {
            model.addAttribute("reasonId2", reasonId2);
        }
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
        return "/customerBalanceLog/consumptionRecord";
    }

    //指定账号充值记录
    @RequestMapping(value = "/findAllRechargeCustomerBalanceLogByCustomerId/{customerId}")
    public String findAllRechargeCustomerBalanceLogByCustomerId(@PathVariable String customerId,String beginDate,String endDate,String reasonId3,String reasonId4,String reasonId5, HttpServletRequest request,Model model){

        System.out.println("customerId="+customerId);
        System.out.println("beginDate="+beginDate+" "+"00:00:00");
        System.out.println("endDate="+endDate+" "+"23:59:59");
        System.out.println("reasonId3=" + reasonId3);
        System.out.println("reasonId4=" + reasonId4);
        System.out.println("reasonId5=" + reasonId5);

        PageModel pageModel = new PageModel();
        String pageSize = request.getParameter("pageSize");//当前页
        String lineSize = "12";//每页显示条数
        pageModel.setPageSize(pageSize);
        pageModel.setLineSize(lineSize);
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("customerId", Integer.parseInt(customerId));
        List<Integer> reasonIdList = new ArrayList<>();
        if (reasonId3 != null && reasonId3 != "") {
            reasonIdList.add(Integer.parseInt(reasonId3));
        }
        if(reasonId4 != null && reasonId4 != ""){
            reasonIdList.add(Integer.parseInt(reasonId4));
        }
        if(reasonId5 != null && reasonId5 != ""){
            reasonIdList.add(Integer.parseInt(reasonId5));
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
        model.addAttribute("reasonId3", reasonId3);
        model.addAttribute("reasonId4", reasonId4);
        model.addAttribute("reasonId5", reasonId5);
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
        return "/customerBalanceLog/consumptionRecord";
    }

}
