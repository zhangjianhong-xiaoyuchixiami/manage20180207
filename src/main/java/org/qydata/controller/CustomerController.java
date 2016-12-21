package org.qydata.controller;

import org.apache.log4j.Logger;
import org.qydata.entity.Customer;
import org.qydata.entity.Dept;
import org.qydata.entity.User;
import org.qydata.regex.RegexUtil;
import org.qydata.service.CustomerService;
import org.qydata.service.DeptService;
import org.qydata.tools.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/11/8.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DeptService deptService;

    /**
     * 添加新客户时验证账户名是否已存在
     * @param authId
     * @param response
     */
    @RequestMapping(value = "/findCustomerByAuthId/{authId}")
    public void findCustomerByAuthIdAdd(@PathVariable("authId") String authId,HttpServletResponse response){
        Customer customer = customerService.findByAuthId(authId);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if(customer!=null){
                out.print("yes");
            }else{
                out.print("no");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //添加账号View
    @RequestMapping(value = "/addCustomerAccountView/{companyId}")
    public String addCustomerAccountView(@PathVariable String companyId,Model model){
        model.addAttribute("companyId",companyId);
        return "customer/addCustomerAccount";
    }

    //添加账号Action
    @RequestMapping(value = "/addCustomerAccountAction")
    public String addCustomerAccountAction(String companyId,String authId,RedirectAttributes model){

        if(RegexUtil.isNull(companyId)){
            return "redirect:/customer/addCustomerAccountView/"+companyId;
        }
        if(RegexUtil.isNull(authId)){
            model.addFlashAttribute("CustomerMessageAuthId","请输入账户!");
            return "redirect:/customer/addCustomerAccountView/"+companyId;
        }
        if(!RegexUtil.stringCheck(authId)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("CustomerMessageAuthId","账户格式输入不正确!");
            return "redirect:/customer/addCustomerAccountView/"+companyId;
        }
        try{
            boolean flag = customerService.insertCustomerAccount(companyId,authId);
            if (!flag) {
                model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
                return "redirect:/customer/addCustomerViewCommon";
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
            return "redirect:/customer/addCustomerViewCommon";
        }
        return "redirect:/company/findAllCustomerAccountByCompanyId/"+companyId;

    }

    //查找公司账号
    @RequestMapping(value = "/findAllCustomer")
    public String findAllCustomer(HttpServletRequest request,Model model,String content,String customerTypeId1,String customerTypeId2){

        PageModel<Customer> pageModel = new PageModel();
        String pageSize= request.getParameter("pageSize");//当前页
        String lineSize = "8";//每页显示条数
        pageModel.setPageSize(pageSize);
        pageModel.setLineSize(lineSize);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("beginIndex",pageModel.getBeginIndex());
        map.put("lineSize",pageModel.getLineSize());
        if(content!=null){
            map.put("content",content);
        }
        List customerTypeIdList = new ArrayList();
        if(customerTypeId1 != null && customerTypeId1 != ""){
            customerTypeIdList.add(Integer.parseInt(customerTypeId1));
        }
        if(customerTypeId2 != null && customerTypeId2 != ""){
            customerTypeIdList.add(Integer.parseInt(customerTypeId2));
        }
        map.put("customerTypeIdList",customerTypeIdList);
        PageModel<Customer> pageModelOne = null;
        try {
            pageModelOne = customerService.findAllCustomer(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("content",content);
        model.addAttribute("customerTypeId1",customerTypeId1);
        model.addAttribute("customerTypeId2",customerTypeId2);
        model.addAttribute("count",pageModelOne.getCount());
        model.addAttribute("customerList",pageModelOne.getList());
        Integer totalPage= null;
        Integer count = pageModelOne.getCount();

        if (count%Integer.parseInt(lineSize) == 0) {
            totalPage = (count/Integer.parseInt(lineSize));
        } else {
            totalPage = (count/Integer.parseInt(lineSize)) + 1;
        }
        model.addAttribute("totlePage",totalPage);
        model.addAttribute("pageSize",pageModel.getPageSize());
        return "/customerBalanceLog/customerList";
    }

    //通过部门编号查找公司账号
    @RequestMapping(value = ("/findAllCustomerByDeptNo"))
    public String findAllCustomerByDeptNo(HttpServletRequest request,Model model,String content,String customerTypeId1,String customerTypeId2){
        User user = (User)request.getSession().getAttribute("userInfo");
        List<Dept> deptList = user.getDept();
        List deptIdList = new ArrayList();
        for(int i =0;i<deptList.size();i++){
            deptIdList.add(deptList.get(i).getId());
        }
        PageModel<Customer> pageModel = new PageModel();
        String pageSize= request.getParameter("pageSize");//当前页
        String lineSize = "8";//每页显示条数
        pageModel.setPageSize(pageSize);
        pageModel.setLineSize(lineSize);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("beginIndex",pageModel.getBeginIndex());
        map.put("lineSize",pageModel.getLineSize());
        map.put("deptIdList",deptIdList);
        if(content!=null){
            map.put("content",content);
        }
        List customerTypeIdList = new ArrayList();
        if(customerTypeId1 != null && customerTypeId1 != ""){
            customerTypeIdList.add(Integer.parseInt(customerTypeId1));
        }
        if(customerTypeId2 != null && customerTypeId2 != ""){
            customerTypeIdList.add(Integer.parseInt(customerTypeId2));
        }
        map.put("customerTypeIdList",customerTypeIdList);
        PageModel<Customer> pageModelOne = null;
        try {
            pageModelOne = customerService.findAllCustomer(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("deptIdList",deptIdList);
        model.addAttribute("content",content);
        model.addAttribute("customerTypeId1",customerTypeId1);
        model.addAttribute("customerTypeId2",customerTypeId2);
        model.addAttribute("count",pageModelOne.getCount());
        model.addAttribute("customerList",pageModelOne.getList());
        Integer totalPage= null;
        Integer count = pageModelOne.getCount();

        if (count%Integer.parseInt(lineSize) == 0) {
            totalPage = (count/Integer.parseInt(lineSize));
        } else {
            totalPage = (count/Integer.parseInt(lineSize)) + 1;
        }
        model.addAttribute("totlePage",totalPage);
        model.addAttribute("pageSize",pageModel.getPageSize());
        return "/customerBalanceLog/customerList";
    }

    //通过authId查找账号详细信息
    @RequestMapping(value = "/findCustomerDetailInfo/{authId}")
    public String findCustomerDetailInfo(@PathVariable String authId,Model model){
        Customer customer = customerService.findByAuthId(authId);
        model.addAttribute("customer",customer);
        return "/customerBalanceLog/detailCustomerInfo";
    }



    //（暂时没用到）
    @RequestMapping(value = ("/addCustomerOnlyDeptView/{companyId}"))
    public String addCustomerViewCommon(Model model,HttpServletRequest request,@PathVariable String companyId){
        User user = (User)request.getSession().getAttribute("userInfo");
        List<Dept> deptList = user.getDept();
        model.addAttribute("companyId",companyId);
        model.addAttribute("deptList",deptList);
        return "customer/addCustomerOnlyDept";
    }

    //（暂时没用到）
    @RequestMapping(value = ("/addCustomerAllDeptView/{companyId}"))
    public String addCustomerViewSuper(Model model,HttpServletRequest request,@PathVariable String companyId){
        List<Dept> deptList = null;
        try {
            deptList = deptService.findAllDept();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("companyId",companyId);
        model.addAttribute("deptList",deptList);
        return "customer/addCustomerAllDept";
    }

    //（暂时没用到）
    @RequestMapping(value = ("/addCustomerOnlyDeptAction"))
    public String insertCustomerByDeptNo(String companyId, String authId, String deptId, RedirectAttributes model){

        if(RegexUtil.isNull(companyId)){
            return "redirect:/customer/addCustomerOnlyDeptView/"+companyId;
        }
        if(RegexUtil.isNull(authId)){
            model.addFlashAttribute("CustomerMessageAuthId","请输入账户!");
            return "redirect:/customer/addCustomerOnlyDeptView/"+companyId;
        }
        if(!RegexUtil.stringCheck(authId)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("CustomerMessageAuthId","账户格式输入不正确!");
            return "redirect:/customer/addCustomerOnlyDeptView/"+companyId;
        }
        if(RegexUtil.isNull(deptId)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("CustomerMessageDeptId","请选择所属部门!");
            return "redirect:/customer/addCustomerOnlyDeptView/"+companyId;
        }
        try{
            boolean flag = customerService.insertCustomer(companyId,authId,deptId);
            if (!flag) {
                model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
                return "redirect:/customer/addCustomerViewCommon";
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
            return "redirect:/customer/addCustomerViewCommon";
        }
        return "redirect:/company/findAllCustomerAccountByCompanyId/"+companyId;
    }

    //（暂时没用到）
    @RequestMapping(value = ("/addCustomerAllDeptAction"))
    public String insertCustomer(String companyId, String authId, String deptId, RedirectAttributes model){
        if(RegexUtil.isNull(companyId)){
            return "redirect:/customer/addCustomerAllDeptView/"+companyId;
        }
        if(RegexUtil.isNull(authId)){
            model.addFlashAttribute("CustomerMessageAuthId","请输入账户!");
            return "redirect:/customer/addCustomerAllDeptView/"+companyId;
        }
        if(!RegexUtil.stringCheck(authId)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("CustomerMessageAuthId","账户格式输入不正确!");
            return "redirect:/customer/addCustomerAllDeptView/"+companyId;
        }
        if(RegexUtil.isNull(deptId)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("CustomerMessageDeptId","请选择所属部门!");
            return "redirect:/customer/addCustomerAllDeptView/"+companyId;
        }

        try{
            boolean flag = customerService.insertCustomer(companyId,authId,deptId);
            if (!flag) {
                model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
                return "redirect:/customer/addCustomerViewSuper";
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
            return "redirect:/customer/addCustomerViewSuper";
        }
        return "redirect:/company/findAllCustomerAccountByCompanyId/"+companyId;
    }


}
