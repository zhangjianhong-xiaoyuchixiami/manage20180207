package org.qydata.controller;

import com.google.gson.Gson;
import org.qydata.entity.Role;
import org.qydata.entity.UserRole;
import org.qydata.service.RoleService;
import org.qydata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    //分配角色
    @RequestMapping(value = "/allotRoleView")
    public String allotRoleView(Integer userId, Model model){
        List<Role> roleList= null;
        List<UserRole> userRoleList = null;
        try {
            roleList = roleService.findAllRole();
            userRoleList = roleService.findAllRoleByUsername(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userRoleList",userRoleList);
        model.addAttribute("roleList",roleList);
        model.addAttribute("userId",userId);
        return "/dept/allotRole";
    }

    @RequestMapping(value = "/allotRoleAction")
    @ResponseBody
    public String allotRoleAction(HttpServletRequest request,Integer userId){
        String [] roleId = request.getParameterValues("roleId[]");
        Gson gson = new Gson();
        Map<String,String> map = new HashMap<>();
        try {
            boolean flag = roleService.addRoleUser(userId,roleId);
            if (flag){
                map.put("result","ok");
                map.put("msg","userId");
            }else {
                map.put("result","error");
                map.put("msg","userId");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result","no");
            map.put("msg","userId");
        }
        return gson.toJson(map);
    }
}
