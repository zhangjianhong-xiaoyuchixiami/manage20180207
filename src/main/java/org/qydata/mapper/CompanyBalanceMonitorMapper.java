package org.qydata.mapper;

import org.qydata.entity.monitor.CompanyExt;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/23.
 */
public interface CompanyBalanceMonitorMapper {


    /**
     * 查询客户余额报警列表
     * @param map
     * @return
     */
    public List<CompanyExt> queryCompanyBalanceMonitor(Map<String,Object> map);

}
