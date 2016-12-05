package org.qydata.controller;

import org.apache.log4j.Logger;
import org.qydata.entity.CustomerIp;
import org.qydata.service.CustomerIpService;
import org.qydata.tools.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by jonhn on 2016/11/8.
 */
@Controller
@RequestMapping(value = "/customerIp")
public class CustomerIpController {

    private final Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private CustomerIpService customerIpService;

    //添加Ip
    @RequestMapping("/addCustomerIpView/{id}")
    public String addCustomerIpView(@PathVariable("id") String id, Model model){
        model.addAttribute(id);
        return "customerIp/addCustomerIp";
    }
    @RequestMapping(value = "/addCustomerIpAction")
    public String addCustomerIpAction(String beginIp, String endIp, String customerId, RedirectAttributes model){
        try {
            boolean flag = customerIpService.insertCustomerIp(beginIp, endIp, customerId);
            if (!flag) {
                model.addFlashAttribute("msg", "添加失败");
                return "redirect:/customerIp/addCustomerIpView/"+customerId;
            }
        }catch (Exception e){
            model.addFlashAttribute("msg", "添加失败");
            log.error("addCustomerIpAction:添加Ip异常");
            return "redirect:/customerIp/addCustomerIpView/"+customerId;
        }
        return "redirect:/customerIp/customerIpListAction/"+customerId;
    }

    //根据客户Id查看所有Ip
    @RequestMapping(value = "/customerIpListAction/{customerId}")
    public String customerIpListAction(HttpServletRequest request, Model model, @PathVariable("customerId") String customerId){
        PageModel pageModel = new PageModel();
        String pageSize= request.getParameter("pageSize");//当前页
        String lineSize = "10";//每页显示条数
        pageModel.setPageSize(pageSize);
        pageModel.setLineSize(lineSize);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("beginIndex",pageModel.getBeginIndex());
        map.put("lineSize",pageModel.getLineSize());
        map.put("customerId",customerId);
        PageModel<CustomerIp> pageModelOne = customerIpService.findAllIpByCustomerId(map);
        model.addAttribute("count",pageModelOne.getCount());
        model.addAttribute("customerIpList",pageModelOne.getList());
        model.addAttribute("totlePage",pageModelOne.getTotalpage());
        model.addAttribute("pageSize",pageModelOne.getPageSize());
        return "/customerIp/customerIpList";
    }

    //删除客户Ip
    @RequestMapping(value = "/deleteIp/{id}/{customerId}")
    public String deleteIp(@PathVariable("id") String id,@PathVariable("customerId") String customerId){
        try {
            boolean flag = customerIpService.deleteIpById(Integer.parseInt(id));
        }catch (Exception e){
            e.printStackTrace();
            log.error("deleteIp:删除Ip异常");
        }
        return "redirect:/customerIp/customerIpListAction/" + customerId;
    }
}
