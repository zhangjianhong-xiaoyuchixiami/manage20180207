package org.qydata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jonhn on 2017/6/5.
 */

@Controller
@RequestMapping("/partner")
public class PartnerExcelController {

    @RequestMapping("/export-excel")
    public String PartnerExportExcel(){
        return "/finance/partner-excel";
    }

}
