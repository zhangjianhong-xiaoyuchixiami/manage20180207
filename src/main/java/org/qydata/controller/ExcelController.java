package org.qydata.controller;


import com.google.gson.Gson;
import org.qydata.dst.vendor.VendorBill;
import org.qydata.entity.ApiType;
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

    /**
     * 合作公司财务对账
     * @param export
     * @param pid
     * @param wid
     * @param cid
     * @param beginDate
     * @param endDate
     * @param model
     * @return
     */
    @RequestMapping("/excel/extra-account-partner")
    public ModelAndView extraAccountPartner(String export, Integer pid, Integer wid, Integer [] cid, String beginDate, String endDate, Model model){
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

    /**
     * 根据合作公司查找所属客户
     * @param pid
     * @return
     */
    @RequestMapping("/excel/find-customer-by-pid")
    @ResponseBody
    public String queryCustomerByPid(@RequestParam("pid") Integer pid){
        List<Company> companyList = excelService.queryCompanyByPid(pid);
        return new Gson().toJson(companyList);
    }

    @RequestMapping("/excel/extra-account-customer")
    public ModelAndView extraAccountCustomer(String export,Integer cid,Integer wid,Integer [] tid,String beginDate, String endDate, Model model){
        Map<String,Object> map = new HashMap<>();
        if (cid != null){
            map.put("cid",cid);
            model.addAttribute("cid",cid);
            List<ApiType> apiTypeList = excelService.queryApiTypeByCid(cid);
            model.addAttribute("apiTypeList",apiTypeList);
        }
        if (wid != null){
            map.put("wid",wid);
            model.addAttribute("wid",wid);
        }
        if (tid != null && tid.length > 0){
            map.put("tid",tid);
            model.addAttribute("tid",tid);
        }
        if (beginDate != null && beginDate != ""){
            map.put("beginDate", beginDate);
            model.addAttribute("beginDate",beginDate);
        }
        if (endDate != null && endDate != ""){
            map.put("endDate",endDate);
            model.addAttribute("endDate",endDate);
        }
        Map<String,Object> mapResu = excelService.queryExtraAccountCustomer(map);
        List<ExportExcel> exportExcelList = (List<ExportExcel>) mapResu.get("exportExcelList");
        Double sumCost = (Double)mapResu.get("sumCost");
        model.addAttribute("exportExcelList",exportExcelList);
        model.addAttribute("sumCost",sumCost);
        model.addAttribute("companyList",apiService.queryCompany());
        return new ModelAndView("/excel/excel-customer");
    }


    @RequestMapping("/excel/find-type-by-cid")
    @ResponseBody
    public String queryApiTypeByCid(@RequestParam("cid") Integer cid){
        List<ApiType> apiTypeList = excelService.queryApiTypeByCid(cid);
        return new Gson().toJson(apiTypeList);
    }

    /**
     * 按条件统计供应商账单
     * @param export
     * @param vid
     * @param wid
     * @param tid
     * @param beginDate
     * @param endDate
     * @param model
     * @return
     */
    @RequestMapping("/excel/extra-account-vendor")
    public ModelAndView extraAccountVendor(String export,Integer vid,Integer wid,Integer [] tid,String beginDate, String endDate, Model model){
        Map<String,Object> map = new HashMap<>();
        if (vid != null){
            map.put("vid",vid);
            model.addAttribute("vid",vid);
            List<ApiType> apiTypeList = excelService.queryApiByVid(vid);
            model.addAttribute("apiTypeList",apiTypeList);
        }
        if (wid != null){
            map.put("wid",wid);
            model.addAttribute("wid",wid);
        }
        if (tid != null && tid.length > 0){
            map.put("tid",tid);
            model.addAttribute("tid",tid);
        }
        if (beginDate != null && beginDate != ""){
            map.put("beginDate", beginDate);
            model.addAttribute("beginDate",beginDate);
        }
        if (endDate != null && endDate != ""){
            map.put("endDate",endDate);
            model.addAttribute("endDate",endDate);
        }
        Map<String,Object> mapResu = excelService.queryExtraAccountVendor(map);
        List<VendorBill> exportExcelList = (List<VendorBill>) mapResu.get("exportExcelList");
        Double totalAmount = (Double)mapResu.get("sumCost");
        model.addAttribute("exportExcelList",exportExcelList);
        model.addAttribute("sumCost",totalAmount);
        model.addAttribute("companyList",apiService.queryApiVendor());
        return new ModelAndView("/excel/excel-vendor-api");
    }

    /**
     * 根据供应商id查询产品
     * @param vid
     * @return
     */
    @RequestMapping("/excel/find-type-by-vid")
    @ResponseBody
    public String queryApiByVid(@RequestParam("vid") Integer vid){
        List<ApiType> apiTypeList = excelService.queryApiByVid(vid);
        return new Gson().toJson(apiTypeList);
    }

}
