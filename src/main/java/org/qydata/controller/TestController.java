package org.qydata.controller;

import org.qydata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by jonhn on 2017/1/13.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired private UserService userService;

//
//    @RequestMapping("/user")
//    public ModelAndView user( Model model) throws IOException {
//        List<User> userList = userService.findAllUser(0, 200);
//        model.addAttribute("userList", userList);
//        model.addAttribute("page","page");
//        model.addAttribute("size","size");
//        //ViewExcel viewExcel = new ViewExcel();
//        // ModelMap modelMap = new ModelMap();
//        //modelMap.put("list",userList);
//        ModelAndView modelAndView = new ModelAndView("/test/user");
//        return modelAndView;
//    }

    @RequestMapping("/index")
    public String list1(){
        return "/test/index";
    }

  /*  @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request){
       String aoData = request.getParameter("aoData");
        JSONArray jsonarray = JSONArray.fromObject(aoData);
        System.out.println(aoData);
        String sEcho = null;
        int iDisplayStart = 0; // 起始索引
        int iDisplayLength = 0; // 每页显示的行数
        int iSortCol_0 = 0; //第几列排序
        String sSortDir_0 = null; //按什么排序

        for (int i = 0; i < jsonarray.size(); i++) {
            JSONObject obj = (JSONObject) jsonarray.get(i);
            if (obj.get("name").equals("sEcho")){
                sEcho = obj.get("value").toString();
            }
            if (obj.get("name").equals("iDisplayStart")){
                iDisplayStart = obj.getInt("value");
            }
            if (obj.get("name").equals("iDisplayLength")){
                iDisplayLength = obj.getInt("value");
            }
            if (obj.get("name").equals("iSortCol_0")){
                iSortCol_0 = obj.getInt("value");
            }
            if (obj.get("name").equals("sSortDir_0")){
                sSortDir_0 = obj.get("value").toString();
            }
        }
        System.out.println(iDisplayStart);
        System.out.println(iDisplayLength);
       System.out.println(iSortCol_0);
       System.out.println(sSortDir_0);
        Map<String,Object> mapTran = new HashMap<>();
        mapTran.put("pageSize",iDisplayStart);
        mapTran.put("lineSize",iDisplayLength);
        switch (iSortCol_0){
            case 0:
                mapTran.put("id",sSortDir_0);
                break;
            case 1:
                mapTran.put("name",sSortDir_0);
                break;
            case 2:
                mapTran.put("username",sSortDir_0);
                break;
            case 3:
                mapTran.put("tel",sSortDir_0);
                break;
        }
        Map<String,Object> map = userService.findAllUserTest(mapTran);
        Set<Map.Entry<String,Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        List<User> userList  = null;
        Integer count = null;
        while (it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if(me.getKey().equals("findAllUser")){
                userList =  (List<User>) me.getValue();
            }
            if(me.getKey().equals("getAllCount")){
                count = (Integer) me.getValue();
            }
        }
        JSONArray jsonArray =JSONArray.fromObject(userList);
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
        getObj.put("iTotalRecords", count);//实际的行数
        getObj.put("iTotalDisplayRecords", count);//显示的行数,这个要和上面写的一样
        getObj.put("aaData",jsonArray);//要以JSON格式返回
        return getObj.toString();
    }*/

    @RequestMapping("/delete")
    public void delete(Integer id,String name,String username){
        userService.deleteTest(id);
    }


}
