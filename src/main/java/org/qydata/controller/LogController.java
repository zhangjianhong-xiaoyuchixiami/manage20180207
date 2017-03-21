package org.qydata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jonhn on 2017/3/21.
 */
@Controller
@RequestMapping("/log")
public class LogController {


   @RequestMapping("/log-message")
    public ModelAndView queryLogMessage(){

        return new ModelAndView("/log/logmessage");
    }

}
