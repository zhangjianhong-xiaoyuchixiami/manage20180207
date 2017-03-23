package org.qydata.controller;

import org.qydata.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jonhn on 2017/3/21.
 */
@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired private LogService logService;

    @RequestMapping("/log-message")
    public ModelAndView queryLogMessage(Model model){
        Map<String,Object> map = new HashMap<>();
        model.addAttribute("logList",logService.queryLog(map));
        return new ModelAndView("/log/logmessage");
    }

}
