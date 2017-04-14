package org.qydata.controller;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.qydata.entity.Dept;
import org.qydata.entity.User;
import org.qydata.service.DeptService;
import org.qydata.service.PowerUserService;
import org.qydata.service.RoleService;
import org.qydata.tools.PageModel;
import org.qydata.tools.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/3.
 */
@Controller
@RequestMapping(value = "/dept")
public class DeptController {

    private final Logger log = Logger.getLogger(this.getClass());
    @Autowired private DeptService deptService;
    @Autowired private RoleService roleService;
    @Autowired private PowerUserService powerUserService;

    @RequestMapping( value = "/addDeptView")
    public String addView(){
        return "/dept/addDept";
    }

    @RequestMapping( value = "/addDeptAction")
    public String addAction(String deptName, RedirectAttributes model){

        if(RegexUtil.isNull(deptName)){
            model.addFlashAttribute("DeptMessageDeptName","请输入部门名称");
            return "redirect:/dept/addDeptView";
        }

        try {
            Dept dept = new Dept();
            dept.setDeptName(deptName);
            boolean flag = deptService.addDept(dept);
            if(!flag){
                model.addFlashAttribute("msg", "添加失败");
                return "redirect:/dept/addDeptView";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("addAction:新增部门异常");
            model.addFlashAttribute("msg", "添加失败");
            return "redirect:/dept/addDeptView";
        }
        return "redirect:/dept/findAllDept";
    }

    @RequestMapping(value = "/findAllDept")
    public String findAllDept(HttpServletRequest request,Model model ){
        PageModel pageModel = new PageModel();
        Map<String,Object> map = new HashMap<String,Object>();
        String pageSize = request.getParameter("pageSize");//当前页
        String lineSize = "8";
        pageModel.setPageSize(pageSize);
        pageModel.setLineSize(lineSize);
        map.put("beginIndex",pageModel.getBeginIndex());
        map.put("lineSize",pageModel.getLineSize());
        PageModel pageModelOne = new PageModel();
        try {
            pageModelOne = deptService.findAll(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("count",pageModelOne.getCount());
        model.addAttribute("deptList",pageModelOne.getList());
        Integer totalPage= null;
        Integer count = pageModelOne.getCount();
        if (count%Integer.parseInt(lineSize) == 0) {
            totalPage = (count/Integer.parseInt(lineSize));
        } else {
            totalPage = (count/Integer.parseInt(lineSize)) + 1;
        }
        model.addAttribute("totlePage",totalPage);
        model.addAttribute("pageSize",pageModel.getPageSize());
        return "/dept/deptList";
    }
    //分配部门
    @RequestMapping(value = "/allotDeptView")
    public String allotDeptView(Integer userId, Model model){
        List<Dept> deptList = null;
        List<Integer> userDeptIdList = new ArrayList();
        User user = null;
        try {
            deptList = deptService.findAllDept();
            user = powerUserService.findUserByUsername(userId);
            List<Dept> list = user.getDept();
            for(int i=0;i< list.size();i++){
                userDeptIdList.add(list.get(i).getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userDeptIdList",userDeptIdList);
        model.addAttribute("deptList",deptList);
        model.addAttribute("userId",user.getId());
        return "/dept/allotDept";
    }

    @RequestMapping(value = "/allotDeptAction")
    @ResponseBody
    public String allotDeptAction(HttpServletRequest request,Integer userId){
        String [] deptId = request.getParameterValues("deptId[]");
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        try {
            if (deptService.insertUserDept(userId,deptId)){
                map.put("result","ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gson.toJson(map);
    }

}