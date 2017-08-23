package org.qydata.service.impl;

import org.qydata.entity.monitor.CompanyExt;
import org.qydata.mapper.CompanyBalanceMonitorMapper;
import org.qydata.service.CompanyBalanceMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/23.
 */
@Service
public class CompanyBalanceMonitorServiceImpl implements CompanyBalanceMonitorService {

    @Autowired
    private CompanyBalanceMonitorMapper monitorMapper;

    @Override
    public List<CompanyExt> queryCompanyBalanceMonitor(Map<String, Object> map) {

        List<CompanyExt> extList = monitorMapper.queryCompanyBalanceMonitor(map);
        if (extList == null){
            return null;
        }
        for (CompanyExt ext : extList) {
            if (ext.getIsPrepay() == 1 || "1".equals(ext.getIsPrepay())){
                ext.setPrepayName("是");
            }else {
                ext.setPrepayName("否");
            }
        }
        return extList;
    }
}
