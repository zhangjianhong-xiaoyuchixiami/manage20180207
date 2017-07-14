package org.qydata.mapper;

import org.qydata.entity.Company;
import org.qydata.entity.Partner;
import org.qydata.entity.excel.ExportExcel;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/7/7.
 */
public interface ExcelMapper {

    /**
     *统计合作方使用我方源  统计方式：汇总
     * @param map
     * @return
     */
    public List<ExportExcel> queryPartnerUserOurBySum(Map<String,Object> map);

    /**
     *统计合作方使用我方源  统计方式：按月
     * @param map
     * @return
     */
    public List<ExportExcel> queryPartnerUserOurByMonth(Map<String,Object> map);

    /**
     *统计合作方使用我方源  统计方式：按天
     * @param map
     * @return
     */
    public List<ExportExcel> queryPartnerUserOurByDay(Map<String,Object> map);

    /**
     *统计我方使用合作方源  统计方式：汇总
     * @param map
     * @return
     */
    public List<ExportExcel> queryOurUserPartnerBySum(Map<String,Object> map);

    /**
     *统计我方使用合作方源  统计方式：按月
     * @param map
     * @return
     */
    public List<ExportExcel> queryOurUserPartnerByMonth(Map<String,Object> map);

    /**
     *统计我方使用合作方源  统计方式：按天
     * @param map
     * @return
     */
    public List<ExportExcel> queryOurUserPartnerByDay(Map<String,Object> map);

    /**
     *统计合作方使用我方源售卖  统计方式：汇总
     * @param map
     * @return
     */
    public List<ExportExcel> queryPartnerUserOurSellBySum(Map<String,Object> map);

    /**
     *统计合作方使用我方源售卖  统计方式：按月
     * @param map
     * @return
     */
    public List<ExportExcel> queryPartnerUserOurSellByMonth(Map<String,Object> map);

    /**
     *统计合作方使用我方源售卖  统计方式：按天
     * @param map
     * @return
     */
    public List<ExportExcel> queryPartnerUserOurSellByDay(Map<String,Object> map);

    /**
     *查询合作公司名称根据id
     * @param id
     * @return
     */
    public Partner queryPartnerById(Integer id);

    /**
     * 合作公司Id查询公司
     * @return
     */
    public List<Company> queryCompanyByPid(Integer pid);
}
