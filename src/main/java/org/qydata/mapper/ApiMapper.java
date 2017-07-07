package org.qydata.mapper;

import org.qydata.dst.ApiTypeInfo;
import org.qydata.dst.CustomerApiPartner;
import org.qydata.entity.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/2/28.
 */
public interface ApiMapper {

    /**
     * 查询产品
     * @return
     * @throws Exception
     */
    public List<Api> queryApi(Map<String,Object> map);

    /**
     * 查询产品类型
     * @return
     * @throws Exception
     */
    public List<ApiType> queryApiType()throws Exception;

    /**
     * 查询产品供应商
     * @return
     * @throws Exception
     */
    public List<ApiVendor> queryApiVendor()throws Exception;

    /**
     * 根据产品类型查询产品供应商
     * @return
     * @throws Exception
     */
    public List<ApiVendor> queryApiVendorByApiTypeId(Integer id)throws Exception;

    /**
     * 查询合作公司
     * @return
     */
    public List<Partner> queryPartner();

    /**
     * 以客户纬度查询产品
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerApiPartner> queryApiByCompanyId(Map<String,Object> map);

    /**
     * 查询所有公司
     * @return
     * @throws Exception
     */
    public List<Company> queryCompany()throws Exception;

    /**
     * 查询api最近请求的失败次数
     * @return
     */
    public List<ApiBan> queryApiMonitor(Map<String,Object> map);

    /**
     * 查询产品类型名称、子类型名称、供应商名称
     * @param apiId
     * @return
     */
    public Api queryApiTypeNameStidNameVendorNameByApiId(Integer apiId);

    /**
     * 查看产品改价记录
     * @return
     */
    public List<ApiPriceChanceLog> queryApiPriceChangeLog(Map<String,Object> map);

    /**
     * 根据aid查询同一产品上次改价记录，用于修改失效时间
     * @param aid
     * @return
     */
    public ApiPriceChanceLog queryApiChangeLogByApiId(Integer aid);

    /**
     * 修改产品失效时间
     * @param aid
     * @return
     */
    public boolean updateApiDeadTimeByApiId(Integer aid,Date date);

    /**
     * 新增产品价格记录
     * @param apiPriceChanceLog
     * @return
     */
    public boolean addApiPriceChangeLog(ApiPriceChanceLog apiPriceChanceLog) throws Exception;

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
     * 根据cid,tid,stid查询客户同一产品上次改价记录，用于修改失效时间
     * @param cid
     * @param tid
     * @param stid
     * @return
     */
    public CompanyApiPriceChangeLog queryCompanyApiChangeLogByCidTidStid(Integer cid,Integer tid,Integer stid);

    /**
     * 修改客户同一产品失效时间
     * @param cid
     * @param tid
     * @param stid
     * @param date
     * @return
     */
    public boolean updateCompanyApiDeadTimeByCidTidStid(Integer cid,Integer tid,Integer stid,Date date);

    /**
     * 新增产品价格记录
     * @param companyApiPriceChangeLog
     * @return
     */
    public boolean addCompanyApiPriceChangeLog(CompanyApiPriceChangeLog companyApiPriceChangeLog) throws Exception;

    /**
     * 查询api是否已修改过初始价格
     * @param aid
     * @return
     */
    public ApiFake queryApiFakeByApiId(Integer aid);

    /**
     * 添加api已改价记录
     * @param apiFake
     * @return
     */
    public boolean addApiFake(ApiFake apiFake);

}
