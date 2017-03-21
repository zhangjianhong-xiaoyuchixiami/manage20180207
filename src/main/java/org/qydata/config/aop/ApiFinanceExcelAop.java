package org.qydata.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.qydata.dst.ApiFinance;
import org.qydata.entity.ApiRequestLog;
import org.qydata.service.ApiFinanceService;
import org.qydata.tools.ExportDataHander;
import org.qydata.tools.ExportIoOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by jonhn on 2017/3/17.
 */
@Aspect
@Component
@Slf4j
public class ApiFinanceExcelAop {

    @Autowired private ApiFinanceService apiFinanceService;

    @Autowired private HttpServletResponse response;


    /**
     * Api消费账单导出Excel
     * @param point
     */
    @Around("execution(* org.qydata.controller.ApiFinanceController.findAllApiRecord(..))")
    public Object findAllApiRecordExportExcel(ProceedingJoinPoint point) throws Throwable {
        Object args [] = point.getArgs();
        System.out.println("************* 判断是否是执行导出操作 *************");
        if (args[0] != null && args[0].getClass() == String.class && args[0].equals("true")) {
            System.out.println("************* 执行导出操作 *************");
            Map<String,Object> map = new HashedMap();
            if (args[1] !=null){
                map.put("vendorId",args[1]);
            }
            if (args[2] !=null){
                map.put("apiTypeId",args[2]);
            }
            List<ApiFinance> apiFinanceList = null;
            Map<String,Object> mapResult = apiFinanceService.queryApiOverAllFinance(map);
            Set<Map.Entry<String,Object>> set = mapResult.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while(it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if (args[3] != null && args[3].getClass() == String.class && args[3].equals("true")){
                    if (me.getKey().equals("queryApiOverAllFinanceDead")){
                        apiFinanceList = (List<ApiFinance>) me.getValue();
                    }
                }else {
                    if (me.getKey().equals("queryApiOverAllFinance")){
                        apiFinanceList = (List<ApiFinance>) me.getValue();
                    }
                }
            }
            List<Map<String,Object>> listExport = new ArrayList<>();
            Map<String, Object> mapExport = new HashMap<>();
            mapExport.put("sheetName", "sheet1");
            listExport.add(mapExport);
            for ( int i = 0; i < apiFinanceList.size(); i++ ){
                ApiFinance apiFinance = apiFinanceList.get(i);
                Map<String, Object> mapValue = new HashMap<>();
                mapValue.put("apiTypeName", apiFinance.getApiTypeName());
                mapValue.put("vendorName", apiFinance.getVendorName());
                mapValue.put("wekTotleCost", ExportDataHander.pointsIntoRMB(apiFinance.getWeekTotleCost()));
                mapValue.put("monthTotleCost", ExportDataHander.pointsIntoRMB(apiFinance.getMonthTotleCost()));
                mapValue.put("consumeTotleAmount", ExportDataHander.pointsIntoRMB(apiFinance.getConsumeTotleAmount()));
                listExport.add(mapValue);
            }
            String fileName = "产品消费账单";
            String columnNames[]= {"产品类型","产品供应商","上周消费（单位：元）","上月消费（单位：元）","消费总额（单位：元）"};//列名
            String keys[] = {"apiTypeName","vendorName","wekTotleCost","monthTotleCost","consumeTotleAmount"};//map中的key
            ExportIoOperate.excelEndOperator(listExport,keys,columnNames,fileName,response);
            return null;
        }
        System.out.println("************* 未执行导出操作 *************");
        return point.proceed(args);
    }

    /**
     * Api消费明细账单导出Excel
     * @param point
     */
    @Around("execution(* org.qydata.controller.ApiFinanceController.findAllApiDetailRecord(..))")
    public Object findAllApiDetailRecordExportExcel(ProceedingJoinPoint point) throws Throwable {
        Object args [] = point.getArgs();
        System.out.println("************* 判断是否是执行导出操作 *************");
        if (args[0] != null && args[0].getClass() == String.class && args[0].equals("true")) {
            System.out.println("************* 执行导出操作 *************");
            Map<String,Object> map = new HashedMap();
            if (args[1] != null){
                map.put("apiId",args[1]);
            }
            if (args[2] != null && args[2] != "" ) {
                map.put("beginDate", args[2]+" "+"00:00:00");
            }
            if(args[3] != null && args[3] != ""){
                map.put("endDate", args[3]+" "+"23:59:59");
            }
            List<ApiRequestLog> apiRequestLogList = null;
            Map<String,Object> mapResult = apiFinanceService.queryApiDetailById(map);
            Set<Map.Entry<String,Object>> set = mapResult.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while(it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if (me.getKey().equals("queryApiDetailById")){
                    apiRequestLogList = (List<ApiRequestLog>) me.getValue();
                }
            }
            List<Map<String,Object>> listExport = new ArrayList<>();
            Map<String, Object> mapExport = new HashMap<>();
            mapExport.put("sheetName", "sheet1");
            listExport.add(mapExport);
            for ( int i = 0; i < apiRequestLogList.size(); i++ ){
                ApiRequestLog apiRequestLog = apiRequestLogList.get(i);
                Map<String, Object> mapValue = new HashMap<>();
                if(apiRequestLog.getCompany() != null){
                    mapValue.put("companyName", apiRequestLog.getCompany().getName());
                }else{
                    mapValue.put("companyName", "");
                }
                if(apiRequestLog.getApiResponseLog() != null){
                    mapValue.put("cost", apiRequestLog.getApiResponseLog().getCost()/100.0);
                }else {
                    mapValue.put("cost", "0.0");
                }
                if(apiRequestLog.getApiResponseLog() != null){
                    mapValue.put("resTime", apiRequestLog.getApiResponseLog().getResTime()/1000.0);
                }else {
                    mapValue.put("resTime", "");
                }
                mapValue.put("createTime", apiRequestLog.getCreateTime());
                listExport.add(mapValue);
            }
            String fileName = "产品消费明细账单";
            String columnNames[]= {"公司名称","消费金额（单位：元）","响应时间（单位：秒）","创建时间"};//列名
            String keys[] = {"companyName","cost","resTime","createTime"};//map中的key
            ExportIoOperate.excelEndOperator(listExport,keys,columnNames,fileName,response);
            return null;
        }
        System.out.println("************* 未执行导出操作 *************");
        return point.proceed(args);
    }

    /**
     * 以APIVendor统计消费信息导出Excel
     * @param point
     */
    @Around("execution(* org.qydata.controller.ApiFinanceController.findAllApiVendorConsume(..))")
    public Object findAllApiVendorConsumeExportExcel(ProceedingJoinPoint point) throws Throwable {
        Object args [] = point.getArgs();
        System.out.println("************* 判断是否是执行导出操作 *************");
        if (args[0] != null && args[0].getClass() == String.class && args[0].equals("true")) {
            System.out.println("************* 执行导出操作 *************");
            Map<String,Object> map = new HashMap<>();
            if(args[1] != null){
                map.put("vendorId",args[1]);
            }
            if(args[2] != null){
                map.put("partnerId",args[2]);
            }
            List<ApiFinance> apiFinanceList = null;
            Map<String,Object> mapResult = apiFinanceService.queryApiVendor(map);
            Set<Map.Entry<String,Object>> set = mapResult.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while(it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if (me.getKey().equals("queryApiVendor")){
                    apiFinanceList = (List<ApiFinance>) me.getValue();
                }
            }
            List<ApiFinance> apiFinances = ExportDataHander.processApiFinance(apiFinanceList);
            List<Map<String,Object>> listExport = new ArrayList<>();
            Map<String, Object> mapExport = new HashMap<>();
            mapExport.put("sheetName", "sheet1");
            listExport.add(mapExport);
            for ( int i = 0; i < apiFinances.size(); i++ ){
                ApiFinance apiFinance = apiFinances.get(i);
                Map<String, Object> mapValue = new HashMap<>();
                mapValue.put("vendorName", apiFinance.getVendorName());
                mapValue.put("partnerName", apiFinance.getPartnerName());
                mapValue.put("consumeTotleAmount", ExportDataHander.pointsIntoRMB(apiFinance.getConsumeTotleAmount()));
                mapValue.put("balance", ExportDataHander.pointsIntoRMB(apiFinance.getBalance()));
                mapValue.put("weekTotleCost", ExportDataHander.pointsIntoRMB(apiFinance.getWeekTotleCost()));
                mapValue.put("monthTotleAmount",ExportDataHander.pointsIntoRMB(apiFinance.getMonthTotleCost()));
                listExport.add(mapValue);
            }
            String fileName = "供应商财务账单";
            String columnNames[]= {"供应商","合作公司","消费金额（单位：元）","所剩余额（单位：元）","上周消费（单位：元）","上月消费（单位：元）"};//列名
            String keys[] = {"vendorName","partnerName","consumeTotleAmount","balance","weekTotleCost","monthTotleAmount"};//map中的key
            ExportIoOperate.excelEndOperator(listExport,keys,columnNames,fileName,response);
            return null;
        }
        System.out.println("************* 未执行导出操作 *************");
        return point.proceed(args);
    }



}
