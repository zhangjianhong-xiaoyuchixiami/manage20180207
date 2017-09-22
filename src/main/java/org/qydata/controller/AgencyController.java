package org.qydata.controller;

import com.google.gson.Gson;
import org.qydata.entity.agency.AgencyCustomer;
import org.qydata.entity.agency.RebateAgency;
import org.qydata.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/9/13.
 */
@Controller
@RequestMapping("/finance")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @RequestMapping("/rebate")
    public String rebate(Integer rate,Integer cache,Integer agency,Integer [] cid,String [] cyc,Model model){
        Map<String,Object> param = new HashMap<>();
        if (rate != null){
            param.put("rate",rate);
            model.addAttribute("rate",rate);
        }
        if (cache != null){
            param.put("cache",cache);
            model.addAttribute("cache",cache);
        }
        if (agency != null){
            param.put("agency",agency);
            model.addAttribute("agencyId",agency);
            List<AgencyCustomer> companyList = agencyService.queryAgencyCustomer(param);
            model.addAttribute("companyList",companyList);
        }
        if (cid != null){
            param.put("cid",cid);
            model.addAttribute("companyArray",cid);
        }
        if (cyc != null){
            param.put("cyc",cyc);
            model.addAttribute("cycleArray",cyc);
        }
        Map<String,Object> resu = agencyService.queryAgencyBill(param);
        if (resu != null){
            for (Map.Entry<String,Object> me : resu.entrySet()) {
                if (me.getKey().equals("billList")){
                    model.addAttribute("billList",me.getValue());
                }
                if (me.getKey().equals("netProfitTot")){
                    model.addAttribute("netProfitTot",me.getValue());
                }
            }
        }
        List<RebateAgency> agencyList = agencyService.queryRebateAgency();
        List<String> cycleList = agencyService.queryConsumeCycle();
        model.addAttribute("agencyList",agencyList);
        model.addAttribute("cycleList",cycleList);
        return "/agency/rebate";
    }

    @RequestMapping("/rebate/customer")
    @ResponseBody
    public String rebateCustomer(Integer agency){
        Map<String,Object> param = new HashMap<>();
        param.put("agency",agency);
        List<AgencyCustomer> companyList = agencyService.queryAgencyCustomer(param);
        return new Gson().toJson(companyList);
    }

    @RequestMapping("/rebate/detail")
    public String rebateDetail(Integer agencyId, String [] cyc, String [] tid, Integer rate, Integer cache,Model model){
        Map<String,Object> param = new HashMap<>();
        if (agencyId != null){
            param.put("agencyId",agencyId);
        }
        if (cyc != null){
            param.put("cyc",cyc);
        }
        if (tid != null){
            param.put("tid",tid);
        }
        if (rate != null){
            param.put("rate",rate);
        }
        if (cache != null){
            param.put("cache",cache);
        }
        Map<String,Object> resu = agencyService.queryAgencyBillDetail(param);
        if (resu != null){
            for (Map.Entry<String,Object> me : resu.entrySet()) {
                if (me.getKey().equals("detailList")){
                    model.addAttribute("detailList",me.getValue());
                }
            }
        }
        return "/agency/rebate_detail";
    }

    //修改扣费量
    @RequestMapping("/rebate/detail/update-amount")
    @ResponseBody
    public String rebateUpdateAmount(Integer id,Integer amount){
        Map<String,Object> resu = new HashMap<>();
        if (agencyService.updateRebateBillAmount(id,amount)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

}
