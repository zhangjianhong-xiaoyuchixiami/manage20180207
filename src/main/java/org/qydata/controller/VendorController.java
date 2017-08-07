package org.qydata.controller;

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

    @RequestMapping("/all-vendor")
    public String queryAllVendor(Integer [] vid,Integer [] pid,Integer preId,Model model){
        Map<String,Object> param = new HashMap<>();
        if (vid != null && vid.length > 0){
            param.put("vid",vid);
        }
        if (pid != null && pid.length > 0){
            param.put("pid",pid);
        }
        if (preId != null){
            param.put("preId",preId);
        }
        List<VendorExt> vendorExtList = vendorService.queryAllVendor(param);
        List<Partner> partnerList = vendorService.queryAllPartner();
        model.addAttribute("vendorExtList",vendorExtList);
        model.addAttribute("partnerList",partnerList);
        return "/vendor/vendor-message";
    }

    @RequestMapping("/all-vendor/charge")
    @ResponseBody
    public String vendorCharge(Integer vid,String amount,String remark,String date){
        return "";
    }

    @RequestMapping("/all-vendor/charge-record")
    public String vendorChargeRecord(Integer vid){
        return "";
    }

}
