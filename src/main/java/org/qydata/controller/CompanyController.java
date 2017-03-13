package org.qydata.controller;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import org.qydata.entity.CustomerBalanceModifyReason;
import org.qydata.entity.User;
import org.qydata.service.CompanyService;
import org.qydata.service.CustomerApiService;
import org.qydata.service.DeptService;
import org.qydata.tools.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/12/15.
 */
@Controller
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired private DeptService deptService;
    @Autowired private CompanyService companyService;
    @Autowired private CustomerApiService customerApiService;

    /**
     * 查找客户
     * @param partnerId
     * @param content
     * @param model
     * @return
     */
    @RequestMapping(value = "/find-all-company-customer")
    public String findAllCompanyCustomer(Integer partnerId,String content,Model model){
        Map<String,Object> map = new HashMap<>();
        if(content!=null){
            map.put("content",content);
        }
        map.put("partnerId",partnerId);
        model.addAttribute("deptList",deptService.findAllDept());
        model.addAttribute("partnerList",companyService.findAllPartner());
        model.addAttribute("companyList",companyService.findAllCompany(map));
        model.addAttribute("content",content);
        return "/company/company";
    }

    /**
     * 通过部门编号查找客户
     * @param request
     * @param partnerId
     * @param content
     * @param model
     * @return
     */
    @RequestMapping(value = "/find-all-company-customer-by-dept-id")
    public String findAllCompanyCustomerByDeptId(HttpServletRequest request,Integer partnerId,String content,Model model){
        User user = (User) request.getSession().getAttribute("userInfo");
        List deptIdList = new ArrayList();
        if (user.getDept().size() > 0) {
            for (int i = 0; i < user.getDept().size(); i++) {
                deptIdList.add(user.getDept().get(i).getId());
            }
            Map<String, Object> map = new HashMap<>();
            map.put("deptIdList", deptIdList);
            map.put("partnerId",partnerId);
            if (content != null) {
                map.put("content", content);
            }
            model.addAttribute("deptList",deptService.findDeptByUserId(user.getId()));
            model.addAttribute("partnerList",companyService.findAllPartner());
            model.addAttribute("companyList",companyService.findAllCompanyByDeptId(map));
            model.addAttribute("content",content);
            return "/company/company";
        }
        return "/company/company";
    }

    /**
     * 新增客户
     * @param companyName
     * @param authId
     * @param deptId
     * @return
     */
    @RequestMapping(value = "/add-company-customer")
    @ResponseBody
    public String addCompanyCustomer(String companyName,String authId,Integer partnerId,Integer deptId){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap();
        if(RegexUtil.isNull(companyName)){
            map.put("companyNameMessage","请输入公司名称!");
            return gson.toJson(map);
        }
        if(RegexUtil.isNull(authId)){
            map.put("authIdMessage","请输入账户!");
            return gson.toJson(map);
        }
        if(!RegexUtil.stringCheck(authId)){
            map.put("authIdMessage","账户格式输入不正确!");
            return gson.toJson(map);
        }
        if(RegexUtil.isNull(deptId)){
            map.put("deptMessage","请选择所属部门!");
            return gson.toJson(map);
        }
        boolean flag = companyService.addCompanyCustomer(companyName,authId,partnerId,deptId);
        if (flag){
            map.put("successMessage","恭喜你，操作成功！");
        }else {
            map.put("errorMessage","操作失败，请检查你的输入");
        }
        return gson.toJson(map);
    }

    /**
     * 添加公司账号
     * @param authId
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/add-customer-account")
    @ResponseBody
    public String addCustomerAccount(String authId,Integer companyId){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap();

        if(RegexUtil.isNull(authId)){
            map.put("authIdMessage","请输入账户!");
            return gson.toJson(map);
        }
        if(!RegexUtil.stringCheck(authId)){
            map.put("authIdMessage","账户格式输入不正确!");
            return gson.toJson(map);
        }
        boolean flag = companyService.addCustomer(authId, companyId);
        if (flag){
            map.put("successMessage","恭喜你，操作成功！");
        }else {
            map.put("errorMessage","操作失败，请检查你的输入");
        }
        return gson.toJson(map);
    }

    /**
     * 充值弹框
     * @return
     */
    @RequestMapping("/charge-customer-balance")
    @ResponseBody
    public String chargeCustomerBalance(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<CustomerBalanceModifyReason> customerBalanceModifyReasonList = companyService.findBalanceReason(list);
        JSONArray jsonArray = JSONArray.fromObject(customerBalanceModifyReasonList);
        return jsonArray.toString();
    }

    /**
     * 扣费弹框
     * @return
     */
    @RequestMapping("/consume-customer-balance")
    @ResponseBody
    public String consumeCustomerBalance(){
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        list.add(-2);
        List<CustomerBalanceModifyReason> customerBalanceModifyReasonList = companyService.findBalanceReason(list);
        JSONArray jsonArray = JSONArray.fromObject(customerBalanceModifyReasonList);
        return jsonArray.toString();
    }

    /**
     * 充值/扣费
     * @param customerId
     * @param reason
     * @param amount
     * @return
     */
    @RequestMapping("/update-customer-balance")
    @ResponseBody
    public String updateCustomerBalance(Integer customerId,Integer reason,String amount){
        System.out.println(customerId);
        System.out.println(reason);
        System.out.println(amount);
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap();
        if(RegexUtil.isNull(amount)){
            map.put("amountMessage","请输入金额!");
            return gson.toJson(map);
        }
        if(!RegexUtil.isFloatZero(amount)){
            map.put("amountMessage","金额格式不正确!");
            return gson.toJson(map);
        }else {
            if (Long.parseLong(amount)<=0){
                map.put("amountMessage","金额必须大于0!");
                return gson.toJson(map);
            }
        }
        if(RegexUtil.isNull(reason)){
            map.put("reasonMessage","请选择理由!");
            return gson.toJson(map);
        }
        boolean flag = companyService.updateCustomerBalance(customerId, reason, Long.parseLong(amount));
        if (flag){
            map.put("successMessage","恭喜你，操作成功！");
        }else {
            map.put("errorMessage","操作失败，请检查你的输入");
        }
        return gson.toJson(map);
    }

}
