package org.qydata.service.impl;

import org.qydata.entity.CustomerCompanyEmail;
import org.qydata.entity.Partner;
import org.qydata.entity.monitor.CompanyBalanceMonitor;
import org.qydata.mapper.mapper1.CompanyBalanceMonitorMapper;
import org.qydata.mapper.mapper2.CompanyBalanceMonitorSelectMapper;
import org.qydata.service.CompanyBalanceMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/23.
 */
@Service
public class CompanyBalanceMonitorServiceImpl implements CompanyBalanceMonitorService {

    @Autowired
    private CompanyBalanceMonitorMapper monitorMapper;
    @Autowired
    private CompanyBalanceMonitorSelectMapper monitorSelectMapper;

    @Override
    public List<CompanyBalanceMonitor> queryCompanyBalanceMonitor(Map<String, Object> map) {

        Map<String,Object> param = new HashMap<>();
        if (map != null){
            for (Map.Entry<String, Object> me : map.entrySet()) {
                if (me.getKey().equals("name")) {
                    param.put("name",me.getValue());
                }
                if (me.getKey().equals("pid")) {
                    if ((Integer)me.getValue() == -100 || "-100".equals(me.getValue())){
                        param.put("nullPid",me.getValue());
                    }else {
                        param.put("pid", me.getValue());
                    }
                }
            }
        }

        List<CompanyBalanceMonitor> extList = monitorSelectMapper.queryCompanyBalanceMonitor(param);
        if (extList == null){
            return null;
        }
        for (CompanyBalanceMonitor ext : extList) {
            if (ext.getIsPrepay() != null && ext.getIsPrepay() == 1 || "1".equals(ext.getIsPrepay())){
                ext.setPrepayName("是");
            }else {
                ext.setPrepayName("否");
            }
            if (ext.getIsAlarm() != null && ext.getIsAlarm() == 1 || "1".equals(ext.getIsAlarm())){
                ext.setAlarmName("是");
            }else {
                ext.setAlarmName("否");
            }
            if (ext.getIsRemindCustomer() != null && ext.getIsRemindCustomer() == 1 || "1".equals(ext.getIsRemindCustomer())){
                ext.setRemindCustomerName("是");
            }else {
                ext.setRemindCustomerName("否");
            }
            if (ext.getAhead() == null){
                ext.setAhead(5);
            }
        }
        return extList;
    }

    @Override
    public boolean updatePrepay(Integer cid, Integer value) {
        CompanyBalanceMonitor monitor = monitorSelectMapper.queryCompanyBalanceMonitorById(cid);
        if (monitor == null){
            CompanyBalanceMonitor param = new CompanyBalanceMonitor();
            param.setCompanyId(cid);
            param.setIsPrepay(value);
            param.setIsAlarm(0);
            param.setIsRemindCustomer(0);
            param.setAhead(5);
            return monitorMapper.insertCompanyBalanceMonitor(param);
        }
        return monitorMapper.updatePrepay(cid,value);
    }

    @Override
    public boolean updateAlarm(Integer cid, Integer value) {
        CompanyBalanceMonitor monitor = monitorSelectMapper.queryCompanyBalanceMonitorById(cid);
        if (monitor == null){
            CompanyBalanceMonitor param = new CompanyBalanceMonitor();
            param.setCompanyId(cid);
            param.setIsPrepay(0);
            param.setIsAlarm(value);
            param.setIsRemindCustomer(0);
            param.setAhead(5);
            return monitorMapper.insertCompanyBalanceMonitor(param);
        }
        return monitorMapper.updateAlarm(cid,value);
    }

    @Override
    public boolean updateRemindCustomer(Integer cid, Integer value) {
        CompanyBalanceMonitor monitor = monitorSelectMapper.queryCompanyBalanceMonitorById(cid);
        if (monitor == null){
            CompanyBalanceMonitor param = new CompanyBalanceMonitor();
            param.setCompanyId(cid);
            param.setIsPrepay(0);
            param.setIsAlarm(0);
            param.setIsRemindCustomer(value);
            param.setAhead(5);
            return monitorMapper.insertCompanyBalanceMonitor(param);
        }
        return monitorMapper.updateRemindCustomer(cid,value);
    }

    @Override
    public boolean updateAhead(Integer cid, Integer value) {
        CompanyBalanceMonitor monitor = monitorSelectMapper.queryCompanyBalanceMonitorById(cid);
        if (monitor == null){
            CompanyBalanceMonitor param = new CompanyBalanceMonitor();
            param.setCompanyId(cid);
            param.setIsPrepay(0);
            param.setIsAlarm(0);
            param.setIsRemindCustomer(0);
            param.setAhead(value);
            return monitorMapper.insertCompanyBalanceMonitor(param);
        }
        return monitorMapper.updateAhead(cid,value);
    }

    @Override
    public List<CustomerCompanyEmail> queryEmailById(Map<String, Object> map) {
        return monitorSelectMapper.queryEmailById(map);
    }

    @Override
    public boolean deleteEmail(Integer id, Integer cid) {
        return monitorMapper.deleteEmail(id,cid);
    }

    @Override
    public boolean addEmail(Integer cid, String email) {
        CustomerCompanyEmail companyEmail = new CustomerCompanyEmail();
        companyEmail.setCompanyId(cid);
        companyEmail.setEmail(email);
        return monitorMapper.addEmail(companyEmail);
    }

    @Override
    public List<Partner> queryAllPartner() {
        return monitorSelectMapper.queryAllPartner();
    }
}
