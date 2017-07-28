package org.qydata.service.impl;

import org.qydata.entity.ApiType;
import org.qydata.entity.Company;
import org.qydata.entity.Partner;
import org.qydata.entity.excel.ExportExcel;
import org.qydata.mapper.ExcelMapper;
import org.qydata.service.ExcelService;
import org.qydata.tools.date.CalendarUtil;
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
                cidList.add(excelMapper.queryCustomerIdByCompanyId(cid[i]));
                System.out.println("正式账号Id" + excelMapper.queryCustomerIdByCompanyId(cid[i]));
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
            partner = excelMapper.queryPartnerById(pid);
            if (wid == 1) {
                exportExcelListPartnerUserOur = excelMapper.queryPartnerUserOurBySum(mapParam);
                exportExcelListOurUserPartner = excelMapper.queryOurUserPartnerBySum(mapParam);
                exportExcelListPartnerUserOurSell = excelMapper.queryPartnerUserOurSellBySum(mapParam);
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
                exportExcelListPartnerUserOur = excelMapper.queryPartnerUserOurByMonth(mapParam);
                exportExcelListOurUserPartner = excelMapper.queryOurUserPartnerByMonth(mapParam);
                exportExcelListPartnerUserOurSell = excelMapper.queryPartnerUserOurSellByMonth(mapParam);
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
                exportExcelListPartnerUserOur = excelMapper.queryPartnerUserOurByDay(mapParam);
                exportExcelListOurUserPartner = excelMapper.queryOurUserPartnerByDay(mapParam);
                exportExcelListPartnerUserOurSell = excelMapper.queryPartnerUserOurSellByDay(mapParam);
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
        return excelMapper.queryCompanyByPid(pid);
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
            mapParam.put("cid", excelMapper.queryCustomerIdByCompanyId(cid));
            System.out.println("正式账号Id" + excelMapper.queryCustomerIdByCompanyId(cid));
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
            companyName = excelMapper.queryCompanyNameByCompanyId(cid);
            if (wid == 1) {
                exportExcelList = excelMapper.queryCustomerConsumeStatusBySum(mapParam);
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
                exportExcelList = excelMapper.queryCustomerConsumeStatusByMonth(mapParam);
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
                exportExcelList = excelMapper.queryCustomerConsumeStatusByDay(mapParam);
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
        return excelMapper.queryApiTypeByCid(cid);
    }
}
