package org.qydata.service.impl;

import org.qydata.entity.Partner;
import org.qydata.entity.VendorExt;
import org.qydata.mapper.VendorMapper;
import org.qydata.service.VendorService;
import org.qydata.tools.date.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jonhn on 2017/8/7.
 */
@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorMapper vendorMapper;

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
                preId = (Integer) me.getValue();
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
                pidList.add(pid[i]);
            }
            param.put("pidList",pidList);
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
                           vendorExt.setRemaining(vendorExt.getTotleCost());
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
}
