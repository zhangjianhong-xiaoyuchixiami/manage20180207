package org.qydata.controller;

import org.qydata.entity.Api;
import org.qydata.entity.CustomerApi;
import org.qydata.regex.RegexUtil;
import org.qydata.service.CustomerApiService;
import org.qydata.tools.IpTool;
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
            Map<String,Object> map = new HashMap();
            map.put("customerId",Integer.parseInt(customerId));
            apiList = customerApiService.findAllApi(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute(customerId);
        model.addAttribute("apiList",apiList);
        return "/customerApi/addCustomerApi";
    }

    @RequestMapping(value = "/addCustomerApiAction")
    public String addCustomerApiAction(RedirectAttributes model,String price, String customerId, String apiId, String enabled){

        if(RegexUtil.isNull(price)){
            model.addFlashAttribute("CustomerMessagePrice","请输入金额");
            return "redirect:/customerApi/addCustomerApiView/"+customerId;
        }
        List priceList = IpTool.spiltStr(price);
        for(int i=0;i<priceList.size();i++){
            if(!RegexUtil.isFloatZero((String) priceList.get(i))){
                model.addFlashAttribute("price",price);
                model.addFlashAttribute("CustomerMessagePrice","金额格式不正确");
                return "redirect:/customerApi/addCustomerApiView/"+customerId;
            }else{
                if(Integer.parseInt((String) priceList.get(i))<=0){
                    model.addFlashAttribute("CustomerMessagePrice","金额必须大于0");
                    return "redirect:/customerApi/addCustomerApiView/"+customerId;
                }
            }
        }
        if(RegexUtil.isNull(customerId)){
            return "redirect:/customerApi/addCustomerApiView/"+customerId;
        }
        if(RegexUtil.isNull(apiId)){
            return "redirect:/customerApi/addCustomerApiView/"+customerId;
        }
        if(RegexUtil.isNull(enabled)){
            return "redirect:/customerApi/addCustomerApiView/"+customerId;
        }

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
        Integer totalPage= null;
        Integer count = pageModelOne.getCount();

        if (count%Integer.parseInt(lineSize) == 0) {
            totalPage = (count/Integer.parseInt(lineSize));
        } else {
            totalPage = (count/Integer.parseInt(lineSize)) + 1;
        }
        model.addAttribute("totlePage",totalPage);
        model.addAttribute("pageSize",pageModel.getPageSize());
        return "/customerApi/customerApiList";
    }

    //根据id查找指定的customerApi
    @RequestMapping(value = "/findCustomerApiById/{id}")
    public String findCustomerApiById(@PathVariable("id") String id,Model model){
        List<Api> list = null;
        CustomerApi customerApi = null;
        try {
            Map<String,Object> map = new HashMap<>();
            list = customerApiService.findAllApi(map);
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
        if(RegexUtil.isNull(id)){
            return "redirect:/customerApi/findCustomerApiById/"+id;
        }
        if(RegexUtil.isNull(price)){
            model.addFlashAttribute("CustomerMessagePrice","请输入金额");
            return "redirect:/customerApi/findCustomerApiById/"+id;
        }
        if(!RegexUtil.isFloatZero(price)){
            model.addFlashAttribute("CustomerMessagePrice","金额格式不正确");
            return "redirect:/customerApi/findCustomerApiById/"+id;
        }else {
            if(Integer.parseInt(price)<=0){
                model.addFlashAttribute("CustomerMessagePrice","金额必须大于0");
                return "redirect:/customerApi/findCustomerApiById/"+id;
            }
        }
        if(RegexUtil.isNull(customerId)){
            return "redirect:/customerApi/findCustomerApiById/"+id;
        }
        if(RegexUtil.isNull(apiId)){
            return "redirect:/customerApi/findCustomerApiById/"+id;
        }
        if(RegexUtil.isNull(enabled)){
            return "redirect:/customerApi/findCustomerApiById/"+id;
        }


        try {
            boolean flag = customerApiService.updateCustomerApiById(id,price,apiId,enabled);
            if (!flag) {
                model.addFlashAttribute("msg","修改失败");
                return "redirect:/customerApi/findCustomerApiById/"+id;
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("msg","修改失败");
            return "redirect:/customerApi/findCustomerApiById/"+id;
        }
        return "redirect:/customerApi/customerApiListAction/" + customerId;
    }

}
