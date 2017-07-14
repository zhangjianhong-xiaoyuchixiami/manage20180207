package org.qydata.service;

import org.qydata.entity.Company;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/7/7.
 */
public interface ExcelService {

    /**
     * 财务对账
     * @param map
     * @return
     */
    public Map<String,Object> queryExtraAccount(Map<String,Object> map);

    /**
     * 合作公司Id查询公司
     * @return
     */
    public List<Company> queryCompanyByPid(Integer pid);

}
