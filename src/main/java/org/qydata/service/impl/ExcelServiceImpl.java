package org.qydata.service.impl;

import org.qydata.dst.vendor.VendorBill;
import org.qydata.entity.ApiType;
import org.qydata.entity.Company;
import org.qydata.entity.Partner;
import org.qydata.entity.excel.ExportExcel;
import org.qydata.mapper.mapper1.ExcelMapper;
import org.qydata.mapper.mapper2.ExcelSelectMapper;
import org.qydata.service.ExcelService;
import org.qydata.tools.date.CalendarUtil;
import org.qydata.tools.finance.ApiTypeMobileOperatorNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jonhn on 2017/7/7.
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private ExcelMapper excelMapper;
    @Autowired
    private ExcelSelectMapper excelSelectMapper;

    @Override
    public Map<String, Object> queryExtraAccount(Map<String, Object> map) {

        Integer pid = null;
        Integer wid = null;
        Integer[] cid = null;
        String beginDate = null;
        String endDate = null;
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> me = it.next();
            if (me.getKey().equals("pid")) {
                pid = (Integer) me.getValue();
            }
            if (me.getKey().equals("wid")) {
                wid = (Integer) me.getValue();
            }
            if (me.getKey().equals("cid")) {
                cid = (Integer[]) me.getValue();
            }
            if (me.getKey().equals("beginDate")) {
                beginDate = (String) me.getValue();
            }
            if (me.getKey().equals("endDate")) {
                endDate = (String) me.getValue();
            }
        }
        Map<String, Object> mapParam = new HashMap<>();
        if (pid != null) {
            mapParam.put("pid", pid);
        }
        if (cid != null && cid.length > 0) {
            List<Integer> cidList = new ArrayList<>();
            for (int i = 0; i < cid.length; i++) {
                cidList.add(excelSelectMapper.queryCustomerIdByCompanyId(cid[i]));
                System.out.println("正式账号Id" + excelSelectMapper.queryCustomerIdByCompanyId(cid[i]));
            }
            mapParam.put("cidList", cidList);
        }
        if (beginDate != null && beginDate != "") {
            mapParam.put("beginDate", CalendarUtil.getTranByInputTime(beginDate));
        }
        if (endDate != null && endDate != "") {
            mapParam.put("endDate", CalendarUtil.getAfterDayByInputTime(endDate));
        }

        List<ExportExcel> exportExcelListPartnerUserOur = null;
        List<ExportExcel> exportExcelListOurUserPartner = null;
        List<ExportExcel> exportExcelListPartnerUserOurSell = null;
        double partnerUserOurTotle = 0.0;
        double ourUserPartnerTotle = 0.0;
        double partnerUserOurSellTotle = 0.0;
        double sum = 0.0;
        Partner partner = null;
        if (wid != null) {
            partner = excelSelectMapper.queryPartnerById(pid);
            if (wid == 1) {
                exportExcelListPartnerUserOur = excelSelectMapper.queryPartnerUserOurBySum(mapParam);
                exportExcelListOurUserPartner = excelSelectMapper.queryOurUserPartnerBySum(mapParam);
                exportExcelListPartnerUserOurSell = excelSelectMapper.queryPartnerUserOurSellBySum(mapParam);
                if (exportExcelListPartnerUserOur != null) {
                    for (int i = 0; i < exportExcelListPartnerUserOur.size(); i++) {
                        ExportExcel exportExcel = exportExcelListPartnerUserOur.get(i);
                        if (beginDate != null && endDate != null) {
                            if (CalendarUtil.isInputTimeAfterDayGreaterThanEqualCurrTimeAfterDay(endDate)){
                                exportExcel.setConsuTime(beginDate + "-" + "昨天");
                            }else {
                                exportExcel.setConsuTime(beginDate + "-" + endDate);
                            }
                        }
                        if (beginDate != null && endDate == null) {
                            exportExcel.setConsuTime(beginDate + "-" + "昨天");
                        }
                        if (beginDate == null && endDate != null) {
                            if (CalendarUtil.isInputTimeAfterDayGreaterThanEqualCurrTimeAfterDay(endDate)){
                                exportExcel.setConsuTime("开通后" + "-" + "昨天");
                            }else {
                                exportExcel.setConsuTime("开通后" + "-" + endDate);
                            }
                        }
                        if (beginDate == null && endDate == null) {
                            exportExcel.setConsuTime("开通后" + "-" + "昨天");
                        }
                        if (exportExcel.getCost() != null) {
                            exportExcel.setCost(exportExcel.getCost() / 100.0);
                        }
                        if (exportExcel.getCost() != null && exportExcel.getSumCount() != null) {
                            exportExcel.setSumCost(exportExcel.getCost() * exportExcel.getSumCount());
                        }
                        if (exportExcel.getSumCost() != null) {
                            partnerUserOurTotle += exportExcel.getSumCost();
                        }
                    }
                }
                if (exportExcelListOurUserPartner != null) {
                    for (int i = 0; i < exportExcelListOurUserPartner.size(); i++) {
                        ExportExcel exportExcel = exportExcelListOurUserPartner.get(i);
                        if (beginDate != null && endDate != null) {
                            if (CalendarUtil.isInputTimeAfterDayGreaterThanEqualCurrTimeAfterDay(endDate)){
                                exportExcel.setConsuTime(beginDate + "-" + "昨天");
                            }else {
                                exportExcel.setConsuTime(beginDate + "-" + endDate);
                            }
                        }
                        if (beginDate != null && endDate == null) {
                            exportExcel.setConsuTime(beginDate + "-" + "昨天");
                        }
                        if (beginDate == null && endDate != null) {
                            if (CalendarUtil.isInputTimeAfterDayGreaterThanEqualCurrTimeAfterDay(endDate)){
                                exportExcel.setConsuTime("开通后" + "-" + "昨天");
                            }else {
                                exportExcel.setConsuTime("开通后" + "-" + endDate);
                            }
                        }
                        if (beginDate == null && endDate == null) {
                            exportExcel.setConsuTime("开通后" + "-" + "昨天");
                        }
                        if (exportExcel.getCost() != null) {
                            exportExcel.setCost(exportExcel.getCost() / 100.0);
                        }
                        if (exportExcel.getCost() != null && exportExcel.getSumCount() != null) {
                            exportExcel.setSumCost(exportExcel.getCost() * exportExcel.getSumCount());
                        }
                        if (exportExcel.getSumCost() != null) {
                            ourUserPartnerTotle += exportExcel.getSumCost();
                        }
                    }
                }
                if (exportExcelListPartnerUserOurSell != null) {
                    for (int i = 0; i < exportExcelListPartnerUserOurSell.size(); i++) {
                        ExportExcel exportExcel = exportExcelListPartnerUserOurSell.get(i);
                        if (beginDate != null && endDate != null) {
                            if (CalendarUtil.isInputTimeAfterDayGreaterThanEqualCurrTimeAfterDay(endDate)){
                                exportExcel.setConsuTime(beginDate + "-" + "昨天");
                            }else {
                                exportExcel.setConsuTime(beginDate + "-" + endDate);
                            }
                        }
                        if (beginDate != null && endDate == null) {
                            exportExcel.setConsuTime(beginDate + "-" + "昨天");
                        }
                        if (beginDate == null && endDate != null) {
                            if (CalendarUtil.isInputTimeAfterDayGreaterThanEqualCurrTimeAfterDay(endDate)){
                                exportExcel.setConsuTime("开通后" + "-" + "昨天");
                            }else {
                                exportExcel.setConsuTime("开通后" + "-" + endDate);
                            }
                        }
                        if (beginDate == null && endDate == null) {
                            exportExcel.setConsuTime("开通后" + "-" + "昨天");
                        }
                        if (exportExcel.getCost() != null) {
                            exportExcel.setCost(exportExcel.getCost() / 100.0);
                        }
                        if (exportExcel.getCost() != null && exportExcel.getSumCount() != null) {
                            exportExcel.setSumCost(exportExcel.getCost() * exportExcel.getSumCount());
                        }
                        if (exportExcel.getSumCost() != null) {
                            partnerUserOurSellTotle += exportExcel.getSumCost();
                        }
                    }
                }
                sum = ((partnerUserOurTotle - ourUserPartnerTotle) + ((partnerUserOurSellTotle - partnerUserOurTotle) / 2));
            }
            if (wid == 2) {
                exportExcelListPartnerUserOur = excelSelectMapper.queryPartnerUserOurByMonth(mapParam);
                exportExcelListOurUserPartner = excelSelectMapper.queryOurUserPartnerByMonth(mapParam);
                exportExcelListPartnerUserOurSell = excelSelectMapper.queryPartnerUserOurSellByMonth(mapParam);
                if (exportExcelListPartnerUserOur != null) {
                    for (int i = 0; i < exportExcelListPartnerUserOur.size(); i++) {
                        ExportExcel exportExcel = exportExcelListPartnerUserOur.get(i);
                        if (exportExcel.getConsuTime() != null) {
                            exportExcel.setConsuTime(exportExcel.getConsuTime());
                        }
                        if (exportExcel.getCost() != null) {
                            exportExcel.setCost(exportExcel.getCost() / 100.0);
                        }
                        if (exportExcel.getCost() != null && exportExcel.getSumCount() != null) {
                            exportExcel.setSumCost(exportExcel.getCost() * exportExcel.getSumCount());
                        }
                        if (exportExcel.getSumCost() != null) {
                            partnerUserOurTotle += exportExcel.getSumCost();
                        }
                    }
                }
                if (exportExcelListOurUserPartner != null) {
                    for (int i = 0; i < exportExcelListOurUserPartner.size(); i++) {
                        ExportExcel exportExcel = exportExcelListOurUserPartner.get(i);
                        if (exportExcel.getConsuTime() != null) {
                            exportExcel.setConsuTime(exportExcel.getConsuTime());
                        }
                        if (exportExcel.getCost() != null) {
                            exportExcel.setCost(exportExcel.getCost() / 100.0);
                        }
                        if (exportExcel.getCost() != null && exportExcel.getSumCount() != null) {
                            exportExcel.setSumCost(exportExcel.getCost() * exportExcel.getSumCount());
                        }
                        if (exportExcel.getSumCost() != null) {
                            partnerUserOurTotle += exportExcel.getSumCost();
                        }
                    }
                }
                if (exportExcelListPartnerUserOurSell != null) {
                    for (int i = 0; i < exportExcelListPartnerUserOurSell.size(); i++) {
                        ExportExcel exportExcel = exportExcelListPartnerUserOurSell.get(i);
                        if (exportExcel.getConsuTime() != null) {
                            exportExcel.setConsuTime(exportExcel.getConsuTime());
                        }
                        if (exportExcel.getCost() != null) {
                            exportExcel.setCost(exportExcel.getCost() / 100.0);
                        }
                        if (exportExcel.getCost() != null && exportExcel.getSumCount() != null) {
                            exportExcel.setSumCost(exportExcel.getCost() * exportExcel.getSumCount());
                        }
                        if (exportExcel.getSumCost() != null) {
                            partnerUserOurTotle += exportExcel.getSumCost();
                        }
                    }
                }
                sum = ((partnerUserOurTotle - ourUserPartnerTotle) + ((partnerUserOurSellTotle - partnerUserOurTotle) / 2));
            }
            if (wid == 3) {
                exportExcelListPartnerUserOur = excelSelectMapper.queryPartnerUserOurByDay(mapParam);
                exportExcelListOurUserPartner = excelSelectMapper.queryOurUserPartnerByDay(mapParam);
                exportExcelListPartnerUserOurSell = excelSelectMapper.queryPartnerUserOurSellByDay(mapParam);
                if (exportExcelListPartnerUserOur != null) {
                    for (int i = 0; i < exportExcelListPartnerUserOur.size(); i++) {
                        ExportExcel exportExcel = exportExcelListPartnerUserOur.get(i);
                        if (exportExcel.getConsuTime() != null) {
                            exportExcel.setConsuTime(exportExcel.getConsuTime());
                        }
                        if (exportExcel.getCost() != null) {
                            exportExcel.setCost(exportExcel.getCost() / 100.0);
                        }
                        if (exportExcel.getCost() != null && exportExcel.getSumCount() != null) {
                            exportExcel.setSumCost(exportExcel.getCost() * exportExcel.getSumCount());
                        }
                        if (exportExcel.getSumCost() != null) {
                            partnerUserOurTotle += exportExcel.getSumCost();
                        }
                    }
                }
                if (exportExcelListOurUserPartner != null) {
                    for (int i = 0; i < exportExcelListOurUserPartner.size(); i++) {
                        ExportExcel exportExcel = exportExcelListOurUserPartner.get(i);
                        if (exportExcel.getConsuTime() != null) {
                            exportExcel.setConsuTime(exportExcel.getConsuTime());
                        }
                        if (exportExcel.getCost() != null) {
                            exportExcel.setCost(exportExcel.getCost() / 100.0);
                        }
                        if (exportExcel.getCost() != null && exportExcel.getSumCount() != null) {
                            exportExcel.setSumCost(exportExcel.getCost() * exportExcel.getSumCount());
                        }
                        if (exportExcel.getSumCost() != null) {
                            partnerUserOurTotle += exportExcel.getSumCost();
                        }
                    }
                }
                if (exportExcelListPartnerUserOurSell != null) {
                    for (int i = 0; i < exportExcelListPartnerUserOurSell.size(); i++) {
                        ExportExcel exportExcel = exportExcelListPartnerUserOurSell.get(i);
                        if (exportExcel.getConsuTime() != null) {
                            exportExcel.setConsuTime(exportExcel.getConsuTime());
                        }
                        if (exportExcel.getCost() != null) {
                            exportExcel.setCost(exportExcel.getCost() / 100.0);
                        }
                        if (exportExcel.getCost() != null && exportExcel.getSumCount() != null) {
                            exportExcel.setSumCost(exportExcel.getCost() * exportExcel.getSumCount());
                        }
                        if (exportExcel.getSumCost() != null) {
                            partnerUserOurTotle += exportExcel.getSumCost();
                        }
                    }
                }
                sum = ((partnerUserOurTotle - ourUserPartnerTotle) + ((partnerUserOurSellTotle - partnerUserOurTotle) / 2));
            }
        }
        Map<String, Object> mapResu = new HashMap<String, Object>();
        mapResu.put("exportExcelListPartnerUserOur", exportExcelListPartnerUserOur);
        mapResu.put("exportExcelListOurUserPartner", exportExcelListOurUserPartner);
        mapResu.put("exportExcelListPartnerUserOurSell", exportExcelListPartnerUserOurSell);
        mapResu.put("partner", partner);
        mapResu.put("partnerUserOurTotle", partnerUserOurTotle);
        mapResu.put("ourUserPartnerTotle", ourUserPartnerTotle);
        mapResu.put("partnerUserOurSellTotle", partnerUserOurSellTotle);
        mapResu.put("sum", sum);
        return mapResu;
    }

    @Override
    public List<Company> queryCompanyByPid(Integer pid) {
        return excelSelectMapper.queryCompanyByPid(pid);
    }

    @Override
    public Map<String, Object> queryExtraAccountCustomer(Map<String, Object> map) {
        Integer cid = null;
        Integer wid = null;
        Integer [] tid = null;
        String beginDate = null;
        String endDate = null;
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> me = it.next();
            if (me.getKey().equals("cid")) {
                cid = (Integer) me.getValue();
            }
            if (me.getKey().equals("wid")) {
                wid = (Integer) me.getValue();
            }
            if (me.getKey().equals("tid")) {
                tid = (Integer[]) me.getValue();
            }
            if (me.getKey().equals("beginDate")) {
                beginDate = (String) me.getValue();
            }
            if (me.getKey().equals("endDate")) {
                endDate = (String) me.getValue();
            }
        }
        Map<String, Object> mapParam = new HashMap<>();
        if (cid != null) {
            mapParam.put("cid", excelSelectMapper.queryCustomerIdByCompanyId(cid));
            System.out.println("正式账号Id" + excelSelectMapper.queryCustomerIdByCompanyId(cid));
        }
        if (tid != null && tid.length > 0) {
            List<Integer> tidList = new ArrayList<>();
            for (int i = 0; i < tid.length; i++) {
                tidList.add(tid[i]);
            }
            mapParam.put("tidList", tidList);
        }
        if (beginDate != null && beginDate != "") {
            mapParam.put("beginDate", CalendarUtil.getTranByInputTime(beginDate));
        }
        if (endDate != null && endDate != "") {
            mapParam.put("endDate", CalendarUtil.getAfterDayByInputTime(endDate));
        }
        List<ExportExcel> exportExcelList = null;
        double sumCost = 0.0;
        String companyName = null;
        if (wid != null) {
            companyName = excelSelectMapper.queryCompanyNameByCompanyId(cid);
            if (wid == 1) {
                exportExcelList = excelSelectMapper.queryCustomerConsumeStatusBySum(mapParam);
                if (exportExcelList != null) {
                    for (int i = 0; i < exportExcelList.size(); i++) {
                        ExportExcel exportExcel = exportExcelList.get(i);
                        if (beginDate != null && endDate != null) {
                            if (CalendarUtil.isInputTimeAfterDayGreaterThanEqualCurrTimeAfterDay(endDate)){
                                exportExcel.setConsuTime(beginDate + "-" + "昨天");
                            }else {
                                exportExcel.setConsuTime(beginDate + "-" + endDate);
                            }
                        }
                        if (beginDate != null && endDate == null) {
                            exportExcel.setConsuTime(beginDate + "-" + "昨天");
                        }
                        if (beginDate == null && endDate != null) {
                            if (CalendarUtil.isInputTimeAfterDayGreaterThanEqualCurrTimeAfterDay(endDate)){
                                exportExcel.setConsuTime("开通后" + "-" + "昨天");
                            }else {
                                exportExcel.setConsuTime("开通后" + "-" + endDate);
                            }
                        }
                        if (beginDate == null && endDate == null) {
                            exportExcel.setConsuTime("开通后" + "-" + "昨天");
                        }
                        if (exportExcel.getCost() != null) {
                            exportExcel.setCost(exportExcel.getCost() / 100.0);
                        }
                        if (exportExcel.getCost() != null && exportExcel.getSumCount() != null) {
                            exportExcel.setSumCost(exportExcel.getCost() * exportExcel.getSumCount());
                        }
                        if (exportExcel.getSumCost() != null) {
                            sumCost += exportExcel.getSumCost();
                        }
                    }
                }
            }
            if (wid == 2) {
                exportExcelList = excelSelectMapper.queryCustomerConsumeStatusByMonth(mapParam);
                if (exportExcelList != null) {
                    for (int i = 0; i < exportExcelList.size(); i++) {
                        ExportExcel exportExcel = exportExcelList.get(i);
                        if (exportExcel.getConsuTime() != null) {
                            exportExcel.setConsuTime(exportExcel.getConsuTime());
                        }
                        if (exportExcel.getCost() != null) {
                            exportExcel.setCost(exportExcel.getCost() / 100.0);
                        }
                        if (exportExcel.getCost() != null && exportExcel.getSumCount() != null) {
                            exportExcel.setSumCost(exportExcel.getCost() * exportExcel.getSumCount());
                        }
                        if (exportExcel.getSumCost() != null) {
                            sumCost += exportExcel.getSumCost();
                        }
                    }
                }
            }
            if (wid == 3) {
                exportExcelList = excelSelectMapper.queryCustomerConsumeStatusByDay(mapParam);
                if (exportExcelList != null) {
                    for (int i = 0; i < exportExcelList.size(); i++) {
                        ExportExcel exportExcel = exportExcelList.get(i);
                        if (exportExcel.getConsuTime() != null) {
                            exportExcel.setConsuTime(exportExcel.getConsuTime());
                        }
                        if (exportExcel.getCost() != null) {
                            exportExcel.setCost(exportExcel.getCost() / 100.0);
                        }
                        if (exportExcel.getCost() != null && exportExcel.getSumCount() != null) {
                            exportExcel.setSumCost(exportExcel.getCost() * exportExcel.getSumCount());
                        }
                        if (exportExcel.getSumCost() != null) {
                            sumCost += exportExcel.getSumCost();
                        }
                    }
                }
            }
        }
        Map<String, Object> mapResu = new HashMap<String, Object>();
        mapResu.put("exportExcelList", exportExcelList);
        mapResu.put("sumCost", sumCost);
        mapResu.put("companyName", companyName);
        return mapResu;
    }

    @Override
    public List<ApiType> queryApiTypeByCid(Integer cid) {
        return excelSelectMapper.queryApiTypeByCid(cid);
    }

    /**
     * 根据供应商id查询产品
     * @param vid
     * @return
     */
    @Override
    public List<ApiType> queryApiByVid(Integer vid) {
        return excelSelectMapper.queryApiByVid(vid);
    }

    /**
     * 按条件查询供应商账单
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> queryExtraAccountVendor(Map<String, Object> map) {
        Set<Map.Entry<String, Object>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, Object>> it = entrySet.iterator();

        Integer vid = null;
        Integer wid = null;
        String beginDate = null;
        String endDate = null;
        Integer[]tid = null;
        List<Integer> tidList = new ArrayList<Integer>();
        while (it.hasNext()){
            Map.Entry<String, Object> entry = it.next();
            if ("vid".equals(entry.getKey())){
                vid = Integer.valueOf(entry.getValue().toString());
            }
            if ("wid".equals(entry.getKey())){
                wid = Integer.valueOf(entry.getValue().toString());
            }
            if ("beginDate".equals(entry.getKey())){
                beginDate = entry.getValue().toString();
            }
            if ("endDate".equals(entry.getKey())){
                endDate = entry.getValue().toString();
            }
            if ("tid".equals(entry.getKey())){
                tid = (Integer[])entry.getValue();
            }
        }

        Map<String, Object>param = new HashMap<String, Object>();
        if (vid != null){
            param.put("vid", vid);
        }
        if (wid != null){
            param.put("wid", wid);
        }
        if (beginDate != null){
            param.put("beginDate", CalendarUtil.getTranByInputTime(beginDate));
        }
        if (endDate != null){
            param.put("endDate", CalendarUtil.getAfterDayByInputTime(endDate));
        }
        if (tid != null && tid.length > 0){
            for (int i = 0; i < tid.length; i++){
                tidList.add(tid[i]);
            }
            param.put("tidList", tidList);
        }

        Map<String, Object> resultMap = new HashMap<>();
        Double totalAmount = 0.0;
        List<VendorBill> vendorBills = null;
        String companyName = null;
        if(wid != null) {
            companyName = excelSelectMapper.queryCompanyNameByVid(vid + "");
            if (wid == 1) {
                vendorBills = excelSelectMapper.queryVendorBill(param);
                if (vendorBills != null && vendorBills.size() > 0) {
                    for (VendorBill vendorBill : vendorBills) {
                        String name = ApiTypeMobileOperatorNameUtils.apiTypeMobileOperatorName(vendorBill.getApiTypeName(), vendorBill.getMobileList());
                        vendorBill.setApiName(name);
                        if (vendorBill.getCost() != null) {
                            vendorBill.setCost(vendorBill.getCost() / 100);
                        }
                        if (vendorBill.getAmount() != null) {
                            vendorBill.setAmount(vendorBill.getAmount() / 100);
                        }
                        if (beginDate != null && endDate != null) {
                            if (CalendarUtil.isInputTimeAfterDayGreaterThanEqualCurrTimeAfterDay(endDate)) {
                                vendorBill.setConsuTime(beginDate + "-" + "昨天");
                            } else {
                                vendorBill.setConsuTime(beginDate + "-" + endDate);
                            }
                        }
                        if (beginDate != null && endDate == null) {
                            vendorBill.setConsuTime(beginDate + "-" + "昨天");
                        }
                        if (beginDate == null && endDate != null) {
                            if (CalendarUtil.isInputTimeAfterDayGreaterThanEqualCurrTimeAfterDay(endDate)) {
                                vendorBill.setConsuTime("开通后" + "-" + "昨天");
                            } else {
                                vendorBill.setConsuTime("开通后" + "-" + endDate);
                            }
                        }
                        if (beginDate == null && endDate == null) {
                            vendorBill.setConsuTime("开通后" + "-" + "昨天");
                        }
                        if (vendorBill.getAmount() != null) {
                            totalAmount += vendorBill.getAmount();
                        }

                    }
                }
            }

            if (wid == 2) {
                vendorBills = excelSelectMapper.queryVendorBillByMonth(param);
                if (vendorBills != null && vendorBills.size() > 0) {
                    for (VendorBill vendorBill : vendorBills) {
                        String name = ApiTypeMobileOperatorNameUtils.apiTypeMobileOperatorName(vendorBill.getApiTypeName(), vendorBill.getMobileList());
                        vendorBill.setApiName(name);
                        if (vendorBill.getCost() != null) {
                            vendorBill.setCost(vendorBill.getCost() / 100);
                        }
                        if (vendorBill.getAmount() != null) {
                            vendorBill.setAmount(vendorBill.getAmount() / 100);
                        }
                        if (vendorBill.getAmount() != null) {
                            totalAmount += vendorBill.getAmount();
                        }
                        if (vendorBill.getConsuTime() != null) {
                            vendorBill.setConsuTime(vendorBill.getConsuTime());
                        }

                    }
                }
            }

            if (wid == 3) {
                vendorBills = excelSelectMapper.queryVendorBillByDay(param);
                if (vendorBills != null && vendorBills.size() > 0) {
                    for (VendorBill vendorBill : vendorBills) {
                        String name = ApiTypeMobileOperatorNameUtils.apiTypeMobileOperatorName(vendorBill.getApiTypeName(), vendorBill.getMobileList());
                        vendorBill.setApiName(name);
                        if (vendorBill.getCost() != null) {
                            vendorBill.setCost(vendorBill.getCost() / 100);
                        }
                        if (vendorBill.getAmount() != null) {
                            vendorBill.setAmount(vendorBill.getAmount() / 100);
                        }
                        if (vendorBill.getAmount() != null) {
                            totalAmount += vendorBill.getAmount();
                        }
                        if (vendorBill.getConsuTime() != null) {
                            vendorBill.setConsuTime(vendorBill.getConsuTime());
                        }

                    }
                }
            }
        }
        Map<String, Object> mapResu = new HashMap<String, Object>();
        mapResu.put("exportExcelList", vendorBills);
        mapResu.put("sumCost", totalAmount);
        System.out.println(totalAmount);
        mapResu.put("companyName", companyName);

        return mapResu;
    }
}
