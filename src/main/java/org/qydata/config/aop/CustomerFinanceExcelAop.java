package org.qydata.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.qydata.dst.CustomerApiType;
import org.qydata.dst.CustomerApiVendor;
import org.qydata.dst.CustomerFinance;
import org.qydata.entity.CustomerBalanceLog;
import org.qydata.entity.User;
import org.qydata.entity.WeekMonthAmount;
import org.qydata.service.CustomerFinanceService;
import org.qydata.service.PowerUserService;
import org.qydata.service.RoleService;
import org.qydata.tools.CalendarTools;
import org.qydata.tools.ExportDataHander;
import org.qydata.tools.ExportIoOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * Created by jonhn on 2017/3/16.
 */
@Aspect
@Component
@Slf4j
public class CustomerFinanceExcelAop {

    @Autowired private CustomerFinanceService customerFinanceService;

    @Autowired private HttpServletResponse response;

    @Autowired private HttpServletRequest request;

    @Autowired private RoleService roleService;

    @Autowired private PowerUserService powerUserService;
    /**
     * 公司财务账单导出Excel
     * @param point
     */
    @Around("execution(* org.qydata.controller.FinanceController.queryAllCustomer(..))")
    public Object queryAllCustomerExportExcel(ProceedingJoinPoint point) throws Throwable {
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
            if (args[3] != null && args[3] != "" ) {
                map.put("beginDate", sdf.format(sdfInput.parse((String) args[3]))+" "+"00:00:00");
            }
            if(args[4] != null && args[4] != ""){
                map.put("endDate", sdf.format(sdfInput.parse((String) args[4]))+" "+"23:59:59");
            }
            List statusList = new ArrayList();
            String status [] = (String[]) args[5];
            if (status != null && status.length >0) {
                for(int i=0;i<status.length;i++){
                    statusList.add(status[i]);
                }
            }else {
                statusList.add(0);
                statusList.add(-1);
            }
            map.put("statusList", statusList);
            map.put("currDayTime",sdf.format(new Date()) + " " + "00:00:00");
            map.put("currMonthTime", CalendarTools.getCurrentMonthFirstDay() + " " + "00:00:00");
            List<CustomerFinance> customerFinanceList = null;
            Map<String,Object> mapResult = customerFinanceService.queryCompanyCustomerOverAllFinance(map);
            Set<Map.Entry<String,Object>> set = mapResult.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while(it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if (me.getKey().equals("customerFinanceList")){
                    customerFinanceList = (List<CustomerFinance>) me.getValue();
                }
            }
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
                mapValue.put("currMonthAmount", ExportDataHander.pointsIntoRMB(customerFinance.getCurrMonthAmount()));
                mapValue.put("currDayAmount", ExportDataHander.pointsIntoRMB(customerFinance.getCurrDayAmount()));
                listExport.add(mapValue);
            }
            String fileName = "客户财务报表文件";
            String columnNames[]= {"公司名称","合作公司","上周充值（单位：元）","上周消费（单位：元）","上月充值（单位：元）","上月消费（单位：元）","本月消费（单位：元）","当天消费（单位：元）","充值总额（单位：元）","消费总额（单位：元）","余额（单位：元）"};//列名
            String keys[] = {"companyName","partnerName","chargeWeekTotleAmount","consumeWeekTotleAmount","chargeMonthTotleAmount","consumeMonthTotleAmount","currMonthAmount","currDayAmount","chargeTotleAmount","consumeTotleAmount","balance"};//map中的key
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
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Object args [] = point.getArgs();
        System.out.println("************* 判断是否是执行导出操作 *************");
        if (args[0] != null && args[0].getClass() == String.class && args[0].equals("true")) {
            System.out.println("************* 执行导出操作 *************");
            Map<String,Object> map = new HashMap<>();
            User user = powerUserService.findUserByEmail((String) SecurityUtils.getSubject().getPrincipal());
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
            if (args[3] != null && args[3] != "" ) {
                map.put("beginDate", sdf.format(sdfInput.parse((String) args[3]))+" "+"00:00:00");
            }
            if(args[4] != null && args[4] != ""){
                map.put("endDate", sdf.format(sdfInput.parse((String) args[4]))+" "+"23:59:59");
            }
            List statusList = new ArrayList();
            String status [] = (String[]) args[5];
            if (status != null && status.length >0) {
                for(int i=0;i<status.length;i++){
                    statusList.add(status[i]);
                }
            }else {
                statusList.add(0);
                statusList.add(-1);
            }
            map.put("statusList", statusList);
            map.put("currDayTime",sdf.format(new Date()) + " " + "00:00:00");
            map.put("currMonthTime",CalendarTools.getCurrentMonthFirstDay() + " " + "00:00:00");
            List<CustomerFinance> customerFinanceList = null;
            Map<String,Object> mapResult = customerFinanceService.queryCompanyCustomerOverAllFinance(map);
            Set<Map.Entry<String,Object>> set = mapResult.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while(it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if (me.getKey().equals("customerFinanceList")){
                    customerFinanceList = (List<CustomerFinance>) me.getValue();
                }
            }
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
                mapValue.put("currMonthAmount", ExportDataHander.pointsIntoRMB(customerFinance.getCurrMonthAmount()));
                mapValue.put("currDayAmount", ExportDataHander.pointsIntoRMB(customerFinance.getCurrDayAmount()));
                listExport.add(mapValue);
            }
            String fileName = "客户财务报表文件";
            String columnNames[]= {"公司名称","合作公司","上周充值（单位：元）","上周消费（单位：元）","上月充值（单位：元）","上月消费（单位：元）","本月消费（单位：元）","当天消费（单位：元）","充值总额（单位：元）","消费总额（单位：元）","余额（单位：元）"};//列名
            String keys[] = {"companyName","partnerName","chargeWeekTotleAmount","consumeWeekTotleAmount","chargeMonthTotleAmount","consumeMonthTotleAmount","currMonthAmount","currDayAmount","chargeTotleAmount","consumeTotleAmount","balance"};//map中的key
            ExportIoOperate.excelEndOperator(listExport,keys,columnNames,fileName,response);
            return null;
        }
        System.out.println("************* 未执行导出操作 *************");
        return point.proceed(args);
    }


    /**
     * 指定账号充值记录导出Excel
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
            String fileName = args[2]+"充值记录";
            String columnNames[]= {"金额（单位：元）","时间","理由"};//列名
            String keys[] = {"amount","createTime","reasonName"};//map中的key
            ExportIoOperate.excelEndOperator(listExport,keys,columnNames,fileName,response);
            return null;
        }
        System.out.println("************* 未执行导出操作 *************");
        return point.proceed(args);
    }

    /**
     * 指定账号消费记录导出Excel
     * @param point
     */
    @Around("execution(* org.qydata.controller.FinanceController.findAllApiConsumeRecordByCustomerId(..))")
    public Object findAllApiConsumeRecordByCustomerIdExportExcel(ProceedingJoinPoint point) throws Throwable {
        Object args [] = point.getArgs();
        System.out.println("************* 判断是否是执行导出操作 *************");
        if (args[1] != null && args[1].getClass() == String.class && args[1].equals("true")) {
            System.out.println("************* 执行导出操作 *************");
            Map<String,Object> map = new HashMap();
            if (args[0] != null){
                map.put("customerId",args[0]);
            }
            if(args[2] != null){
                map.put("apiTypeId",args[2]);
            }
            if(args[3] != null){
                map.put("apiVendorId",args[3]);
            }
            List<CustomerApiType> customerApiTypeList = null;
            Map<String,Object> mapResult = customerFinanceService.queryCompanyCustomerApiConsumeRecordByCustomerId(map);
            Set<Map.Entry<String,Object>> set = mapResult.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while(it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if(me.getKey().equals("queryCompanyCustomerApiConsumeRecordByCustomerId") ){
                    customerApiTypeList = (List<CustomerApiType>) me.getValue();
                }
            }
            List<Map<String,Object>> listExport = new ArrayList<>();
            Map<String, Object> mapExport = new HashMap<>();
            mapExport.put("sheetName", "sheet1");
            listExport.add(mapExport);
            for ( int i = 0; i < customerApiTypeList.size(); i++ ){
                CustomerApiType customerApiType = customerApiTypeList.get(i);
                Map<String, Object> mapValue = new HashMap<>();
                mapValue.put("apiType", customerApiType.getApiTypeName());

                StringBuffer apiVendorName = new StringBuffer();
                for (int j=0; j < customerApiType.getCustomerApiVendors().size(); j++){
                    CustomerApiVendor customerApiVendor = customerApiType.getCustomerApiVendors().get(j);
                    if(customerApiVendor.getVendorName() != null) {
                        apiVendorName = new StringBuffer(customerApiVendor.getVendorName() + "," + apiVendorName);
                    }
                }
                mapValue.put("apiVendor", apiVendorName);
                mapValue.put("price", ExportDataHander.pointsIntoRMB(customerApiType.getTotlePrice()));
                listExport.add(mapValue);
            }
            String fileName = args[4]+"消费记录";
            if (SecurityUtils.getSubject().hasRole("sell")){
                String columnNames [] = {"产品类型","金额（单位：元）"};//列名
                String keys [] = {"apiType","price"};//map中的key
                ExportIoOperate.excelEndOperator(listExport,keys,columnNames,fileName,response);
                return null;
            }
            String columnNames [] = {"产品类型","产品供应商","金额（单位：元）"};//列名
            String keys [] = {"apiType","apiVendor","price"};//map中的key
            ExportIoOperate.excelEndOperator(listExport,keys,columnNames,fileName,response);
            return null;
        }
        System.out.println("************* 未执行导出操作 *************");
        return point.proceed(args);
    }

    /**
     * 指定账号消费明细记录导出Excel
     * @param point
     */
    @Around("execution(* org.qydata.controller.FinanceController.findAllApiConsumeDetailRecordByCustomerId(..))")
    public Object findAllApiConsumeDetailRecordByCustomerIdExportExcel(ProceedingJoinPoint point) throws Throwable {
        Object args [] = point.getArgs();
        System.out.println("************* 判断是否是执行导出操作 *************");
        if (args[1] != null && args[1].getClass() == String.class && args[1].equals("true")) {
            System.out.println("************* 执行导出操作 *************");
            Map<String,Object> map = new HashMap();
            if (args[0] != null){
                map.put("customerId",args[0]);
            }
            if(args[2] != null){
                map.put("apiTypeId",args[2]);
            }
            if (args[6] != null && args[6] != "" ) {
                map.put("beginDate", args[6]+" "+"00:00:00");
            }
            if(args[7] != null && args[7] != ""){
                map.put("endDate", args[7]+" "+"23:59:59");
            }
            List<Integer> reasonIdList = new ArrayList<>();
            Integer reasonId [] = (Integer[]) args[5];
            if (reasonId != null && reasonId.length > 0) {
                for(int i = 0;i <  reasonId.length;i++){
                    reasonIdList.add(reasonId[i]);
                }
            }else{
                reasonIdList.add(-1);
                reasonIdList.add(-2);
                reasonIdList.add(-3);
            }
            map.put("reasonIdList", reasonIdList);
            List<CustomerBalanceLog> customerBalanceLogList = null;
            Map<String,Object> mapResult = customerFinanceService.queryCompanyCustomerApiDetailConsumeRecordByCustomerId(map);
            Set<Map.Entry<String,Object>> set = mapResult.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while(it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if(me.getKey().equals("queryCompanyCustomerApiDetailConsumeRecordByCustomerId") ){
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
                if(customerBalanceLog.getApiType() != null){
                    if (customerBalanceLog.getMobileOperator() != null){
                        mapValue.put("apiName", customerBalanceLog.getApiType().getName()+"——"+customerBalanceLog.getMobileOperator().getName());
                    }else {
                        mapValue.put("apiName", customerBalanceLog.getApiType().getName());
                    }
                }else {
                    mapValue.put("apiName","");
                }
                mapValue.put("amount", ExportDataHander.pointsIntoRMB(customerBalanceLog.getAmount()));
                mapValue.put("createTime", customerBalanceLog.getCreateTime());
                if(customerBalanceLog.getCustomerBalanceModifyReason() != null){
                    mapValue.put("reasonName", customerBalanceLog.getCustomerBalanceModifyReason().getName());
                }else{
                    mapValue.put("reasonName", "");
                }
                listExport.add(mapValue);
            }
            String fileName = args[3]+""+args[4]+"消费明细记录";
            String columnNames[]= {"产品","消费金额（单位：元）","创建时间","类型"};//列名
            String keys[] = {"apiName","amount","createTime","reasonName"};//map中的key
            ExportIoOperate.excelEndOperator(listExport,keys,columnNames,fileName,response);
            return null;
        }
        System.out.println("************* 未执行导出操作 *************");
        return point.proceed(args);
    }

    /**
     * 周历史记录导出Excel
     * @param point
     */
    @Around("execution(* org.qydata.controller.FinanceController.findWeekRecordByCustomerId(..))")
    public Object findWeekRecordByCustomerIdExportExcel(ProceedingJoinPoint point) throws Throwable {
        Object args [] = point.getArgs();
        System.out.println("************* 判断是否是执行导出操作 *************");
        if (args[1] != null && args[1].getClass() == String.class && args[1].equals("true")) {
            System.out.println("************* 执行导出操作 *************");
            Map<String,Object> map = new HashMap();
            if (args[0] != null){
                map.put("customerId",args[0]);
            }
            map.put("weekMonthTypeId",1);
            List<Integer> tableIdList = new ArrayList();
            if (args[5] != null){
                tableIdList.add((Integer) args[5]);
            }
            map.put("tableIdList",tableIdList);
            if(args[2] != null){
                map.put("years",args[2]);
            }
            if(args[3] != null){
                map.put("months",args[3]);
            }
            if(args[4] != null){
                map.put("weeks",args[4]);
            }
            List<WeekMonthAmount> weekMonthAmountList = null;
            Map<String,Object> mapResult = customerFinanceService.queryCompanyCustomerWeekMonthRecordByCustomerId(map);
            Set<Map.Entry<String,Object>> set = mapResult.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while(it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if(me.getKey().equals("queryCompanyCustomerWeekMonthRecordByCustomerId") ){
                    weekMonthAmountList = (List<WeekMonthAmount>) me.getValue();
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<Map<String,Object>> listExport = new ArrayList<>();
            Map<String, Object> mapExport = new HashMap<>();
            mapExport.put("sheetName", "sheet1");
            listExport.add(mapExport);
            for ( int i = 0; i < weekMonthAmountList.size(); i++ ){
                WeekMonthAmount weekMonthAmount = weekMonthAmountList.get(i);
                Map<String, Object> mapValue = new HashMap<>();
                mapValue.put("yearMonthWeek", weekMonthAmount.getYears()+"年第"+weekMonthAmount.getMonths()+"月第"+weekMonthAmount.getWeeks()+"周");
                mapValue.put("totleAmount", weekMonthAmount.getTotleAmount()/100.0);
                mapValue.put("beginTime", sdf.format(weekMonthAmount.getBeginTime()));
                mapValue.put("endTime", sdf.format(weekMonthAmount.getEndTime()));
                listExport.add(mapValue);
            }
            String fileName = args[6]+"周历史记录";
            String columnNames[]= {"周期","金额（单位：元）","开始时间","结束时间"};//列名
            String keys[] = {"yearMonthWeek","totleAmount","beginTime","endTime"};//map中的key
            ExportIoOperate.excelEndOperator(listExport,keys,columnNames,fileName,response);
            return null;
        }
        System.out.println("************* 未执行导出操作 *************");
        return point.proceed(args);
    }

    /**
     * 月历史记录导出Excel
     * @param point
     */
    @Around("execution(* org.qydata.controller.FinanceController.findMonthRecordByCustomerId(..))")
    public Object findMonthRecordByCustomerIdExportExcel(ProceedingJoinPoint point) throws Throwable {
        Object args [] = point.getArgs();
        System.out.println("************* 判断是否是执行导出操作 *************");
        if (args[1] != null && args[1].getClass() == String.class && args[1].equals("true")) {
            System.out.println("************* 执行导出操作 *************");
            Map<String,Object> map = new HashMap();
            if (args[0] != null){
                map.put("customerId",args[0]);
            }
            map.put("weekMonthTypeId",2);
            List<Integer> tableIdList = new ArrayList();
            if (args[4] != null){
                tableIdList.add((Integer) args[4]);
            }
            map.put("tableIdList",tableIdList);
            if(args[2] != null){
                map.put("years",args[2]);
            }
            if(args[3] != null){
                map.put("months",args[3]);
            }
            List<WeekMonthAmount> weekMonthAmountList = null;
            Map<String,Object> mapResult = customerFinanceService.queryCompanyCustomerWeekMonthRecordByCustomerId(map);
            Set<Map.Entry<String,Object>> set = mapResult.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while(it.hasNext()){
                Map.Entry<String,Object> me = it.next();
                if(me.getKey().equals("queryCompanyCustomerWeekMonthRecordByCustomerId") ){
                    weekMonthAmountList = (List<WeekMonthAmount>) me.getValue();
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<Map<String,Object>> listExport = new ArrayList<>();
            Map<String, Object> mapExport = new HashMap<>();
            mapExport.put("sheetName", "sheet1");
            listExport.add(mapExport);
            for ( int i = 0; i < weekMonthAmountList.size(); i++ ){
                WeekMonthAmount weekMonthAmount = weekMonthAmountList.get(i);
                Map<String, Object> mapValue = new HashMap<>();
                mapValue.put("yearMonthWeek", weekMonthAmount.getYears()+"年第"+weekMonthAmount.getMonths()+"月");
                mapValue.put("totleAmount", weekMonthAmount.getTotleAmount()/100.0);
                mapValue.put("beginTime", sdf.format(weekMonthAmount.getBeginTime()));
                mapValue.put("endTime", sdf.format(weekMonthAmount.getEndTime()));
                listExport.add(mapValue);
            }
            String fileName = args[5]+"月历史记录";
            String columnNames[]= {"周期","金额（单位：元）","开始时间","结束时间"};//列名
            String keys[] = {"yearMonthWeek","totleAmount","beginTime","endTime"};//map中的key
            ExportIoOperate.excelEndOperator(listExport,keys,columnNames,fileName,response);
            return null;
        }
        System.out.println("************* 未执行导出操作 *************");
        return point.proceed(args);
    }


}
