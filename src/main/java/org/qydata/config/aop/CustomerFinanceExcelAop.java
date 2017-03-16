package org.qydata.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.qydata.dst.CustomerFinance;
import org.qydata.entity.CustomerBalanceLog;
import org.qydata.entity.User;
import org.qydata.service.CustomerFinanceService;
import org.qydata.service.UserService;
import org.qydata.tools.ExportDataHander;
import org.qydata.tools.ExportIoOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * Created by jonhn on 2017/3/16.
 */
@Aspect
@Component
@Slf4j
public class CustomerFinanceExcelAop {

    @Autowired
    private CustomerFinanceService customerFinanceService;

    @Autowired private HttpServletResponse response;

    @Autowired private UserService userService;

    /**
     * 公司财务账单导出Excel
     * @param point
     */
    @Around("execution(* org.qydata.controller.FinanceController.queryAllCustomer(..))")
    public Object queryAllCustomerExportExcel(ProceedingJoinPoint point) throws Throwable {
        Object args [] = point.getArgs();
        System.out.println("************* 判断是否是执行导出操作 *************");
        if (args[0] != null && args[0].getClass() == String.class && args[0].equals("true")) {
            System.out.println("************* 执行导出操作 *************");
            Map<String,Object> map = new HashMap<>();
            if(args[1] != null && args[1] != ""){
                map.put("content",args[1]);
            }
            if(args[2] != null){
                map.put("partnerId",args[2]);
            }
            List<CustomerFinance> customerFinanceList = customerFinanceService.queryCompanyCustomerOverAllFinance(map);
            List<Map<String,Object>> listExport = new ArrayList<>();
            Map<String, Object> mapExport = new HashMap<>();
            mapExport.put("sheetName", "sheet1");
            listExport.add(mapExport);
            for ( int i = 0; i < customerFinanceList.size(); i++ ){
                CustomerFinance customerFinance = customerFinanceList.get(i);
                Map<String, Object> mapValue = new HashMap<>();
                mapValue.put("companyName", customerFinance.getCompanyName());
                mapValue.put("partnerName", customerFinance.getPartnerName());
                mapValue.put("chargeWeekTotleAmount", ExportDataHander.pointsIntoRMB(customerFinance.getChargeWeekTotleAmount()));
                mapValue.put("consumeWeekTotleAmount", ExportDataHander.pointsIntoRMB(customerFinance.getConsumeWeekTotleAmount()));
                mapValue.put("chargeMonthTotleAmount", ExportDataHander.pointsIntoRMB(customerFinance.getChargeMonthTotleAmount()));
                mapValue.put("consumeMonthTotleAmount", ExportDataHander.pointsIntoRMB(customerFinance.getConsumeMonthTotleAmount()));
                mapValue.put("chargeTotleAmount", ExportDataHander.pointsIntoRMB(customerFinance.getChargeTotleAmount()));
                mapValue.put("consumeTotleAmount", ExportDataHander.pointsIntoRMB(customerFinance.getConsumeTotleAmount()));
                mapValue.put("balance", ExportDataHander.pointsIntoRMB(customerFinance.getBalance()));
                listExport.add(mapValue);
            }
            String fileName = "客户财务报表文件";
            String columnNames[]= {"公司名称","合作公司","上周充值（单位：元）","上周消费（单位：元）","上月充值（单位：元）","上月消费（单位：元）","充值总额（单位：元）","消费总额（单位：元）","余额（单位：元）"};//列名
            String keys[] = {"companyName","partnerName","chargeWeekTotleAmount","consumeWeekTotleAmount","chargeMonthTotleAmount","consumeMonthTotleAmount","chargeTotleAmount","consumeTotleAmount","balance"};//map中的key
            ExportIoOperate.excelEndOperator(listExport,keys,columnNames,fileName,response);
            return null;
        }
        System.out.println("************* 未执行导出操作 *************");
        return point.proceed(args);
    }

    /**
     * 公司财务账单导出Excel（根据部门编号）
     * @param point
     */
    @Around("execution(* org.qydata.controller.FinanceController.queryAllCustomerByDeptId(..))")
    public Object queryAllCustomerByDeptIdExportExcel(ProceedingJoinPoint point) throws Throwable {
        Object args [] = point.getArgs();
        System.out.println("************* 判断是否是执行导出操作 *************");
        if (args[0] != null && args[0].getClass() == String.class && args[0].equals("true")) {
            System.out.println("************* 执行导出操作 *************");
            Map<String,Object> map = new HashMap<>();
            User user = userService.findUserByEmail((String) SecurityUtils.getSubject().getPrincipal());
            List deptList = new ArrayList();
            for (int i = 0; i < user.getDept().size(); i++) {
                deptList.add(user.getDept().get(i).getId());
            }
            map.put("deptIdList", deptList);
            if(args[1] != null && args[1] != ""){
                map.put("content",args[1]);
            }
            if(args[2] != null){
                map.put("partnerId",args[2]);
            }
            List<CustomerFinance> customerFinanceList = customerFinanceService.queryCompanyCustomerOverAllFinance(map);
            List<Map<String,Object>> listExport = new ArrayList<>();
            Map<String, Object> mapExport = new HashMap<>();
            mapExport.put("sheetName", "sheet1");
            listExport.add(mapExport);
            for ( int i = 0; i < customerFinanceList.size(); i++ ){
                CustomerFinance customerFinance = customerFinanceList.get(i);
                Map<String, Object> mapValue = new HashMap<>();
                mapValue.put("companyName", customerFinance.getCompanyName());
                mapValue.put("chargeWeekTotleAmount", ExportDataHander.pointsIntoRMB(customerFinance.getChargeWeekTotleAmount()));
                mapValue.put("consumeWeekTotleAmount", ExportDataHander.pointsIntoRMB(customerFinance.getConsumeWeekTotleAmount()));
                mapValue.put("chargeMonthTotleAmount", ExportDataHander.pointsIntoRMB(customerFinance.getChargeMonthTotleAmount()));
                mapValue.put("consumeMonthTotleAmount", ExportDataHander.pointsIntoRMB(customerFinance.getConsumeMonthTotleAmount()));
                mapValue.put("chargeTotleAmount", ExportDataHander.pointsIntoRMB(customerFinance.getChargeTotleAmount()));
                mapValue.put("consumeTotleAmount", ExportDataHander.pointsIntoRMB(customerFinance.getConsumeTotleAmount()));
                mapValue.put("balance", ExportDataHander.pointsIntoRMB(customerFinance.getBalance()));
                listExport.add(mapValue);
            }
            String fileName = "客户财务报表文件";
            String columnNames[]= {"公司名称","上周充值（单位：元）","上周消费（单位：元）","上月充值（单位：元）","上月消费（单位：元）","充值总额（单位：元）","消费总额（单位：元）","余额（单位：元）"};//列名
            String keys[] = {"companyName","chargeWeekTotleAmount","consumeWeekTotleAmount","chargeMonthTotleAmount","consumeMonthTotleAmount","chargeTotleAmount","consumeTotleAmount","balance"};//map中的key
            ExportIoOperate.excelEndOperator(listExport,keys,columnNames,fileName,response);
            return null;
        }
        System.out.println("************* 未执行导出操作 *************");
        return point.proceed(args);
    }


    /**
     * 指定账号充值记录
     * @param point
     */
    @Around("execution(* org.qydata.controller.FinanceController.findAllCustomerRechargeLogByCustomerId(..))")
    public Object findAllCustomerRechargeLogByCustomerIdExportExcel(ProceedingJoinPoint point) throws Throwable {
        Object args [] = point.getArgs();
        System.out.println("************* 判断是否是执行导出操作 *************");
        if (args[1] != null && args[1].getClass() == String.class && args[1].equals("true")) {
            System.out.println("************* 执行导出操作 *************");
            Map<String, Object> map = new HashMap<>();
            if (args[0] != null) {
                map.put("customerId", args[0]);
            }
            if (args[3] != null && args[3] != "" ) {
                map.put("beginDate", args[3]+" "+"00:00:00");
            }
            if(args[4] != null && args[4] != ""){
                map.put("endDate", args[4]+" "+"23:59:59");
            }
            List<Integer> reasonIdList = new ArrayList<>();
            String reasonId [] = (String[]) args[5];
            if (reasonId != null && reasonId.length >0) {
                for(int i=0;i<reasonId.length;i++){
                    System.out.println(reasonId[i]);
                    reasonIdList.add(parseInt(reasonId[i]));
                }
            }else {
                reasonIdList.add(1);
                reasonIdList.add(2);
                reasonIdList.add(3);
            }
            map.put("reasonIdList", reasonIdList);
            List<CustomerBalanceLog> customerBalanceLogList = null;
            Map<String,Object> mapResult = customerFinanceService.queryCompanyCustomerRechargeRecordByCustomerId(map);
            Set<Map.Entry<String,Object>> set = mapResult.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while(it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if(me.getKey().equals("queryCompanyCustomerRechargeRecordByCustomerId") ){
                    customerBalanceLogList = (List<CustomerBalanceLog>) me.getValue();
                }
            }
            List<Map<String,Object>> listExport = new ArrayList<>();
            Map<String, Object> mapExport = new HashMap<>();
            mapExport.put("sheetName", "sheet1");
            listExport.add(mapExport);
            for ( int i = 0; i < customerBalanceLogList.size(); i++ ){
                CustomerBalanceLog customerBalanceLog = customerBalanceLogList.get(i);
                Map<String, Object> mapValue = new HashMap<>();
                mapValue.put("amount", ExportDataHander.pointsIntoRMB(customerBalanceLog.getAmount()));
                mapValue.put("createTime", customerBalanceLog.getCreateTime());
                mapValue.put("reasonName", customerBalanceLog.getCustomerBalanceModifyReason().getName());
                listExport.add(mapValue);
            }
            String fileName = args[2] +"充值记录";
            String columnNames[]= {"金额（单位：元）","时间","理由"};//列名
            String keys[] = {"amount","createTime","reasonName"};//map中的key
            ExportIoOperate.excelEndOperator(listExport,keys,columnNames,fileName,response);
            return null;
        }
        System.out.println("************* 未执行导出操作 *************");
        return point.proceed(args);
    }



}
