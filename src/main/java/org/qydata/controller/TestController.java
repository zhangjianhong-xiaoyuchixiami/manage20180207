package org.qydata.controller;

import org.qydata.entity.User;
import org.qydata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;


/**
 * Created by jonhn on 2017/1/13.
 */
@Controller
@RequestMapping("/test-controller")
public class TestController {

    @Autowired private UserService userService;

    @RequestMapping("/user")

    public ModelAndView user( Model model) throws IOException {

        List<User> userList = userService.findAllUser(0, 200);
        model.addAttribute("userList", userList);
        model.addAttribute("page","page");
        model.addAttribute("size","size");
        //ViewExcel viewExcel = new ViewExcel();
       // ModelMap modelMap = new ModelMap();
        //modelMap.put("list",userList);
        ModelAndView modelAndView = new ModelAndView("/test/user");
        return modelAndView;

    }


}
