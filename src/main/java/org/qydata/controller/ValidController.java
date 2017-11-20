package org.qydata.controller;

import com.google.gson.Gson;
import org.qydata.dst.api.Aid;
import org.qydata.service.ValidService;
import org.qydata.tools.RegexUtil;
import org.qydata.tools.customer.IdcardValidator;
import org.qydata.tools.customer.OperatorList;
import org.qydata.tools.customer.RequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/11/17.
 */
@Controller
@RequestMapping("/data")
public class ValidController {

    @Autowired
    private ValidService service;

    /*运营商核验首页*/
    @RequestMapping("/mobile/valid")
    public String mobileValid(){
        return "/valid/mobile_valid_index";
    }

    /*运营商核验结果页*/
    @RequestMapping("/mobile/valid/result")
    public String validResult(String tid, String mobile, String realName, String idNo, Integer aid, String omit, String skip, Model model){
        System.out.println(tid);
        System.out.println(mobile);
        System.out.println(realName);
        System.out.println(idNo);
        System.out.println(aid);
        System.out.println(omit);
        System.out.println(skip);
        if (RegexUtil.isNull(tid)){
            return "/valid/mobile_valid_result";
        }
        if (tid != null && tid != ""){
            model.addAttribute("tid",tid);
            List<Aid> aidList = service.queryAidByUrl(tid.trim());
            model.addAttribute("aidList",aidList);
        }
        if (mobile != null && mobile != ""){
            model.addAttribute("mobile",mobile);
        }
        if (realName != null && realName != ""){
            model.addAttribute("realName",realName);
        }
        if (idNo != null && idNo != ""){
            model.addAttribute("idNo",idNo);
        }
        if (aid != null){
            model.addAttribute("aid",aid);
        }
        if (omit != null && omit != ""){
            model.addAttribute("omit",omit);
        }
        if (skip != null && skip != ""){
            model.addAttribute("skip",skip);
        }
        Map<String,Object> map = RequestData.result(tid,mobile,realName,idNo,aid,omit,skip);
        if (map != null){
            for (Map.Entry<String,Object> me : map.entrySet()) {
                if (me.getKey().equals("result")){
                    model.addAttribute("result",me.getValue());
                }
                if (me.getKey().equals("respResult")){
                    model.addAttribute("respResult",me.getValue());
                }
            }
        }
        return "/valid/mobile_valid_result";
    }

    /*运营商判断*/
    @RequestMapping("/mobile/operator")
    @ResponseBody
    public String validMobile(String mobile){
        Map<String,Object> map = new HashMap<>();
        int operator =  OperatorList.isOperator(mobile);
        if (operator == 1){
            map.put("operator","中国移动");
            return new Gson().toJson(map);
        }
        if (operator == 2){
            map.put("operator","中国联通");
            return new Gson().toJson(map);
        }
        if (operator == 3){
            map.put("operator","中国电信");
            return new Gson().toJson(map);
        }
        map.put("operator","虚拟运营商或错误号段");
        return new Gson().toJson(map);
    }

    /*身份证效验*/
    @RequestMapping("/mobile/idCard")
    @ResponseBody
    public String validIdCard(String idCard){
        Map<String,Object> map = new HashMap<>();
        boolean flag =  IdcardValidator.isValidatedAllIdcard(idCard.trim());
        if (flag){
            map.put("flag","身份证效验通过");
            return new Gson().toJson(map);
        }
        map.put("flag","身份证不合法");
        return new Gson().toJson(map);
    }

    /*指定产品级联*/
    @RequestMapping("/mobile/aid")
    @ResponseBody
    public String validAid(String tid){
        List<Aid> aidList = service.queryAidByUrl(tid.trim());
        return new Gson().toJson(aidList);
    }

}
