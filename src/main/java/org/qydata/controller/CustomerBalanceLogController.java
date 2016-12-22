package org.qydata.controller;

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
        model.addFlashAttribute("success","恭喜您，操作成功");
        return "redirect:/customerBalance/customerBalanceChangeView/"+authId+"?reasonId="+reasonId;
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
    public String findAllCustomerBalanceLogByCustomerId(@PathVariable String customerId,String beginDate,String endDate,String [] reasonId, HttpServletRequest request,Model model){

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
    @RequestMapping(value = "/findAllRechargeCustomerBalanceLogByCustomerId/{customerId}")
    public String findAllRechargeCustomerBalanceLogByCustomerId(@PathVariable String customerId,String beginDate,String endDate,String [] reasonId, HttpServletRequest request,Model model){
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

}
