package org.qydata.controller;

import org.apache.log4j.Logger;
import org.qydata.entity.Dept;
import org.qydata.entity.User;
import org.qydata.service.UserService;
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
 * Created by jonhn on 2016/11/30.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUserView")
    public String addUserView(){
        return "/user/addUser";
    }
    //super
    @RequestMapping(value = "/addUserAction")
    public String addUserAction(User user, RedirectAttributes model){
        try {
            boolean flag = userService.addUser(user);
            if(!flag){
                model.addFlashAttribute("msg","很遗憾，添加失败！");
                return "redirect:/user/addUserView";
            }
        } catch (Exception e) {
            model.addFlashAttribute("msg","很遗憾，添加失败！");
            log.error("addUserAction:新增用户异常");
            return "redirect:/user/addUserView";
        }
        return "redirect:/user/findAllUser";
    }
    //common
    @RequestMapping(value = "/addUserCommonAction")
    public String addUserCommonAction(User user, RedirectAttributes model){
        try {
            boolean flag = userService.addUserCommon(user);
            if(!flag){
                model.addFlashAttribute("msg","很遗憾，添加失败！");
                return "redirect:/user/addUserView";
            }
        } catch (Exception e) {
            model.addFlashAttribute("msg","很遗憾，添加失败！");
            log.error("addUserCommonAction:新增用户异常");
            return "redirect:/user/addUserView";
        }
        return "redirect:/user/findAllUser";
    }
    //super
    @RequestMapping(value = "/findAllUser")
    public String findAllUser(HttpServletRequest request, Model model,String content){

        PageModel pageModel = new PageModel();
        Map<String,Object> map = new HashMap<String,Object>();
        String pageSize = request.getParameter("pageSize");//当前页
        String lineSize = "8";
        pageModel.setPageSize(pageSize);
        pageModel.setLineSize(lineSize);
        map.put("beginIndex",pageModel.getBeginIndex());
        map.put("lineSize",pageModel.getLineSize());
        if(content!=null){
            map.put("content",content);
        }
        PageModel<User> pageModelOne = null;
        try {
            pageModelOne = userService.findAllUser(map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("content",content);
        model.addAttribute("count",pageModelOne.getCount());
        model.addAttribute("userDeptList",pageModelOne.getList());
        Integer totalPage= null;
        Integer count = pageModelOne.getCount();
        if (count%Integer.parseInt(lineSize) == 0) {
            totalPage = (count/Integer.parseInt(lineSize));
        } else {
            totalPage = (count/Integer.parseInt(lineSize)) + 1;
        }
        model.addAttribute("totlePage",totalPage);
        model.addAttribute("pageSize",pageModel.getPageSize());
        return "/user/userList";
    }
    //common
    @RequestMapping(value = "/findAllUserCommon")
    public String findAllUserCommon(HttpServletRequest request, Model model,String content){
        User user = (User)request.getSession().getAttribute("userInfo");
        List<Dept> deptList = user.getDept();
        List deptNoList = new ArrayList();
        for(int i =0;i<deptList.size();i++){
            deptNoList.add(deptList.get(i).getDeptNo());
        }
        PageModel pageModel = new PageModel();
        Map<String,Object> map = new HashMap<String,Object>();
        String pageSize = request.getParameter("pageSize");//当前页
        String lineSize = "8";
        pageModel.setPageSize(pageSize);
        pageModel.setLineSize(lineSize);
        map.put("beginIndex",pageModel.getBeginIndex());
        map.put("lineSize",pageModel.getLineSize());
        map.put("deptNoList",deptNoList);

        if(content!=null){
            map.put("content",content);
        }

        PageModel<User> pageModelOne = null;
        try {
            pageModelOne = userService.findAllUser(map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("deptNoList",deptNoList);
        model.addAttribute("content",content);
        model.addAttribute("count",pageModelOne.getCount());
        model.addAttribute("userDeptList",pageModelOne.getList());
        Integer totalPage= null;
        Integer count = pageModelOne.getCount();
        if (count%Integer.parseInt(lineSize) == 0) {
            totalPage = (count/Integer.parseInt(lineSize));
        } else {
            totalPage = (count/Integer.parseInt(lineSize)) + 1;
        }
        model.addAttribute("totlePage",totalPage);
        model.addAttribute("pageSize",pageModel.getPageSize());
        return "/user/userList";
    }

    //启动账号
    @RequestMapping(value = "/statusStart/{username}")
    public String statusStart(@PathVariable String username){
        try {
            userService.updateStatusStart(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/findAllUser";
    }
    //禁用账号
    @RequestMapping(value = "/statusForbid/{username}")
    public String statusForbid(@PathVariable String username){
        try {
            userService.updateStatusForbid(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/findAllUser";
    }
    //重置密码
    @RequestMapping(value = "/resetPassword/{username}")
    public String resetPassword(@PathVariable String username){
        try {
            userService.resetPassword(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/findAllUser";
    }

    //修改密码
    @RequestMapping(value = "/updatePasswordView")
    public String updatePasswordView(){
        return "/user/updatePassword";
    }

    @RequestMapping(value = "/updatePasswordAction")
    public String updatePasswordAction(String username,String password,String newPassword,RedirectAttributes model){
        try {
            boolean flag = userService.updatePassword(username.trim(),password.trim(),newPassword.trim());
            if(!flag){
                model.addFlashAttribute("msg","修改失败！");
                return "redirect:/user/updatePasswordView";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updatePasswordErroe:修改密码异常");
            model.addFlashAttribute("msg","修改失败！");
            return "redirect:/user/updatePasswordView";
        }
        return "redirect:/";
    }

}
