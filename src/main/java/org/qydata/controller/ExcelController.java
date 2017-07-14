package org.qydata.controller;


import com.google.gson.Gson;
import org.qydata.entity.Company;
import org.qydata.entity.Partner;
import org.qydata.entity.excel.ExportExcel;
import org.qydata.service.ApiService;
import org.qydata.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/7/4.
 */
@Controller
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private ApiService apiService;

    @RequestMapping("/excel/extra-account-partner")
    public ModelAndView extraAccount(String export, Integer pid, Integer wid, Integer [] cid, String beginDate, String endDate, Model model){
        Map<String,Object> map = new HashMap<>();
        if (pid != null){
            map.put("pid",pid);
            List<Company> companyList = excelService.queryCompanyByPid(pid);
            model.addAttribute("pid",pid);
            model.addAttribute("companyList",companyList);
        }
        if (wid != null){
            map.put("wid",wid);
            model.addAttribute("wid",wid);
        }
        if (cid != null && cid.length > 0){
            map.put("cid",cid);
            model.addAttribute("cid",cid);
        }
        if (beginDate != null && beginDate != ""){
            map.put("beginDate", beginDate);
            model.addAttribute("beginDate",beginDate);
        }
        if (endDate != null && endDate != ""){
            map.put("endDate",endDate);
            model.addAttribute("endDate",endDate);
        }
        Map<String,Object> mapResu = excelService.queryExtraAccount(map);
        List<ExportExcel> exportExcelListPartnerUserOur = (List<ExportExcel>) mapResu.get("exportExcelListPartnerUserOur");
        List<ExportExcel> exportExcelListOurUserPartner = (List<ExportExcel>) mapResu.get("exportExcelListOurUserPartner");
        List<ExportExcel> exportExcelListPartnerUserOurSell = (List<ExportExcel>) mapResu.get("exportExcelListPartnerUserOurSell");
        Partner partner = (Partner) mapResu.get("partner");
        Double partnerUserOurTotle = (Double)mapResu.get("partnerUserOurTotle");
        Double ourUserPartnerTotle = (Double)mapResu.get("ourUserPartnerTotle");
        Double partnerUserOurSellTotle = (Double)mapResu.get("partnerUserOurSellTotle");
        Double sum = (Double)mapResu.get("sum");
        String pim = null;
        if (partner != null){
            pim = partner.getName();
        }
        List<Partner> partnerList = apiService.queryPartner();
        model.addAttribute("exportExcelListPartnerUserOur",exportExcelListPartnerUserOur);
        model.addAttribute("exportExcelListOurUserPartner",exportExcelListOurUserPartner);
        model.addAttribute("exportExcelListPartnerUserOurSell",exportExcelListPartnerUserOurSell);
        model.addAttribute("pim",pim);
        model.addAttribute("partnerUserOurTotle",partnerUserOurTotle);
        model.addAttribute("ourUserPartnerTotle",ourUserPartnerTotle);
        model.addAttribute("partnerUserOurSellTotle",partnerUserOurSellTotle);
        model.addAttribute("sum",sum);
        model.addAttribute("partnerList",partnerList);
        return new ModelAndView("/excel/excel-partner");
    }

    @RequestMapping("/excel/find-customer-by-pid")
    @ResponseBody
    public String queryCustomerByPid(@RequestParam("pid") Integer pid){
        List<Company> companyList = excelService.queryCompanyByPid(pid);
        return new Gson().toJson(companyList);
    }

}
