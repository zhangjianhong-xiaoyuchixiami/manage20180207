package org.qydata.controller;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.qydata.entity.Dept;
import org.qydata.entity.User;
import org.qydata.service.DeptService;
import org.qydata.service.UserService;
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
 * Created by Administrator on 2016/12/3.
 */
@Controller
@RequestMapping(value = "/dept")
public class DeptController {

    private final Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private DeptService deptService;
    @Autowired
    private UserService userService;


    @RequestMapping( value = "/addDeptView")
    public String addView(){
        return "/dept/addDept";
    }

    @RequestMapping( value = "/addDeptAction")
    public String addAction(Dept dept, RedirectAttributes model){
        try {
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
    @RequestMapping(value = "/allotDeptView/{username}")
    public String allotDeptView(@PathVariable String username, Model model){
        List<Dept> deptList = null;
        List<Integer> userDeptNoList = new ArrayList();
        User user = null;
        try {
            deptList = deptService.findAllDept();
            user = userService.findUserByUsername(username);
            List<Dept> list = user.getDept();
            for(int i=0;i< list.size();i++){
                userDeptNoList.add(list.get(i).getDeptNo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userDeptNoList",userDeptNoList);
        model.addAttribute("deptList",deptList);
        model.addAttribute("userId",user.getId());
        model.addAttribute("username",user.getUsername());
        return "/dept/allotDept";
    }

    @RequestMapping(value = "/allotDeptAction")
    @ResponseBody
    public String allotDeptAction(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String username = request.getParameter("username");
        String [] deptNo = request.getParameterValues("deptNo[]");
        Gson gson = new Gson();
        Map<String,String> map = new HashMap<>();
        try {
            boolean flag = deptService.insertUserDept(userId,deptNo);
            if (flag){
                map.put("result","ok");
                map.put("msg",username);
            }else {
                map.put("result","fail");
                map.put("msg",username);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result","no");
            map.put("msg",username);
        }
        return gson.toJson(map);
    }


    @RequestMapping(value = "/demo")
    public String demo(){
        return "/customer/demo";
    }
}