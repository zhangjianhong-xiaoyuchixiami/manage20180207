package org.qydata.controller;

import org.qydata.service.CustomerHistoryBillService;
import org.qydata.service.SearchService;
import org.qydata.tools.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jonhn on 2017/11/15.
 */
@Controller
@RequestMapping("/search")
public class SearchControllor {

    @Autowired
    private SearchService service;
    @Autowired
    private CustomerHistoryBillService billService;

    /**
     * 搜索首页
     * @return
     */
    @RequestMapping("/customer-log")
    public String searchCustomerLogIndex(Model model){
        model.addAttribute("companyList",billService.queryAllCompany());
        return "/search/search_customer_log_index";
    }

    /**
     * 搜索结果页
     * @param k_reqId
     * @param cid
     * @param content
     * @param model
     * @return
     */
    @RequestMapping("/customer-log/result")
    public String searchCustomerLogResult(String k_reqId, Integer cid, String content, Model model){
        if (RegexUtil.isNull(k_reqId)){
            return "/search/search_customer_log_result";
        }
        if (RegexUtil.isNull(content)){
            return "/search/search_customer_log_result";
        }
        Map<String,Object> param = new HashMap<>();
        if (k_reqId != null){
            param.put("k_reqId",k_reqId);
            model.addAttribute("k_reqId",k_reqId);
        }
        if (cid != null){
            param.put("cid",cid);
            model.addAttribute("cid",cid);
        }
        if (content != null){
            param.put("content",content.trim());
            model.addAttribute("content",content.trim());
        }
        model.addAttribute("logList",service.queryCustomerReqLog(param));
        model.addAttribute("companyList",billService.queryAllCompany());
        return "/search/search_customer_log_result";
    }


}
