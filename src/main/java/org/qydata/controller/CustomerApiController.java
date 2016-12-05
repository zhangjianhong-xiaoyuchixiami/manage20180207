package org.qydata.controller;

import org.qydata.entity.Api;
import org.qydata.entity.CustomerApi;
import org.qydata.service.CustomerApiService;
import org.qydata.tools.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/11/8.
 */
@Controller
@RequestMapping(value = "/customerApi")
public class CustomerApiController {

    @Autowired
    private CustomerApiService customerApiService;


    //添加CustomerApi
    @RequestMapping(value = "/addCustomerApiView/{customerId}")
    public String addCustomerApiView(@PathVariable("customerId") String customerId, Model model){
        List<Api> apiList = null;
        try {
            apiList = customerApiService.findAllApi();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute(customerId);
        model.addAttribute("apiList",apiList);
        return "/customerApi/addCustomerApi";
    }


    @RequestMapping(value = "/addCustomerApiAction")
    public String addCustomerApiAction(RedirectAttributes model,String price, String customerId, String apiId, String enabled){
        try {
            boolean flag = customerApiService.insertCustomerApi(price, customerId, apiId, enabled);
            if (!flag) {
                model.addFlashAttribute("msg","添加失败");
                return "redirect:/customerApi/addCustomerApiView/"+customerId;
            }
        }catch (Exception e){
            model.addFlashAttribute("msg","添加失败");
            return "redirect:/customerApi/addCustomerApiView/"+customerId;
        }
        return "redirect:/customerApi/customerApiListAction/"+customerId;
    }
    //根据客户Id查看所对应的的所有Api
    @RequestMapping(value = "/customerApiListAction/{customerId}")
    public String customerApiListAction(HttpServletRequest request, @PathVariable("customerId") String customerId, Model model){
        PageModel pageModel = new PageModel();
        String pageSize= request.getParameter("pageSize");//当前页
        String lineSize = "8";//每页显示条数
        pageModel.setPageSize(pageSize);
        pageModel.setLineSize(lineSize);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("beginIndex",pageModel.getBeginIndex());
        map.put("lineSize",pageModel.getLineSize());
        map.put("customerId",customerId);
        PageModel<CustomerApi> pageModelOne = null;
        try {
            pageModelOne = customerApiService.findAllByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("count",pageModelOne.getCount());
        model.addAttribute("customerApiList",pageModelOne.getList());
        model.addAttribute("totlePage",pageModelOne.getTotalpage());
        model.addAttribute("pageSize",pageModelOne.getPageSize());
        return "/customerApi/customerApiList";
    }

    //根据id查找指定的customerApi
    @RequestMapping(value = "/findCustomerApiById/{id}")
    public String findCustomerApiById(@PathVariable("id") String id,Model model){
        List<Api> list = null;
        try {
            list = customerApiService.findAllApi();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CustomerApi customerApi = null;
        try {
            customerApi = customerApiService.findById(Integer.parseInt(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("apiList",list);
        model.addAttribute(customerApi);
        return "/customerApi/updateCustomerApi";
    }

    //根据id修改指定的customerApi
    @RequestMapping(value = "/updateCustomerApiById")
    public String updateCustomerApiById(String id,String customerId,String price,String apiId,String enabled,RedirectAttributes model ){
        CustomerApi customerApi = new CustomerApi();
        customerApi.setId(Integer.parseInt(id));
        customerApi.setCustomerId(Integer.parseInt(customerId));
        customerApi.setPrice(Integer.parseInt(price.trim().replaceAll(",","")));
        customerApi.setApiId(Integer.parseInt(apiId));
        customerApi.setEnabled(Boolean.parseBoolean(enabled));
        try {
            boolean flag = customerApiService.updateCustomerApiById(customerApi);
            if (!flag) {
                model.addFlashAttribute("msg","修改失败");
                return "redirect:/customerApi/findCustomerApiById/"+customerApi.getId();
            }
        }catch (Exception e){
            model.addFlashAttribute("msg","修改失败");
            return "redirect:/customerApi/findCustomerApiById/"+customerApi.getId();
        }
        return "redirect:/customerApi/customerApiListAction/" + customerApi.getCustomerId();
    }

}
