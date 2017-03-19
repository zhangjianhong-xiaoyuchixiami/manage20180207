package org.qydata.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.qydata.dst.PartnerFinance;
import org.qydata.entity.PartnerIncomeExpenditureLog;
import org.qydata.service.PartnerFinanceService;
import org.qydata.tools.ExportDataHander;
import org.qydata.tools.ExportIoOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
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
     * 合作伙伴来往账目导出Excel
     * @param point
     */
    @Around("execution(* org.qydata.controller.PartnerFinanceController.findPartnersFinancialAccount(..))")
    public Object findPartnersFinancialAccountExportExcel(ProceedingJoinPoint point) throws Throwable {
        Object args [] = point.getArgs();
        System.out.println("************* 判断是否是执行导出操作 *************");
        if (args[0] != null && args[0].getClass() == String.class && args[0].equals("true")) {
            System.out.println("************* 执行导出操作 *************");
            Map<String,Object> map = new HashMap<>();
            if (args[1] != null && args[1] != "") {
                map.put("partnerName", args[1]);
            }
            List<PartnerFinance> partnerFinanceList = null;
            Map<String,Object> mapResult = partnerFinanceService.queryPartnerOverFinance(map);
            Set<Map.Entry<String,Object>> set = mapResult.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while (it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if (me.getKey().equals("queryPartnerOverFinance")){
                    partnerFinanceList = (List<PartnerFinance>) me.getValue();
                }
            }
            List<Map<String,Object>> listExport = new ArrayList<>();
            Map<String, Object> mapExport = new HashMap<>();
            mapExport.put("sheetName", "sheet1");
            listExport.add(mapExport);
            for ( int i = 0; i < partnerFinanceList.size(); i++ ){
                PartnerFinance partnerFinance = partnerFinanceList.get(i);
                Map<String,Object> mapValue = new HashMap<>();
                mapValue.put("partnerName", partnerFinance.getPartnerName());
                mapValue.put("weekIncomeAmount", ExportDataHander.pointsIntoRMB(partnerFinance.getWeekIncomeAmount()));
                mapValue.put("weekExpenditureAmount", ExportDataHander.pointsIntoRMB(partnerFinance.getWeekExpenditureAmount()));
                mapValue.put("monthIncomeAmount", ExportDataHander.pointsIntoRMB(partnerFinance.getMonthIncomeAmount()));
                mapValue.put("monthExpenditureAmount", ExportDataHander.pointsIntoRMB(partnerFinance.getMonthExpenditureAmount()));
                mapValue.put("totleIncomeAmount", ExportDataHander.pointsIntoRMB(partnerFinance.getTotleIncomeAmount()));
                mapValue.put("totleExpenditureAmount", ExportDataHander.pointsIntoRMB(partnerFinance.getTotleExpenditureAmount()));
                listExport.add(mapValue);
            }
            String fileName = "合作公司财务账单";
            String columnNames[]= {"公司名称","上周收入（单位：元）","上周支出（单位：元）","上月收入（单位：元）","上月支出（单位：元）","收入总额（单位：元）","支出总额（单位：元）"};//列名
            String keys[] = {"partnerName","weekIncomeAmount","weekExpenditureAmount","monthIncomeAmount","monthExpenditureAmount","totleIncomeAmount","totleExpenditureAmount"};
            ExportIoOperate.excelEndOperator(listExport,keys,columnNames,fileName,response);
            return null;
        }
        System.out.println("************* 未执行导出操作 *************");
        return point.proceed(args);
    }


    /**
     * 合作伙伴来往账目-收支明细导出Excel
     * @param point
     */
    @Around("execution(* org.qydata.controller.PartnerFinanceController.findPartnerIncomeAndExpenditureRecord(..))")
    public Object findPartnerIncomeAndExpenditureRecordExportExcel(ProceedingJoinPoint point) throws Throwable {
        Object args [] = point.getArgs();
        System.out.println("************* 判断是否是执行导出操作 *************");
        if (args[0] != null && args[0].getClass() == String.class && args[0].equals("true")) {
            System.out.println("************* 执行导出操作 *************");
            Map<String,Object> map = new HashMap<>();
            if (args[1] != null){
                map.put("partnerId",args[1]);
            }
            if (args[2] != null){
                map.put("reasonId",args[2]);
            }
            List<PartnerIncomeExpenditureLog> partnerIncomeExpenditureLogList = null;
            Map<String,Object> mapResult = partnerFinanceService.queryPartnerDetailLog(map);
            Set<Map.Entry<String,Object>> set = mapResult.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while (it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if (me.getKey().equals("queryPartnerDetailLog")){
                    partnerIncomeExpenditureLogList = (List<PartnerIncomeExpenditureLog>) me.getValue();
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<Map<String,Object>> listExport = new ArrayList<>();
            Map<String, Object> mapExport = new HashMap<>();
            mapExport.put("sheetName", "sheet1");
            listExport.add(mapExport);
            for ( int i = 0; i < partnerIncomeExpenditureLogList.size(); i++ ){
                PartnerIncomeExpenditureLog partnerIncomeExpenditureLog = partnerIncomeExpenditureLogList.get(i);
                Map<String,Object> mapValue = new HashMap<>();
                mapValue.put("amount", ExportDataHander.pointsIntoRMB(partnerIncomeExpenditureLog.getAmount()));
                mapValue.put("createTime", sdf.format(partnerIncomeExpenditureLog.getCreateTime()));
                mapValue.put("remark", partnerIncomeExpenditureLog.getRemark());
                mapValue.put("reasonName", partnerIncomeExpenditureLog.getPartnerIncomeExpenditureReason().getName());
                listExport.add(mapValue);
            }
            String fileName = "合作公司财务明细账单";
            String columnNames[]= {"金额（单位：元）","创建时间","备注","类型"};
            String keys[] = {"amount","createTime","remark","reasonName"};
            ExportIoOperate.excelEndOperator(listExport,keys,columnNames,fileName,response);
            return null;
        }
        System.out.println("************* 未执行导出操作 *************");
        return point.proceed(args);
    }


}
