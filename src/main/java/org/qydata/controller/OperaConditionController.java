package org.qydata.controller;

import org.qydata.dst.CustomerIncome;
import org.qydata.dst.OperaCondition;
import org.qydata.dst.VendorCost;
import org.qydata.service.OperaConditionService;
import org.qydata.tools.JsonUtils;
import org.qydata.tools.OrderUtils;
import org.qydata.tools.OrderVendorAmount;
import org.qydata.tools.thread.OrderCustomerAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
    public String getOperaCondition(){
        Map<String, Object> consumeConditionAccount = operaConditionServiceImpl.getConsumeConditionAccount();
        String json = JsonUtils.mapToJson(consumeConditionAccount);
        return json;
    }

    @RequestMapping("/api_operation_condition")
    public String getApiOperaCondition(Model model){
        List<OperaCondition> apiOperaConditionList = operaConditionServiceImpl.getApiOperaCondition();
        OrderUtils orderUtils = new OrderUtils();
        Collections.sort(apiOperaConditionList, orderUtils);
        model.addAttribute("apiOperaCondition", apiOperaConditionList);
        return  "/finance/api-response-condition";
    }

    @RequestMapping("/getCurrCustomerIncomeCondition")
    @ResponseBody
    public String getCurrCustomerIncomeCondition(HttpServletRequest request, HttpServletResponse response){
        String apiTypeId = request.getParameter("typeId");
        String subTypeId = request.getParameter("subTypeId");

        Map<String, Object>map = new HashMap<String, Object>();
        map.put("apiTypeId", apiTypeId);
        if (subTypeId != null){
            map.put("subTypeId", subTypeId);
        }
        List<CustomerIncome> currCustomerIncomes = operaConditionServiceImpl.getCurrCustomerIncomeCondition(map);
        OrderVendorAmount ova = new OrderVendorAmount();
        Collections.sort(currCustomerIncomes, ova);
        String json = JsonUtils.listToJson(currCustomerIncomes);

        return json;
    }


    @RequestMapping("/getYestCustomerIncomeCondition")
    @ResponseBody
    public String getYestCustomerIncomeCondition(HttpServletRequest request, HttpServletResponse response){
        String apiTypeId = request.getParameter("typeId");
        String subTypeId = request.getParameter("subTypeId");

        Map<String, Object>map = new HashMap<String, Object>();
        map.put("apiTypeId", apiTypeId);
        if (subTypeId != null){
            map.put("subTypeId", subTypeId);
        }
        List<CustomerIncome> yestCustomerIncomes = operaConditionServiceImpl.getYesterCustomerIncomeCondition(map);
        OrderVendorAmount ova = new OrderVendorAmount();
        Collections.sort(yestCustomerIncomes, ova);
        String json = JsonUtils.listToJson(yestCustomerIncomes);

        return json;
    }


    @RequestMapping("/getYestVendorCostCondition")
    @ResponseBody
    public String getYestVendorCostCondition(HttpServletRequest request, HttpServletResponse response){
        String apiTypeId = request.getParameter("typeId");
        String subTypeId = request.getParameter("subTypeId");

        Map<String, Object>map = new HashMap<String, Object>();
        map.put("apiTypeId", apiTypeId);
        if (subTypeId != null){
            map.put("subTypeId", subTypeId);
        }
        List<VendorCost> yestVendorCostCondition = operaConditionServiceImpl.getYesterVendorCostCondition(map);
        OrderCustomerAmount oca = new OrderCustomerAmount();
        Collections.sort(yestVendorCostCondition, oca);
        String json = JsonUtils.listToJson(yestVendorCostCondition);

        return json;
    }

    @RequestMapping("/getCurrVendorCostCondition")
    @ResponseBody
    public String getCurrVendorCostCondition(HttpServletRequest request, HttpServletResponse response){
        String apiTypeId = request.getParameter("typeId");
        String subTypeId = request.getParameter("subTypeId");

        Map<String, Object>map = new HashMap<String, Object>();
        map.put("apiTypeId", apiTypeId);
        if (subTypeId != null){
            map.put("subTypeId", subTypeId);
        }
        List<VendorCost> currVendorCostCondition = operaConditionServiceImpl.getCurrVendorCostCondition(map);
        OrderCustomerAmount oca = new OrderCustomerAmount();
        Collections.sort(currVendorCostCondition, oca);
        String json = JsonUtils.listToJson(currVendorCostCondition);

        return json;
    }

}
