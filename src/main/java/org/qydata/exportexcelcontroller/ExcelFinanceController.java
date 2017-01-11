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
import org.qydata.tools.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }
    /**
     * 通过部门编号查找公司财务账单
     * @param request
     * @return
     */
    @RequestMapping(value = ("/find-all-customer-by-dept-id"))
    public void findAllCustomerByDeptId(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String companyName = request.getParameter("content");
        User user = (User)request.getSession().getAttribute("userInfo");
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
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            ServletOutputStream out = null;
            try {
                out = response.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (final IOException e) {
                throw e;
            } finally {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            }
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
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
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
                apiVendorName =new StringBuffer(customerApiVendor.getVendorName()+","+apiVendorName);
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
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }
    /**
     * 指定账号Api消费明细记录
     * @param customerId
     * @param apiTypeId
     * @param companyName
     * @return
     */
    @RequestMapping("/find-all-customer/find-all-customer-api-consume-record-by-customer-id/detail")
    public void findAllApiConsumeDetailRecordByCustomerId(Integer customerId, Integer apiTypeId, String companyName,HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("apiTypeId",apiTypeId);
        List<CustomerApiVendor> customerApiVendorList = null;
        try {
            customerApiVendorList = customerFinanceService.queryCompanyCustomerApiDetailConsumeRecordByCustomerId(map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> mapA = new HashMap<String, Object>();
        mapA.put("sheetName", "sheet1");
        list.add(mapA);
        CustomerApiVendor customerApiVendor = null;
        for (int j = 0; j < customerApiVendorList.size(); j++) {
            customerApiVendor = customerApiVendorList.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("apiVendor", customerApiVendor.getVendorName());
            mapValue.put("apiName", customerApiVendor.getApiName());
            if(customerApiVendor.getTotlePrice() != null){
                mapValue.put("price", customerApiVendor.getTotlePrice()/100.0);
            }else{
                mapValue.put("price", 0.0);
            }
            list.add(mapValue);
        }
        String fileName = companyName+"消费明细记录";
        String columnNames[]= {"产品供应商","产品名称","金额（单位：元）"};//列名
        String keys[] = {"apiVendor","apiName","price"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
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
    public void findWeekRecordByCustomerId(String companyName,Integer [] typeId,Integer customerId,Integer years,Integer months,Integer weeks,HttpServletResponse  response) throws IOException {
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("weekMonthTypeId",1);
        List<Integer> tableIdList = new ArrayList();
        if(typeId != null && typeId.length>0){
            for(int i=0; i<typeId.length; i++){
                tableIdList.add(typeId[i]);
            }
        }else {
            tableIdList.add(1);
            tableIdList.add(2);
        }
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
            if (weekMonthAmount.getTableId() == 1){
                mapValue.put("type", "充值");
            }
            if (weekMonthAmount.getTableId() == 2){
                mapValue.put("type", "消费");
            }
            list.add(mapValue);
        }
        String fileName = companyName+"周历史记录";
        String columnNames[]= {"年月周","金额（单位：元）","开始时间","结束时间","类型"};//列名
        String keys[] = {"yearMonthWeek","totleAmount","beginTime","endTime","type"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
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
    public void findMonthRecordByCustomerId(String companyName,Integer [] typeId,Integer customerId,Integer years,Integer months,HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("weekMonthTypeId",2);
        List<Integer> tableIdList = new ArrayList();
        if(typeId != null && typeId.length>0){
            for(int i=0; i<typeId.length; i++){
                tableIdList.add(typeId[i]);
            }
        }else {
            tableIdList.add(1);
            tableIdList.add(2);
        }
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
            if (weekMonthAmount.getTableId() == 1){
                mapValue.put("type", "充值");
            }
            if (weekMonthAmount.getTableId() == 2){
                mapValue.put("type", "消费");
            }
            list.add(mapValue);
        }
        String fileName = companyName+"月历史记录";
        String columnNames[]= {"年月","金额（单位：元）","开始时间","结束时间","类型"};//列名
        String keys[] = {"yearMonthWeek","totleAmount","beginTime","endTime","type"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }

}
