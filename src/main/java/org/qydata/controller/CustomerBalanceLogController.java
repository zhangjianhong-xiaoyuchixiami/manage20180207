package org.qydata.controller;

import org.apache.log4j.Logger;
import org.qydata.entity.Customer;
import org.qydata.entity.CustomerBalanceModifyReason;
import org.qydata.regex.RegexUtil;
import org.qydata.service.CustomerBalanceLogService;
import org.qydata.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


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

    //余额变动日志
    @RequestMapping(value = "/customerBalanceChangeView")
    public  String customerBalanceChangeView(Model model){
        List<CustomerBalanceModifyReason> list= customerBalanceLogService.findAll();
        model.addAttribute("customerBalanceModifyReasonList",list);
        return "/customer/customerBalanceChange";
    }


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
        if(!RegexUtil.isFloat(amount)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("amount",amount);
            if(Integer.parseInt(amount)<=0){
                model.addFlashAttribute("CustomerMessageAmount","金额必须大于0");
            }else{
                model.addFlashAttribute("CustomerMessageAmount","金额格式不正确");
            }
            return "redirect:/customerBalance/customerBalanceChangeView";
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


    @RequestMapping(value = "/customerBalanceChangeSuccess/{authId}")
    public String customerBalanceChangeSuccess(@PathVariable String authId,Model model){
        Customer customer = customerService.findByAuthId(authId);
        model.addAttribute("customer",customer);
        return "/customer/customerBalanceChangeSuccess";

    }
}
