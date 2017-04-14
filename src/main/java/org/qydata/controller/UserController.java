package org.qydata.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.qydata.entity.Dept;
import org.qydata.entity.User;
import org.qydata.service.PowerUserService;
import org.qydata.service.RoleService;
import org.qydata.tools.Md5Tools;
import org.qydata.tools.PageModel;
import org.qydata.tools.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired private PowerUserService powerUserService;
    @Autowired private RoleService roleService;

    @RequestMapping(value = "/addUserView")
    public String addUserView(){
        return "/user/addUser";
    }

    @RequestMapping(value = "/addUserViewCommon")
    public String addUserViewCommon(HttpServletRequest request, Model model){
        User user = (User)request.getSession().getAttribute("userInfo");
        List<Dept> deptList = user.getDept();
        model.addAttribute("deptList",deptList);
        return "/user/addUserCommon";
    }


    //super
    @RequestMapping(value = "/addUserAction")
    public String addUserAction(String email, Integer status, Integer typeId, RedirectAttributes model){

        User user = new User();
        user.setEmail(email);
        user.setStatus(status);
        user.setTypeId(typeId);

        try {
            boolean flag = roleService.addUser(user);
            if(!flag){
                model.addFlashAttribute("msg","很遗憾，添加失败！");
                return "redirect:/user/addUserView";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addFlashAttribute("msg","很遗憾，添加失败！");
            log.error("addUserAction:新增用户异常");
            return "redirect:/user/addUserView";
        }
        return "redirect:/user/findAllUser";
    }


    //common
    @RequestMapping(value = "/addUserCommonAction")
    public String addUserCommonAction(User user,String deptId, RedirectAttributes model){


        if(RegexUtil.isNull(user.getStatus().toString())){
            return "redirect:/user/addUserViewCommon";
        }
        if(RegexUtil.isNull(deptId)){
            return "redirect:/user/addUserViewCommon";
        }
        try {
            boolean flag = roleService.addUserCommon(user,deptId);
            if(!flag){
                model.addFlashAttribute("msg","很遗憾，添加失败！");
                return "redirect:/user/addUserViewCommon";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addFlashAttribute("msg","很遗憾，添加失败！");
            log.error("addUserCommonAction:新增用户异常");
            return "redirect:/user/addUserViewCommon";
        }
        return "redirect:/user/findAllUserCommon";
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
            pageModelOne = powerUserService.queryAllUser(map);

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
        List deptIdList = new ArrayList();
        if(deptList.size() > 0){
            for(int i =0;i<deptList.size();i++){
                deptIdList.add(deptList.get(i).getId());
            }
            PageModel pageModel = new PageModel();
            Map<String,Object> map = new HashMap<String,Object>();
            String pageSize = request.getParameter("pageSize");//当前页
            String lineSize = "8";
            pageModel.setPageSize(pageSize);
            pageModel.setLineSize(lineSize);
            map.put("beginIndex",pageModel.getBeginIndex());
            map.put("lineSize",pageModel.getLineSize());
            map.put("deptIdList",deptIdList);

            if(content!=null){
                map.put("content",content);
            }

            PageModel<User> pageModelOne = null;
            try {
                pageModelOne = powerUserService.queryAllUser(map);

            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("deptIdList",deptIdList);
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
        }else{
            model.addAttribute("deptIdList", deptIdList);
            model.addAttribute("count", 0);
            model.addAttribute("userDeptList", null);
            return "/user/userList";
        }
    }

    //启动账号super
    @RequestMapping(value = "/statusStart")
    public String statusStart(Integer userId){
        try {
            roleService.updateStatusStart(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/findAllUser";
    }

    //禁用账号super
    @RequestMapping(value = "/statusForbid")
    public String statusForbid(Integer userId){
        try {
            roleService.updateStatusForbid(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/findAllUser";
    }

    //重置密码super
    @RequestMapping(value = "/resetPassword")
    public String resetPassword(Integer userId){
        try {
            roleService.resetPassword(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/findAllUser";
    }

    //启动账号common
    @RequestMapping(value = "/statusStartCommon")
    public String statusStartCommon(Integer userId){
        try {
            roleService.updateStatusStart(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/findAllUserCommon";
    }

    //禁用账号common
    @RequestMapping(value = "/statusForbidCommon")
    public String statusForbidCommon(Integer userId){
        try {
            roleService.updateStatusForbid(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/findAllUserCommon";
    }

    //重置密码common
    @RequestMapping(value = "/resetPasswordCommon")
    public String resetPasswordCommon(Integer userId){
        try {
            roleService.resetPassword(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/findAllUserCommon";
    }

    //修改密码
    @RequestMapping(value = "/updatePasswordView")
    public String updatePasswordView(){
        return "/user/updatePassword";
    }

    /**
     *修改密码
     * @param newPassword
     * @param rppassword
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatePasswordAction")
    public String updatePasswordAction(String newPassword,String rppassword,RedirectAttributes model,HttpServletRequest request){

        if(RegexUtil.isNull(newPassword)){
            model.addFlashAttribute("UserMessageNewPassword","请输入新密码");
            return "redirect:/user/updatePasswordView";
        }
        if(!RegexUtil.isPwd(newPassword)){
            model.addFlashAttribute("newPassword",newPassword);
            model.addFlashAttribute("UserMessageNewPassword","新密码格式不正确");
            return "redirect:/user/updatePasswordView";
        }
        if(RegexUtil.isNull(rppassword)){
            model.addFlashAttribute("newPassword",newPassword);
            model.addFlashAttribute("UserMessageRpPassword","请再次输入新密码");
            return "redirect:/user/updatePasswordView";
        }
        if(!newPassword.equals(rppassword)) {
            model.addFlashAttribute("newPassword",newPassword);
            model.addFlashAttribute("newPassword",rppassword);
            model.addFlashAttribute("UserMessageRpPassword", "两次密码不一致");
            return "redirect:/user/updatePasswordView";
        }
        Integer userId = powerUserService.findUserByEmail((String) SecurityUtils.getSubject().getPrincipal()).getId();
        String md5NewPassword = Md5Tools.md5(SecurityUtils.getSubject().getPrincipal()+newPassword.trim());
        try {
            boolean flag = roleService.updatePassword(userId,md5NewPassword);
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
        return "redirect:/view/logout";
    }

}
