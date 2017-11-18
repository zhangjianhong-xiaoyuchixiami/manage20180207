package org.qydata.controller;

import com.google.gson.Gson;
import org.qydata.tools.RegexUtil;
import org.qydata.tools.customer.OperatorList;
import org.qydata.tools.customer.RequestData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jonhn on 2017/11/17.
 */
@Controller
@RequestMapping("/data")
public class validController {


    @RequestMapping("/mobile/valid")
    public String mobileValid(){
        return "/valid/mobile_valid_index";
    }

    @RequestMapping("/mobile/valid/result")
    public String validResult(String tid, String mobile, String realName, String idNo, Integer aid, String omit, String skip, Model model){
        if (RegexUtil.isNull(tid)){
            return "/valid/mobile_valid_result";
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

//    @RequestMapping("/mobile/verify/3f")
//    public String mobileValid(){
//        return "/valid/mobile_valid";
//    }

}
