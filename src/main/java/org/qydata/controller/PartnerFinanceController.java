package org.qydata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jonhn on 2017/2/6.
 */
@Controller
@RequestMapping("/partner")
public class PartnerFinanceController {


    //合作伙伴来往账目
    @RequestMapping("/find-all-partner-financial-account")
    public String findPartnersFinancialAccount(){
        return "/finance/partnersFinancialAccount";
    }

    //合作伙伴来往账目-收支明细
    @RequestMapping("/find-all-partner-financial-account/receipt-and-paying-record")
    public String findPartnersReceiptAndPayingRecord(){
        return "/finance/partnersReceiptAndPayingRecord";
    }
}
