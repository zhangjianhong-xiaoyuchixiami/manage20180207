package org.qydata.service;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.entity.ApiType;
import org.qydata.entity.Company;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/7/7.
 */
public interface ExcelService {

    /**
     * 合作伙伴财务对账
     * @param map
     * @return
     */
    @SystemServiceLog(description = "合作伙伴财务对账")
    public Map<String,Object> queryExtraAccount(Map<String,Object> map);

    /**
     * 合作公司Id查询公司
     * @return
     */
    @SystemServiceLog(description = "根据公司Id查询公司")
    public List<Company> queryCompanyByPid(Integer pid);

    /**
     * 客户临时对账
     * @param map
     * @return
     */
    @SystemServiceLog(description = "客户临时对账")
    public Map<String,Object> queryExtraAccountCustomer(Map<String,Object> map);

    /**
     * 公司Id查询产品类型
     * @param cid
     * @return
     */
    @SystemServiceLog(description = "公司Id查询产品类型")
    public List<ApiType> queryApiTypeByCid(Integer cid);


}
