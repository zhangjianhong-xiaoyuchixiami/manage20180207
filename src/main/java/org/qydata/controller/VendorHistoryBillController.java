package org.qydata.controller;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import org.qydata.service.VendorHistoryBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/30.
 */
@Controller
@RequestMapping("/finance")
public class VendorHistoryBillController {

    @Autowired
    private VendorHistoryBillService billService;

    /**
     * 查询供应商历史消费
     * @param status
     * @param vid
     * @param pid
     * @param beg_month
     * @param model
     * @return
     */
    @RequestMapping("/vendor-history-bill")
    public String vendorHistoryBill(Integer [] status, Integer [] vid, Integer pid, String [] beg_month, Model model){
        Map<String,Object> param = new HashMap<>();
        if (status == null){
            status= new Integer[]{0, -1};
            param.put("status",status);
            model.addAttribute("status",status);
        }else {
            param.put("status",status);
            model.addAttribute("status",status);
        }
        if (vid != null && vid.length > 0){
            param.put("vid",vid);
            model.addAttribute("vid",vid);
        }
        if (pid != null){
            param.put("pid",pid);
            model.addAttribute("pid",pid);
        }
        if (beg_month != null && beg_month.length > 0){
            param.put("beg_month",beg_month);
            model.addAttribute("beg_month",beg_month);
        }
        Map<String,Object> resu = billService.queryVendorHistoryBill(param);
        if (resu != null){
            for (Map.Entry<String, Object> me : resu.entrySet()) {
                if (me.getKey().equals("chargeTot")) {
                    model.addAttribute("chargeTot",me.getValue());
                }
                if (me.getKey().equals("consumeTot")) {
                    model.addAttribute("consumeTot",me.getValue());
                }
                if (me.getKey().equals("billList")) {
                    model.addAttribute("billList",me.getValue());
                }
            }
        }
        model.addAttribute("vendorList",billService.queryAllVendor());
        model.addAttribute("partnerList",billService.queryAllPartner());
        model.addAttribute("conTimeList",billService.queryAllConsumeTime());
        return "/finance/vendor-history-bill";
    }

    /**
     * 查询供应商历史消费明细
     * @return
     */
    @RequestMapping("/vendor-history-bill/detail")
    public String vendorHistoryBillDetail(Integer vid,String name, String [] cyc, String [] aid,Integer isLock,Model model){

        Map<String,Object> param = new HashMap<>();
        if (vid != null){
            param.put("vid",vid);
            model.addAttribute("vid",vid);
        }
        if (cyc != null && cyc.length > 0){
            param.put("cyc",cyc);
            model.addAttribute("cyc",cyc);
        }
        if (aid != null && aid.length > 0){
            param.put("aid",aid);
            model.addAttribute("aid",aid);
        }
        if (isLock != null){
            param.put("isLock",isLock);
            model.addAttribute("isLock",isLock);
        }
        model.addAttribute("name",name);
        Map<String,Object> resu = billService.queryVendorHistoryBillDetail(param);
        if (resu != null){
            for (Map.Entry<String,Object> me : resu.entrySet()) {
                if (me.getKey().equals("conTot")) {
                    model.addAttribute("conTot",me.getValue());
                }
                if (me.getKey().equals("billDetailList")) {
                    model.addAttribute("billDetailList",me.getValue());
                }
            }
        }
        model.addAttribute("conTimeList",billService.queryAllConsumeTime());
        model.addAttribute("apiList",billService.queryApiByVendorId(vid));
        return "/finance/vendor-history-bill-month-detail";
    }

    /**
     * 修改单价
     * @param id
     * @param oldCost
     * @param data
     * @return
     */
    @RequestMapping("/vendor-history-bill/detail/update-cost")
    @ResponseBody
    public String vendorHistoryBillDetailUpdateCost(Integer id,Double oldCost,String data){
        JSONArray array = JSONArray.fromObject(data);
        Double newCost = array.getDouble(0);
        String content = array.getString(1);
        Map<String,Object> resu = new HashMap<>();
        if (billService.updateVendorHistoryBillCost(id,oldCost,newCost,content)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 修改数量
     * @param id
     * @param oldAmount
     * @param data
     * @return
     */
    @RequestMapping("/vendor-history-bill/detail/update-amount")
    @ResponseBody
    public String vendorHistoryBillDetailUpdateAmount(Integer id,Integer oldAmount,String data){
        JSONArray array = JSONArray.fromObject(data);
        Integer newAmount = array.getInt(0);
        String content = array.getString(1);
        Map<String,Object> resu = new HashMap<>();
        if (billService.updateVendorHistoryBillAmount(id,oldAmount,newAmount,content)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 历史账单锁定
     * @param request
     * @return
     */
    @RequestMapping("/vendor-history-bill/detail/lock")
    @ResponseBody
    public String vendorHistoryBillDetailLock(HttpServletRequest request){
        String [] id = request.getParameterValues("id[]");
        Map<String,Object> resu = new HashMap<>();
        if (billService.updateVendorHistoryBillIsLock(id,1)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 历史账单未锁定
     * @param request
     * @return
     */
    @RequestMapping("/vendor-history-bill/detail/unLock")
    @ResponseBody
    public String vendorHistoryBillDetailUnLock(HttpServletRequest request){
        String [] id = request.getParameterValues("id[]");
        Map<String,Object> resu = new HashMap<>();
        if (billService.updateVendorHistoryBillIsLock(id,0)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 删除历史记录
     * @param request
     * @return
     */
    @RequestMapping("/vendor-history-bill/detail/delete")
    @ResponseBody
    public String vendorHistoryBillDetailDelete(HttpServletRequest request){
        String [] id = request.getParameterValues("id[]");
        Map<String,Object> resu = new HashMap<>();
        if (billService.deleteVendorHistoryBill(id)){
            resu.put("success","success");
            return new Gson().toJson(resu);
        }
        resu.put("fail","fail");
        return new Gson().toJson(resu);
    }

    /**
     * 增加
     * @param vid
     * @param aid
     * @param cost
     * @param amount
     * @param yearMonth
     * @return
     */
    @RequestMapping("/vendor-history-bill/detail/add")
    @ResponseBody
    public String vendorHistoryBillDetailAdd(Integer vid,Integer aid,Double cost,Integer amount,String yearMonth){
        Map<String,Object> resu = new HashMap<>();
        if (billService.addVendorHistoryBill(vid, aid, cost, amount, yearMonth)){
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
    @RequestMapping("/vendor-history-bill/detail/check-lock")
    @ResponseBody
    public String queryVendorHistoryBillLock(Integer id){
       Integer lock = billService.queryVendorHistoryBillLockById(id);
        Map<String,Object> resu = new HashMap<>();
        if (lock == 1 && "1".equals(lock)){
            resu.put("lock","lock");
            return new Gson().toJson(resu);
        }
        resu.put("lock","unlock");
        return new Gson().toJson(resu);
    }

    /**
     * 根据Id查看锁定状态
     * @param request
     * @return
     */
    @RequestMapping("/vendor-history-bill/detail/batch-check-lock")
    @ResponseBody
    public String queryVendorHistoryBillBatchLock(HttpServletRequest request){
        String [] id = request.getParameterValues("id[]");
        List<Integer> list = new ArrayList<>();
        if (id != null){
            for (String s : id) {
                System.out.println(s);
                Integer lock = billService.queryVendorHistoryBillLockById(Integer.parseInt(s));
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

}
