package org.qydata.exportexcelcontroller;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.qydata.dst.CustomerApiType;
import org.qydata.dst.CustomerApiVendor;
import org.qydata.dst.CustomerFinance;
import org.qydata.entity.CustomerBalanceLog;
import org.qydata.entity.Dept;
import org.qydata.entity.User;
import org.qydata.entity.WeekMonthAmount;
import org.qydata.service.CustomerFinanceService;
import org.qydata.service.UserService;
import org.qydata.tools.ExportIoOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * Created by Administrator on 2017/1/10.
 */
@Controller
@RequestMapping("/excel-finance")
public class ExcelFinanceController {

    private final Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerFinanceService customerFinanceService;
    /**
     * 查找公司财务账单
     * @param request
     * @return
     */
    @RequestMapping(value = "/find-all-customer")
    public void findAllCustomer(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String companyName = request.getParameter("content");
        Map<String,Object> map = new HashMap<String,Object>();
        List customerTypeIdList = new ArrayList();
        if(companyName!=null){
            map.put("content",companyName);
        }
        customerTypeIdList.add(1);
        map.put("customerTypeIdList",customerTypeIdList);

        List<CustomerFinance> customerFinances = null;
        try {
            customerFinances  =customerFinanceService.queryCompanyCustomerOverAllFinance(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> mapA = new HashMap<String, Object>();
        mapA.put("sheetName", "sheet1");
        list.add(mapA);
        CustomerFinance customerFinance=null;
        for (int j = 0; j < customerFinances.size(); j++) {
            customerFinance = customerFinances.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("companyName", customerFinance.getCompanyName());
            if(customerFinance.getChargeWeekTotleAmount() != null){
                mapValue.put("chargeWeekTotleAmount", customerFinance.getChargeWeekTotleAmount()/100.0);
            }else {
                mapValue.put("chargeWeekTotleAmount", 0.0);
            }
            if(customerFinance.getConsumeWeekTotleAmount() != null){
                mapValue.put("consumeWeekTotleAmount", customerFinance.getConsumeWeekTotleAmount()/100.0);
            }else {
                mapValue.put("consumeWeekTotleAmount", 0.0);
            }
            if(customerFinance.getChargeMonthTotleAmount() != null){
                mapValue.put("chargeMonthTotleAmount", customerFinance.getChargeMonthTotleAmount()/100.0);
            }else {
                mapValue.put("chargeMonthTotleAmount", 0.0);
            }
            if(customerFinance.getConsumeMonthTotleAmount() != null){
                mapValue.put("consumeMonthTotleAmount", customerFinance.getConsumeMonthTotleAmount()/100.0);
            }else {
                mapValue.put("consumeMonthTotleAmount", 0.0);
            }
            if(customerFinance.getChargeTotleAmount() != null){
                mapValue.put("chargeTotleAmount", customerFinance.getChargeTotleAmount()/100.0);
            }else {
                mapValue.put("chargeTotleAmount", 0.0);
            }
            if(customerFinance.getConsumeTotleAmount() != null){
                mapValue.put("consumeTotleAmount", customerFinance.getConsumeTotleAmount()/100.0);
            }else {
                mapValue.put("consumeTotleAmount", 0.0);
            }
            if(customerFinance.getBalance() != null){
                mapValue.put("balance", customerFinance.getBalance()/100.0);
            }else {
                mapValue.put("balance", 0.0);
            }
            list.add(mapValue);
        }
        String fileName = "客户财务报表文件";
        String columnNames[]= {"公司名称","周充值（单位：元）","周消费（单位：元）","月充值（单位：元）","月消费（单位：元）","充值总额（单位：元）","消费总额（单位：元）","余额（单位：元）"};//列名
        String keys[] = {"companyName","chargeWeekTotleAmount","consumeWeekTotleAmount","chargeMonthTotleAmount","consumeMonthTotleAmount","chargeTotleAmount","consumeTotleAmount","balance"};//map中的key
        ExportIoOperate.excelEndOperator(list,keys,columnNames,fileName,response);
    }
    /**
     * 通过部门编号查找公司财务账单
     * @param request
     * @return
     */
    @RequestMapping("/find-all-customer-by-dept-id")
    public void findAllCustomerByDeptId(String username,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String companyName = request.getParameter("content");
        User user = null;
        try {
            user = userService.findUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Dept> deptList = user.getDept();
        List deptIdList = new ArrayList();
        if (deptList.size() > 0) {
            for (int i = 0; i < deptList.size(); i++) {
                deptIdList.add(deptList.get(i).getId());
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deptIdList", deptIdList);
            if (companyName != null) {
                map.put("content", companyName);
            }
            List customerTypeIdList = new ArrayList();
            customerTypeIdList.add(1);
            map.put("customerTypeIdList", customerTypeIdList);
            List<CustomerFinance> customerFinances = null;
            try {
                customerFinances = customerFinanceService.queryCompanyCustomerOverAllFinance(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<Map<String,Object>> list = new ArrayList<>();
            Map<String, Object> mapA = new HashMap<String, Object>();
            mapA.put("sheetName", "sheet1");
            list.add(mapA);
            CustomerFinance customerFinance=null;
            for (int j = 0; j < customerFinances.size(); j++) {
                customerFinance = customerFinances.get(j);
                Map<String, Object> mapValue = new HashMap<String, Object>();
                mapValue.put("companyName", customerFinance.getCompanyName());
                if(customerFinance.getChargeWeekTotleAmount() != null){
                    mapValue.put("chargeWeekTotleAmount", customerFinance.getChargeWeekTotleAmount()/100.0);
                }else {
                    mapValue.put("chargeWeekTotleAmount", 0.0);
                }
                if(customerFinance.getConsumeWeekTotleAmount() != null){
                    mapValue.put("consumeWeekTotleAmount", customerFinance.getConsumeWeekTotleAmount()/100.0);
                }else {
                    mapValue.put("consumeWeekTotleAmount", 0.0);
                }
                if(customerFinance.getChargeMonthTotleAmount() != null){
                    mapValue.put("chargeMonthTotleAmount", customerFinance.getChargeMonthTotleAmount()/100.0);
                }else {
                    mapValue.put("chargeMonthTotleAmount", 0.0);
                }
                if(customerFinance.getConsumeMonthTotleAmount() != null){
                    mapValue.put("consumeMonthTotleAmount", customerFinance.getConsumeMonthTotleAmount()/100.0);
                }else {
                    mapValue.put("consumeMonthTotleAmount", 0.0);
                }
                if(customerFinance.getChargeTotleAmount() != null){
                    mapValue.put("chargeTotleAmount", customerFinance.getChargeTotleAmount()/100.0);
                }else {
                    mapValue.put("chargeTotleAmount", 0.0);
                }
                if(customerFinance.getConsumeTotleAmount() != null){
                    mapValue.put("consumeTotleAmount", customerFinance.getConsumeTotleAmount()/100.0);
                }else {
                    mapValue.put("consumeTotleAmount", 0.0);
                }
                if(customerFinance.getBalance() != null){
                    mapValue.put("balance", customerFinance.getBalance()/100.0);
                }else {
                    mapValue.put("balance", 0.0);
                }
                list.add(mapValue);
            }
            String fileName = "客户财务报表文件";
            String columnNames[]= {"公司名称","周充值（单位：元）","周消费（单位：元）","月充值（单位：元）","月消费（单位：元）","充值总额（单位：元）","消费总额（单位：元）","余额（单位：元）"};//列名
            String keys[] = {"companyName","chargeWeekTotleAmount","consumeWeekTotleAmount","chargeMonthTotleAmount","consumeMonthTotleAmount","chargeTotleAmount","consumeTotleAmount","balance"};//map中的key
            ExportIoOperate.excelEndOperator(list,keys,columnNames,fileName,response);
        }
    }
    /**
     * 指定账号充值记录
     * @param customerId
     * @param beginDate
     * @param endDate
     * @param reasonId
     * @param request
     * @return
     */
    @RequestMapping(value = "/find-all-customer/find-all-customer-recharge-log-by-customer-id")
    public void findAllCustomerRechargeLogByCustomerId(HttpServletRequest request,HttpServletResponse response,Integer customerId,String companyName, String beginDate, String endDate, String [] reasonId) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("customerId", customerId);
        List<Integer> reasonIdList = new ArrayList<>();
        if (reasonId != null && reasonId.length >0) {
            for(int i=0;i<reasonId.length;i++){
                reasonIdList.add(parseInt(reasonId[i]));
            }
        }else {
            reasonIdList.add(1);
            reasonIdList.add(2);
            reasonIdList.add(3);
        }
        map.put("reasonIdList", reasonIdList);
        if (beginDate != null && beginDate != "" ) {
            map.put("beginDate", beginDate+" "+"00:00:00");
        }
        if(endDate != null && endDate != ""){
            map.put("endDate", endDate+" "+"23:59:59");
        }
        List<CustomerBalanceLog> customerBalanceLogList = null;
        try {
            customerBalanceLogList = customerFinanceService.queryCompanyCustomerRechargeRecordByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> mapA = new HashMap<String, Object>();
        mapA.put("sheetName", "sheet1");
        list.add(mapA);
        CustomerBalanceLog customerBalanceLog=null;
        for (int j = 0; j < customerBalanceLogList.size(); j++) {
            customerBalanceLog = customerBalanceLogList.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();

            if (customerBalanceLog.getAmount() != null){
                mapValue.put("amount", customerBalanceLog.getAmount()/100.0);
            }else{
                mapValue.put("amount", 0.0);
            }
            mapValue.put("createTime", customerBalanceLog.getCreateTime());
            mapValue.put("reasonName", customerBalanceLog.getCustomerBalanceModifyReason().getName());
            list.add(mapValue);
        }
        String fileName = companyName+"充值记录";
        String columnNames[]= {"金额（单位：元）","时间","理由"};//列名
        String keys[] = {"amount","createTime","reasonName"};//map中的key
        ExportIoOperate.excelEndOperator(list,keys,columnNames,fileName,response);
    }
    /**
     * 指定账号Api消费记录
     * @return
     */
    @RequestMapping("/find-all-customer/find-all-customer-api-consume-record-by-customer-id")
    public void findAllApiConsumeRecordByCustomerId(Integer customerId,Integer apiTypeId,Integer apiVendorId,String companyName,HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        if(apiTypeId != null){
            map.put("apiTypeId",apiTypeId);
        }
        if(apiVendorId != null){
            map.put("apiVendorId",apiVendorId);
        }
        List<CustomerApiType> customerApiTypeList = null;
        try {
            customerApiTypeList = customerFinanceService.queryCompanyCustomerApiConsumeRecordByCustomerId(map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> mapA = new HashMap<String, Object>();
        mapA.put("sheetName", "sheet1");
        list.add(mapA);
        CustomerApiType customerApiType = null;
        for (int j = 0; j < customerApiTypeList.size(); j++) {
            customerApiType = customerApiTypeList.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("apiType", customerApiType.getApiTypeName());
            List<CustomerApiVendor> customerApiVendorList = customerApiType.getCustomerApiVendors();
            StringBuffer apiVendorName = new StringBuffer();
            for (int i=0; i<customerApiVendorList.size(); i++){
                CustomerApiVendor customerApiVendor = customerApiVendorList.get(i);
                if(customerApiVendor.getVendorName() != null) {
                    apiVendorName = new StringBuffer(customerApiVendor.getVendorName() + "," + apiVendorName);
                }
            }
            mapValue.put("apiVendor", apiVendorName);
            if(customerApiType.getTotlePrice() != null){
                mapValue.put("price", customerApiType.getTotlePrice()/100.0);
            }else{
                mapValue.put("price", 0.0);
            }
            list.add(mapValue);
        }
        String fileName = companyName+"消费记录";
        String columnNames[]= {"产品类型","产品供应商","金额（单位：元）"};//列名
        String keys[] = {"apiType","apiVendor","price"};//map中的key
        ExportIoOperate.excelEndOperator(list,keys,columnNames,fileName,response);
    }
    /**
     * 指定账号Api消费明细记录
     * @param customerId
     * @param apiTypeId
     * @param companyName
     * @return
     */
    @RequestMapping("/find-all-customer/find-all-customer-api-consume-record-by-customer-id/detail")
    public void findAllApiConsumeDetailRecordByCustomerId(Integer customerId, Integer apiTypeId, String companyName,Integer [] reasonId,String beginDate,String endDate,HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("apiTypeId",apiTypeId);
        if (beginDate != null && beginDate != "" ) {
            map.put("beginDate", beginDate+" "+"00:00:00");
        }
        if(endDate != null && endDate != ""){
            map.put("endDate", endDate+" "+"23:59:59");
        }
        List<Integer> reasonIdList = new ArrayList<>();
        if (reasonId != null && reasonId.length >0) {
            for(int i=0;i<reasonId.length;i++){
                reasonIdList.add(reasonId[i]);
            }
        }else {
            reasonIdList.add(-1);
            reasonIdList.add(-2);
        }
        map.put("reasonIdList", reasonIdList);
        List<CustomerBalanceLog> customerBalanceLogList = null;
        try {
            customerBalanceLogList = customerFinanceService.queryCompanyCustomerApiDetailConsumeRecordByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> mapA = new HashMap<String, Object>();
        mapA.put("sheetName", "sheet1");
        list.add(mapA);
        CustomerBalanceLog customerBalanceLog = null;
        for (int j = 0; j < customerBalanceLogList.size(); j++) {
            customerBalanceLog = customerBalanceLogList.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();

            if(customerBalanceLog.getApi() != null && customerBalanceLog.getApi().getName() != null ){
                mapValue.put("apiName", customerBalanceLog.getApi().getName());
            }else{
                mapValue.put("apiName", "");
            }
            if(customerBalanceLog.getAmount() != null){
                mapValue.put("amount", customerBalanceLog.getAmount()/100.0);
            }else{
                mapValue.put("amount", 0.0);
            }
            if(customerBalanceLog.getCreateTime() != null){
                mapValue.put("createTime", customerBalanceLog.getCreateTime());
            }else{
                mapValue.put("createTime", "");
            }
            if(customerBalanceLog.getCustomerBalanceModifyReason() != null && customerBalanceLog.getCustomerBalanceModifyReason().getName() != null ){
                mapValue.put("reasonName", customerBalanceLog.getCustomerBalanceModifyReason().getName());
            }else{
                mapValue.put("reasonName", "");
            }
            list.add(mapValue);
        }
        String fileName = companyName+"消费明细记录";
        String columnNames[]= {"产品名称","消费金额（单位：元）","创建时间","类型"};//列名
        String keys[] = {"apiName","amount","createTime","reasonName"};//map中的key
        ExportIoOperate.excelEndOperator(list,keys,columnNames,fileName,response);
    }
    /**
     * 周历史记录
     * @param customerId
     * @param years
     * @param months
     * @param weeks
     * @param typeId
     * @param companyName
     * @return
     */
    @RequestMapping("/find-all-customer/find-week-record-by-customer-id")
    public void findWeekRecordByCustomerId(String companyName,Integer typeId,Integer customerId,Integer years,Integer months,Integer weeks,HttpServletResponse  response) throws IOException {
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("weekMonthTypeId",1);
        List<Integer> tableIdList = new ArrayList();
        tableIdList.add(typeId);
        map.put("tableIdList",tableIdList);

        if(years != null){
            map.put("years",years);

        }
        if(months != null){
            map.put("months",months);
        }
        if(weeks != null){
            map.put("weeks",weeks);
        }
        List<WeekMonthAmount> weekMonthAmountList = null;
        try {
            weekMonthAmountList = customerFinanceService.queryCompanyCustomerWeekMonthRecordByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> mapA = new HashMap<String, Object>();
        mapA.put("sheetName", "sheet1");
        list.add(mapA);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        WeekMonthAmount weekMonthAmount = null;
        for (int j = 0; j < weekMonthAmountList.size(); j++) {
            weekMonthAmount = weekMonthAmountList.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();

            mapValue.put("yearMonthWeek", weekMonthAmount.getYears()+"年第"+weekMonthAmount.getMonths()+"月第"+weekMonthAmount.getWeeks()+"周");
            if(weekMonthAmount.getTotleAmount() != null){
                mapValue.put("totleAmount", weekMonthAmount.getTotleAmount()/100.0);
            }else{
                mapValue.put("totleAmount", 0.0);
            }
            mapValue.put("beginTime", sdf.format(weekMonthAmount.getBeginTime()));
            mapValue.put("endTime", sdf.format(weekMonthAmount.getEndTime()));

            list.add(mapValue);
        }
        String fileName = companyName+"周历史记录";
        String columnNames[]= {"周期","金额（单位：元）","开始时间","结束时间"};//列名
        String keys[] = {"yearMonthWeek","totleAmount","beginTime","endTime"};//map中的key
        ExportIoOperate.excelEndOperator(list,keys,columnNames,fileName,response);
    }
    /**
     * 月历史记录
     * @param customerId
     * @param years
     * @param months
     * @param typeId
     * @param companyName
     * @return
     */
    @RequestMapping("/find-all-customer/find-month-record-by-customer-id")
    public void findMonthRecordByCustomerId(String companyName,Integer typeId,Integer customerId,Integer years,Integer months,HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("weekMonthTypeId",2);
        List<Integer> tableIdList = new ArrayList();
        tableIdList.add(typeId);
        map.put("tableIdList",tableIdList);
        if(years != null){
            map.put("years",years);
        }
        if(months != null){
            map.put("months",months);
        }
        List<WeekMonthAmount> weekMonthAmountList = null;
        try {
            weekMonthAmountList = customerFinanceService.queryCompanyCustomerWeekMonthRecordByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> mapA = new HashMap<String, Object>();
        mapA.put("sheetName", "sheet1");
        list.add(mapA);
        WeekMonthAmount weekMonthAmount = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int j = 0; j < weekMonthAmountList.size(); j++) {
            weekMonthAmount = weekMonthAmountList.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();

            mapValue.put("yearMonthWeek", weekMonthAmount.getYears()+"年第"+weekMonthAmount.getMonths()+"月");
            if(weekMonthAmount.getTotleAmount() != null){
                mapValue.put("totleAmount", weekMonthAmount.getTotleAmount()/100.0);
            }else{
                mapValue.put("totleAmount", 0.0);
            }
            mapValue.put("beginTime", sdf.format(weekMonthAmount.getBeginTime()));
            mapValue.put("endTime", sdf.format(weekMonthAmount.getEndTime()));
            list.add(mapValue);
        }
        String fileName = companyName+"月历史记录";
        String columnNames[]= {"周期","金额（单位：元）","开始时间","结束时间"};//列名
        String keys[] = {"yearMonthWeek","totleAmount","beginTime","endTime"};//map中的key
        ExportIoOperate.excelEndOperator(list,keys,columnNames,fileName,response);
    }

}
