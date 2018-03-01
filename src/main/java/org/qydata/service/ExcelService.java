package org.qydata.service;

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
    public Map<String,Object> queryExtraAccount(Map<String,Object> map);

    /**
     * 合作公司Id查询公司
     * @return
     */
    public List<Company> queryCompanyByPid(Integer pid);

    /**
     * 客户临时对账
     * @param map
     * @return
     */
    public Map<String,Object> queryExtraAccountCustomer(Map<String,Object> map);

    /**
     * 公司Id查询产品类型
     * @param cid
     * @return
     */
    public List<ApiType> queryApiTypeByCid(Integer cid);

    /**
     * 供应商id查询产品
     * @param vide
     * @return
     */
    public List<ApiType> queryApiByVid(Integer vide);

    /**
     * 按条件查询供应商账单
     * @param map
     * @return
     */
    public Map<String,Object> queryExtraAccountVendor(Map<String, Object>map);

}
