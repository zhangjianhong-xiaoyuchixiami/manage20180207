package org.qydata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/operation")
public class ApiConsumeConditionController {

    /**
     * 查询产品消费情况
     * @return
     */
    @RequestMapping("/api-consume-condition")
    public String apiCondition(){
        return "/finance/api-response-condition";
    }
}
