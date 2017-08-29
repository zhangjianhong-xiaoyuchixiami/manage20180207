package org.qydata.controller;

import com.google.gson.Gson;
import org.qydata.entity.ApiVendorBalanceLog;
import org.qydata.entity.Partner;
import org.qydata.entity.VendorExt;
import org.qydata.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/7.
 */
@RequestMapping("/vendor")
@Controller
public class VendorController {

    @Autowired
    private VendorService vendorService;

    /**
     * 查询所有供应商
     * @param vid
     * @param pid
     * @param preId
     * @param model
     * @return
     */
    @RequestMapping("/all-vendor")
    public String queryAllVendor(Integer [] vid,Integer [] pid,Integer preId,Model model){
        Map<String,Object> param = new HashMap<>();
        if (vid != null && vid.length > 0){
            param.put("vid",vid);
            model.addAttribute("vid",vid);
        }
        if (pid != null && pid.length > 0){
            param.put("pid",pid);
            model.addAttribute("pid",pid);
        }
        if (preId != null){
            param.put("preId",preId);
            model.addAttribute("preId",preId);
        }
        List<VendorExt> vendorExtList = vendorService.queryAllVendor(param);
        Map<String,Object> param_1 = new HashMap<>();
        List<VendorExt> vendorList = vendorService.queryAllVendor(param_1);
        List<Partner> partnerList = vendorService.queryAllPartner();
        model.addAttribute("vendorExtList",vendorExtList);
        model.addAttribute("partnerList",partnerList);
        model.addAttribute("vendorList",vendorList);
        return "/vendor/vendor-message";
    }

    /**
     * 供应商充值
     * @param vid
     * @param amount
     * @param date
     * @param remark
     * @return
     */
    @RequestMapping("/all-vendor/charge")
    @ResponseBody
    public String vendorCharge(Integer vid,Double amount,String date,String remark){
        boolean flag = false;
        try {
          flag = vendorService.updateVendorBalance(vid, amount, date, remark);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,Object> resu = new HashMap<>();
        if (flag){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 供应商扣费
     * @param vid
     * @param amount
     * @param date
     * @param remark
     * @return
     */
    @RequestMapping("/all-vendor/fee")
    @ResponseBody
    public String vendorFee(Integer vid,Double amount,String date,String remark){
        boolean flag = false;
        try {
            flag = vendorService.updateVendorBalance(vid, -amount, date, remark);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,Object> resu = new HashMap<>();
        if (flag){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 充值记录
     * @param vid
     * @return
     */
    @RequestMapping("/all-vendor/charge-record")
    public String vendorChargeRecord(Integer vid,String name,Model model){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("vid",vid);
        List<ApiVendorBalanceLog>  logList = vendorService.queryVendorBalanceLog(param);
        model.addAttribute("logList",logList);
        model.addAttribute("name",name);
        return "/vendor/vendor-charge-record";
    }

    /**
     * 修改是否是预付供应商
     * @param vid
     * @param preId
     * @return
     */
    @RequestMapping("/all-vendor/update-prepay")
    @ResponseBody
    public String vendorUpdatePrepay(Integer vid,Integer preId){
        Map<String,Object> resu = new HashMap<>();
       boolean flag = vendorService.updateVendorPrepay(vid,preId);
       if (flag){
           resu.put("success","success");
           return new Gson().toJson(resu);
       }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

}
