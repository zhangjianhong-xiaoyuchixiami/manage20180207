package org.qydata.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jonhn on 2017/7/4.
 */
@Controller
public class ExcelController {

    @RequestMapping("/excel/extra-account")
    public String extraAccount(){
        return "/excel/excel";
    }

}
