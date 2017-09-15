package org.qydata.controller;

import org.qydata.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
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
    public String rebate(Model model){
        Map<String,Object> param = new HashMap<>();
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
        return "/agency/rebate";
    }


}
