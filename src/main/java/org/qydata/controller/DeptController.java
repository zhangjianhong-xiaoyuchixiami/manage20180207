package org.qydata.controller;

import org.apache.log4j.Logger;
import org.qydata.entity.Dept;
import org.qydata.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/12/3.
 */
@Controller
@RequestMapping(value = "/dept")
public class DeptController {

    private final Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private DeptService deptService;


    @RequestMapping( value = "/addView")
    public String addView(){
        return "";
    }
    @RequestMapping( value = "/addAction")
    public String addAction(Dept dept){
        try {
            deptService.addDept(dept);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("");
        }
        return "";
    }
}
