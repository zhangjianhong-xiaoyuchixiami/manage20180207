package org.qydata.service;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/2/28.
 */
public interface ApiService {

    /**
     * 查询产品
     * @return
     */

    public Map<String,Object> queryApi(Map<String,Object> map);

    /**
     * 查询产品类型
     * @return
     */

    public List<ApiType> queryApiType();

    /**
     * 根据产品类型查询产品供应商
     * @return
     */

    public List<ApiVendor> queryApiVendor();

    /**
     * 根据产品类型查询产品供应商
     * @return
     */
    public List<ApiVendor> queryApiVendorByApiTypeId(Integer id);

    /**
     * 以客户纬度查询产品
     * @param map
     * @return
     */
    public Map<String,Object> queryApiByCompanyId(Map<String,Object> map);

    /**
     * 查询所有公司
     * @return
     */
    public List<Company> queryCompany();

    /**
     * 查询api最近请求的失败次数
     * @return
     */
    public List<ApiBan> queryApiMonitor();

    /**
     * 禁用公司
     * @param apiId
     * @return
     */
    @SystemServiceLog(description = "上游产品禁用")
    public Map<String,Object> updateApiBan(String [] apiId)throws Exception;

    /**
     * 解禁公司
     * @param apiId
     * @return
     */
    @SystemServiceLog(description = "上游产品解禁")
    public Map<String,Object> updateApiUnBan(String [] apiId)throws Exception;

    /**
     * 修改上游产品价格
     * @param aid
     * @param pic
     * @return
     * @throws Exception
     */
    @SystemServiceLog(description = "修改上游产品价格")
    public int updatePrice(Integer aid,Double pic) throws Exception;


    /**
     * 查看产品改价记录
     * @return
     */
    public List<ApiPriceChanceLog> queryApiPriceChangeLog(Map<String,Object> map);

    /**
     * 新增产品价格记录
     * @param tid
     * @param vid
     * @param pic
     * @param date
     * @return
     */
    @SystemServiceLog(description = "新增产品改价记录")
    public boolean addApiPriceChangeLog(Integer tid,Integer vid,Double pic,String date) throws Exception;


}
