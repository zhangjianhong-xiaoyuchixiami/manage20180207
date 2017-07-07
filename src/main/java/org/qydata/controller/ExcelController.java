package org.qydata.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jonhn on 2017/7/4.
 */
@Controller
public class ExcelController {

    @RequestMapping("/excel/extra-account")
    public String extraAccount(Integer pid,Integer obj,Integer wid,Integer cid,String beginDate,String endDate){
        Map<String,Object> map = new HashMap<>();

        if (pid != null){
            map.put("pid",pid);
        }
        if (obj != null){
            map.put("obj",obj);
        }
        if (wid != null){
            map.put("wid",wid);
        }
        if (cid != null){
            map.put("cid",cid);
        }
        if (beginDate != null && beginDate != ""){
            map.put("beginDate",beginDate);
        }
        if (endDate != null && endDate != ""){
            map.put("endDate",endDate);
        }

        return "/excel/excel";
    }

}
