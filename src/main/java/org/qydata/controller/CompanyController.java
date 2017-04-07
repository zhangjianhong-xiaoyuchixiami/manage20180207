package org.qydata.controller;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import org.qydata.entity.CompanyApi;
import org.qydata.entity.CustomerBalanceModifyReason;
import org.qydata.entity.CustomerIp;
import org.qydata.entity.User;
import org.qydata.service.CompanyService;
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


    @Autowired private CompanyService companyService;


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
        model.addAttribute("partnerList",companyService.findAllPartner());
        model.addAttribute("companyList",companyService.findAllCompany(map));
        model.addAttribute("content",content);
        model.addAttribute("pageSize",20);
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
            model.addAttribute("partnerList",companyService.findAllPartner());
            model.addAttribute("companyList",companyService.findAllCompany(map));
            model.addAttribute("content",content);
            return "/company/company";
        }
        return "/company/company";
    }

    /**
     * 新增客户
     * @param companyName
     * @param authId
     * @return
     */
    @RequestMapping(value = "/add-company-customer")
    @ResponseBody
    public String addCompanyCustomer(String companyName,String authId,Integer partnerId){
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

        boolean flag = companyService.addCompanyCustomer(companyName,authId,partnerId);
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


    /**
     * 给账号添加IP
     * @param customerId
     * @param request
     * @return
     */
    @RequestMapping("/customer/add/ip")
    @ResponseBody
    public String addCustomerIp(Integer customerId,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        Gson gson = new Gson();
        String beginIp [] = request.getParameterValues("beginIp[]");
        String endIp [] = request.getParameterValues("endIp[]");
        if (companyService.addCustomerIp(customerId,beginIp,endIp)){
            map.put("success","操作成功");
            return gson.toJson(map);
        }
        map.put("fail","添加失败，请检查你的操作");
        return gson.toJson(map);
    }

    /**
     * 禁用账号
     * @param customerId
     * @return
     */
    @RequestMapping("/customer/ban")
    @ResponseBody
    public String customerBan(Integer customerId){

        return "";
    }

    /**
     * 解禁账号
     * @param customerId
     * @return
     */
    @RequestMapping("/customer/unban")
    @ResponseBody
    public String customerUnBan(Integer customerId){

        return "";
    }

    /**
     * 禁用公司
     * @return
     */
    @RequestMapping("/ban")
    @ResponseBody
    public String companyBan(HttpServletRequest request){
        String [] companyId = request.getParameterValues("companyId[]");
        for (int i=0; i<companyId.length; i++){
            System.out.println(companyId[i]);
        }
        return "";
    }

    /**
     * 解禁公司
     * @param companyId
     * @return
     */
    @RequestMapping("/unban")
    @ResponseBody
    public String companyUnBan(Integer companyId){

        return "";
    }

    /**
     * 管理产品权限
     * @param companyId
     * @return
     */
    @RequestMapping("/find-company-api")
    @ResponseBody
    public String queryCompanyApiByCompanyId(Integer companyId,String aoData){
        System.out.println(companyId);
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        map.put("companyId",companyId);
        List<CompanyApi> companyApiList = companyService.queryCompanyApiByCompanyId(map);
        return gson.toJson(companyApiList);
    }

    /**
     * 禁用产品权限
     * @param id
     * @return
     */
    @RequestMapping("/ban-api")
    @ResponseBody
    public String banCompanyApiById(Integer id){
        companyService.banCompanyApi(id);
        return "";
    }

    /**
     * 解禁产品权限
     * @param id
     * @return
     */
    @RequestMapping("/unban-api")
    @ResponseBody
    public String unBanCompanyApiById(Integer id){
        companyService.unBanCompanyApi(id);
        return "";
    }

    /**
     * 根据账号Id查询Ip
     * @param customerId
     * @return
     */
    @RequestMapping("/customer/find-ip")
    @ResponseBody
    public String queryCustomerIpById(Integer customerId){
        Gson gson = new Gson();
        List<CustomerIp> customerIpList = companyService.queryCustomerIpById(customerId);
        return gson.toJson(customerIpList);
    }

    /**
     * 根据Id删除Ip
     * @param id
     * @return
     */
    @RequestMapping("/customer/delete-ip")
    @ResponseBody
    public String deleteIpById(Integer id){
        companyService.deleteIpById(id);
        return "";
    }

    @RequestMapping("/edit")
    public String edit(){
        return "/test/table_editable";
    }
}
