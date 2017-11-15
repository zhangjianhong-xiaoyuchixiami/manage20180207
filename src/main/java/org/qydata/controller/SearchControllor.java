package org.qydata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jonhn on 2017/11/15.
 */
@Controller
@RequestMapping("/search")
public class SearchControllor {

    @RequestMapping("/customer-log")
    public String searchCustomerLogIndex(){
        return "/search/search_customer_log_index";
    }

}
