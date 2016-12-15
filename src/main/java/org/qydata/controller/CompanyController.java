package org.qydata.controller;

import org.qydata.entity.*;
import org.qydata.service.CompanyService;
import org.qydata.service.DeptService;
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
 * Created by jonhn on 2016/12/15.
 */
@Controller
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private DeptService deptService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/addCompanyAllDeptView")
    public String addCompanyAllDeptView(Model model){
        List<Dept> deptList = null;
        try {
            deptList = deptService.findAllDept();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("deptList",deptList);
        return "/company/addCompanyAllDept";
    }

    @RequestMapping(value = "/addCompanyOnlyDeptView")
    public String addCompanyOnlyDeptView(HttpServletRequest request,Model model){
        User user = (User)request.getSession().getAttribute("userInfo");
        List<Dept> deptList = user.getDept();
        model.addAttribute("deptList",deptList);
        return "/company/addCompanyOnlyDept";
    }

    @RequestMapping(value = "/findAllByDeptIdAction")
    public String findAllByDeptIdAction(HttpServletRequest request,String content,Model model){
        User user = (User) request.getSession().getAttribute("userInfo");
        List<Dept> deptList = user.getDept();
        List deptIdList = new ArrayList();
        for(int i =0;i<deptList.size();i++){
            deptIdList.add(deptList.get(i).getId());
        }
        PageModel pageModel = new PageModel();
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
        PageModel<Company> pageModelOne = null;
        try {
            pageModelOne = companyService.findAllCompany(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("deptIdList",deptIdList);
        model.addAttribute("content",content);
        model.addAttribute("count",pageModelOne.getCount());
        model.addAttribute("companyList",pageModelOne.getList());
        Integer totalPage= null;
        Integer count = pageModelOne.getCount();

        if (count%Integer.parseInt(lineSize) == 0) {
            totalPage = (count/Integer.parseInt(lineSize));
        } else {
            totalPage = (count/Integer.parseInt(lineSize)) + 1;
        }
        model.addAttribute("totlePage",totalPage);
        model.addAttribute("pageSize",pageModel.getPageSize());
        return "/company/companyList";
    }

    @RequestMapping(value = "/findAllAction")
    public String findAllAction(HttpServletRequest request,String content,Model model){
        PageModel pageModel = new PageModel();
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
        PageModel<Company> pageModelOne = null;
        try {
            pageModelOne = companyService.findAllCompany(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("content",content);
        model.addAttribute("count",pageModelOne.getCount());
        model.addAttribute("companyList",pageModelOne.getList());
        Integer totalPage= null;
        Integer count = pageModelOne.getCount();

        if (count%Integer.parseInt(lineSize) == 0) {
            totalPage = (count/Integer.parseInt(lineSize));
        } else {
            totalPage = (count/Integer.parseInt(lineSize)) + 1;
        }
        model.addAttribute("totlePage",totalPage);
        model.addAttribute("pageSize",pageModel.getPageSize());
        return "/company/companyList";
    }

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

    @RequestMapping(value = "/addCustomerApiAction")
    public String addCustomerApiAction(RedirectAttributes model, String companyId, String price, String apiId, String enabled){

        return null;
    }

    @RequestMapping(value = "/findAllCustomerApiByCompanyId/{companyId}")
    public String findAllCustomerApiByCompanyId(@PathVariable String companyId,Model model, HttpServletRequest request){
        PageModel pageModel = new PageModel();
        String pageSize= request.getParameter("pageSize");//当前页
        String lineSize = "8";//每页显示条数
        pageModel.setPageSize(pageSize);
        pageModel.setLineSize(lineSize);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("beginIndex",pageModel.getBeginIndex());
        map.put("lineSize",pageModel.getLineSize());
        map.put("companyId",companyId);
        PageModel<CustomerApi> pageModelOne = null;
        try {
            pageModelOne = companyService.findAllCustomerApiByCompanyId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("count",pageModelOne.getCount());
        model.addAttribute("customerApiList",pageModelOne.getList());
        Integer totalPage= null;
        Integer count = pageModelOne.getCount();

        if (count%Integer.parseInt(lineSize) == 0) {
            totalPage = (count/Integer.parseInt(lineSize));
        } else {
            totalPage = (count/Integer.parseInt(lineSize)) + 1;
        }
        model.addAttribute("totlePage",totalPage);
        model.addAttribute("pageSize",pageModel.getPageSize());
        return "/customerApi/customerApiList";
    }


}
