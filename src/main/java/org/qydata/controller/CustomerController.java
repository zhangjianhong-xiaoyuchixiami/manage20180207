package org.qydata.controller;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.qydata.entity.Customer;
import org.qydata.entity.CustomerRequestLog;
import org.qydata.service.CustomerService;
import org.qydata.service.DeptService;
import org.qydata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by jonhn on 2016/11/8.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private UserService userService;

    /**
     * 添加新客户时验证账户名是否已存在
     * @param authId
     * @param response
     */
    @RequestMapping(value = "/findCustomerByAuthId/{authId}")
    public void findCustomerByAuthIdAdd(@PathVariable("authId") String authId,HttpServletResponse response){
        Customer customer = customerService.findCustomerByAuthId(authId);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if(customer!=null){
                out.print("yes");
            }else{
                out.print("no");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/find-all-customer-request-log-view")
    public String findAllCustomerRequestLogView(){

        return "/customer/customerrequestlog";
    }


    @RequestMapping(value = "/find-all-customer-request-log")
    @ResponseBody
    public String findAllCustomerRequestLog(Model model,String aoData){
        Map<String,Object> mapTran = new HashMap<>();
        JSONArray jsonarray = JSONArray.fromObject(aoData);
        System.out.println(aoData);
        String sEcho = null;
        int iDisplayStart = 0; // 起始索引
        int iDisplayLength = 0; // 每页显示的行数
        int iSortCol = 0; //第几列排序
        String sSortDir = null; //按什么排序

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
            if (obj.get("name").equals("iSortCol_0")) {
                iSortCol = obj.getInt("value");
            }
            if (obj.get("name").equals("sSortDir_0")) {
                sSortDir = obj.get("value").toString();
            }
        }

        mapTran.put("pageSize", iDisplayStart);
        mapTran.put("lineSize", iDisplayLength);
        switch (iSortCol) {
            case 0:
                mapTran.put("id", sSortDir);
                break;
            case 1:
                mapTran.put("name", sSortDir);
                break;
            case 2:
                mapTran.put("username", sSortDir);
                break;
            case 3:
                mapTran.put("tel", sSortDir);
                break;
            default:
                break;
        }
        Map<String, Object> map = customerService.findAllCustomerRequestLog(mapTran);
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        List<CustomerRequestLog> customerRequestLogList = null;
        Integer count = null;
        while (it.hasNext()) {
            Map.Entry<String, Object> me = it.next();
            if (me.getKey().equals("findAllCustomerRequestLog")) {
                customerRequestLogList = (List<CustomerRequestLog>) me.getValue();
            }
            if (me.getKey().equals("getCountAllCustomerRequestLog")) {
                count = (Integer) me.getValue();
            }
        }
        JSONArray jsonArray = JSONArray.fromObject(customerRequestLogList);
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
        getObj.put("iTotalRecords", count);//实际的行数
        getObj.put("iTotalDisplayRecords", count);//显示的行数,这个要和上面写的一样
        getObj.put("aaData", jsonArray);//要以JSON格式返回
        return getObj.toString();
    }

}
