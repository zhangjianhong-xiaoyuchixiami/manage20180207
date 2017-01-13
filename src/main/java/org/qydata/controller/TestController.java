package org.qydata.controller;

import org.qydata.entity.User;
import org.qydata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;



/**
 * Created by jonhn on 2017/1/13.
 */
@Controller
@RequestMapping("/test-controller")
public class TestController {

    @Autowired private UserService userService;

    @RequestMapping("/user")
    public ModelAndView user(Model model){
        ModelAndView modelAndView = new ModelAndView("/test/user");
        List<User> userList = userService.findAllUser(0, 200);
        model.addAttribute("userList", userList);
        model.addAttribute("user", "user");
        model.addAttribute("page", "page");
        return modelAndView;
//        }else {
//            ViewExcel viewExcel = new ViewExcel();
//            map.put("list",userList);
//            return new ModelAndView(viewExcel,map);
//        }
    }


}
