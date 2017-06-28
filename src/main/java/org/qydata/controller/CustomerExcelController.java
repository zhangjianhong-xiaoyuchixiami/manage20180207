package org.qydata.controller;

import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.qydata.entity.CustomerConsumeExcel;
import org.qydata.service.CustomerExcelService;
import org.qydata.tools.excel.DownLoadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jonhn on 2017/6/21.
 */
@Controller
public class CustomerExcelController {


    @Autowired
    private CustomerExcelService customerExcelService;

    /**
     * 预览客户财务账单邮件
     * @param request
     * @return
     */
    @RequestMapping("/email/preview-customer-finance-account")
    @ResponseBody
    public String previewCustomerFinanceAccount(HttpServletRequest request){
        String email = (String) SecurityUtils.getSubject().getPrincipal();
        Gson gson = new Gson();
        String [] customerId = request.getParameterValues("customerId[]");
        Map<String,Object> mapResu = null;
        Map<String,Object> mapParam = new HashMap<>();
        mapParam.put("customerId",customerId);
        mapParam.put("email",email);
        try {
            mapResu = customerExcelService.queryCustomerFinanceAccountExcel(mapParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gson.toJson(mapResu);
    }

    /**
     * 发送客户财务账单邮件
     * @param request
     * @return
     */
    @RequestMapping("/email/send-customer-finance-account")
    @ResponseBody
    public String sendCustomerFinanceAccount(HttpServletRequest request){
        Gson gson = new Gson();
        String [] customerId = request.getParameterValues("customerId[]");
        Map<String,Object> mapResu = null;
        Map<String,Object> mapParam = new HashMap<>();
        mapParam.put("customerId",customerId);
        try {
            mapResu = customerExcelService.queryCustomerFinanceAccountExcel(mapParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gson.toJson(mapResu);
    }

    /**
     * 下载消费账单Excel
     * @return
     */
    @RequestMapping("/download-consume-check")
    public ResponseEntity downloadConsumeCheck(String companyName,Integer customerId,Integer year,Integer month) throws IOException {
        Map<String,Object> map = new HashMap<>();
        map.put("customerId",customerId);
        map.put("year",year);
        map.put("month",month);
        CustomerConsumeExcel customerConsumeExcel = customerExcelService.queryCustomerConsumeExcelByCustomerId(map);
        if (customerConsumeExcel != null && customerConsumeExcel.getExcelCode() != null){
            return DownLoadFile.downloadFile(customerConsumeExcel.getExcelCode(),"xxxx.xls");
        }
        return null;
    }
}
