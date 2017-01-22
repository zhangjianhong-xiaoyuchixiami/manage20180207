package org.qydata.exportexcelcontroller;

import org.apache.commons.collections.map.HashedMap;
import org.qydata.dst.ApiFinance;
import org.qydata.entity.ApiRequestLog;
import org.qydata.service.ApiFinanceService;
import org.qydata.tools.ExportIoOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/20.
 */
@Controller
@RequestMapping("excel-api-finance")
public class ExcelApiFinanceController {

    @Autowired
    private ApiFinanceService apiFinanceService;

    //Api消费账单
    @RequestMapping("/find-all-api-record")
    public void findAllApiRecord(Integer apiId, Integer apiTypeId, Integer vendorId,HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashedMap();
        map.put("apiId",apiId);
        map.put("apiTypeId",apiTypeId);
        map.put("vendorId",vendorId);
        List<ApiFinance> apiFinanceList = apiFinanceService.queryApiOverAllFinance(map);
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> mapA = new HashMap<String, Object>();
        mapA.put("sheetName", "sheet1");
        list.add(mapA);
        ApiFinance apiFinance=null;
        for (int j = 0; j < apiFinanceList.size(); j++) {
            apiFinance = apiFinanceList.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("apiName", apiFinance.getApiName());
            mapValue.put("apiTypeName", apiFinance.getApiTypeName());
            mapValue.put("vendorName", apiFinance.getVendorName());
            if(apiFinance.getWeekTotleCost() != null){
                mapValue.put("wekTotleCost", apiFinance.getWeekTotleCost()/100.0);
            }else {
                mapValue.put("wekTotleCost", 0.0);
            }
            if(apiFinance.getMonthTotleCost() != null){
                mapValue.put("monthTotleCost", apiFinance.getMonthTotleCost()/100.0);
            }else {
                mapValue.put("monthTotleCost", 0.0);
            }
            if(apiFinance.getConsumeTotleAmount() != null){
                mapValue.put("consumeTotleAmount", apiFinance.getConsumeTotleAmount()/100.0);
            }else {
                mapValue.put("consumeTotleAmount", 0.0);
            }
            if(apiFinance.getBalance() != null){
                mapValue.put("balance", apiFinance.getBalance()/100.0);
            }else {
                mapValue.put("balance", 0.0);
            }
            list.add(mapValue);
        }
        String fileName = "产品消费账单";
        String columnNames[]= {"产品名称","产品类型","产品供应商","上周消费（单位：元）","上月消费（单位：元）","消费总额（单位：元）","余额（单位：元）"};//列名
        String keys[] = {"apiName","apiTypeName","vendorName","wekTotleCost","monthTotleCost","consumeTotleAmount","balance"};//map中的key
        ExportIoOperate.excelEndOperator(list,keys,columnNames,fileName,response);
    }

    //Api消费账单-消费明细
    @RequestMapping("/find-all-api-record/detail")
    public void findAllApiDetailRecord(Integer apiId,String beginDate,String endDate,HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashedMap();
        map.put("apiId",apiId);
        if (beginDate != null && beginDate != "" ) {
            map.put("beginDate", beginDate+" "+"00:00:00");
        }
        if(endDate != null && endDate != ""){
            map.put("endDate", endDate+" "+"23:59:59");
        }
        List<ApiRequestLog> apiRequestLogList = apiFinanceService.queryApiDetailById(map);
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> mapA = new HashMap<String, Object>();
        mapA.put("sheetName", "sheet1");
        list.add(mapA);
        ApiRequestLog apiRequestLog=null;
        for (int j = 0; j < apiRequestLogList.size(); j++) {
            apiRequestLog = apiRequestLogList.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            if(apiRequestLog.getApiResponseLog() != null){
                mapValue.put("cost", apiRequestLog.getApiResponseLog().getCost()/100.0);
            }else {
                mapValue.put("cost", "");
            }
            if(apiRequestLog.getApiResponseLog() != null){
                mapValue.put("resTime", apiRequestLog.getApiResponseLog().getResTime()/1000.0);
            }else {
                mapValue.put("consumeTotleAmount", "");
            }
            mapValue.put("createTime", apiRequestLog.getCreateTime());
            list.add(mapValue);
        }
        String fileName = "产品消费明细账单";
        String columnNames[]= {"消费金额（单位：元）","响应时间（单位：秒）","创建时间"};//列名
        String keys[] = {"cost","vendorName","resTime","createTime"};//map中的key
        ExportIoOperate.excelEndOperator(list,keys,columnNames,fileName,response);
    }
}
