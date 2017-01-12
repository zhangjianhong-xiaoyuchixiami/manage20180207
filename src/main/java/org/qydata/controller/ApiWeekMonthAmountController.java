package org.qydata.controller;

import org.apache.log4j.Logger;
import org.qydata.service.ApiWeekMonthAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jonhn on 2017/1/6.
 */
@Controller
@RequestMapping("/api-week-month-amount")
public class ApiWeekMonthAmountController {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private ApiWeekMonthAmountService apiWeekMonthAmountService;

    /**
     * 统计和插入客户每周的Api消费数据
     * @return
     */
    @RequestMapping("/get-all-api-week-consume-record-and-add-api-week-month-amount")
    @ResponseBody
    public String getAllApiWeekConsumeRecordAndAddApiWeekMonthAmount(){
        try {
            apiWeekMonthAmountService.getAllApiWeekConsumeRecordAndAddApiWeekMonthAmount();
        } catch (Exception e) {
            log.info("getAllApiWeekConsumeRecordAndAddApiWeekMonthAmount:统计和插入每周的Api消费数据异常");
            e.printStackTrace();
            return "no:500";
        }
        return "ok:200";
    }

    /**
     * 统计和插入客户每月的Api消费数据
     * @return
     */
    @RequestMapping("/get-all-api-month-consume-record-and-add-api-week-month-amount")
    @ResponseBody
    public String getAllApiMonthConsumeRecordAndAddApiWeekMonthAmount(){
        try {
            apiWeekMonthAmountService.getAllApiMonthConsumeRecordAndAddApiWeekMonthAmount();
        } catch (Exception e) {
            log.info("getAllApiMonthConsumeRecordAndAddApiWeekMonthAmount:统计和插入每月的Api消费数据异常");
            e.printStackTrace();
            return "no:500";
        }
        return "ok:200";
    }
}
