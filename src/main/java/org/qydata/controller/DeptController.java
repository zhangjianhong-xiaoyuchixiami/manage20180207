package org.qydata.controller;

import org.apache.log4j.Logger;
import org.qydata.entity.Dept;
import org.qydata.entity.User;
import org.qydata.service.DeptService;
import org.qydata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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


    @RequestMapping( value = "/addView")
    public String addView(){
        return "";
    }
    @RequestMapping( value = "/addAction")
    public String addAction(Dept dept){
        try {
            deptService.addDept(dept);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("");
        }
        return "";
    }
    //分配部门
    @RequestMapping(value = "/allotDeptView/{username}")
    public String allotDeptView(@PathVariable String username, Model model){
        List<Dept> deptList = null;
        List userDeptNoList = new ArrayList();
        User user = null;
        try {
            deptList = deptService.findAllDept();
            user = userService.findUserByUsername(username);
            List<Dept> list = user.getDept();
            for (int i =0;i<list.size();i++){
                userDeptNoList.add(list.get(i).getDeptNo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("deptList",deptList);
        model.addAttribute("userDeptNoList",userDeptNoList);
        model.addAttribute("userId",user.getId());
        return "/dept/allotDept";
    }

    @RequestMapping(value = "/allotDeptAction")
    @ResponseBody
    public void allotDeptAction(HttpServletRequest request,PrintWriter printWriter){
        String userId = request.getParameter("userId");
        String [] deptNo = request.getParameterValues("deptNo[]");

        try {
            boolean flag = deptService.insertUserDept(userId,deptNo);
            if (flag){
                printWriter.write("yes");
            }else {
                printWriter.write("no");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
