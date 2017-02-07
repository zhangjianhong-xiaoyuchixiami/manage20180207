package org.qydata.controller;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.qydata.dst.PartnerFinance;
import org.qydata.entity.Partner;
import org.qydata.entity.PartnerIncomeExpenditureLog;
import org.qydata.regex.RegexUtil;
import org.qydata.service.PartnerFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/2/6.
 */
@Controller
@RequestMapping("/partner")
public class PartnerFinanceController {

    @Autowired private PartnerFinanceService partnerFinanceService;


    //合作伙伴来往账目
    @RequestMapping("/find-all-partner-financial-account")
    public String findPartnersFinancialAccount(Model model,String partnerName){
        Map<String,Object> map = new HashMap<>();
        map.put("partnerName",partnerName);
        List<PartnerFinance>  partnerFinanceList = partnerFinanceService.queryPartnerOverFinance(map);
        long weekIncomeAmount = 0L;
        long weekExpenditure = 0L;
        long monthIncomeAmount = 0L;
        long monthExpenditure = 0L;
        long totleIncomeAmount = 0L;
        long totleExpenditure = 0L;
        if (partnerFinanceList != null){
            for (int i=0; i<partnerFinanceList.size(); i++){
                PartnerFinance partnerFinance = partnerFinanceList.get(i);
                if(partnerFinance.getWeekIncomeAmount() != null){
                    weekIncomeAmount = weekIncomeAmount + partnerFinance.getWeekIncomeAmount();
                }
                if(partnerFinance.getWeekExpenditureAmount() != null){
                    weekExpenditure = weekExpenditure + partnerFinance.getWeekIncomeAmount();
                }
                if(partnerFinance.getMonthIncomeAmount() != null){
                    monthIncomeAmount = monthIncomeAmount + partnerFinance.getMonthIncomeAmount();
                }
                if(partnerFinance.getMonthExpenditureAmount() != null){
                    monthExpenditure = monthExpenditure + partnerFinance.getMonthExpenditureAmount();
                }
                if(partnerFinance.getTotleIncomeAmount() != null){
                    totleIncomeAmount = totleIncomeAmount + partnerFinance.getTotleIncomeAmount();
                }
                if(partnerFinance.getTotleExpenditureAmount() != null){
                    totleExpenditure = totleExpenditure + partnerFinance.getTotleExpenditureAmount();
                }
            }
        }
        model.addAttribute("partnerFinanceList",partnerFinanceList);
        model.addAttribute("partnerName",partnerName);
        model.addAttribute("weekIncomeAmount",weekIncomeAmount);
        model.addAttribute("weekExpenditure",weekExpenditure);
        model.addAttribute("monthIncomeAmount",monthIncomeAmount);
        model.addAttribute("monthExpenditure",monthExpenditure);
        model.addAttribute("totleIncomeAmount",totleIncomeAmount);
        model.addAttribute("totleExpenditure",totleExpenditure);
        return "/finance/partnersFinancialAccount";
    }

    //付款和收款
    @RequestMapping("/find-all-partner-financial-account/payment-receipt")
    @ResponseBody
    public String chargeApiVendorBalance(Integer partnerId,String amount,String date,String remark,Integer reasonId){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap();
        if(RegexUtil.isNull(amount)){
            map.put("amountMessage","请输入金额!");
            return gson.toJson(map);
        }
        if(!RegexUtil.isFloatZero(amount)){
            map.put("amountMessage","金额格式不正确!");
            return gson.toJson(map);
        }else {
            if (Integer.parseInt(amount)<=0){
                map.put("amountMessage","金额必须大于0!");
                return gson.toJson(map);
            }
        }
        boolean flag = partnerFinanceService.addPartnerIncomeExpenditureLog(partnerId, amount, remark, date, reasonId);
        if (flag){
            map.put("successMessage","恭喜你，操作成功！");
        }else {
            map.put("errorMessage","操作失败，请检查你的输入");
        }
        return gson.toJson(map);
    }

    //新增合作伙伴
    @RequestMapping("/find-all-partner-financial-account/add-partner")
    @ResponseBody
    public String addPartner(String partnerName){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap();
        if(RegexUtil.isNull(partnerName)){
            map.put("partnerMessage","请输入合作公司名称!");
            return gson.toJson(map);
        }
        boolean flag = partnerFinanceService.addPartner(partnerName);
        if (flag){
            map.put("successMessage","恭喜你，操作成功！");
        }else {
            map.put("errorMessage","操作失败，请检查你的输入");
        }
        return gson.toJson(map);
    }

    //合作伙伴来往账目-收支明细
    @RequestMapping("/find-all-partner-financial-account/income-and-expenditure-record")
    public String findPartnerIncomeAndExpenditureRecord(Model model,Integer partnerId,Integer reasonId,String partnerName){
        Map<String,Object> map = new HashMap<>();
        map.put("partnerId",partnerId);
        map.put("reasonId",reasonId);
        List<PartnerIncomeExpenditureLog> partnerIncomeExpenditureLogList = partnerFinanceService.queryPartnerDetailLog(map);
       long totleAmount = 0L;
        if (partnerIncomeExpenditureLogList != null){
            for (int i=0; i<partnerIncomeExpenditureLogList.size(); i++){
                PartnerIncomeExpenditureLog partnerIncomeExpenditureLog = partnerIncomeExpenditureLogList.get(i);
                if(partnerIncomeExpenditureLog.getAmount() != null){
                    totleAmount = totleAmount + partnerIncomeExpenditureLog.getAmount();
                }
            }
        }
        model.addAttribute("partnerIncomeExpenditureLogList",partnerIncomeExpenditureLogList);
        model.addAttribute("partnerName",partnerName);
        model.addAttribute("totleAmount",totleAmount);
        model.addAttribute("partnerId",partnerId);
        model.addAttribute("reasonId",reasonId);
        return "/finance/partnersReceiptAndPayingRecord";
    }

    //支出
    @RequestMapping("/find-all-partner-financial-account/expenditure-bar-chart")
    @ResponseBody
    public String expenditureBarChart(){
        Map<String,Object> map = new HashedMap();
        map.put("reasonId",2);
        List xList = new ArrayList();
        List yList = new ArrayList();
        List<Partner> partnerList = partnerFinanceService.queryPartnerBarChart(map);
        if (partnerList != null){
            for (int i=0; i<partnerList.size(); i++){
                Partner partner = partnerList.get(i);
                xList.add(partner.getName());
                if(partner.getPartnerIncomeExpenditureLog().getAmount() != null){
                    yList.add(partner.getPartnerIncomeExpenditureLog().getAmount()/100.0);
                }else {
                    yList.add(0);
                }
            }
        }
        Map mapOne = new HashedMap();
        mapOne.put("name","合作公司支出总额");
        mapOne.put("data",yList);
        JSONArray jsonArray = JSONArray.fromObject(xList);
        JSONArray jsonArray1 = JSONArray.fromObject(mapOne);
        JSONObject getObj = new JSONObject();
        getObj.put("xList", jsonArray);
        getObj.put("yList", jsonArray1);
        return getObj.toString();
    }

    //收入
    @RequestMapping("/find-all-partner-financial-account/income-bar-chart")
    @ResponseBody
    public String incomeBarChart(){
        Map<String,Object> map = new HashedMap();
        map.put("reasonId",1);
        List xList = new ArrayList();
        List yList = new ArrayList();
        List<Partner> partnerList = partnerFinanceService.queryPartnerBarChart(map);
        if (partnerList != null){
            for (int i=0; i<partnerList.size(); i++){
                Partner partner = partnerList.get(i);
                xList.add(partner.getName());
                if(partner.getPartnerIncomeExpenditureLog().getAmount() != null){
                    yList.add(partner.getPartnerIncomeExpenditureLog().getAmount()/100.0);
                }else {
                    yList.add(0);
                }
            }
        }
        Map mapOne = new HashedMap();
        mapOne.put("name","合作公司收入总额");
        mapOne.put("data",yList);
        JSONArray jsonArray = JSONArray.fromObject(xList);
        JSONArray jsonArray1 = JSONArray.fromObject(mapOne);
        JSONObject getObj = new JSONObject();
        getObj.put("xList", jsonArray);
        getObj.put("yList", jsonArray1);
        return getObj.toString();
    }

}
