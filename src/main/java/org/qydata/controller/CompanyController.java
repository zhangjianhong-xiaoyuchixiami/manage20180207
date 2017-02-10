package org.qydata.controller;

import com.google.gson.Gson;
import org.qydata.dst.CustomerApiInfo;
import org.qydata.dst.CustomerCompanyPartner;
import org.qydata.entity.*;
import org.qydata.regex.RegexUtil;
import org.qydata.service.CompanyService;
import org.qydata.service.CustomerApiService;
import org.qydata.service.DeptService;
import org.qydata.tools.IpTool;
import org.qydata.tools.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
     * @param request
     * @param content
     * @param model
     * @return
     */
    @RequestMapping(value = "/find-all-company-customer")
    public String findAllCompanyCustomer(HttpServletRequest request,String content,Model model){
        Map<String,Object> map = new HashMap<String,Object>();
        if(content!=null){
            map.put("content",content);
        }
        List<CustomerCompanyPartner> customerCompanyPartnerList = companyService.findAllCompany(map);
        List<Dept> deptList = deptService.findAllDept();
        model.addAttribute("deptList",deptList);
        model.addAttribute("content",content);
        model.addAttribute("companyList",customerCompanyPartnerList);
        return "/company/company";
    }

    /**
     * 通过部门编号查找客户
     * @param request
     * @param content
     * @param model
     * @return
     */
    @RequestMapping(value = "/find-all-company-customer-by-dept-id")
    public String findAllCompanyCustomerByDeptId(HttpServletRequest request,String content,Model model){
        User user = (User) request.getSession().getAttribute("userInfo");
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
            List<CustomerCompanyPartner> customerCompanyPartnerList = companyService.findAllCompanyByDeptId(map);
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
    public String addCompanyCustomer(String companyName,String authId,String deptId){
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
        boolean flag = companyService.addCompanyCustomer(companyName,authId,deptId);
        if (flag){
            map.put("successMessage","恭喜你，操作成功！");
        }else {
            map.put("errorMessage","操作失败，请检查你的输入");
        }
        return gson.toJson(map);
    }


    //给所有账号添加ApiView
    @RequestMapping(value = "/addCustomerApiView/{companyId}")
    public String addCustomerApiView(@PathVariable String companyId,Model model){
        List<Api> apiList = null;
        try {
            apiList = companyService.findAllApi(companyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute(companyId);
        model.addAttribute("apiList",apiList);
        return "/company/addCustomerApi";
    }

    //给所有账号添加ApiAction
    @RequestMapping(value = "/addCustomerApiAction")
    public String addCustomerApiAction(RedirectAttributes model, String companyId, String price, String apiId, String enabled){
        if(RegexUtil.isNull(price)){
            model.addFlashAttribute("CustomerMessagePrice","请输入金额");
            return "redirect:/company/addCustomerApiView/"+companyId;
        }
        List priceList = IpTool.spiltStr(price);
        for(int i=0;i<priceList.size();i++){
            if(!RegexUtil.isFloatZero((String) priceList.get(i))){
                model.addFlashAttribute("price",price);
                model.addFlashAttribute("CustomerMessagePrice","金额格式不正确");
                return "redirect:/company/addCustomerApiView/"+companyId;
            }else{
                if(Integer.parseInt((String) priceList.get(i))<=0){
                    model.addFlashAttribute("CustomerMessagePrice","金额必须大于0");
                    return "redirect:/company/addCustomerApiView/"+companyId;
                }
            }
        }
        if(RegexUtil.isNull(companyId)){
            return "redirect:/company/addCustomerApiView/"+companyId;
        }
        if(RegexUtil.isNull(apiId)){
            return "redirect:/company/addCustomerApiView/"+companyId;
        }
        if(RegexUtil.isNull(enabled)){
            return "redirect:/company/addCustomerApiView/"+companyId;
        }

        try {
            boolean flag = companyService.insertCustomerApi(companyId,price,apiId,enabled);
            if (!flag) {
                model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
                return "redirect:/company/addCustomerApiView/"+companyId;
            }
        }catch (Exception e){
            model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
            return "redirect:/company/addCustomerApiView/"+companyId;
        }
        return "redirect:/company/findAllCustomerApiByCompanyId/"+companyId;
    }

    //查看所有账号共有的Api
    @RequestMapping(value = "/findAllCustomerApiByCompanyId/{companyId}")
    public String findAllCustomerApiByCompanyId(@PathVariable String companyId,Model model, HttpServletRequest request){
        PageModel pageModel = new PageModel();
        String pageSize= request.getParameter("pageSize");//当前页
        String lineSize = "12";//每页显示条数
        pageModel.setPageSize(pageSize);
        pageModel.setLineSize(lineSize);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("beginIndex",pageModel.getBeginIndex());
        map.put("lineSize",pageModel.getLineSize());
        map.put("companyId",companyId);
        PageModel<CustomerApiInfo> pageModelOne = null;
        try {
            pageModelOne = companyService.findAllCustomerApiByCompanyId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("count",pageModelOne.getCount());
        model.addAttribute("customerApiList",pageModelOne.getList());
        model.addAttribute("companyId",companyId);
        Integer totalPage= null;
        Integer count = pageModelOne.getCount();

        if (count%Integer.parseInt(lineSize) == 0) {
            totalPage = (count/Integer.parseInt(lineSize));
        } else {
            totalPage = (count/Integer.parseInt(lineSize)) + 1;
        }
        model.addAttribute("totlePage",totalPage);
        model.addAttribute("pageSize",pageModel.getPageSize());
        return "/company/customerApiList";
    }

    //根据id查找指定的customerApi
    @RequestMapping(value = "/findCustomerApiById/{apiId}/{companyId}")
    public String findCustomerApiById(@PathVariable String apiId,@PathVariable String companyId, Model model){
        List<Api> apiList = null;
        CustomerApi customerApi = null;
        try {
            Map<String,Object> map = new HashMap<>();
            apiList =customerApiService .findAllApi(map);
            customerApi = companyService.findById(apiId,companyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("apiList",apiList);
        model.addAttribute(customerApi);

        return "/company/updateCustomerApi";
    }

    //根据id修改指定的customerApi
    @RequestMapping(value = "/updateCustomerApiById")
    public String updateCustomerApiById(String beforApiId,String companyId,String price,String afterApiId,String enabled,RedirectAttributes model ){

        System.out.println(beforApiId);
        System.out.println(companyId);
        System.out.println(price);
        System.out.println(afterApiId);
        System.out.println(enabled);


        if(RegexUtil.isNull(beforApiId)){
            return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
        }
        if(RegexUtil.isNull(price)){
            model.addFlashAttribute("CustomerMessagePrice","请输入金额");
            return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
        }
        if(!RegexUtil.isFloatZero(price)){
            model.addFlashAttribute("CustomerMessagePrice","金额格式不正确");
            return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
        }else {
            if(Integer.parseInt(price)<=0){
                model.addFlashAttribute("CustomerMessagePrice","金额必须大于0");
                return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
            }
        }
        if(RegexUtil.isNull(companyId)){
            return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
        }
        if(RegexUtil.isNull(afterApiId)){
            return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
        }
        if(RegexUtil.isNull(enabled)){
            return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
        }


        try {
            boolean flag = companyService.updateCustomerApiById(beforApiId,companyId,price,afterApiId,enabled);
            if (!flag) {
                model.addFlashAttribute("msg","对不起，修改失败，请检查你的输入！");
                return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("msg","对不起，修改失败，请检查你的输入！");
            return "redirect:/company/findCustomerApiById/"+beforApiId+"/"+companyId;
        }
        return "redirect:/company/findAllCustomerApiByCompanyId/" + companyId;
    }


}
