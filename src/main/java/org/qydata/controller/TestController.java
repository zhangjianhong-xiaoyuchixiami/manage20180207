package org.qydata.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.qydata.entity.User;
import org.qydata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/list1")
    public String list1(){
        return "/customer/list";
    }
    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request){
        String aoData = request.getParameter("aoData");
        JSONArray jsonarray = JSONArray.fromObject(aoData);
        System.out.println(aoData);
        String sEcho = null;
        int iDisplayStart = 0; // 起始索引
        int iDisplayLength = 0; // 每页显示的行数
//        int iColumns = 4;
//        String sColumns = "";
//        int mDataProp_0 = 0;
//        int mDataProp_1 = 1;
//        int mDataProp_2 = 2;
//        int mDataProp_3 = 3;
//        int iSortCol_0 = 0;
//        String sSortDir_0 = "asc";
//        int iSortingCols = 1;
//        boolean bSortable_0 = true;
//        boolean bSortable_1 = true;
//        boolean bSortable_2 = true;
//        boolean bSortable_3 = true;
        for (int i = 0; i < jsonarray.size(); i++) {
            JSONObject obj = (JSONObject) jsonarray.get(i);
            if (obj.get("name").equals("sEcho"))
                sEcho = obj.get("value").toString();

            if (obj.get("name").equals("iDisplayStart"))
                iDisplayStart = obj.getInt("value");

            if (obj.get("name").equals("iDisplayLength"))
                iDisplayLength = obj.getInt("value");
        }
        System.out.println(sEcho);
        System.out.println(iDisplayStart);
        System.out.println(iDisplayLength);
        List<User> userList = userService.findAllUser(iDisplayStart,iDisplayLength);
        JSONArray jsonArray =JSONArray.fromObject(userList);
        System.out.println(jsonArray.toString());
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
        getObj.put("iTotalRecords", 27);//实际的行数
        getObj.put("iTotalDisplayRecords", 27);//显示的行数,这个要和上面写的一样
        getObj.put("aaData",jsonArray);//要以JSON格式返回
        return getObj.toString();
    }


}
