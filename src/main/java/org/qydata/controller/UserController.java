package org.qydata.controller;

import org.apache.log4j.Logger;
import org.qydata.entity.User;
import org.qydata.service.UserService;
import org.qydata.tools.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

//    private Integer getLineSize(String lineSize){
//        if(lineSize==null||lineSize.trim().isEmpty()||Integer.parseInt(lineSize)<=0){
//            return 1;
//        }
//        return Integer.parseInt(lineSize);
//    }

    @RequestMapping(value = "/addUserView")
    public String addUserView(){
        return "/user/addUser";
    }

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

    @RequestMapping(value = "/findAllUser")
    public String findAllAdmin(HttpServletRequest request, Model model){
        PageModel pageModel = new PageModel();
        Map<String,Object> map = new HashMap<String,Object>();
        String pageSize = request.getParameter("pageSize");//当前页
        pageModel.setPageSize(pageSize);
        map.put("beginIndex",pageModel.getBeginIndex());
        map.put("lineSize",pageModel.getLineSize());


        PageModel<User> pageModelOne = null;
        try {
            pageModelOne = userService.findAllUser(map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("count",pageModelOne.getCount());
        model.addAttribute("userDeptList",pageModelOne.getList());
        model.addAttribute("totlePage",pageModelOne.getTotalpage());
        model.addAttribute("pageSize",pageModelOne.getPageSize());


        System.out.println(pageModelOne.getCount());
        System.out.println(pageModelOne.getLineSize());
        System.out.println(pageModelOne.getPageSize());
        System.out.println(pageModelOne.getTotalpage());
        System.out.println(pageModelOne.getBeginIndex());
        System.out.println(pageModelOne.getEndIndex());
        System.out.println(pageModelOne.getList().size());

        return "/user/userList";
    }
//
//    @RequestMapping(value = "/findAllByColumn")
//    public String findAllByColumn(HttpServletRequest request,Model model,String content){
//        PageModel<User> pageModel = new PageModel<User>();
//        Map<String,Object> map = new HashMap<String,Object>();
//        Integer lineSize = this.getLineSize(request.getParameter("lineSize"));//当前页
//        Integer pageSize = 5;//每页显示条数
//        pageModel.setCpage(lineSize);
//        pageModel.setPageSize(pageSize);
//        map.put("beginIndex",pageModel.getBeginIndex());
//        map.put("line",pageModel.getPageSize());
//        map.put("content",content);
//        PageModel<User> pageModelA;
//        List<User> list = null;
//        Integer count = null;
//        Integer totlePage = null;
//        try {
//            pageModelA = userService.findAllByColumn(map);
//            list =  pageModelA.getList();
//            count = pageModelA.getRows();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if(count%pageSize==0){
//            totlePage=(count/pageSize);
//        }else{
//            totlePage=(count/pageSize)+1;
//        }
//        model.addAttribute("content",content);
//        model.addAttribute("count",count);
//        model.addAttribute("adminList",list);
//        model.addAttribute("totlePage",totlePage);
//        model.addAttribute("lineSize",lineSize);
//        return "/user/adminListSerach";
//
//    }
//
//    @RequestMapping(value = "/statusStart/{loginName}")
//    public String statusStart(@PathVariable String loginName){
//        try {
//            userService.updateStatusStart(loginName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/user/findAllAdmin";
//    }
//
//    @RequestMapping(value = "/statusForbid/{loginName}")
//    public String statusForbid(@PathVariable String loginName){
//        try {
//            userService.updateStatusForbid(loginName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/user/findAllAdmin";
//    }
//
//    @RequestMapping(value = "/updatePasswordView")
//    public String updatePasswordView(){
//        return "/user/updatePassword";
//    }
//
//    @RequestMapping(value = "/updatePasswordAction")
//    public String updatePasswordAction(String loginName,String password,String newPassword,RedirectAttributes model){
//        try {
//           boolean flag = userService.updatePassword(loginName.trim(),password.trim(),newPassword.trim());
//           if(!flag){
//               model.addFlashAttribute("msg","修改失败！");
//               return "redirect:/user/updatePasswordView";
//           }
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("updatePasswordErroe:修改密码异常");
//            model.addFlashAttribute("msg","修改失败！");
//            return "redirect:/user/updatePasswordView";
//        }
//        return "redirect:/";
//    }

}
