package org.qydata.controller;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.qydata.dst.CustomerFinance;
import org.qydata.entity.CustomerBalanceLog;
import org.qydata.entity.Dept;
import org.qydata.entity.User;
import org.qydata.service.CustomerBalanceLogService;
import org.qydata.service.CustomerFinanceService;
import org.qydata.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    @RequestMapping(value = "/find-all-customer/find-all-customer-recharge-log-by-customer-id/{customerId}")
    public String findAllCustomerRechargeLogByCustomerId(@PathVariable Integer customerId,String companyName, String beginDate, String endDate, String [] reasonId, HttpServletRequest request, Model model){
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
            customerBalanceLogList = customerFinanceService.queryCompanyCustomerRechargeRecordByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long totleAmount = null;
        Iterator<CustomerBalanceLog> iterator = customerBalanceLogList.iterator();
        while (iterator.hasNext()){
            CustomerBalanceLog customerBalanceLog = iterator.next();
            totleAmount+=customerBalanceLog.getAmount();
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
    public String findAllApiConsumeRecordByCustomerId(){
        return "/finance/customerApiConsumeRecord";
    }

    @RequestMapping("/find-all-customer/find-all-customer-api-consume-record-by-customer-id/detail")
    public String findAllApiConsumeDetailRecordByCustomerId(){
        return "/finance/customerApiConsumeDetailRecord";
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
