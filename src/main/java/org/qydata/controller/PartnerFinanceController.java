package org.qydata.controller;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.qydata.entity.Partner;
import org.qydata.entity.PartnerIncomeExpenditureLog;
import org.qydata.service.PartnerFinanceService;
import org.qydata.tools.CalendarTools;
import org.qydata.tools.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by jonhn on 2017/2/6.
 */
@Controller
@RequestMapping("/partner")
public class PartnerFinanceController {

    @Autowired private PartnerFinanceService partnerFinanceService;


    //合作伙伴来往账目
    @RequestMapping("/find-all-partner-financial-account")
    public ModelAndView findPartnersFinancialAccount(String export,String partnerName,Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("partnerName",partnerName);
        Map<String,Object> mapResult = partnerFinanceService.queryPartnerOverFinance(map);
        Set<Map.Entry<String,Object>> set = mapResult.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while (it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if (me.getKey().equals("queryPartnerOverFinance")){
                model.addAttribute("partnerFinanceList",me.getValue());
            }
            if (me.getKey().equals("getCountWeekIncomePartnerOverFinance")){
                model.addAttribute("weekIncomeAmount",me.getValue());
            }
            if (me.getKey().equals("getCountWeekExpenditurePartnerOverFinance")){
                model.addAttribute("weekExpenditure",me.getValue());
            }
            if (me.getKey().equals("getCountMonthIncomePartnerOverFinance")){
                model.addAttribute("monthIncomeAmount",me.getValue());
            }
            if (me.getKey().equals("getCountMonthExpenditurePartnerOverFinance")){
                model.addAttribute("monthExpenditure",me.getValue());
            }
            if (me.getKey().equals("getCountTotleIncomePartnerOverFinance")){
                model.addAttribute("totleIncomeAmount",me.getValue());
            }
            if (me.getKey().equals("getCountTotleExpenditurePartnerOverFinance")){
                model.addAttribute("totleExpenditure",me.getValue());
            }
        }
        model.addAttribute("partnerName",partnerName);
        model.addAttribute("year", CalendarTools.getYearMonthCount(1));
        model.addAttribute("month",CalendarTools.getMonthCount(1));
        model.addAttribute("week_month",CalendarTools.getWeekMonthCount(1));
        model.addAttribute("week",CalendarTools.getYearWeekCount(1));
        return new ModelAndView("/finance/partnersFinancialAccount");
    }

    //合作伙伴来往账目-收支明细
    @RequestMapping("/find-all-partner-financial-account/income-and-expenditure-record")
    public ModelAndView findPartnerIncomeAndExpenditureRecord(String export, Integer partnerId,Integer reasonId,String partnerName,Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("partnerId",partnerId);
        map.put("reasonId",reasonId);
        Map<String,Object> mapResult = partnerFinanceService.queryPartnerDetailLog(map);
        Set<Map.Entry<String,Object>> set = mapResult.entrySet();
        Iterator<Map.Entry<String,Object>> it = set.iterator();
        while (it.hasNext()){
            Map.Entry<String,Object> me = it.next();
            if (me.getKey().equals("queryPartnerDetailLog")){
                model.addAttribute("partnerIncomeExpenditureLogList",me.getValue());
                List<PartnerIncomeExpenditureLog> list = (List<PartnerIncomeExpenditureLog>) me.getValue();

                System.out.println(list.size());
            }
            if (me.getKey().equals("getCountPartnerDetailLog")){
                model.addAttribute("totleAmount",me.getValue());
            }
        }
        model.addAttribute("partnerName",partnerName);
        model.addAttribute("partnerId",partnerId);
        model.addAttribute("reasonId",reasonId);
        return new ModelAndView("/finance/partnersReceiptAndPayingRecord");
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
