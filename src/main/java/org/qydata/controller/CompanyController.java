package org.qydata.controller;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import org.qydata.dst.CustomerCompanyPartner;
import org.qydata.entity.CustomerBalanceModifyReason;
import org.qydata.entity.Dept;
import org.qydata.entity.Partner;
import org.qydata.entity.User;
import org.qydata.regex.RegexUtil;
import org.qydata.service.CompanyService;
import org.qydata.service.CustomerApiService;
import org.qydata.service.DeptService;
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
        Map<String,Object> map = new HashMap<String,Object>();
        if(content!=null){
            map.put("content",content);
        }
        List<CustomerCompanyPartner> customerCompanyPartnerList = companyService.findAllCompany(map);
        List<Dept> deptList = deptService.findAllDept();
        List<Partner> partnerList = companyService.findAllPartner();
        model.addAttribute("deptList",deptList);
        model.addAttribute("partnerList",partnerList);
        model.addAttribute("content",content);
        model.addAttribute("companyList",customerCompanyPartnerList);
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
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deptIdList", deptIdList);
            if (content != null) {
                map.put("content", content);
            }
            List<CustomerCompanyPartner> customerCompanyPartnerList = companyService.findAllCompanyByDeptId(map);
            List<Dept> deptList = deptService.findDeptByUserId(user.getId());
            List<Partner> partnerList = companyService.findAllPartner();
            model.addAttribute("deptList",deptList);
            model.addAttribute("partnerList",partnerList);
            model.addAttribute("content",content);
            model.addAttribute("companyList",customerCompanyPartnerList);
            return "/company/company";
        }else{
            model.addAttribute("companyList", null);
            return "/company/company";
        }
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
        System.out.println(companyName);
        System.out.println(authId);
        System.out.println(deptId);
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
     * 充值
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
     * 扣费
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


//
//    //给所有账号添加ApiView
//    @RequestMapping(value = "/addCustomerApiView/{companyId}")
//    public String addCustomerApiView(@PathVariable String companyId,Model model){
//        List<Api> apiList = null;
//        try {
//            apiList = companyService.findAllApi(companyId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        model.addAttribute(companyId);
//        model.addAttribute("apiList",apiList);
//        return "/company/addCustomerApi";
//    }
//
//    //给所有账号添加ApiAction
//    @RequestMapping(value = "/addCustomerApiAction")
//    public String addCustomerApiAction(RedirectAttributes model, String companyId, String price, String apiId, String enabled){
//        if(RegexUtil.isNull(price)){
//            model.addFlashAttribute("CustomerMessagePrice","请输入金额");
//            return "redirect:/company/addCustomerApiView/"+companyId;
//        }
//        List priceList = IpTool.spiltStr(price);
//        for(int i=0;i<priceList.size();i++){
//            if(!RegexUtil.isFloatZero((String) priceList.get(i))){
//                model.addFlashAttribute("price",price);
//                model.addFlashAttribute("CustomerMessagePrice","金额格式不正确");
//                return "redirect:/company/addCustomerApiView/"+companyId;
//            }else{
//                if(Integer.parseInt((String) priceList.get(i))<=0){
//                    model.addFlashAttribute("CustomerMessagePrice","金额必须大于0");
//                    return "redirect:/company/addCustomerApiView/"+companyId;
//                }
//            }
//        }
//        if(RegexUtil.isNull(companyId)){
//            return "redirect:/company/addCustomerApiView/"+companyId;
//        }
//        if(RegexUtil.isNull(apiId)){
//            return "redirect:/company/addCustomerApiView/"+companyId;
//        }
//        if(RegexUtil.isNull(enabled)){
//            return "redirect:/company/addCustomerApiView/"+companyId;
//        }
//
//        try {
//            boolean flag = companyService.insertCustomerApi(companyId,price,apiId,enabled);
//            if (!flag) {
//                model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
//                return "redirect:/company/addCustomerApiView/"+companyId;
//            }
//        }catch (Exception e){
//            model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
//            return "redirect:/company/addCustomerApiView/"+companyId;
//        }
//        return "redirect:/company/findAllCustomerApiByCompanyId/"+companyId;
//    }
//
//    //查看所有账号共有的Api
//    @RequestMapping(value = "/findAllCustomerApiByCompanyId/{companyId}")
//    public String findAllCustomerApiByCompanyId(@PathVariable String companyId,Model model, HttpServletRequest request){
//        PageModel pageModel = new PageModel();
//        String pageSize= request.getParameter("pageSize");//当前页
//        String lineSize = "12";//每页显示条数
//        pageModel.setPageSize(pageSize);
//        pageModel.setLineSize(lineSize);
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("beginIndex",pageModel.getBeginIndex());
//        map.put("lineSize",pageModel.getLineSize());
//        map.put("companyId",companyId);
//        PageModel<CustomerApiInfo> pageModelOne = null;
//        try {
//            pageModelOne = companyService.findAllCustomerApiByCompanyId(map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        model.addAttribute("count",pageModelOne.getCount());
//        model.addAttribute("customerApiList",pageModelOne.getList());
//        model.addAttribute("companyId",companyId);
//        Integer totalPage= null;
//        Integer count = pageModelOne.getCount();
//
//        if (count%Integer.parseInt(lineSize) == 0) {
//            totalPage = (count/Integer.parseInt(lineSize));
//        } else {
//            totalPage = (count/Integer.parseInt(lineSize)) + 1;
//        }
//        model.addAttribute("totlePage",totalPage);
//        model.addAttribute("pageSize",pageModel.getPageSize());
//        return "/company/customerApiList";
//    }
//
//    //根据id查找指定的customerApi
//    @RequestMapping(value = "/findCustomerApiById/{apiId}/{companyId}")
//    public String findCustomerApiById(@PathVariable String apiId,@PathVariable String companyId, Model model){
//        List<Api> apiList = null;
//        CustomerApi customerApi = null;
//        try {
//            Map<String,Object> map = new HashMap<>();
//            apiList =customerApiService .findAllApi(map);
//            customerApi = companyService.findById(apiId,companyId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        model.addAttribute("apiList",apiList);
//        model.addAttribute(customerApi);
//
//        return "/company/updateCustomerApi";
//    }
//
//    //根据id修改指定的customerApi
//    @RequestMapping(value = "/updateCustomerApiById")
//    public String updateCustomerApiById(String beforApiId,String companyId,String price,String afterApiId,String enabled,RedirectAttributes model ){
//
//        System.out.println(beforApiId);
//        System.out.println(companyId);
//        System.out.println(price);
//        System.out.println(afterApiId);
//        System.out.println(enabled);
//
//
//        if(RegexUtil.isNull(beforApiId)){
//            return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
//        }
//        if(RegexUtil.isNull(price)){
//            model.addFlashAttribute("CustomerMessagePrice","请输入金额");
//            return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
//        }
//        if(!RegexUtil.isFloatZero(price)){
//            model.addFlashAttribute("CustomerMessagePrice","金额格式不正确");
//            return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
//        }else {
//            if(Integer.parseInt(price)<=0){
//                model.addFlashAttribute("CustomerMessagePrice","金额必须大于0");
//                return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
//            }
//        }
//        if(RegexUtil.isNull(companyId)){
//            return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
//        }
//        if(RegexUtil.isNull(afterApiId)){
//            return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
//        }
//        if(RegexUtil.isNull(enabled)){
//            return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
//        }
//
//
//        try {
//            boolean flag = companyService.updateCustomerApiById(beforApiId,companyId,price,afterApiId,enabled);
//            if (!flag) {
//                model.addFlashAttribute("msg","对不起，修改失败，请检查你的输入！");
//                return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            model.addFlashAttribute("msg","对不起，修改失败，请检查你的输入！");
//            return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
//        }
//        return "redirect:/company/findAllCustomerApiByCompanyId/" + companyId;
//    }


}
