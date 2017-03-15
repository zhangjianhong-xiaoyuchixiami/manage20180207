package org.qydata.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.qydata.entity.CustomerRequestLog;
import org.qydata.service.CustomerService;
import org.qydata.tools.ExportIoOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by jonhn on 2017/3/15.
 */
@Aspect
@Component
@Slf4j
public class ExportExcelAop {

    @Autowired private CustomerService customerService;

    @Autowired private HttpServletResponse response;

    /**
     * Aop测试导出Excel
     * @param point
     */
    @Around("execution(* org.qydata.controller.TestController.userTest(..))")
    public Object userTestExportExcel(ProceedingJoinPoint point) throws Throwable {
        Object args [] = point.getArgs();
        System.out.println("************* 判断是否是执行导出操作 *************");
        if (args[0] != null && args[0].getClass() == String.class && args[0].equals("true")) {
            System.out.println("************* 执行导出操作 *************");
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
                if (customerRequestLog.getMobileOperator() != null){
                    mapValue.put("stidName",customerRequestLog.getMobileOperator().getName());
                }else {
                    mapValue.put("stidName","");
                }

                listExport.add(mapValue);
            }
            String fileName = "客户请求日志";
            String columnNames[]= {"id","apiTypeId","stid","customerId","stidName"};//列名
            String keys[] = {"id","apiTypeId","stid","customerId","stidName"};//map中的key
            ExportIoOperate.excelEndOperator(listExport,keys,columnNames,fileName,response);
            return null;
        }
        System.out.println("************* 未执行导出操作 *************");
        return point.proceed(args);
    }

}
