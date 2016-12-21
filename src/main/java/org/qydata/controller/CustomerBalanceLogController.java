package org.qydata.controller;

import org.apache.log4j.Logger;
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
    @RequestMapping(value = "/customerBalanceChangeView")
    public  String customerBalanceChangeView(Model model){
        List<CustomerBalanceModifyReason> list= customerBalanceLogService.findAll();
        model.addAttribute("customerBalanceModifyReasonList",list);
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
    public String findAllCustomerBalanceLogByCustomerId(@PathVariable String customerId,String beginDate,String endDate,String reasonId, HttpServletRequest request,Model model){

        System.out.println("customerId="+customerId);
        System.out.println("beginDate="+beginDate+" "+"00:00:00");
        System.out.println("endDate="+endDate+" "+"23:59:59");
        System.out.println("reasonId=" + reasonId);

        PageModel pageModel = new PageModel();
        String pageSize = request.getParameter("pageSize");//当前页
        String lineSize = "12";//每页显示条数
        pageModel.setPageSize(pageSize);
        pageModel.setLineSize(lineSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("beginIndex", pageModel.getBeginIndex());
        map.put("lineSize", pageModel.getLineSize());
        map.put("customerId", Integer.parseInt(customerId));
        List<Integer> reasonIdList = new ArrayList<>();
        if (reasonId != null && reasonId != "") {
            reasonIdList.add(Integer.parseInt(reasonId));
        } else {
            reasonIdList.add(-1);
        }
        map.put("reasonIdList", reasonIdList);
        if (beginDate != null && beginDate != "" && endDate != null && endDate != "") {
            map.put("beginDate", beginDate+" "+"00:00:00");
            map.put("endDate", endDate+" "+"23:59:59");
        }
        PageModel<CustomerBalanceLog> pageModelOne = null;
        try {
            pageModelOne = customerBalanceLogService.findAllCustomerBalanceLogByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (beginDate != null && beginDate != "" && endDate != null && endDate != "") {
            model.addAttribute("beginDate", beginDate);
            model.addAttribute("endDate", endDate);
        }
        if (reasonId != null && reasonId != "") {
            model.addAttribute("reasonId", reasonId);
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
        return "/customerBalanceLog/consumptionRecord";
    }

}
