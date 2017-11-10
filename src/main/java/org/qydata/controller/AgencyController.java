package org.qydata.controller;

import com.google.gson.Gson;
import org.qydata.entity.CompanyApi;
import org.qydata.entity.agency.AgencyCustomer;
import org.qydata.entity.agency.RebateAgency;
import org.qydata.service.AgencyService;
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
 * Created by jonhn on 2017/9/13.
 */
@Controller
@RequestMapping("/finance")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @RequestMapping("/rebate")
    public String rebateAgency(Model model){
       List<RebateAgency> agencyList = agencyService.queryAgency();
       model.addAttribute("agencyList",agencyList);
       return "/agency/rebate_agency";
    }

    //代理人返佣明细
    @RequestMapping("/rebate/detail")
    public String rebateDetail(Integer agencyId,String name,Integer [] cid ,String [] cyc, String [] tid,Model model){
        Map<String,Object> param = new HashMap<>();
        if (agencyId != null){
            param.put("agencyId",agencyId);
            model.addAttribute("agencyId",agencyId);
            model.addAttribute("rule",agencyService.queryRebateRuleById(agencyId));
        }
        if (name != null){
            model.addAttribute("name",name);
        }
        if (cid != null){
            param.put("cid",cid);
            model.addAttribute("companyArray",cid);
        }
        if (cyc != null){
            param.put("cyc",cyc);
            model.addAttribute("cycleArray",cyc);
        }
        if (tid != null){
            param.put("tid",tid);
            model.addAttribute("typeArray",tid);
        }
        Map<String,Object> resu = agencyService.queryAgencyBill(param);
        if (resu != null){
            for (Map.Entry<String,Object> me : resu.entrySet()) {
                if (me.getKey().equals("detailList")){
                    model.addAttribute("detailList",me.getValue());
                }
                if (me.getKey().equals("detailListOppo")){
                    model.addAttribute("detailListOppo",me.getValue());
                }
                if (me.getKey().equals("detailListCache")){
                    model.addAttribute("detailListCache",me.getValue());
                }
                if (me.getKey().equals("data")){
                    Map<String,Object> data = (Map<String, Object>) me.getValue();
                    if (data != null){
                        for (Map.Entry<String,Object> meData : data.entrySet()) {
                            if (meData.getKey().equals("costRealOur")){
                                model.addAttribute("costRealOur",meData.getValue());
                            }
                            if (meData.getKey().equals("costVirtualOur")){
                                model.addAttribute("costVirtualOur",meData.getValue());
                            }
                            if (meData.getKey().equals("costOpposite")){
                                model.addAttribute("costOpposite",meData.getValue());
                            }
                            if (meData.getKey().equals("firstRebate")){
                                model.addAttribute("firstRebate",meData.getValue());
                            }
                            if (meData.getKey().equals("secondaryRebate")){
                                model.addAttribute("secondaryRebate",meData.getValue());
                            }
                            if (meData.getKey().equals("cacheRebate")){
                                model.addAttribute("cacheRebate",meData.getValue());
                            }
                            if (meData.getKey().equals("costCache")){
                                model.addAttribute("costCache",meData.getValue());
                            }
                            if (meData.getKey().equals("netProfit")){
                                model.addAttribute("netProfit",meData.getValue());
                            }
                        }
                    }
                }
            }
        }
        List<AgencyCustomer> companyList = agencyService.queryAgencyCustomer(param);
        List<String> cycleList = agencyService.queryConsumeCycle();
        List<CompanyApi> typeList = agencyService.queryConsumeApiType();
        model.addAttribute("cycleList",cycleList);
        model.addAttribute("typeList",typeList);
        model.addAttribute("companyList",companyList);
        return "/agency/rebate_detail";
    }

    //修改扣费量
    @RequestMapping("/rebate/detail/update-amount")
    @ResponseBody
    public String rebateUpdateAmount(Integer id,Integer amount){
        Map<String,Object> resu = new HashMap<>();
        if (agencyService.updateRebateBillAmount(id,amount)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    //修改单价
    @RequestMapping("/rebate/detail/update-cost")
    @ResponseBody
    public String rebateUpdateCost(Integer id,Double cost){
        Map<String,Object> resu = new HashMap<>();
        if (agencyService.updateRebateBillCost(id,cost)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    //修改售价
    @RequestMapping("/rebate/detail/update-price")
    @ResponseBody
    public String rebateUpdatePrice(Integer id,Double price){
        Map<String,Object> resu = new HashMap<>();
        if (agencyService.updateRebateBillPrice(id,price)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    //修改业务回扣起始价
    @RequestMapping("/rebate/detail/update-rebate-begin-price")
    @ResponseBody
    public String rebateUpdateBeginPrice(Integer id,Double price){
        Map<String,Object> resu = new HashMap<>();
        if (agencyService.updateRebateBillBeginPrice(id,price)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    //修改业务回扣结算价
    @RequestMapping("/rebate/detail/update-rebate-end-price")
    @ResponseBody
    public String rebateUpdateEndPrice(Integer id,Double price){
        Map<String,Object> resu = new HashMap<>();
        if (agencyService.updateRebateBillEndPrice(id,price)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    //删除记录
    @RequestMapping("/rebate/detail/delete")
    @ResponseBody
    public String rebateDetailDelete(HttpServletRequest request){
        String [] id = request.getParameterValues("id[]");
        Map<String,Object> resu = new HashMap<>();
        if (agencyService.deleteRebateDetail(id)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    //修改缓存售价
    @RequestMapping("/rebate/detail/update-cache-price")
    @ResponseBody
    public String cacheUpdatePrice(Integer id,Double price){
        Map<String,Object> resu = new HashMap<>();
        if (agencyService.updateCachePrice(id,price)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    //修改缓存扣费量
    @RequestMapping("/rebate/detail/update-cache-count")
    @ResponseBody
    public String cacheUpdateCount(Integer id,Integer count){
        Map<String,Object> resu = new HashMap<>();
        if (agencyService.updateCacheCount(id,count)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    //删除缓存记录
    @RequestMapping("/rebate/detail/delete-cache")
    @ResponseBody
    public String rebateCacheDelete(HttpServletRequest request){
        String [] id = request.getParameterValues("id[]");
        Map<String,Object> resu = new HashMap<>();
        if (agencyService.deleteCacheDetail(id)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }


    //删除消费对方记录
    @RequestMapping("/rebate/detail/delete-opposite")
    @ResponseBody
    public String rebateOppositeDelete(HttpServletRequest request){
        String [] id = request.getParameterValues("id[]");
        Map<String,Object> resu = new HashMap<>();
        if (agencyService.deleteRebateDetail(id)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

}
