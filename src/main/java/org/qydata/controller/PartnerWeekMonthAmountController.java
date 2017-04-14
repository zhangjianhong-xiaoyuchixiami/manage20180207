package org.qydata.controller;

import org.apache.log4j.Logger;
import org.qydata.service.PartnerWeekMonthAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jonhn on 2017/1/6.
 */
@Controller
@RequestMapping("/partner-week-month-amount")
public class PartnerWeekMonthAmountController {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private PartnerWeekMonthAmountService partnerWeekMonthAmountService;

    /**
     * 统计和插入合作公司每周的付款数据
     * @return
     */
    @RequestMapping("/get-all-partner-week-payment-record-and-add-week-month-amount")
    @ResponseBody
    public String getAllPartnerWeekPaymentRecordAndAddWeekMonthAmount(Integer result){
        try {
            if (result == null) {
                result = 1;
            }
            partnerWeekMonthAmountService.addAllPartnerWeekPaymentRecordAndAddWeekMonthAmount(result);
        } catch (Exception e) {
            log.info("getAllPartnerWeekPaymentRecordAndAddWeekMonthAmount:统计和插入合作公司每周的付款数据");

            e.printStackTrace();
            return "no:500";
        }
        return "ok:200";
    }

    /**
     * 统计和插入合作公司每月的付款数据
     * @return
     */
    @RequestMapping("/get-all-partner-month-payment-record-and-add-week-month-amount")
    @ResponseBody
    public String getAllPartnerMonthPaymentRecordAndAddWeekMonthAmount(Integer result){
        try {
            if (result == null) {
                result = 1;
            }
            partnerWeekMonthAmountService.addAllPartnerMonthPaymentRecordAndAddWeekMonthAmount(result);
        } catch (Exception e) {
            log.info("getAllPartnerMonthPaymentRecordAndAddWeekMonthAmount:统计和插入合作公司每月的付款数据");
            e.printStackTrace();
            return "no:500";
        }
        return "ok:200";
    }

    /**
     * 统计和插入合作公司每周的收款数据
     * @return
     */
    @RequestMapping("/get-all-partner-week-receipt-record-and-add-week-month-amount")
    @ResponseBody
    public String getAllPartnerWeekReceiptRecordAndAddWeekMonthAmount(Integer result){
        try {
            if (result == null) {
                result = 1;
            }
            partnerWeekMonthAmountService.addAllPartnerWeekReceiptRecordAndAddWeekMonthAmount(result);
        } catch (Exception e) {
            log.info("getAllPartnerWeekReceiptRecordAndAddWeekMonthAmount:统计和插入合作公司每周的收款数据");
            e.printStackTrace();
            return "no:500";
        }
        return "ok:200";
    }

    /**
     * 统计和插入合作公司每月的收款数据
     * @return
     */
    @RequestMapping("/get-all-partner-month-receipt-record-and-add-week-month-amount")
    @ResponseBody
    public String getAllPartnerMonthReceiptRecordAndAddWeekMonthAmount(Integer result){
        try {
            if (result == null) {
                result = 1;
            }
            partnerWeekMonthAmountService.addAllPartnerMonthReceiptRecordAndAddWeekMonthAmount(result);
        } catch (Exception e) {
            log.info("getAllPartnerMonthReceiptRecordAndAddWeekMonthAmount:统计和插入合作公司每月的收款数据");
            e.printStackTrace();
            return "no:500";
        }
        return "ok:200";
    }
}
