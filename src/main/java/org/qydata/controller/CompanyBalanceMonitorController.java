package org.qydata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jonhn on 2017/8/22.
 */
@Controller
@RequestMapping("/company")
public class CompanyBalanceMonitorController {

    @RequestMapping("/balance-monitor")
    public String companyBalanceMonitor(){
        return "/company/company-balance-monitor";
    }

}
