package org.qydata.service.impl;

import org.qydata.dst.ApiWeb;
import org.qydata.dst.VendorHistoryBill;
import org.qydata.dst.VendorHistoryBillDetail;
import org.qydata.entity.ApiVendor;
import org.qydata.entity.Partner;
import org.qydata.entity.VendorHistoryBillUpdateLog;
import org.qydata.mapper.VendorHistoryBillMapper;
import org.qydata.service.VendorHistoryBillService;
import org.qydata.tools.CalendarTools;
import org.qydata.tools.date.CalendarUtil;
import org.qydata.tools.finance.ApiTypeMobileOperatorNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                if (me.getKey().equals("vid")) {
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

        param.put("currMonthTime",CalendarTools.getCurrentMonthFirstDay() + " " + "00:00:00");
        param.put("currDayTime",CalendarUtil.formatCurrTime());
        List<VendorHistoryBill> billList = billMapper.queryVendorHistoryBill(param);
        List<VendorHistoryBill> staticBillList = billMapper.queryVendorStaticConsumeAmount();
        List<VendorHistoryBill> currMonthBillList = billMapper.queryVendorCurrMonthRealTimeConsume(param);
        List<VendorHistoryBill> currDayBillList = billMapper.queryVendorCurrDayRealTimeConsume(param);
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

            if (staticBillList != null){
                for (VendorHistoryBill staticConsume : staticBillList) {
                    if (bill.getVendorId() == staticConsume.getVendorId() || bill.getVendorId().equals(staticConsume.getVendorId())){
                        if (staticConsume.getStaticConsumeAmount() != null){
                            bill.setStaticConsumeAmount(staticConsume.getStaticConsumeAmount()/100.0);
                        }
                    }
                }
            }

            if (bill.getChargeAmount() != null && bill.getStaticConsumeAmount() != null){
                bill.setBalance(bill.getChargeAmount() - bill.getStaticConsumeAmount());
            }
            if (bill.getChargeAmount() != null && bill.getStaticConsumeAmount() == null){
                bill.setBalance(bill.getChargeAmount());
            }
            if (bill.getChargeAmount() == null && bill.getStaticConsumeAmount() != null){
                bill.setBalance(-(bill.getStaticConsumeAmount()));
            }

            if (currMonthBillList != null){
                for (VendorHistoryBill currMonthBill : currMonthBillList) {
                    if (bill.getVendorId() == currMonthBill.getVendorId() || bill.getVendorId().equals(currMonthBill.getVendorId())){
                        if (bill.getBalance() != null && currMonthBill.getCurrMonthRealTimeConsume() != null){
                            bill.setBalance(bill.getBalance() - (currMonthBill.getCurrMonthRealTimeConsume()/100.0));
                        }
                        if (bill.getBalance() != null && currMonthBill.getCurrMonthRealTimeConsume() == null){
                            bill.setBalance(bill.getBalance());
                        }
                        if (bill.getBalance() == null && currMonthBill.getCurrMonthRealTimeConsume() != null){
                            bill.setBalance(-(currMonthBill.getCurrMonthRealTimeConsume()/100.0));
                        }
                    }
                }
            }
            if (currDayBillList != null){
                for (VendorHistoryBill currDayBill : currDayBillList) {
                    if (bill.getVendorId() == currDayBill.getVendorId() || bill.getVendorId().equals(currDayBill.getVendorId())){
                        if (bill.getBalance() != null && currDayBill.getCurrDayRealTimeConsume() != null){
                            bill.setBalance(bill.getBalance() - (currDayBill.getCurrDayRealTimeConsume()/100.0));
                        }
                        if (bill.getBalance() != null && currDayBill.getCurrDayRealTimeConsume() == null){
                            bill.setBalance(bill.getBalance());
                        }
                        if (bill.getBalance() == null && currDayBill.getCurrDayRealTimeConsume() != null){
                            bill.setBalance(-(currDayBill.getCurrDayRealTimeConsume()/100.0));
                        }
                    }
                }
            }
        }
        Map<String,Object> resu = new HashMap<>();
        resu.put("chargeTot",chargeTot);
        resu.put("consumeTot",consumeTot);
        resu.put("billList",billList);
        return resu;
    }

    @Override
    public List<ApiVendor> queryAllVendor() {
        return billMapper.queryAllVendor();
    }

    @Override
    public List<Partner> queryAllPartner() {
        return billMapper.queryAllPartner();
    }

    @Override
    public List<String> queryAllConsumeTime() {
        return billMapper.queryAllConsumeTime();
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
                if (me.getKey().equals("aid")) {
                    param.put("aidList",me.getValue());
                }
                if (me.getKey().equals("isLock")) {
                    param.put("isLock",me.getValue());
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
            if (detail.getIsLock() != null && detail.getIsLock() == 1 || "1".equals(detail.getIsLock())){
                detail.setIsLockName("已锁定");
            }else {
                detail.setIsLockName("未锁定");
            }
        }
        Map<String,Object> resu = new HashMap<>();
        resu.put("billDetailList",billDetailList);
        resu.put("conTot",conTot);
        return resu;
    }

    @Override
    public List<ApiWeb> queryApiByVendorId(Integer vid) {
        List<ApiWeb> apiWebList = billMapper.queryApiByVendorId(vid);
        if (apiWebList == null){
            return null;
        }
        for (ApiWeb apiWeb : apiWebList) {
            if (apiWeb.getApiTypeName() != null){
                String type_stid_name = ApiTypeMobileOperatorNameUtils.apiTypeMobileOperatorName(apiWeb.getApiTypeName(),apiWeb.getMobileList());
                apiWeb.setApiTypeName(type_stid_name);
            }
        }
        return apiWebList;
    }

    @Override
    public boolean updateVendorHistoryBillCost(Integer id, Double oldCost, Double newCost, String content) {
        boolean flag_1 = billMapper.updateVendorHistoryBillCost(id, (int) (newCost*100));
        VendorHistoryBillUpdateLog log = new VendorHistoryBillUpdateLog();
        log.setVendorHistoryBillId(id);
        log.setBeforData((int) (oldCost*100));
        log.setAfterData((int) (newCost*100));
        log.setContent(content);
        log.setType(1);
        boolean flag_2 = billMapper.insertVendorHistoryBillUpdateLog(log);
        if (flag_1 && flag_2){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateVendorHistoryBillAmount(Integer id, Integer oldAmount, Integer newAmount, String content) {
        boolean flag_1 = billMapper.updateVendorHistoryBillAmount(id, newAmount);
        VendorHistoryBillUpdateLog log = new VendorHistoryBillUpdateLog();
        log.setVendorHistoryBillId(id);
        log.setBeforData(oldAmount);
        log.setAfterData(newAmount);
        log.setContent(content);
        log.setType(2);
        boolean flag_2 = billMapper.insertVendorHistoryBillUpdateLog(log);
        if (flag_1 && flag_2){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateVendorHistoryBillIsLock(String[] id, Integer isLock) {
        Map<String,Object> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (String s : id) {
            list.add(Integer.parseInt(s));
        }
        map.put("isLock",isLock);
        map.put("list",list);
        return billMapper.updateVendorHistoryBillIsLock(map);
    }

    @Override
    public boolean addVendorHistoryBill(Integer vid, Integer aid, Double cost, Integer amount, String yearMonth) {
        VendorHistoryBillDetail bill = new VendorHistoryBillDetail();
        bill.setVendorId(vid);
        bill.setApiId(aid);
        bill.setCost(cost * 100);
        bill.setAmount(amount);
        String [] yearMonths = yearMonth.split("-");
        bill.setYear(Integer.parseInt(yearMonths[0]));
        bill.setMonth(Integer.parseInt(yearMonths[1]));
        bill.setYearMonth(yearMonth);
        bill.setIsLock(0);
        return billMapper.addVendorHistoryBill(bill);
    }

    @Override
    public boolean deleteVendorHistoryBill(String[] id) {
        List<Integer> list = new ArrayList<>();
        for (String s : id) {
            list.add(Integer.parseInt(s));
        }
        return billMapper.deleteVendorHistoryBill(list);
    }

    @Override
    public Integer queryVendorHistoryBillLockById(Integer id) {
        return billMapper.queryVendorHistoryBillLockById(id);
    }

    @Override
    public List<VendorHistoryBillUpdateLog> queryVendorHistoryBillDetailUpdateLog(Map<String,Object> map) {
        List<VendorHistoryBillUpdateLog> logList = billMapper.queryVendorHistoryBillDetailUpdateLog(map);
        if (logList == null){
            return null;
        }
        for (VendorHistoryBillUpdateLog log : logList) {
            if (log.getContent() == null || log.getContent() == "" || "".equals(log.getContent())){
                log.setContent("无");
            }
        }
        return logList;
    }
}
