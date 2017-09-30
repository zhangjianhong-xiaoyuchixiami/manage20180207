package org.qydata.controller;

import com.google.gson.Gson;
import org.qydata.entity.CustomerCompanyEmail;
import org.qydata.entity.monitor.CompanyBalanceMonitor;
import org.qydata.service.CompanyBalanceMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/22.
 */
@Controller
@RequestMapping("/company")
public class CompanyBalanceMonitorController {

    @Autowired
    private CompanyBalanceMonitorService monitorService;

    /**
     * 查询客户余额报警列表
     * @param model
     * @return
     */
    @RequestMapping("/balance-monitor")
    public String companyBalanceMonitor(String name,Integer pid,Model model){
        Map<String,Object> param = new HashMap<>();
        if (name != null){
            param.put("name",name);
            model.addAttribute("name",name);
        }
        if (pid != null){
            param.put("pid",pid);
            model.addAttribute("pid",pid);
        }
        List<CompanyBalanceMonitor> monitorList = monitorService.queryCompanyBalanceMonitor(param);
        model.addAttribute("monitorList",monitorList);
        model.addAttribute("partnerList",monitorService.queryAllPartner());
        return "/company/company-balance-monitor";
    }

    /**
     * 修改是否预付
     * @param cid
     * @param value
     * @return
     */
    @RequestMapping("/balance-monitor/update-prepay")
    @ResponseBody
    public String updatePrepay(Integer cid,Integer value){
        Map<String,Object> resu = new HashMap<>();
        boolean flag = monitorService.updatePrepay(cid,value);
        if (flag){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 修改是否报警
     * @param cid
     * @param value
     * @return
     */
    @RequestMapping("/balance-monitor/update-alarm")
    @ResponseBody
    public String updateAlarm(Integer cid,Integer value){
        Map<String,Object> resu = new HashMap<>();
        boolean flag = monitorService.updateAlarm(cid,value);
        if (flag){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 修改是否提醒客户
     * @param cid
     * @param value
     * @return
     */
    @RequestMapping("/balance-monitor/update-remind-customer")
    @ResponseBody
    public String updateRemindCustomer(Integer cid,Integer value){
        Map<String,Object> resu = new HashMap<>();
        boolean flag = monitorService.updateRemindCustomer(cid,value);
        if (flag){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 修改余额可以几天提醒客户
     * @param cid
     * @param value
     * @return
     */
    @RequestMapping("/balance-monitor/update-ahead")
    @ResponseBody
    public String updateAhead(Integer cid,Integer value){
        Map<String,Object> resu = new HashMap<>();
        boolean flag = monitorService.updateAhead(cid,value);
        if (flag){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 查询客户邮箱
     * @param cid
     * @return
     */
    @RequestMapping("/balance-monitor/query-email")
    @ResponseBody
    public String queryEmailById(Integer cid){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        map.put("cid",cid);
        List<CustomerCompanyEmail> list = monitorService.queryEmailById(map);
        return gson.toJson(list);
    }

    /**
     * 删除邮箱
     * @param id
     * @param cid
     * @return
     */
    @RequestMapping("/balance-monitor/query-email/delete")
    @ResponseBody
    public String deleteEmail(Integer id,Integer cid){
        Map<String,Object> resu = new HashMap<>();
        boolean flag = monitorService.deleteEmail(id,cid);
        if (flag){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 添加邮箱
     * @param cid
     * @param value
     * @return
     */
    @RequestMapping("/balance-monitor/query-email/add")
    @ResponseBody
    public String addEmail(Integer cid,String value){
        Map<String,Object> resu = new HashMap<>();
        boolean flag = monitorService.addEmail(cid,value);
        if (flag){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }


}
