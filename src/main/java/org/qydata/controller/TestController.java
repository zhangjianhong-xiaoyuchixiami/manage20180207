package org.qydata.controller;


import org.qydata.entity.CustomerRequestLog;
import org.qydata.service.CustomerService;
import org.qydata.service.UserService;
import org.qydata.tools.ExportIoOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * Created by jonhn on 2017/1/13.
 */
@Controller
public class TestController {

    @Autowired private UserService userService;

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
        mapTran.put("lineSize",30);
        Map<String, Object> map = customerService.findAllCustomerRequestLog(mapTran);
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> me = it.next();
            if (me.getKey().equals("findAllCustomerRequestLog")) {
                model.addAttribute("customerRequestLogList",me.getValue());
            }
        }
        return new ModelAndView("/test/user");
    }

}
