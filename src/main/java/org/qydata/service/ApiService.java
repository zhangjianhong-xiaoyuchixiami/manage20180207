package org.qydata.service;

import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.dst.ApiTypeInfo;
import org.qydata.dst.CustomerApiPartner;
import org.qydata.entity.*;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Api> queryApi(Map<String,Object> map);

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
     * 查询合作公司
     * @return
     */
    public List<Partner> queryPartner();

    /**
     * 以客户纬度查询产品
     * @param map
     * @return
     */
    public List<CustomerApiPartner> queryApiByCompanyId(Map<String,Object> map);

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
    @Transactional
    @SystemServiceLog(description = "修改上游产品价格")
    public int updatePrice(Integer aid,Double pic) throws Exception;

    /**
     * 查看上游产品改价记录
     * @return
     */
    public List<ApiPriceChanceLog> queryApiPriceChangeLog(Map<String,Object> map);

    /**
     * 新增上游产品价格记录
     * @param aid
     * @param pic
     * @param date
     * @return
     */
    @Transactional
    @SystemServiceLog(description = "新增上游产品改价记录")
    public boolean addApiPriceChangeLog(Integer aid,Double pic,String date) throws Exception;

    /**
     * 查询所有Api用于新增产品价格记录
     * @param map
     * @return
     */
    public List<Api> queryAllApi(Map<String,Object> map);

    /**
     * 查看客户改价记录
     * @return
     */
    public List<CompanyApiPriceChangeLog> queryCompanyApiPriceChangeLog(Map<String,Object> map);

    /**
     * 根据公司Id查询所拥有的产品 -- 用于新增客户改价记录级联
     * @param cid
     * @return
     */
    public List<ApiTypeInfo> queryCompanyApiByCompanyId(Integer cid);

    /**
     * 新增下游客户产品价格记录
     * @param tid_stid
     * @param pic
     * @param date
     * @return
     */
    @Transactional
    @SystemServiceLog(description = "新增客户产品改价记录")
    public boolean addCompanyApiPriceChangeLog(Integer cid,String tid_stid,Double pic,String date) throws Exception;

    /**
     * 修改下游产品价格
     * @param cid
     * @param tid
     * @param stid
     * @param pic
     * @return
     * @throws Exception
     */
    @SystemServiceLog(description = "修改客户产品价格")
    public int updateCompanyApiPrice(Integer cid,Integer tid,Integer stid,Double pic)throws Exception;


    /**
     * 禁用客户产品权限
     * @return
     */
    @SystemServiceLog(description = "客户产品权限禁用")
    public Map<String,Object> banCompanyApi(String [] cid_id)throws Exception;

    /**
     * 修改上游产品当前配额
     * @param aid
     * @param prob
     * @return
     * @throws Exception
     */
    @SystemServiceLog(description = "修改上游产品当前配额")
    public int updateApiCurrProb(Integer aid,Integer prob)throws Exception;

    /**
     * 修改上游产品预设配额
     * @param aid
     * @param prob
     * @return
     * @throws Exception
     */
    @Transactional
    @SystemServiceLog(description = "修改上游产品预设配额")
    public boolean updateApiDefProb(Integer aid,Integer prob)throws Exception;

    /**
     * 修改上游产品预设比例
     * @param aid
     * @param prop
     * @return
     * @throws Exception
     */
    @Transactional
    @SystemServiceLog(description = "修改上游产品预设比例")
    public boolean updateApiDefProp(Integer aid,Double prop)throws Exception;

    /**
     * 恢复上游产品配额
     * @param aid
     * @return
     * @throws Exception
     */
    @SystemServiceLog(description = "恢复上游产品配额")
    public void updateRecoverApiProb(String [] aid)throws Exception;

    /**
     * 根据ApiId查询Api类型
     * @param aid
     * @return
     */
    public Integer queryApiTypeByApiId(Integer aid);

    /**
     * 查询产品恢复日志
     * @param aid
     * @return
     */
    public RecoverProbLog queryDetailLogByApiId(Integer aid);

    /**
     * 检查当前是否在进行恢复配额操作
     * @return
     */
    public List<RecoverProbCheck> queryAllRecoverProbCheck(Map<String,Object> map);


}
