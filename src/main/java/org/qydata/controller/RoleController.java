package org.qydata.controller;

import com.google.gson.Gson;
import org.qydata.entity.Role;
import org.qydata.entity.UserRole;
import org.qydata.service.RoleService;
import org.qydata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/12/7.
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    //分配部门
    @RequestMapping(value = "/allotRoleView/{username}")
    public String allotRoleView(@PathVariable String username, Model model){
        List<Role> roleList= null;
        List<UserRole> userRoleList = null;
        try {
            roleList = roleService.findAllRole();
            userRoleList = roleService.findAllRoleByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userRoleList",userRoleList);
        model.addAttribute("roleList",roleList);
        model.addAttribute("username",username);
        return "/user/allotRole";
    }

    @RequestMapping(value = "/allotRoleAction")
    @ResponseBody
    public String allotRoleAction(HttpServletRequest request){
        String username = request.getParameter("username");
        String [] roleId = request.getParameterValues("roleId[]");
        Gson gson = new Gson();
        Map<String,String> map = new HashMap<>();
        try {
            boolean flag = roleService.addRoleUser(username,roleId);
            if (flag){
                map.put("result","ok");
                map.put("msg",username);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gson.toJson(map);
    }
}
