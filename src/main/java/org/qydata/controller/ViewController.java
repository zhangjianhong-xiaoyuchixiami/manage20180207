package org.qydata.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.qydata.entity.User;
import org.qydata.service.UserService;
import org.qydata.tools.Md5Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/11/20.
 */
@Controller

public class ViewController {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    UserService userService;

    //登录
    @RequestMapping("/login")
    public String loginUrl() { return "view/login";}

    //登录提交
    @RequestMapping("/view/login-action")
    public String login(HttpServletRequest request, String username, String password, RedirectAttributes model) {
        Subject subject = SecurityUtils.getSubject();

        String md5Password = Md5Tools.md5(username.trim()+password.trim());
        System.out.println(md5Password);

        UsernamePasswordToken token = new UsernamePasswordToken(username, md5Password);
        try {
            subject.login(token);

            User user = userService.findUserByUsername(username);
            request.getSession().setAttribute("userInfo", user);
            model.addFlashAttribute("user",user);
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            model.addFlashAttribute("msg","用户名或密码不正确");
            return "redirect:/login";
        }
    }

    //登录成功
    @RequestMapping("/")
    public String successUrl() {
        return "view/welcome";
    }

   //未授权
    @RequestMapping("/view/unauthurl")
    public String unauthUrl() {
        return "view/role";
    }

    //注销
    @RequestMapping(value = "/view/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }
}
