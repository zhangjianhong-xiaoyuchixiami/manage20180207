package org.qydata.controller;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import org.qydata.config.annotation.RolePermission;
import org.qydata.dst.ApiTypeSubType;
import org.qydata.entity.*;
import org.qydata.service.CompanyService;
import org.qydata.service.CustomerService;
import org.qydata.tools.RegexUtil;
import org.qydata.tools.checkNumber.CheckNumberUtil;
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
    @Autowired private CustomerService customerService;

    /**
     * 添加新客户时验证账户名是否已存在
     * @param authId
     */
    @RequestMapping(value = "/findCustomerByAuthId")
    @ResponseBody
    public String findCustomerByAuthIdAdd(String authId){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        Customer customer = customerService.findCustomerByAuthId(authId);
        System.out.println(customer);
        if (customer == null){
            map.put("success","true");
            return gson.toJson(map);
        }
        map.put("fail","false");
        return gson.toJson(map);
    }


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
        model.addAttribute("apiTypeList",companyService.queryAllApi());
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
            model.addAttribute("partnerList",companyService.findPartnerByEmail(user.getEmail()));
            model.addAttribute("companyList",companyService.findAllCompany(map));
            model.addAttribute("apiTypeList",companyService.queryAllApi());
            model.addAttribute("content",content);
            return "/company/company-dept";
        }
        return "/company/company-dept";
    }


    /**
     * 新增客户
     * @return
     */
    @RequestMapping(value = "/add-company-customer")
    @ResponseBody
    @RolePermission
    public String addCompanyCustomer(HttpServletRequest request ) {
        String companyName = request.getParameter("companyName").trim();
        String authId = request.getParameter("authId").trim();
        String partnerId = request.getParameter("partnerId").trim();
        String add_api_type_sub [] = request.getParameterValues("add_api_type_sub[]");
        String add_api_type_sub_price [] = request.getParameterValues("add_api_type_sub_price[]");
        String beginIp [] = request.getParameterValues("beginIp[]");
        String endIp [] = request.getParameterValues("endIp[]");

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
        int code = 0;
        try {
            code = companyService.addCompanyCustomer(companyName,authId,partnerId,add_api_type_sub,add_api_type_sub_price,beginIp,endIp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (200 == code){
            map.put("success","操作成功！");
            return gson.toJson(map);
        }
        map.put("error","操作失败！");
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
        list.add(-4);
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
        list.add(-3);
        List<CustomerBalanceModifyReason> customerBalanceModifyReasonList = companyService.findBalanceReason(list);
        JSONArray jsonArray = JSONArray.fromObject(customerBalanceModifyReasonList);
        return jsonArray.toString();
    }

    /**
     * 充值/扣费
     * @param companyId
     * @param reason
     * @param amount
     * @return
     */
    @RequestMapping("/update-customer-balance")
    @ResponseBody
    public String updateCustomerBalance(Integer companyId,Integer reason,String amount){
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
            if (Double.parseDouble(amount)<=0){
                map.put("amountMessage","金额必须大于0!");
                return gson.toJson(map);
            }
        }
        if(RegexUtil.isNull(reason)){
            map.put("reasonMessage","请选择理由!");
            return gson.toJson(map);
        }
        int code = 0;
        try {
            code = companyService.updateCustomerBalance(companyId, reason, amount.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (200 == code ){
            map.put("successMessage","恭喜你，操作成功！");
        }else {
            map.put("errorMessage","操作失败，请检查你的输入");
        }
        return gson.toJson(map);
    }

    /**
     * 禁用账号
     * @param authId
     * @return
     */
    @RequestMapping("/customer/ban")
    @ResponseBody
    @RolePermission
    public String customerBan(String authId){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        int code = 0;
        try {
            code = companyService.updateCustomerBan(authId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (200 == code){
            map.put("success","禁用成功！");
            return gson.toJson(map);
        }
        map.put("error","禁用失败！");
        return gson.toJson(map);
    }

    /**
     * 解禁账号
     * @param authId
     * @return
     */
    @RequestMapping("/customer/unban")
    @ResponseBody
    @RolePermission
    public String customerUnBan(String authId){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        int code = 0;
        try {
            code = companyService.updateCustomerUnBan(authId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (200 == code){
            map.put("success","禁用成功！");
            return gson.toJson(map);
        }
        map.put("error","禁用失败！");
        return gson.toJson(map);
    }

    /**
     * 禁用公司
     * @return
     */
    @RequestMapping("/ban")
    @ResponseBody
    @RolePermission
    public String companyBan(HttpServletRequest request){
        Gson gson = new Gson();
        String [] companyId = request.getParameterValues("companyId[]");
        Map<String,Object> mapResu = null;
        try {
            mapResu = companyService.updateCompanyBan(companyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gson.toJson(mapResu);
    }

    /**
     * 解禁公司
     * @param request
     * @return
     */
    @RequestMapping("/unban")
    @ResponseBody
    @RolePermission
    public String companyUnBan(HttpServletRequest request){
        Gson gson = new Gson();
        String [] companyId = request.getParameterValues("companyId[]");
        Map<String,Object> mapResu = null;
        try {
            mapResu = companyService.updateCompanyUnBan(companyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gson.toJson(mapResu);
    }

    /**
     * 管理产品权限
     * @param companyId
     * @return
     */
    @RequestMapping("/find-company-api")
    @ResponseBody
    public String queryCompanyApiByCompanyId(Integer companyId) {
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
    @RolePermission
    public String banCompanyApiById(Integer companyId,Integer id){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        int code = 0;
        try {
            code = companyService.banCompanyApi(companyId,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (200 == code){
            map.put("success","操作成功！");
            return gson.toJson(map);
        }
        map.put("error","操作失败！");
        return gson.toJson(map);
    }

    /**
     * 解禁产品权限
     * @param id
     * @return
     */
    @RequestMapping("/unban-api")
    @ResponseBody
    @RolePermission
    public String unBanCompanyApiById(Integer companyId,Integer id){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
         int code = 0;
        try {
           code = companyService.unBanCompanyApi(companyId,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (200 == code){
            map.put("success","客户产品权限启用成功！");
            return gson.toJson(map);
        }
        map.put("error","哎呀，操作失败了！");
        return gson.toJson(map);
    }

    /**
     * 查询客户未拥有的产品权限列表
     * @param companyId
     * @return
     */
    @RequestMapping("/query-not-have-api")
    @ResponseBody
    public String queryNotHaveApi(Integer companyId){
        Gson gson = new Gson();
        List<ApiTypeSubType> apiTypeSubTypeList = companyService.queryNotHaveApi(companyId);
        return gson.toJson(apiTypeSubTypeList);
    }

    /**
     * 保存产品权限
     * @param companyId
     * @param apiTypeId
     * @param price
     * @return
     */
    @RequestMapping("/add-company-api")
    @ResponseBody
    @RolePermission
    public String addCompanyApi(int companyId,String apiTypeId,String price,String aid){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap();
        if(RegexUtil.isNull(apiTypeId)){
            map.put("apiTypeIdMessage","请选择产品!");
            return gson.toJson(map);
        }
        if(RegexUtil.isNull(price)){
            map.put("priceMessage","请输入金额!");
            return gson.toJson(map);
        }
        if(!RegexUtil.isFloatZero(price)){
            map.put("priceMessage","金额格式不正确!");
            return gson.toJson(map);
        }else {
            if (Double.parseDouble(price)<0){
                map.put("priceMessage","金额必须大于0!");
                return gson.toJson(map);
            }
        }
        int code = 0;
        try {
            code = companyService.addCompanyApi(companyId, apiTypeId, price,aid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (200 == code){
            map.put("successMessage","操作成功!");
            return gson.toJson(map);
        }
        map.put("errorMessage","操作失败!");
        return gson.toJson(map);
    }

    /**
     * 根据产品类型查找同一产品类型的产品列表
     * @param tid_stid
     * @return
     */
    @RequestMapping("/query-api-by-typeId")
    @ResponseBody
    public String queryApiByTypeId(String tid_stid){
       List<Api> apiList = companyService.queryApiByTypeId(tid_stid);
        return new Gson().toJson(apiList);
    }

    /**
     * 修改产品价格
     * @param companyId
     * @return
     */
    @RequestMapping("/mid-company-api-price")
    @ResponseBody
    public String midCompanyApiPrice(Integer companyId,Integer tid,Integer stid,String pic,Integer aid){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap();
        if (!CheckNumberUtil.isStipulateNumeric(pic)){
            map.put("fail","数据格式不正确或大于最大值（修改最大值是：5）");
            return gson.toJson(map);
        }
        int code = 0;
        try {
            code = companyService.updateCompanyApiPrice(companyId,tid,stid,pic,aid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (200 == code){
            map.put("success","价格修改完成");
            return gson.toJson(map);
        }
        map.put("fail","哎呦，修改失败了");
        return gson.toJson(map);
    }

    /**
     *修改指定产品
     * @return
     */
    @RequestMapping("/mid-company-api-appoint-api")
    @ResponseBody
    @RolePermission
    public String midCompanyApiAppointApi(Integer companyId,Integer tid,Integer stid,String pic,Integer aid){
        System.out.println(companyId);
        System.out.println(tid);
        System.out.println(stid);
        System.out.println(pic);
        System.out.println(aid);
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap();
        int code = 0;
        try {
            code = companyService.updateCompanyApiAppointApi(companyId,tid,stid,pic,aid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (200 == code){
            map.put("success","指定产品修改完成!");
            return gson.toJson(map);
        }
        map.put("fail","哎呦，修改失败了!");
        return gson.toJson(map);
    }

    /**
     * 根据产品类型查询产品
     * @param tid
     * @return
     */
    @RequestMapping("/query-appoint-api-by-type")
    @ResponseBody
    public String queryAppointApiByTypeId(String tid){
        List<Api> apiList = companyService.queryApiByTypeId(tid);
        Map<Integer,String> map = new HashMap<>();
        if (apiList != null){
            for (int i = 0; i < apiList.size() ; i++) {
                Api api = apiList.get(i);
                if (api != null){
                    map.put(api.getId(),api.getName());
                }
            }
        }
        return new Gson().toJson(map);
    }

    /**
     * 根据账号Id查询Ip
     * @param companyId
     * @return
     */
    @RequestMapping("/customer/find-ip")
    @ResponseBody
    public String queryCustomerIpById(Integer companyId) {
        Gson gson = new Gson();
        List<CustomerIp> customerIpList = companyService.queryCustomerIpById(companyId);
        return gson.toJson(customerIpList);
    }

    /**
     * 根据Id删除Ip
     * @param id
     * @return
     */
    @RequestMapping("/customer/delete-ip")
    @ResponseBody
    @RolePermission
    public String deleteIpById(Integer companyId,Integer id) {
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        int code = 0;
        try {
            code = companyService.deleteIpById(companyId, id);
        }catch (Exception e){
             e.printStackTrace();
        }
        if (200 == code){
            map.put("success","操作成功！");
            return gson.toJson(map);
        }
        map.put("fail","操作失败！");
        return gson.toJson(map);
    }

    /**
     * 给账号添加IP
     * @param companyId
     * @return
     */
    @RequestMapping("/customer/add/ip")
    @ResponseBody
    @RolePermission
    public String addCustomerIp(Integer companyId,String beginIp,String endIp){
        Map<String,Object> map = new HashMap<>();
        Gson gson = new Gson();
        int result = 0;
        try {
            result = companyService.addCustomerIp(companyId,beginIp,endIp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (200 == result){
            map.put("success","操作成功");
            return gson.toJson(map);
        }
        map.put("fail","添加失败，请检查你的操作");
        return gson.toJson(map);
    }

    /**
     * 查询全部的产品类型--用于给新增公司绑定产品权限
     * JSONArray.fromObject().toString(); int类型null会序列化为0，String类型的null值会序列化为“”；
     * new Gson().toJson();null值不会序列化
     * @return
     */
    @RequestMapping("/find-all-api-type")
    @ResponseBody
    public String findAllApiType(HttpServletRequest request){
        Gson gson = new Gson();
        String add_api_type_sub [] = request.getParameterValues("add_api_type_sub[]");
        List<ApiTypeSubType> apiTypeSubTypeList = companyService.queryAllApi();
        System.out.println(gson.toJson(add_api_type_sub));
        System.out.println(gson.toJson(apiTypeSubTypeList));
        List<ApiTypeSubType> apiTypeSubTypeListTran = new ArrayList<>();
        if (add_api_type_sub != null && add_api_type_sub.length >0){
            if (apiTypeSubTypeList != null){
                for (int i = 0 ; i < apiTypeSubTypeList.size(); i++ ){
                    ApiTypeSubType apiTypeSubType = apiTypeSubTypeList.get(i);
                    for (int j = 0; j < add_api_type_sub.length; j++){

                        if (apiTypeSubType.getMobileOperatorId() == null){
                            if (RegexUtil.isPoint(add_api_type_sub[j])){
                                if (apiTypeSubType.getApiTypeId() == Integer.parseInt(add_api_type_sub[j])){
                                    apiTypeSubTypeListTran.add(apiTypeSubType);
                                    break;
                                }
                            }
                        }else {
                            if (!RegexUtil.isPoint(add_api_type_sub[j])){
                                String api_type_sub [] = add_api_type_sub[j].split("-");
                                if (apiTypeSubType.getApiTypeId() == Integer.parseInt(api_type_sub[0]) && apiTypeSubType.getMobileOperatorId() == Integer.parseInt(api_type_sub[1])){
                                    apiTypeSubTypeListTran.add(apiTypeSubType);
                                    break;
                                }
                            }
                        }
                    }

                }
                for (int j = 0; j<apiTypeSubTypeListTran.size(); j++){
                    if (apiTypeSubTypeList.contains(apiTypeSubTypeListTran.get(j))){
                        apiTypeSubTypeList.remove(apiTypeSubTypeListTran.get(j));
                    }
                }
                return gson.toJson(apiTypeSubTypeList);
            }
        }
        return gson.toJson(apiTypeSubTypeList);
    }


    /**
     * 修改信用额度
     * @param companyId
     * @param credit
     * @return
     */
    @RequestMapping("/customer/update-credit")
    @ResponseBody
    public String updateCredit(Integer companyId,Integer credit){
        System.out.println(companyId);
        System.out.println(credit);
        Map<String,Object> map = new HashMap<>();
        Gson gson = new Gson();
        int code = 0;
        try {
            code = companyService.updateCredit(companyId,credit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (200 == code){
            map.put("success","操作成功");
            return gson.toJson(map);
        }
        map.put("fail","修改失败，请检查你的操作");
        return gson.toJson(map);
    }


}
