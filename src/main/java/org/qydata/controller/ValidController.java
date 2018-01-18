package org.qydata.controller;

import com.google.gson.Gson;
import org.qydata.dst.api.Aid;
import org.qydata.dst.valid.ValidResult;
import org.qydata.service.ValidService;
import org.qydata.tools.RegexUtil;
import org.qydata.tools.customer.IdcardValidator;
import org.qydata.tools.customer.OperatorList;
import org.qydata.tools.customer.RequestData;
import org.qydata.tools.customer.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @ResponseBody
    public String validResult(HttpServletRequest request,String omit, String skip, String address){
        String [] tid = request.getParameterValues("tid[]");
        String [] aid = request.getParameterValues("aid[]");
        String [] mobile = request.getParameterValues("mobile[]");
        String [] idNo = request.getParameterValues("idNo[]");
        String [] realName = request.getParameterValues("realName[]");
        String [] bankcard = request.getParameterValues("bankcard[]");
        List<ValidResult> resultList = service.queryValidResult(tid,aid,mobile,idNo,realName,bankcard,omit,skip,address);
        System.out.println(new Gson().toJson(resultList));
        return new Gson().toJson(resultList);
    }

//    /*运营商判断*/
//    @RequestMapping("/mobile/operator")
//    @ResponseBody
//    public String validMobile(String mobile){
//        Map<String,Object> map = new HashMap<>();
//        int operator =  OperatorList.isOperator(mobile);
//        if (operator == 1){
//            map.put("operator","中国移动");
//            return new Gson().toJson(map);
//        }
//        if (operator == 2){
//            map.put("operator","中国联通");
//            return new Gson().toJson(map);
//        }
//        if (operator == 3){
//            map.put("operator","中国电信");
//            return new Gson().toJson(map);
//        }
//        map.put("operator","虚拟运营商或错误号段");
//        return new Gson().toJson(map);
//    }
//
//    /*身份证效验*/
//    @RequestMapping("/mobile/idCard")
//    @ResponseBody
//    public String validIdCard(String idCard){
//        Map<String,Object> map = new HashMap<>();
//        boolean flag =  IdcardValidator.isValidatedAllIdcard(idCard.trim());
//        if (flag){
//            map.put("flag","身份证效验通过");
//            return new Gson().toJson(map);
//        }
//        map.put("flag","身份证不合法");
//        return new Gson().toJson(map);
//    }

    /*指定产品*/
    @RequestMapping("/mobile/aid")
    @ResponseBody
    public String validAid(HttpServletRequest request){
        String [] tid = request.getParameterValues("tid[]");
        List<Aid> aidList = service.queryAidByTid(tid);
        return new Gson().toJson(aidList);
    }

    /*分练输入参数*/
    @RequestMapping("/mobile/valid_content")
    @ResponseBody
    public String validContent(String valid_content){
        if (!RegexUtil.isNull(valid_content)){
            return StringUtils.check(valid_content);
        }
      return "";
    }

}
