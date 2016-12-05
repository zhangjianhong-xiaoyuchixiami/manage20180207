package org.qydata.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jonhn on 2016/11/29.
 */
@Controller
public class ErrorPages {

    @RequestMapping(value = "/error/404")
    public String get404Error(){
        return "/pages/404";
    }

    @RequestMapping(value = "/error/500")
    public String get500Error(){
        return "/pages/500";
    }
}
