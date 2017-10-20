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

    //代理人返佣
    @RequestMapping("/rebate")
    public String rebate(Integer rate,Integer cache,Integer agency,Integer [] cid,String [] cyc,Model model){
        Map<String,Object> param = new HashMap<>();
        if (rate != null){
            param.put("rate",rate);
            model.addAttribute("rate",rate);
        }
        if (cache != null){
            param.put("cache",cache);
            model.addAttribute("cache",cache);
        }
        if (agency != null){
            param.put("agency",agency);
            model.addAttribute("agencyId",agency);
            List<AgencyCustomer> companyList = agencyService.queryAgencyCustomer(param);
            model.addAttribute("companyList",companyList);
        }
        if (cid != null){
            param.put("cid",cid);
            model.addAttribute("companyArray",cid);
        }
        if (cyc != null){
            param.put("cyc",cyc);
            model.addAttribute("cycleArray",cyc);
        }
        Map<String,Object> resu = agencyService.queryAgencyBill(param);
        if (resu != null){
            for (Map.Entry<String,Object> me : resu.entrySet()) {
                if (me.getKey().equals("billList")){
                    model.addAttribute("billList",me.getValue());
                }
                if (me.getKey().equals("netProfitTot")){
                    model.addAttribute("netProfitTot",me.getValue());
                }
            }
        }
        List<RebateAgency> agencyList = agencyService.queryRebateAgency();
        List<String> cycleList = agencyService.queryConsumeCycle();
        model.addAttribute("agencyList",agencyList);
        model.addAttribute("cycleList",cycleList);
        return "/agency/rebate";
    }

    //代理的客户
    @RequestMapping("/rebate/customer")
    @ResponseBody
    public String rebateCustomer(Integer agency){
        Map<String,Object> param = new HashMap<>();
        param.put("agency",agency);
        List<AgencyCustomer> companyList = agencyService.queryAgencyCustomer(param);
        return new Gson().toJson(companyList);
    }

    //代理人返佣明细
    @RequestMapping("/rebate/detail")
    public String rebateDetail(Integer agencyId, String [] cyc, String [] tid, String cache,Model model){
        Map<String,Object> param = new HashMap<>();
        if (agencyId != null){
            param.put("agencyId",agencyId);
            model.addAttribute("agencyId",agencyId);
        }
        if (cyc != null){
            param.put("cyc",cyc);
            model.addAttribute("cycleArray",cyc);
        }
        if (tid != null){
            param.put("tid",tid);
            model.addAttribute("typeArray",tid);
        }
        if (cache != null){
            param.put("cache",cache);
            model.addAttribute("cache",cache);
        }
        Map<String,Object> resu = agencyService.queryAgencyBillDetail(param);
        if (resu != null){
            for (Map.Entry<String,Object> me : resu.entrySet()) {
                if (me.getKey().equals("detailList")){
                    model.addAttribute("detailList",me.getValue());
                }
            }
        }
        List<String> cycleList = agencyService.queryConsumeCycle();
        List<CompanyApi> typeList = agencyService.queryConsumeApiType();
        model.addAttribute("cycleList",cycleList);
        model.addAttribute("typeList",typeList);
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

}
