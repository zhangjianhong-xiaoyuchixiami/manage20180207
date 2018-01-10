package org.qydata.controller;

import org.qydata.dst.ApiResponseCondition;
import org.qydata.service.ApiResponseConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/apiResponseCondition")
public class ApiResponseConditionController {

    @Autowired
    private ApiResponseConditionService apiResponseConditionServiceImpl;
    @RequestMapping("/allApiResponse")
    public String queryAllApiResponse(Model model){
        List<ApiResponseCondition> apiResponseConditions = apiResponseConditionServiceImpl.queryAllApiResponse();
        model.addAttribute("apiResponseConditions", apiResponseConditions);
        return  "/api/api-response-condition";
    }

}
