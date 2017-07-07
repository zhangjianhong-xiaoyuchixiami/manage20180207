package org.qydata.controller;

import com.google.gson.Gson;
import org.qydata.dst.ApiTypeInfo;
import org.qydata.dst.CustomerApiPartner;
import org.qydata.entity.*;
import org.qydata.service.ApiService;
import org.qydata.tools.date.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by jonhn on 2017/2/28.
 */
@Controller
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired private ApiService apiService;

    /**
     * 查询产品
     * @param tid
     * @param vid
     * @param pid
     * @param LPic
     * @param HPic
     * @param model
     * @return
     */
    @RequestMapping(value = "/api-message")
    public String queryApi(Integer tid,Integer vid,Integer pid,Double LPic, Double HPic,Integer statId, Model model){
        Map<String,Object> map = new HashMap<>();
        if (tid != null){
            map.put("apiTypeId",tid);
            model.addAttribute("tid",tid);
        }
        if (vid != null){
            map.put("vendorId",vid);
            model.addAttribute("vid",vid);
        }
        if (pid != null){
            map.put("partnerId",pid);
            model.addAttribute("pid",pid);
        }
        if (LPic != null){
            map.put("lowPrice",LPic*100);
            model.addAttribute("LPic",LPic);
        }
        if (HPic != null){
            map.put("highPrice",HPic*100);
            model.addAttribute("HPic",HPic);
        }
        if (statId != null && statId == 1){
            map.put("statId",1);
            model.addAttribute("statId",statId);
        }
        if (statId != null && statId == 2){
            map.put("statId",2);
            model.addAttribute("statId",statId);
        }
        List<Api> apiList = apiService.queryApi(map);
        List<Partner> partnerList = apiService.queryPartner();
        model.addAttribute("apiList",apiList);
        model.addAttribute("partnerList",partnerList);
        model.addAttribute("apiTypeList",apiService.queryApiType());
        model.addAttribute("apiVendorList",apiService.queryApiVendor());

        return "/api/apiproduct";
    }

    /**
     * 查询api最近请求的失败次数
     * @param model
     * @return
     */
    @RequestMapping("/api-monitor")
    public String queryApiMonitor(Model model){
        List<ApiBan> apiBanList = apiService.queryApiMonitor();
        model.addAttribute("apiBanList",apiBanList);
        return "/api/apimonitor";
    }

    /**
     * 禁用产品
     * @return
     */
    @RequestMapping("/ban")
    @ResponseBody
    public String apiBan(HttpServletRequest request){
        Gson gson = new Gson();
        String [] apiId = request.getParameterValues("apiId[]");
        Map<String,Object> mapResu = null;
        try {
            mapResu = apiService.updateApiBan(apiId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gson.toJson(mapResu);
    }

    /**
     * 解禁产品
     * @param request
     * @return
     */
    @RequestMapping("/unban")
    @ResponseBody
    public String apiUnBan(HttpServletRequest request){
        Gson gson = new Gson();
        String [] apiId = request.getParameterValues("apiId[]");
        Map<String,Object> mapResu = null;
        try {
            mapResu = apiService.updateApiUnBan(apiId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gson.toJson(mapResu);
    }

    /**
     * 修改上游产品价格
     * @param aid
     * @param pic
     * @return
     */
    @RequestMapping("/update-price")
    @ResponseBody
    public String updatePrice(Integer aid,Double pic){

        Map<String,Object> map = new HashMap<>();
        Gson gson = new Gson();
        int code = 0;
        try {
            code = apiService.updatePrice(aid,pic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (200 == code){
            map.put("success","操作成功");
            return gson.toJson(map);
        }
        map.put("fail","修改失败，请检查你的操作");
        return gson.toJson(map);
    }

    /**
     * 查看上游产品改价记录
     * @return
     */
    @RequestMapping("/api-price-change-log")
    public String queryApiPriceChangeLog(Integer tid, Integer vid, Integer pid, Double LPic, Double HPic, String beginDate, String endDate, Model model){
        Map<String,Object> mapParam = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        if (tid != null){
            mapParam.put("apiTypeId",tid);
            model.addAttribute("tid",tid);
        }
        if (vid != null){
            mapParam.put("vendorId",vid);
            model.addAttribute("vid",vid);
        }
        if (pid != null){
            mapParam.put("partnerId",pid);
            model.addAttribute("pid",pid);
        }
        if (LPic != null){
            mapParam.put("lowPrice",LPic*100);
            model.addAttribute("LPic",LPic);
        }
        if (HPic != null){
            mapParam.put("highPrice",HPic*100);
            model.addAttribute("HPic",HPic);
        }
        if (beginDate != null && beginDate != ""){
            mapParam.put("beginDate", CalendarUtil.getTranByInputTime(beginDate));
            model.addAttribute("beginDate",beginDate);
        }
        if (endDate != null && endDate != ""){
            mapParam.put("endDate",CalendarUtil.getAfterDayByInputTime(endDate));
            model.addAttribute("endDate",endDate);
        }
        List<ApiPriceChanceLog> apclList =  apiService.queryApiPriceChangeLog(mapParam);
        List<Api> apiList = apiService.queryAllApi(map);
        List<ApiType> apiTypeList = apiService.queryApiType();
        List<ApiVendor> apiVendorList = apiService.queryApiVendor();
        List<Partner> partnerList = apiService.queryPartner();
        model.addAttribute("apclList",apclList);
        model.addAttribute("apiList",apiList);
        model.addAttribute("apiTypeList",apiTypeList);
        model.addAttribute("apiVendorList",apiVendorList);
        model.addAttribute("partnerList",partnerList);
        return "/api/api_price_change_log";
    }

    /**
     * 新增上游产品价格记录
     * @param aid
     * @param pic
     * @param date
     * @return
     */
    @RequestMapping("/add-api-price-change")
    @ResponseBody
    public String addApiPriceChangeLog(Integer aid,Double pic,String date){
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        try {
            flag = apiService.addApiPriceChangeLog(aid, pic, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag){
            map.put("success","新增改价记录成功！");
            return new Gson().toJson(map);
        }
        map.put("fail","新增改价记录失败！");
        return new Gson().toJson(map);
    }

    /**
     * 以客户纬度查询产品
     * @param cid
     * @param pid
     * @param tid
     * @param LPic
     * @param HPic
     * @param model
     * @return
     */
    @RequestMapping("/api-message-by-company")
    public String queryApiByCompanyId(Integer cid,Integer pid,Integer tid,Double LPic, Double HPic,Model model){
        Map<String,Object> map = new HashMap<>();
        if (cid != null){
            map.put("companyId",cid);
            model.addAttribute("cid",cid);
        }
        if (pid != null){
            map.put("partnerId",pid);
            model.addAttribute("pid",pid);
        }
        if (tid != null){
            map.put("apiTypeId",tid);
            model.addAttribute("tid",tid);
        }
        if (LPic != null){
            map.put("lowPrice",LPic*100);
            model.addAttribute("LPic",LPic);
        }
        if (HPic != null){
            map.put("highPrice",HPic*100);
            model.addAttribute("HPic",HPic);
        }
        List<CustomerApiPartner> customerApiPartnerList = apiService.queryApiByCompanyId(map);
        List<Partner> partnerList = apiService.queryPartner();
        model.addAttribute("companyApiList",customerApiPartnerList);
        model.addAttribute("apiTypeList",apiService.queryApiType());
        model.addAttribute("companyList",apiService.queryCompany());
        model.addAttribute("partnerList",partnerList);
        return "api/companyapiproduct";
    }

    /**
     * 修改客户产品价格
     * @param cid
     * @param tid
     * @param stid
     * @param pic
     * @return
     */
    @RequestMapping("/company/mod-company-api-price")
    @ResponseBody
    public String midCompanyApiPrice(Integer cid,Integer tid,Integer stid,Double pic){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap();
        int code = 0;
        try {
            code = apiService.updateCompanyApiPrice(cid, tid, stid, pic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (200 == code){
            map.put("success","操作成功!");
            return gson.toJson(map);
        }
        map.put("fail","操作失败!");
        return gson.toJson(map);
    }

    /**
     * 禁用客户产品权限
     * @return
     */
    @RequestMapping("/company/ban-api")
    @ResponseBody
    public String banCompanyApiById(HttpServletRequest request){
        String [] cid_id = request.getParameterValues("cid_id[]");
        Gson gson = new Gson();
        Map<String,Object> mapResu = null;
        try {
            mapResu = apiService.banCompanyApi(cid_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gson.toJson(mapResu);
    }

    /**
     * 查看下游客户产品改价记录
     * @return
     */
    @RequestMapping("/company/apiType-price-change-log")
    public String queryCompanyApiPriceChangeLog(Integer cid, Integer pid, Integer tid, Double LPic, Double HPic, String beginDate, String endDate, Model model){
        Map<String,Object> mapParam = new HashMap<>();
        if (cid != null){
            mapParam.put("companyId",cid);
            model.addAttribute("cid",cid);
        }
        if (pid != null){
            mapParam.put("partnerId",pid);
            model.addAttribute("pid",pid);
        }
        if (tid != null){
            mapParam.put("apiTypeId",tid);
            model.addAttribute("tid",tid);
        }
        if (LPic != null){
            mapParam.put("lowPrice",LPic*100);
            model.addAttribute("LPic",LPic);
        }
        if (HPic != null){
            mapParam.put("highPrice",HPic*100);
            model.addAttribute("HPic",HPic);
        }
        if (beginDate != null && beginDate != ""){
            mapParam.put("beginDate", CalendarUtil.getTranByInputTime(beginDate));
            model.addAttribute("beginDate",beginDate);
        }
        if (endDate != null && endDate != ""){
            mapParam.put("endDate",CalendarUtil.getAfterDayByInputTime(endDate));
            model.addAttribute("endDate",endDate);
        }
        List<CompanyApiPriceChangeLog> capclList = apiService.queryCompanyApiPriceChangeLog(mapParam);
        List<Company> companyList = apiService.queryCompany();
        List<Partner> partnerList = apiService.queryPartner();
        List<ApiType> apiTypeList = apiService.queryApiType();
        model.addAttribute("capclList",capclList);
        model.addAttribute("companyList",companyList);
        model.addAttribute("partnerList",partnerList);
        model.addAttribute("apiTypeList",apiTypeList);
        return "/api/company_api_price_change_log";
    }

    /**
     * 新增客户产品价格记录
     * @param cid
     * @param pic
     * @param date
     * @return
     */
    @RequestMapping("/company/add-apiType-price-change")
    @ResponseBody
    public String addCompanyApiPriceChangeLog(Integer cid,String tid_stid,Double pic,String date){
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        try {
            flag = apiService.addCompanyApiPriceChangeLog(cid, tid_stid, pic, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag){
            map.put("success","新增改价记录成功！");
            return new Gson().toJson(map);
        }
        map.put("fail","新增改价记录失败！");
        return new Gson().toJson(map);
    }

    /**
     * 根据公司Id查询所拥有的产品 -- 用于新增客户改价记录级联
     * @param cid
     * @return
     */
    @RequestMapping("/company/query-company-api-by-company-id")
    @ResponseBody
    public String queryCompanyApiByCompanyId(Integer cid){
        List<ApiTypeInfo> companyApiList = apiService.queryCompanyApiByCompanyId(cid);
        return new Gson().toJson(companyApiList);
    }



}
