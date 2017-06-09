package org.qydata.controller;

import org.qydata.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/3/21.
 */
@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired private LogService logService;

    @RequestMapping("/log-message")
    public ModelAndView queryLogMessage(Model model,String [] typeId,String title,String address,String uri,String reqWay,Integer operator,String operBeginDate,String operEndDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,Object> map = new HashMap<>();
        List<Integer> typeList = new ArrayList<>();
        if (typeId != null && typeId.length >0){
            for (int i = 0; i < typeId.length ; i++) {
                typeList.add(Integer.parseInt(typeId[i]));
            }
        }else {
            typeList.add(1);
            typeList.add(2);
        }
        map.put("typeList",typeList);
        if (title != null && title != ""){
            map.put("title",title.trim());
            model.addAttribute("title",title);
        }
        if (address != null && address != ""){
            map.put("address",address.trim());
            model.addAttribute("address",address);
        }
        if (uri != null && uri != ""){
            map.put("uri",uri.trim());
            model.addAttribute("uri",uri);
        }
        if (reqWay != null && reqWay != ""){
            map.put("reqWay",reqWay.trim());
            model.addAttribute("reqWay",reqWay);
        }
        if (operator != null){
            map.put("operator",operator);
            model.addAttribute("operator",operator);
        }
        if (operBeginDate != null && operBeginDate != ""){
            map.put("operBeginDate",sdfDate.format(sdf.parse(operBeginDate.trim())) +" "+"00:00:00");
            model.addAttribute("operBeginDate",operBeginDate);
        }
        if (operEndDate != null && operEndDate != ""){
            map.put("operEndDate",sdfDate.format(sdf.parse(operEndDate.trim())) +" "+"23:59:59");
            model.addAttribute("operEndDate",operEndDate);
        }
        model.addAttribute("typeList",typeList);
        model.addAttribute("logList",logService.queryLog(map));
        model.addAttribute("userList",logService.queryUser());
        return new ModelAndView("/log/logmessage");
    }

}
