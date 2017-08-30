package org.qydata.service.impl;

import org.qydata.dst.VendorHistoryBill;
import org.qydata.dst.VendorHistoryBillDetail;
import org.qydata.mapper.VendorHistoryBillMapper;
import org.qydata.service.VendorHistoryBillService;
import org.qydata.tools.date.CalendarUtil;
import org.qydata.tools.finance.ApiTypeMobileOperatorNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/30.
 */
@Service
public class VendorHistoryBillServiceImpl implements VendorHistoryBillService {

    @Autowired
    private VendorHistoryBillMapper billMapper;

    @Override
    public Map<String, Object> queryVendorHistoryBill(Map<String, Object> map) {
        Map<String,Object> param = new HashMap<>();
        if (map != null){
            for (Map.Entry<String, Object> me : map.entrySet()) {
                if (me.getKey().equals("status")) {
                    param.put("statusList",me.getValue());
                }
                if (me.getKey().equals("cid")) {
                    param.put("vidList", me.getValue());
                }
                if (me.getKey().equals("pid")) {
                    if ((Integer)me.getValue() == -100 || "-100".equals(me.getValue())){
                        param.put("nullPid",me.getValue());
                    }else {
                        param.put("pid", me.getValue());
                    }
                }
                if (me.getKey().equals("beg_month")) {
                    param.put("begList",me.getValue());
                }
            }
        }
        param.put("currDayTime", CalendarUtil.formatCurrTime());
        List<VendorHistoryBill> billList = billMapper.queryVendorHistoryBill(param);
        Double chargeTot = 0.0;
        Double consumeTot = 0.0;
        if (billList == null){
            return null;
        }
        for (VendorHistoryBill bill : billList) {
            if (bill.getChargeAmount() != null){
                bill.setChargeAmount(bill.getChargeAmount()/100.0);
                chargeTot += bill.getChargeAmount();
            }
            if (bill.getConsumeAmount() != null){
                bill.setConsumeAmount(bill.getConsumeAmount()/100.0);
                consumeTot += bill.getConsumeAmount();
            }
        }
        Map<String,Object> resu = new HashMap<>();
        resu.put("chargeTot",chargeTot);
        resu.put("consumeTot",consumeTot);
        resu.put("billList",billList);
        return resu;
    }

    @Override
    public Map<String, Object> queryVendorHistoryBillDetail(Map<String, Object> map) {

        Map<String,Object> param = new HashMap<>();
        if (map != null){
            for (Map.Entry<String, Object> me : map.entrySet()) {
                if (me.getKey().equals("vid")) {
                    param.put("vid",me.getValue());
                }
                if (me.getKey().equals("cyc")) {
                    param.put("cycList", me.getValue());
                }
                if (me.getKey().equals("tid")) {
                    param.put("tidList",me.getValue());
                }
            }
        }
        List<VendorHistoryBillDetail> billDetailList = billMapper.queryVendorHistoryBillDetail(param);
        if (billDetailList == null){
            return null;
        }
        Double conTot = 0.0;
        for (VendorHistoryBillDetail detail : billDetailList) {
            if (detail.getApiTypeId() != null){
                String type_stid_name = ApiTypeMobileOperatorNameUtils.apiTypeMobileOperatorName(detail.getApiTypeName(),detail.getMobileList());
                detail.setApiTypeName(type_stid_name);
            }
            if (detail.getCost() != null){
                if (detail.getCost() != 0){
                    detail.setCost(detail.getCost()/100.0);
                }
            }
            if (detail.getCost() != null && detail.getAmount() != null){
                detail.setConsumeAmount(detail.getCost() * detail.getAmount());
            }
            if (detail.getConsumeAmount() != null){
                conTot += detail.getConsumeAmount();
            }
        }
        Map<String,Object> resu = new HashMap<>();
        resu.put("billDetailList",billDetailList);
        resu.put("conTot",conTot);
        return resu;
    }
}
