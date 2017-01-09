package org.qydata.controller;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.qydata.entity.Customer;
import org.qydata.entity.Dept;
import org.qydata.entity.User;
import org.qydata.regex.RegexUtil;
import org.qydata.service.CustomerService;
import org.qydata.service.DeptService;
import org.qydata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

    @Autowired
    private UserService userService;

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
    @RequestMapping("/list1")
    public String list1(){
        return "/customer/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request){
        String aoData = request.getParameter("aoData");
        JSONArray jsonarray = JSONArray.fromObject(aoData);
        System.out.println(aoData);
        String sEcho = null;
        int iDisplayStart = 0; // 起始索引
        int iDisplayLength = 0; // 每页显示的行数
        int iColumns = 4;
        String sColumns = "";
        int mDataProp_0 = 0;
        int mDataProp_1 = 1;
        int mDataProp_2 = 2;
        int mDataProp_3 = 3;
        int iSortCol_0 = 0;
        String sSortDir_0 = "asc";
        int iSortingCols = 1;
        boolean bSortable_0 = true;
        boolean bSortable_1 = true;
        boolean bSortable_2 = true;
        boolean bSortable_3 = true;
        for (int i = 0; i < jsonarray.size(); i++) {
            JSONObject obj = (JSONObject) jsonarray.get(i);
            if (obj.get("name").equals("sEcho"))
                sEcho = obj.get("value").toString();

            if (obj.get("name").equals("iDisplayStart"))
                iDisplayStart = obj.getInt("value");

            if (obj.get("name").equals("iDisplayLength"))
                iDisplayLength = obj.getInt("value");
        }
        System.out.println(sEcho);
        System.out.println(iDisplayStart);
        System.out.println(iDisplayLength);
        List<User> userList = userService.findAllUser(iDisplayStart,iDisplayLength);
        JSONArray jsonArray =JSONArray.fromObject(userList);
        System.out.println(jsonArray.toString());
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
        getObj.put("iTotalRecords", 27);//实际的行数
        getObj.put("iTotalDisplayRecords", 27);//显示的行数,这个要和上面写的一样
        getObj.put("aaData",jsonArray);//要以JSON格式返回
        return getObj.toString();
    }


}
