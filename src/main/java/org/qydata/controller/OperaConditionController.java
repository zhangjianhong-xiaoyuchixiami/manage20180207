package org.qydata.controller;

import org.qydata.service.OperaConditionService;
import org.qydata.tools.DateUtils;
import org.qydata.tools.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/operation")
public class OperaConditionController {
    @Autowired
    private OperaConditionService operaConditionServiceImpl;

    /**
     * 经营状况
     * @return
     */
    @RequestMapping("/operation_condition")
    @ResponseBody
    public String getCurrprofitAccount(){
        Map<String, Object> consumeConditionAccount = operaConditionServiceImpl.getConsumeConditionAccount();
        String json = JsonUtils.mapToJson(consumeConditionAccount);
        return json;
    }

}
