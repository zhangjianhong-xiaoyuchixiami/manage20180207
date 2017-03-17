package org.qydata.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.qydata.dst.ApiFinance;
import org.qydata.service.PartnerFinanceService;
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
public class PartnerFinanceAop {

    @Autowired private PartnerFinanceService partnerFinanceService;

    @Autowired private HttpServletResponse response;

    /**
     * Api消费账单导出Excel
     * @param point
     */
    @Around("execution(* org.qydata.controller.PartnerFinanceController.findPartnersFinancialAccount(..))")
    public Object findPartnersFinancialAccountExportExcel(ProceedingJoinPoint point) throws Throwable {
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
                if (me.getKey().equals("queryApiOverAllFinance")){
                    apiFinanceList = (List<ApiFinance>) me.getValue();
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



}
