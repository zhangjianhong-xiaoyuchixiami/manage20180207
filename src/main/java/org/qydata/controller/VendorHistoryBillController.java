package org.qydata.controller;

import org.qydata.service.VendorHistoryBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/30.
 */
@Controller
@RequestMapping("/finance")
public class VendorHistoryBillController {

    @Autowired
    private VendorHistoryBillService billService;

    /**
     * 查询供应商历史消费
     * @param status
     * @param vid
     * @param pid
     * @param beg_month
     * @param model
     * @return
     */
    @RequestMapping("/vendor-history-bill")
    public String vendorHistoryBill(Integer [] status, Integer [] vid, Integer pid, String [] beg_month, Model model){
        Map<String,Object> param = new HashMap<>();
        if (status == null){
            status= new Integer[]{0, -1};
            param.put("status",status);
            model.addAttribute("status",status);
        }else {
            param.put("status",status);
            model.addAttribute("status",status);
        }
        if (vid != null && vid.length > 0){
            param.put("vid",vid);
            model.addAttribute("vid",vid);
        }
        if (pid != null){
            param.put("pid",pid);
            model.addAttribute("pid",pid);
        }
        if (beg_month != null && beg_month.length > 0){
            param.put("beg_month",beg_month);
            model.addAttribute("beg_month",beg_month);
        }
        Map<String,Object> resu = billService.queryVendorHistoryBill(param);
        if (resu != null){
            for (Map.Entry<String, Object> me : resu.entrySet()) {
                if (me.getKey().equals("chargeTot")) {
                   model.addAttribute("chargeTot",me.getValue());
                }
                if (me.getKey().equals("consumeTot")) {
                    model.addAttribute("consumeTot",me.getValue());
                }
                if (me.getKey().equals("billList")) {
                    model.addAttribute("billList",me.getValue());
                }
            }
        }
        return "/finance/vendor-history-bill";
    }

    /**
     * 查询供应商历史消费明细
     * @return
     */
    @RequestMapping("/vendor-history-bill/detail")
    public String vendorHistoryBillDetail(Integer vid,String name, String [] cyc, String [] tid,Model model){
        Map<String,Object> param = new HashMap<>();
        if (vid != null){
            param.put("vid",vid);
            model.addAttribute("vid",vid);
        }
        if (cyc != null && cyc.length > 0){
            param.put("cyc",cyc);
            model.addAttribute("cyc",cyc);
        }
        if (tid != null && tid.length > 0){
            param.put("tid",tid);
            model.addAttribute("tid",tid);
        }
        model.addAttribute("name",name);
        Map<String,Object> resu = billService.queryVendorHistoryBillDetail(param);
        if (resu != null){
            for (Map.Entry<String,Object> me : resu.entrySet()) {
                if (me.getKey().equals("conTot")) {
                    model.addAttribute("conTot",me.getValue());
                }
                if (me.getKey().equals("billDetailList")) {
                    model.addAttribute("billDetailList",me.getValue());
                }
            }
        }
        return "/finance/vendor-history-bill-month-detail";
    }

}
