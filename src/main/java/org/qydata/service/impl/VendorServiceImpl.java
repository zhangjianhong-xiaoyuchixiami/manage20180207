package org.qydata.service.impl;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.entity.ApiVendorBalance;
import org.qydata.entity.ApiVendorBalanceLog;
import org.qydata.entity.Partner;
import org.qydata.entity.VendorExt;
import org.qydata.mapper.ApiFinanceMapper;
import org.qydata.mapper.VendorMapper;
import org.qydata.service.VendorService;
import org.qydata.tools.date.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jonhn on 2017/8/7.
 */
@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorMapper vendorMapper;
    @Autowired
    private ApiFinanceMapper apiFinanceMapper;

    @Override
    public List<VendorExt> queryAllVendor(Map<String, Object> map) {

        Integer [] vid = null;
        Integer [] pid = null;
        Integer preId = null;
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> me = it.next();
            if (me.getKey().equals("vid")) {
                vid = (Integer[]) me.getValue();
            }
            if (me.getKey().equals("pid")) {
                pid = (Integer[]) me.getValue();
            }
            if (me.getKey().equals("preId")) {
                preId = (int) me.getValue();
            }
        }
        Map<String,Object> param = new HashMap<>();
        if (vid != null){
            List<Integer> vidList = new ArrayList<>();
            for (int i = 0; i < vid.length ; i++) {
                vidList.add(vid[i]);
            }
            param.put("vidList",vidList);
        }
        if (pid != null){
            List<Integer> pidList = new ArrayList<>();
            for (int i = 0; i < pid.length ; i++) {
                if (!(pid[i] == -100 || "-100".equals(pid[i]))){
                    pidList.add(pid[i]);
                }else {
                    param.put("nullPid",-100);
                }
            }
            param.put("pidList",pidList);
        }
        if (preId != null){
            List<Integer> preIdList = new ArrayList<>();
            preIdList.add(preId);
            param.put("preIdList",preIdList);
        }
        param.put("currDayTime", CalendarUtil.formatCurrTime());
        List<VendorExt> vendorExtList = vendorMapper.queryAllVendor(param);
        List<VendorExt> consumeExtList = vendorMapper.queryVendorConsume(param);
        List<VendorExt> currDayList = vendorMapper.queryVendorConsumeCurrDay(param);
        if (vendorExtList != null && vendorExtList.size() > 0){
            for (int i = 0; i < vendorExtList.size() ; i++) {
                VendorExt vendorExt = vendorExtList.get(i);
                if (vendorExt != null){
                    if (vendorExt.getIsPrepay() != null && vendorExt.getIsPrepay() == 1){
                        vendorExt.setIsPrepayName("是");
                    }else {
                        vendorExt.setIsPrepayName("否");
                    }
                    if (consumeExtList != null && consumeExtList.size() > 0){
                        for (int j = 0; j < consumeExtList.size(); j++) {
                            VendorExt consume = consumeExtList.get(j);
                            if (consume != null){
                                if (vendorExt.getVendorId() == consume.getVendorId()){
                                    vendorExt.setTotleCost(consume.getTotleCost()/100.0);
                                }
                            }
                        }
                    }
                    if (currDayList != null && currDayList.size() > 0){
                        for (int j = 0; j < currDayList.size(); j++) {
                            VendorExt currDay = currDayList.get(j);
                            if (currDay != null){
                                if (vendorExt.getVendorId() == currDay.getVendorId()){
                                    vendorExt.setCurrDayCost(currDay.getCurrDayCost()/100.0);
                                }
                            }
                        }
                    }

                    if (vendorExt.getBalance() != null){
                        vendorExt.setBalance(vendorExt.getBalance()/100.0);
                    }

                    if (vendorExt.getTotleCost() != null){
                        if (vendorExt.getCurrDayCost() != null){
                            vendorExt.setTotleCost(vendorExt.getTotleCost() + vendorExt.getCurrDayCost());
                        }else {
                            vendorExt.setTotleCost(vendorExt.getTotleCost());
                        }
                    }else {
                        if (vendorExt.getCurrDayCost() != null){
                            vendorExt.setTotleCost(vendorExt.getCurrDayCost());
                        }
                    }

                    if (vendorExt.getTotleCost() != null){
                        if (vendorExt.getBalance() != null){
                            vendorExt.setRemaining(vendorExt.getBalance() - vendorExt.getTotleCost());
                        }else {
                            if(vendorExt.getTotleCost() != 0){
                                vendorExt.setRemaining(-vendorExt.getTotleCost());
                            }
                        }
                    }else {
                        if (vendorExt.getBalance() != null){
                            vendorExt.setRemaining(vendorExt.getBalance());
                        }
                    }
                }
            }
        }
        return vendorExtList;
    }

    @Override
    public List<Partner> queryAllPartner() {
        return vendorMapper.queryAllPartner();
    }

    @Override
    public boolean updateVendorPrepay(Integer vid, Integer preId) {
        VendorExt vendorExt =  vendorMapper.queryVendorPrepay(vid);
        if (vendorExt != null){
            return vendorMapper.updateVendorPrepay(vid,preId);
        }else {
            VendorExt param = new VendorExt();
            param.setVendorId(vid);
            param.setIsPrepay(preId);
            return vendorMapper.insertVendorPrepay(param);
        }

    }

    @SystemServiceLog(description = "供应商充值")
    @Override
    public boolean updateVendorBalance(Integer vid, Double amount, String date, String remark) throws Exception {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            ApiVendorBalanceLog log = new ApiVendorBalanceLog();
            log.setVendorId(vid);
            log.setAmount(amount*100);
            log.setRemark(remark);
            if (date == "" || date == null){
                log.setCreateTime(new Date());
            }else {
                log.setCreateTime(sdf.parse(date));
            }
            //插入充值日志
            apiFinanceMapper.insertApiVendorBalanceLog(log);
            ApiVendorBalance apiVendorBalance = apiFinanceMapper.queryApiVendorBalance(vid);
            if (apiVendorBalance == null){
                ApiVendorBalance param = new ApiVendorBalance();
                param.setVendorId(vid);
                apiFinanceMapper.insertApiVendorBalance(param);
                apiFinanceMapper.updateApiVendorBalance(vid, (long) (amount*100));
            }else {
                apiFinanceMapper.updateApiVendorBalance(vid, (long) ((amount*100) + apiVendorBalance.getBalance()));
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @SystemServiceLog(description = "供应商扣费")
    @Override
    public boolean updateVendorBalanceFee(Integer vid, Double amount, String date, String remark) throws Exception {

        return false;
    }

    @Override
    public List<ApiVendorBalanceLog> queryVendorBalanceLog(Map<String, Object> map) {
        List<ApiVendorBalanceLog> logList = vendorMapper.queryVendorBalanceLog(map);
        if (logList != null && logList.size() > 0){
            for (int i = 0; i < logList.size() ; i++) {
                ApiVendorBalanceLog log = logList.get(i);
                if (log != null){
                    if (log.getAmount() != null){
                        log.setAmount(log.getAmount()/100.0);
                    }
                    if (log.getCreateTime() != null){
                        log.setChargeTime(new SimpleDateFormat("yyyy-MM-dd").format(log.getCreateTime()));
                    }
                    if (log.getRemark() == null || "".equals(log.getRemark())){
                        log.setRemark("无");
                    }
                }
            }
        }
        return logList;
    }


}
