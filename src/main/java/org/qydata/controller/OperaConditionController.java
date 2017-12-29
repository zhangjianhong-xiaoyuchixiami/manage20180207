package org.qydata.controller;

import org.qydata.dst.CustomerIncome;
import org.qydata.dst.OperaCondition;
import org.qydata.dst.VendorCost;
import org.qydata.service.OperaConditionService;
import org.qydata.tools.DateUtils;
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

    /**
     * 查询近两天消费的产品的情况
     * @param model
     * @return
     */
    @RequestMapping("/api_operation_condition")
    public String getApiOperaCondition(Model model){
        List<OperaCondition> apiOperaConditionList = operaConditionServiceImpl.getApiOperaCondition();
        OrderUtils orderUtils = new OrderUtils();
        Collections.sort(apiOperaConditionList, orderUtils);
        model.addAttribute("apiOperaCondition", apiOperaConditionList);
        return  "/finance/api-response-condition";
    }

    /**
     * 按产品查询当前收入情况
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getCurrCustomerIncomeCondition")
    @ResponseBody
    public String getCurrCustomerIncomeCondition(HttpServletRequest request, HttpServletResponse response){
        String apiTypeId = request.getParameter("typeId");
        String subTypeId = request.getParameter("subTypeId");

        Map<String, Object>map = new HashMap<String, Object>();
        String currTime = DateUtils.currHour();
        String currDawn = DateUtils.currDawn();
        map.put("Dawn", currDawn);
        map.put("currTime", currTime);
        map.put("apiTypeId", apiTypeId);
        if (subTypeId != null){
            map.put("subTypeId", subTypeId);
        }
        List<CustomerIncome> currCustomerIncomes = operaConditionServiceImpl.getCurrCustomerIncomeCondition(map);
        OrderCustomerAmount oca = new OrderCustomerAmount();
        Collections.sort(currCustomerIncomes, oca);
        String json = JsonUtils.listToJson(currCustomerIncomes);

        return json;
    }

    /**
     * 按产品查昨天的收入情况
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getYestCustomerIncomeCondition")
    @ResponseBody
    public String getYestCustomerIncomeCondition(HttpServletRequest request, HttpServletResponse response){
        String apiTypeId = request.getParameter("typeId");
        String subTypeId = request.getParameter("subTypeId");

        Map<String, Object>map = new HashMap<String, Object>();
        String yesterDawn = DateUtils.yesterDawn();
        String currDawn = DateUtils.currDawn();
        map.put("Dawn", yesterDawn);
        map.put("currTime", currDawn);
        map.put("apiTypeId", apiTypeId);
        if (subTypeId != null){
            map.put("subTypeId", subTypeId);
        }
        List<CustomerIncome> yestCustomerIncomes = operaConditionServiceImpl.getYesterCustomerIncomeCondition(map);

        OrderCustomerAmount oca = new OrderCustomerAmount();
        Collections.sort(yestCustomerIncomes, oca);
        String json = JsonUtils.listToJson(yestCustomerIncomes);

        return json;
    }

    /**
     * 昨天同时段收入
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getYestHourCustomerIncomeCondition")
    @ResponseBody
    public String getYestHourCustomerIncomeCondition(HttpServletRequest request, HttpServletResponse response){
        String apiTypeId = request.getParameter("typeId");
        String subTypeId = request.getParameter("subTypeId");
        String yesterDawn = DateUtils.yesterDawn();
        String yesterHour = DateUtils.yesterHour();

        Map<String, Object>map = new HashMap<String, Object>();
        map.put("Dawn", yesterDawn);
        map.put("currTime", yesterHour);
        map.put("apiTypeId", apiTypeId);
        if (subTypeId != null){
            map.put("subTypeId", subTypeId);
        }
        List<CustomerIncome> yestCustomerIncomes = operaConditionServiceImpl.getYesterHourCustomerIncomeCondition(map);

        OrderCustomerAmount oca = new OrderCustomerAmount();
        Collections.sort(yestCustomerIncomes, oca);
        String json = JsonUtils.listToJson(yestCustomerIncomes);

        return json;
    }

    /**
     * 按产品查询昨天的成本
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getYestVendorCostCondition")
    @ResponseBody
    public String getYestVendorCostCondition(HttpServletRequest request, HttpServletResponse response){
        String apiTypeId = request.getParameter("typeId");
        String subTypeId = request.getParameter("subTypeId");
        String yesterDawn = DateUtils.yesterDawn();
        String currDawn = DateUtils.currDawn();

        Map<String, Object>map = new HashMap<String, Object>();
        map.put("Dawn", yesterDawn);
        map.put("currTime", currDawn);
        map.put("apiTypeId", apiTypeId);
        if (subTypeId != null){
            map.put("subTypeId", subTypeId);
        }
        List<VendorCost> yestVendorCostCondition = operaConditionServiceImpl.getYesterVendorCostCondition(map);

        OrderVendorAmount ova = new OrderVendorAmount();
        Collections.sort(yestVendorCostCondition, ova);
        String json = JsonUtils.listToJson(yestVendorCostCondition);

        return json;
    }

    /**
     * 按产品查询昨天同时段的成本
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getYestHourVendorCostCondition")
    @ResponseBody
    public String getYestHourVendorCostCondition(HttpServletRequest request, HttpServletResponse response){
        String apiTypeId = request.getParameter("typeId");
        String subTypeId = request.getParameter("subTypeId");
        String yesterDawn = DateUtils.yesterDawn();
        String yesterHour = DateUtils.yesterHour();

        Map<String, Object>map = new HashMap<String, Object>();
        map.put("Dawn", yesterDawn);
        map.put("currTime", yesterHour);
        map.put("apiTypeId", apiTypeId);
        if (subTypeId != null){
            map.put("subTypeId", subTypeId);
        }
        List<VendorCost> yestVendorCostCondition = operaConditionServiceImpl.getYesterHourVendorCostCondition(map);

        OrderVendorAmount ova = new OrderVendorAmount();
        Collections.sort(yestVendorCostCondition, ova);
        String json = JsonUtils.listToJson(yestVendorCostCondition);

        return json;
    }

    /**
     * 按产品查询当天的成品
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getCurrVendorCostCondition")
    @ResponseBody
    public String getCurrVendorCostCondition(HttpServletRequest request, HttpServletResponse response){
        String apiTypeId = request.getParameter("typeId");
        String subTypeId = request.getParameter("subTypeId");
        String currTime = DateUtils.currHour();
        String currDawn = DateUtils.currDawn();

        Map<String, Object>map = new HashMap<String, Object>();
        map.put("Dawn", currDawn);
        map.put("currTime", currTime);
        map.put("apiTypeId", apiTypeId);
        if (subTypeId != null){
            map.put("subTypeId", subTypeId);
        }
        List<VendorCost> currVendorCostCondition = operaConditionServiceImpl.getCurrVendorCostCondition(map);
        OrderVendorAmount ova = new OrderVendorAmount();
        Collections.sort(currVendorCostCondition, ova);
        String json = JsonUtils.listToJson(currVendorCostCondition);

        return json;
    }

}
