package org.qydata.controller;

import org.apache.log4j.Logger;
import org.qydata.service.WeekMonthAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jonhn on 2017/1/6.
 */
@Controller
@RequestMapping("/week-month-amount")
public class WeekMonthAmountController {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private WeekMonthAmountService weekMonthAmountService;

    @RequestMapping("/get-all-customer-week-recharge-record-and-add-week-month-amount")
    @ResponseBody
    public String getAllCustomerWeekRechargeRecordAndAddWeekMonthAmount(){
        try {
          weekMonthAmountService.getAllCustomerWeekRechargeRecordAndAddWeekMonthAmount();
        } catch (Exception e) {
            log.info("getAllCustomerWeekRechargeRecordAndAddWeekMonthAmount:统计和插入客户每周的充值数据异常");

            e.printStackTrace();
            return "no:500";
        }
        return "ok:200";
    }

    @RequestMapping("/get-all-customer-month-recharge-record-and-add-week-month-amount")
    @ResponseBody
    public String getAllCustomerMonthRechargeRecordAndAddWeekMonthAmount(){
        try {
           weekMonthAmountService.getAllCustomerMonthRechargeRecordAndAddWeekMonthAmount();
        } catch (Exception e) {
            log.info("getAllCustomerMonthRechargeRecordAndAddWeekMonthAmount:统计和插入客户每月的充值数据异常");
            e.printStackTrace();
            return "no:500";
        }
        return "ok:200";
    }
}
