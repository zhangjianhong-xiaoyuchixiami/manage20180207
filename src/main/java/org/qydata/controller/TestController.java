package org.qydata.controller;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.qydata.entity.CustomerRequestLog;
import org.qydata.entity.User;
import org.qydata.service.CustomerService;
import org.qydata.service.TestService;
import org.qydata.tools.ExportIoOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * Created by jonhn on 2017/1/13.
 */
@Controller
public class TestController {

    @Autowired private TestService testService;

    @Autowired private CustomerService customerService;

    @Autowired private HttpServletResponse response;

    /**
     * 导出Excel
     * @return
     * @throws Exception
     */
    @RequestMapping("/export")
    public ModelAndView user() throws Exception {
        Map<String,Object> mapTran = new HashMap<>();
        mapTran.put("pageSize",0);
        mapTran.put("lineSize",30);
        Map<String, Object> map = customerService.findAllCustomerRequestLog(mapTran);
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        List<CustomerRequestLog> customerRequestLogList = null;
        while (it.hasNext()) {
            Map.Entry<String, Object> me = it.next();
            if (me.getKey().equals("findAllCustomerRequestLog")) {
                customerRequestLogList = (List<CustomerRequestLog>) me.getValue();
            }
        }
        List<Map<String,Object>> listExport = new ArrayList<>();
        Map<String, Object> mapExport = new HashMap<>();
        mapExport.put("sheetName", "sheet1");
        listExport.add(mapExport);
        for ( int i = 0; i < customerRequestLogList.size(); i++ ){
            CustomerRequestLog customerRequestLog = customerRequestLogList.get(i);
            Map<String, Object> mapValue = new HashMap<>();
            mapValue.put("id",customerRequestLog.getId());
            mapValue.put("apiTypeId",customerRequestLog.getApiTypeId());
            mapValue.put("stid",customerRequestLog.getStid());
            mapValue.put("customerId",customerRequestLog.getCustomerId());
            if (customerRequestLog.getMobileOperator() == null){
                mapValue.put("stidName","");
            }else {
                mapValue.put("stidName",customerRequestLog.getMobileOperator().getName());
            }

            listExport.add(mapValue);
        }
        String fileName = "客户请求日志";
        String columnNames[]= {"id","apiTypeId","stid","customerId","stidName"};//列名
        String keys[] = {"id","apiTypeId","stid","customerId","stidName"};//map中的key
        ExportIoOperate.excelEndOperator(listExport,keys,columnNames,fileName,response);
        return null;
    }

    /**
     * 视图
     * @param export
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/user")
    public ModelAndView userTest(String export, Model model) throws Exception {
        Map<String,Object> mapTran = new HashMap<>();
        mapTran.put("pageSize",0);
        mapTran.put("lineSize",15);
        Map<String, Object> map = customerService.findAllCustomerRequestLog(mapTran);
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> me = it.next();
            if (me.getKey().equals("findAllCustomerRequestLog")) {
                model.addAttribute("customerRequestLogList",me.getValue());
            }
        }
        model.addAttribute("pageSize",0);
        model.addAttribute("lineSize",15);
        return new ModelAndView("/test/user");
    }

    /**
     * 链接
     * @param aaa
     * @param bbb
     * @return
     */
    @RequestMapping("/user/href")
    @ResponseBody
    public String userHref(String aaa,String bbb){
        System.out.println(aaa);
        if (bbb != null && bbb != ""){
            System.out.println(bbb);
        }
        return "nihao";
    }

    @RequestMapping("/test/list-view")
    public String userListView(String userId,Model model){
        model.addAttribute("userId",userId);
        return "/test/list";
    }

    @RequestMapping("/test/list-ajax")
    @ResponseBody
    public String userList(HttpServletRequest request){
        String aoData = request.getParameter("aaData");
        String userId = request.getParameter("userId").trim();
        System.out.println(aoData);
        System.out.println(userId);
        Map<String,Object> mapTran = new HashMap<>();
        JSONArray jsonarray = JSONArray.fromObject(aoData);
        String sEcho = null;
        int iDisplayStart = 0; // 起始索引
        int iDisplayLength = 0; // 每页显示的行数
        for (int i = 0; i < jsonarray.size(); i++) {
            JSONObject obj = (JSONObject) jsonarray.get(i);
            if (obj.get("name").equals("sEcho")) {
                sEcho = obj.get("value").toString();
            }
            if (obj.get("name").equals("iDisplayStart")) {
                iDisplayStart = obj.getInt("value");
            }
            if (obj.get("name").equals("iDisplayLength")) {
                iDisplayLength = obj.getInt("value");
            }
        }
        System.out.println(iDisplayStart);
        System.out.println(iDisplayLength);
        mapTran.put("pageSize", iDisplayStart);
        mapTran.put("lineSize", iDisplayLength);
        if(userId != null && userId != ""){
            mapTran.put("userId", userId);
        }
        Map<String, Object> map = testService.queryAllUserTest(mapTran);
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        List<User> userList = null;
        Integer count = null;
        while (it.hasNext()) {
            Map.Entry<String, Object> me = it.next();
            if (me.getKey().equals("queryUserTest")) {
                userList = (List<User>) me.getValue();
            }
            if (me.getKey().equals("getAllUserCount")) {
                count = (Integer) me.getValue();
            }
        }
        JSONArray jsonArray = JSONArray.fromObject(userList);
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
        getObj.put("iTotalRecords", count);//实际的行数
        getObj.put("iTotalDisplayRecords", count);//显示的行数,这个要和上面写的一样
        getObj.put("aaData", jsonArray);//要以JSON格式返回
        return getObj.toString();
    }

    @RequestMapping("/test-map")
    @ResponseBody
    public String testMap(){
        return testService.testMap().toString();
    }

    @RequestMapping("/test-select")
    public String select(){
        return "/test/form_component";
    }

}
