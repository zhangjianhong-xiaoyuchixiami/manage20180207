package org.qydata.controller;

import org.apache.log4j.Logger;
import org.qydata.entity.CustomerBalanceModifyReason;
import org.qydata.service.CustomerBalanceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    //余额变动日志
    @RequestMapping(value = "/customerBalanceChangeView")
    public  String customerBalanceChangeView(Model model){
        List<CustomerBalanceModifyReason> list= customerBalanceLogService.findAll();
        model.addAttribute("customerBalanceModifyReasonList",list);
        return "/customer/customerBalanceChange";
    }

    @RequestMapping(value = "/customerBalanceChangeAction")
    public String customerBalanceChangeAction(String authId, String amount, String reasonId, RedirectAttributes model){
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
        return "redirect:/customer/findAllCustomer";
    }
    //Common
    @RequestMapping(value = "/customerBalanceChangeActionCommon")
    public String customerBalanceChangeActionCommon(String authId, String amount, String reasonId, RedirectAttributes model){
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
        return "redirect:/customer/findAllCustomerByDeptNo";
    }


}
