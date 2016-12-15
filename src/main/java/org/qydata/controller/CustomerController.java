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


    //common
    @RequestMapping(value = ("/addCustomerViewCommon"))
    public String addCustomerViewCommon(Model model,HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("userInfo");
        List<Dept> deptList = user.getDept();
        model.addAttribute("deptList",deptList);
        return "customer/addCustomerCommon";
    }

    //super
    @RequestMapping(value = ("/addCustomerViewSuper"))
    public String addCustomerViewSuper(Model model,HttpServletRequest request){
        List<Dept> deptList = null;
        try {
            deptList = deptService.findAllDept();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("deptList",deptList);
        return "customer/addCustomerSuper";
    }



    @RequestMapping(value = ("/insertCustomerCommon"))
    public String insertCustomerByDeptNo(String name, String authId, String deptId, RedirectAttributes model){

        if(RegexUtil.isNull(name)){
            model.addFlashAttribute("CustomerMessageName","请输入公司名称!");
            return "redirect:/customer/addCustomerViewCommon";
        }
        if(RegexUtil.isNull(authId)){
            model.addFlashAttribute("name",name);
            model.addFlashAttribute("CustomerMessageAuthId","请输入账户!");
            return "redirect:/customer/addCustomerViewCommon";
        }
        if(!RegexUtil.stringCheck(authId)){
            model.addFlashAttribute("name",name);
            model.addFlashAttribute("authId");
            model.addFlashAttribute("CustomerMessageAuthId","账户格式输入不正确!");
            return "redirect:/customer/addCustomerViewCommon";
        }
        if(!RegexUtil.isDigits(deptId)){
            return "redirect:/customer/addCustomerViewCommon";
        }


        try{
            boolean flag = customerService.insertCustomer(name,authId,deptId);
            if (!flag) {
                model.addFlashAttribute("msg", "添加失败");
                return "redirect:/customer/addCustomerViewCommon";
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("msg", "添加失败！");
            return "redirect:/customer/addCustomerViewCommon";
        }
        return "redirect:/customer/findAllCustomerByDeptNo";
    }



    @RequestMapping(value = ("/insertCustomerSuper"))
    public String insertCustomer(String name, String authId, String deptId, RedirectAttributes model){
        if(RegexUtil.isNull(name)){
            model.addFlashAttribute("CustomerMessageName","请输入公司名称!");
            return "redirect:/customer/addCustomerViewSuper";
        }
        if(RegexUtil.isNull(authId)){
            model.addFlashAttribute("name",name);
            model.addFlashAttribute("CustomerMessageAuthId","请输入账户!");
            return "redirect:/customer/addCustomerViewSuper";
        }
        if(!RegexUtil.stringCheck(authId)){
            model.addFlashAttribute("name",name);
            model.addFlashAttribute("authId");
            model.addFlashAttribute("CustomerMessageAuthId","账户格式输入不正确!");
            return "redirect:/customer/addCustomerViewSuper";
        }
        if(!RegexUtil.isDigits(deptId)){
            return "redirect:/customer/addCustomerViewSuper";
        }

        try{
            boolean flag = customerService.insertCustomer(name,authId,deptId);
            if (!flag) {
                model.addFlashAttribute("msg", "添加失败");
                return "redirect:/customer/addCustomerViewSuper";
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("msg", "添加失败！");
            return "redirect:/customer/addCustomerViewSuper";
        }
        return "redirect:/customer/findAllCustomer";
    }



    //查找客户
    @RequestMapping(value = ("/findAllCustomer"))
    public String findAllCustomer(HttpServletRequest request,Model model,String content){

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
        PageModel<Customer> pageModelOne = customerService.findAllCustomer(map);
        model.addAttribute("content",content);
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
        return "/customer/customerList";
    }



    //通过部门编号查找客户
    @RequestMapping(value = ("/findAllCustomerByDeptNo"))
    public String findAllCustomerByDeptNo(HttpServletRequest request,Model model,String content){
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
        PageModel<Customer> pageModelOne = customerService.findAllCustomer(map);
        model.addAttribute("deptIdList",deptIdList);
        model.addAttribute("content",content);
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
        return "/customer/customerList";
    }

}
