package org.qydata.exportexcelcontroller;

import org.qydata.dst.PartnerFinance;
import org.qydata.entity.PartnerIncomeExpenditureLog;
import org.qydata.service.PartnerFinanceService;
import org.qydata.tools.ExportIoOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by jonhn on 2017/1/20.
 */
@Controller
@RequestMapping("excel-partner-finance")
public class ExcelPartnerFinanceController {

    @Autowired
    private PartnerFinanceService partnerFinanceService;

    //合作伙伴来往账目
    @RequestMapping("/find-all-partner-financial-account")
    public void findPartnersFinancialAccount(HttpServletResponse response, String partnerName) throws IOException {
        Map<String,Object> map = new HashMap<>();
        map.put("partnerName",partnerName);
        List<PartnerFinance>  partnerFinanceList = partnerFinanceService.queryPartnerOverFinance(map);
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> mapA = new HashMap<String, Object>();
        mapA.put("sheetName", "sheet1");
        list.add(mapA);
        PartnerFinance partnerFinance=null;
        for (int j = 0; j < partnerFinanceList.size(); j++) {
            partnerFinance = partnerFinanceList.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("partnerName", partnerFinance.getPartnerName());
            if(partnerFinance.getWeekIncomeAmount() != null){
                mapValue.put("weekIncomeAmount", partnerFinance.getWeekIncomeAmount()/100.0);
            }else {
                mapValue.put("weekIncomeAmount", 0.0);
            }
            if(partnerFinance.getWeekExpenditureAmount() != null){
                mapValue.put("weekExpenditureAmount", partnerFinance.getWeekExpenditureAmount()/100.0);
            }else {
                mapValue.put("weekExpenditureAmount", 0.0);
            }
            if(partnerFinance.getMonthIncomeAmount() != null){
                mapValue.put("monthIncomeAmount", partnerFinance.getMonthIncomeAmount()/100.0);
            }else {
                mapValue.put("monthIncomeAmount", 0.0);
            }
            if(partnerFinance.getMonthExpenditureAmount() != null){
                mapValue.put("monthExpenditureAmount", partnerFinance.getMonthExpenditureAmount()/100.0);
            }else {
                mapValue.put("monthExpenditureAmount", 0.0);
            }
            if(partnerFinance.getTotleIncomeAmount() != null){
                mapValue.put("totleIncomeAmount", partnerFinance.getTotleIncomeAmount()/100.0);
            }else {
                mapValue.put("totleIncomeAmount", 0.0);
            }
            if(partnerFinance.getTotleExpenditureAmount() != null){
                mapValue.put("totleExpenditureAmount", partnerFinance.getTotleExpenditureAmount()/100.0);
            }else {
                mapValue.put("totleExpenditureAmount", 0.0);
            }
            list.add(mapValue);
        }
        String fileName = "合作公司财务账单";
        String columnNames[]= {"公司名称","上周收入（单位：元）","上周支出（单位：元）","上月收入（单位：元）","上月支出（单位：元）","收入总额（单位：元）","支出总额（单位：元）"};//列名
        String keys[] = {"partnerName","weekIncomeAmount","weekExpenditureAmount","monthIncomeAmount","monthExpenditureAmount","totleIncomeAmount","totleExpenditureAmount"};
        ExportIoOperate.excelEndOperator(list,keys,columnNames,fileName,response);

    }

    //合作伙伴来往账目-收支明细
    @RequestMapping("/find-all-partner-financial-account/income-and-expenditure-record")
    public void findPartnerIncomeAndExpenditureRecord(HttpServletResponse response,Integer partnerId,Integer reasonId) throws IOException {
        Map<String,Object> map = new HashMap<>();
        map.put("partnerId",partnerId);
        map.put("reasonId",reasonId);
        List<PartnerIncomeExpenditureLog> partnerIncomeExpenditureLogList = partnerFinanceService.queryPartnerDetailLog(map);
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> mapA = new HashMap<String, Object>();
        mapA.put("sheetName", "sheet1");
        list.add(mapA);
        PartnerIncomeExpenditureLog partnerIncomeExpenditureLog=null;
        for (int j = 0; j < partnerIncomeExpenditureLogList.size(); j++) {
            partnerIncomeExpenditureLog = partnerIncomeExpenditureLogList.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("amount", partnerIncomeExpenditureLog.getAmount()/100.0);
            mapValue.put("createTime", (Date)partnerIncomeExpenditureLog.getCreateTime());
            if(partnerIncomeExpenditureLog.getRemark() == null){
                mapValue.put("remark", "");
            }else {
                mapValue.put("remark", partnerIncomeExpenditureLog.getRemark());
            }
            mapValue.put("reasonName", partnerIncomeExpenditureLog.getPartnerIncomeExpenditureReason().getName());
            list.add(mapValue);
        }
        String fileName = "合作公司财务明细账单";
        String columnNames[]= {"金额（单位：元）","创建时间","备注","类型"};
        String keys[] = {"amount","createTime","remark","reasonName"};
        ExportIoOperate.excelEndOperator(list,keys,columnNames,fileName,response);
    }


}
