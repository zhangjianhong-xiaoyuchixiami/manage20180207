package org.qydata.controller;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.qydata.dst.CustomerHistoryBill;
import org.qydata.dst.CustomerHistoryBillDetail;
import org.qydata.entity.Company;
import org.qydata.entity.CompanyApi;
import org.qydata.entity.CustomerHistoryBillUpdateLog;
import org.qydata.entity.Partner;
import org.qydata.service.CustomerHistoryBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by jonhn on 2017/7/28.
 */
@Controller
@RequestMapping("/finance")
public class CustomerHistoryBillController {

    @Autowired
    private CustomerHistoryBillService billService;

    /**
     * 查询客户历史消费账单
     */
    @RequestMapping("/customer-history-bill")
    public String customerHistoryBill(Integer [] status, Integer [] cid, Integer pid,
                                      Double LCredit, Double HCredit, Double LUserAble,
                                      Double HUserAble, Double LConsu, Double HConsu,
                                      String [] beg_month, String end_month, Model model){
        Map<String,Object> param = new HashMap<>();
        if (status == null){
            status= new Integer[]{0, -1};
            param.put("status",status);
            model.addAttribute("status",status);
        }else {
            param.put("status",status);
            model.addAttribute("status",status);
        }
        if (cid != null && cid.length > 0){
            param.put("cid",cid);
            model.addAttribute("cid",cid);
        }
        if (pid != null){
            param.put("pid",pid);
            model.addAttribute("pid",pid);
        }
        if (LCredit != null){
            param.put("LCredit",LCredit);
            model.addAttribute("LCredit",LCredit);
        }
        if (HCredit != null){
            param.put("HCredit",HCredit);
            model.addAttribute("HCredit",HCredit);
        }
        if (LUserAble != null){
            param.put("LUserAble",LUserAble);
            model.addAttribute("LUserAble",LUserAble);
        }
        if (HUserAble != null){
            param.put("HUserAble",HUserAble);
            model.addAttribute("HUserAble",HUserAble);
        }
        if (LConsu != null){
            param.put("LConsu",LConsu);
            model.addAttribute("LConsu",LConsu);
        }
        if (HConsu != null){
            param.put("HConsu",HConsu);
            model.addAttribute("HConsu",HConsu);
        }
        if (beg_month != null && beg_month.length > 0){
            param.put("beg_month",beg_month);
            model.addAttribute("beg_month",beg_month);
        }
        if (end_month != null && end_month != ""){
            param.put("end_month",end_month);
            model.addAttribute("end_month",end_month);
        }
        Map<String,Object> resu =  billService.queryCustomerHistoryBill(param);
        List<CustomerHistoryBill> billList = null;
        Double chargeTot = 0.0;
        Double consumeTot = 0.0;
        Set<Map.Entry<String, Object>> set = resu.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> me = it.next();
            if (me.getKey().equals("billList")) {
                billList = (List<CustomerHistoryBill>) me.getValue();
                model.addAttribute("billList",billList);
            }
            if (me.getKey().equals("chargeTot")) {
                chargeTot = (Double) me.getValue();
                model.addAttribute("chargeTot",chargeTot);
            }
            if (me.getKey().equals("consumeTot")) {
                consumeTot = (Double) me.getValue();
                model.addAttribute("consumeTot",consumeTot);
            }
        }
        List<Company> companyList = billService.queryAllCompany();
        List<Partner> partnerList = billService.queryAllPartner();
        List<String> conTimeList = billService.queryAllConsumeTime();
        model.addAttribute("companyList",companyList);
        model.addAttribute("partnerList",partnerList);
        model.addAttribute("conTimeList",conTimeList);
        return "/finance/customer-history-bill";
    }

    /**
     * 客户历史账单月明细
     */
    @RequestMapping("/customer-history-bill/detail")
    public String customerHistoryBillDetail(Integer cid,String name, String [] cyc, String [] tid,Model model){
        Map<String,Object> param = new HashMap<>();
        if (cid != null){
            param.put("cid",cid);
            model.addAttribute("cid",cid);
        }
        if (cyc != null && cyc.length > 0){
            param.put("cyc",cyc);
            model.addAttribute("cyc",cyc);
        }
        if (tid != null && tid.length > 0){
            param.put("tid",tid);
            model.addAttribute("tid",tid);
        }
        model.addAttribute("name",name);
        Map<String,Object> resu = billService.queryCustomerHistoryBillDetail(param);
        List<CustomerHistoryBillDetail> billDetailList = null;
        Double conTot = 0.0;
        Set<Map.Entry<String, Object>> set = resu.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> me = it.next();
            if (me.getKey().equals("billDetailList")) {
                billDetailList = (List<CustomerHistoryBillDetail>) me.getValue();
                model.addAttribute("billDetailList",billDetailList);
            }
            if (me.getKey().equals("conTot")) {
                conTot = (Double) me.getValue();
                model.addAttribute("conTot",conTot);
            }
        }
        List<String> conTimeList = billService.queryAllConsumeTime();
        List<CompanyApi> companyApiList = billService.queryCompanyApiByCompanyId(cid);
        model.addAttribute("conTimeList",conTimeList);
        model.addAttribute("companyApiList",companyApiList);
        return "/finance/customer-history-bill-month-detail";
    }

    /**
     * 修改单价
     * @return
     */
    @RequestMapping("/customer-history-bill/detail/update-cost")
    @ResponseBody
    public String customerHistoryBillDetailUpdateCost(Integer id,Double oldCost,String data){
        JSONArray array = JSONArray.fromObject(data);
        Double newCost = array.getDouble(0);
        String content = array.getString(1);
        Map<String,Object> resu = new HashMap<>();
        if (billService.updateCustomerHistoryBillCost(id,oldCost,newCost,content)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 修改扣费量
     * @return
     */
    @RequestMapping("/customer-history-bill/detail/update-amount")
    @ResponseBody
    public String customerHistoryBillDetailUpdateAmount(Integer id,Integer oldAmount,String data){
        JSONArray array = JSONArray.fromObject(data);
        Integer newAmount = array.getInt(0);
        String content = array.getString(1);
        Map<String,Object> resu = new HashMap<>();
        if (billService.updateCustomerHistoryBillAmount(id,oldAmount,newAmount,content)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 增加
     * @param cid
     * @param tid
     * @param cost
     * @param amount
     * @param yearMonth
     * @return
     */
    @RequestMapping("/customer-history-bill/detail/add")
    @ResponseBody
    public String customerHistoryBillDetailAdd(Integer cid,String tid,Double cost,Integer amount,String yearMonth){
        Map<String,Object> resu = new HashMap<>();
        if (billService.addCustomerHistoryBill(cid, tid, cost, amount, yearMonth)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @RequestMapping("/customer-history-bill/detail/delete")
    @ResponseBody
    public String customerHistoryBillDetailDelete(HttpServletRequest request){
        String [] id = request.getParameterValues("id[]");
        Map<String,Object> resu = new HashMap<>();
        if (billService.deleteCustomerHistoryBill(id)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 客户历史账单消费走势
     * @param cid
     * @param name
     * @param model
     * @return
     */
    @RequestMapping("/customer-history-bill/trend")
    public String customerHistoryBillTrend(Integer cid, String name, Model model){
        model.addAttribute("cid",cid);
        model.addAttribute("name",name);
        return "/finance/customer-history-bill_trend";
    }

    /**
     * 客户历史账单消费走势加载数据
     * @param cid
     * @return
     */
    @RequestMapping("/customer-history-bill/trend/data")
    @ResponseBody
    public String customerHistoryBillTrendData(Integer cid){
        Map<String,Object> param = new HashMap<>();
        param.put("cid",cid);
        Map<String,Object> resu = billService.queryCustomerHistoryBillTrendData(param);
        JSONArray jsonArrayX = null;
        JSONArray jsonArrayS = null;
        JSONArray jsonArrayX_2 = null;
        JSONArray jsonArrayS_2 = null;
        Set<Map.Entry<String, Object>> set = resu.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> me = it.next();
            if (me.getKey().equals("jsonArrayX")) {
                jsonArrayX = (JSONArray) me.getValue();
            }
            if (me.getKey().equals("jsonArrayS")) {
                jsonArrayS = (JSONArray) me.getValue();
            }
            if (me.getKey().equals("jsonArrayX_2")) {
                jsonArrayX_2 = (JSONArray) me.getValue();
            }
            if (me.getKey().equals("jsonArrayS_2")) {
                jsonArrayS_2 = (JSONArray) me.getValue();
            }
        }
        JSONObject getObj = new JSONObject();
        getObj.put("xList", jsonArrayX);
        getObj.put("seriesData", jsonArrayS);
        getObj.put("xList_2", jsonArrayX_2);
        getObj.put("seriesData_2", jsonArrayS_2);
        return getObj.toString();
    }

    /**
     * 历史账单锁定
     * @param request
     * @return
     */
    @RequestMapping("/customer-history-bill/detail/lock")
    @ResponseBody
    public String customerHistoryBillDetailLock(HttpServletRequest request){
        String [] id = request.getParameterValues("id[]");
        Map<String,Object> resu = new HashMap<>();
        if (billService.updateCustomerHistoryBillIsLock(id,1)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 历史账单解锁定
     * @param request
     * @return
     */
    @RequestMapping("/customer-history-bill/detail/unLock")
    @ResponseBody
    public String customerHistoryBillDetailUnLock(HttpServletRequest request){
        String [] id = request.getParameterValues("id[]");
        Map<String,Object> resu = new HashMap<>();
        if (billService.updateCustomerHistoryBillIsLock(id,0)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 根据Id查看锁定状态
     * @param id
     * @return
     */
    @RequestMapping("/customer-history-bill/detail/check-lock")
    @ResponseBody
    public String queryCustomerHistoryBillLock(Integer id){
        Integer lock = billService.queryCustomerHistoryBillLockById(id);
        Map<String,Object> resu = new HashMap<>();
        if (lock == 1 || "1".equals(lock)){
            resu.put("lock","lock");
            return new Gson().toJson(resu);
        }
        resu.put("lock","unlock");
        return new Gson().toJson(resu);
    }

    /**
     * 根据Id批量查看锁定状态
     * @param request
     * @return
     */
    @RequestMapping("/customer-history-bill/detail/batch-check-lock")
    @ResponseBody
    public String queryCustomerHistoryBillBatchLock(HttpServletRequest request){
        String [] id = request.getParameterValues("id[]");
        List<Integer> list = new ArrayList<>();
        if (id != null){
            for (String s : id) {
                System.out.println(s);
                Integer lock = billService.queryCustomerHistoryBillLockById(Integer.parseInt(s));
                list.add(lock);
            }
        }
        Map<String,Object> resu = new HashMap<>();
        if (list.contains(1) || list.contains("1")){
            resu.put("lock","lock");
            return new Gson().toJson(resu);
        }
        resu.put("lock","unlock");
        return new Gson().toJson(resu);
    }


    /**
     * 查看修改日志
     * @param id
     * @param cName
     * @param tName
     * @param cyc
     * @param typeId
     * @param model
     * @return
     */
    @RequestMapping("/customer-history-bill/detail/log")
    public String customerHistoryBillDetailUpdateLog(Integer id,String cName,String tName,String cyc,Integer typeId,Model model){
        Map<String,Object> param = new HashMap<>();
        if (id != null){
            param.put("id",id);
            model.addAttribute("id",id);
        }
        if (typeId != null){
            param.put("typeId",typeId);
            model.addAttribute("typeId",typeId);
        }
        List<CustomerHistoryBillUpdateLog> logList = billService.queryCustomerHistoryBillDetailUpdateLog(param) ;
        model.addAttribute("cName",cName);
        model.addAttribute("tName",tName);
        model.addAttribute("cyc",cyc);
        model.addAttribute("logList",logList);
        return "/finance/customer-history-bill-month-detail-update-log";
    }

}
