package org.qydata.controller;

import com.google.gson.Gson;
import org.qydata.dst.ApiTurnoverBill;
import org.qydata.service.ApiTurnoverBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/9/5.
 */
@Controller
@RequestMapping("/finance")
public class ApiTurnoverBillController {

    @Autowired
    private ApiTurnoverBillService billService;

    @RequestMapping("/api-turnover-bill")
    public String apiTurnoverBill(String [] tid,String cyc,Model model){
        Map<String,Object> param = new HashMap<>();
        if (cyc != null){
            param.put("cyc",cyc);
            model.addAttribute("cyc",cyc);
        }
        if (tid != null && tid.length > 0){
            param.put("tid",tid);
            model.addAttribute("tid",tid);
        }
        Map<String,Object> resu =  billService.queryApiTurnoverBill(param);
        if (resu != null){
            for (Map.Entry<String,Object> me : resu.entrySet()) {
                if (me.getKey().equals("billList")){
                    model.addAttribute("billList",me.getValue());
                }
                if (me.getKey().equals("vendorTot")){
                    model.addAttribute("vendorTot",me.getValue());
                }
                if (me.getKey().equals("customerTot")){
                    model.addAttribute("customerTot",me.getValue());
                }
                if (me.getKey().equals("profitTot")){
                    model.addAttribute("profitTot",me.getValue());
                }
            }
        }
        List<String> conTimeList = billService.queryAllConsumeTime();
        List<ApiTurnoverBill> typeList = billService.queryConsumeType();
        model.addAttribute("conTimeList",conTimeList);
        model.addAttribute("typeList",typeList);
        return "/finance/api-turnover-bill";
    }

    @RequestMapping("/api-turnover-bill/trend")
    public String apiTurnoverBillTrend(String tid,String name,String cyc,Model model){
        Map<String,Object> param = new HashMap<>();
        if (tid != null){
            param.put("tid",tid);
            model.addAttribute("tid",tid);
        }
        if (cyc != null){
            param.put("cyc",cyc);
            model.addAttribute("cyc",cyc);
        }
        model.addAttribute("name",name);
       Map<String,Object> resu =  billService.queryApiTurnoverBillTrend(param);
        if (resu != null){
            for (Map.Entry<String,Object> me : resu.entrySet()) {
                if (me.getKey().equals("vendorTrendList")){
                    model.addAttribute("vendorTrendList",me.getValue());
                }
                if (me.getKey().equals("customerTrendList")){
                    model.addAttribute("customerTrendList",me.getValue());
                }
            }
        }
        List<String> conTimeList = billService.queryAllConsumeTime();
        model.addAttribute("conTimeList",conTimeList);
        return "/finance/api-turnover-bill-trend";
    }

    @RequestMapping("/api-turnover-bill/trend-data")
    @ResponseBody
    public String apiTurnoverBillTrendData(String tid,String cyc,Model model){
        Map<String,Object> param = new HashMap<>();
        if (tid != null){
            param.put("tid",tid);
            model.addAttribute("tid",tid);
        }
        if (cyc != null){
            param.put("cyc",cyc);
            model.addAttribute("cyc",cyc);
        }
        Map<String,Object> resu =  billService.queryApiTurnoverBillTrendData(param);
        return new Gson().toJson(resu).toString();
    }

}
