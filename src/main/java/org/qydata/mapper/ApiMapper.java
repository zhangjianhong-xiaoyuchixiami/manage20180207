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

    /**
     * 根据aid查询预设配额
     * @param aid
     * @return
     */
    public ApiExt queryApiDefProb(Integer aid);

    /**
     * 插入Api预设配额
     * @param apiExt
     * @return
     */
    public int addApiDefProb(ApiExt apiExt);

    /**
     * 修改上游产品预设配额
     * @param apiExt
     * @return
     * @throws Exception
     */
    public int updateApiDefProb(ApiExt apiExt)throws Exception;

    /**
     * 根据aid查询预设比例
     * @param aid
     * @return
     */
    public ApiExt queryApiDefProp(Integer aid);

    /**
     * 插入Api预设比例
     * @param apiExt
     * @return
     */
    public int addApiDefProp(ApiExt apiExt);

    /**
     * 修改上游产品预设比例
     * @param apiExt
     * @return
     * @throws Exception
     */
    public int updateApiDefProp(ApiExt apiExt)throws Exception;


    //恢复配额开始*****************************************************************


    /**
     * 根据ApiId查询Api状态
     * @param aid
     * @return
     */
    public  Integer queryApiStatusByApiId(Integer aid)throws Exception;

    /**
     * 根据ApiId查询Api类型
     * @param aid
     * @return
     */
    public Integer queryApiTypeByApiId(Integer aid) throws Exception;

    /**
     * 获取除了要恢复的产品同一类型并且状态启用的通道数是多少
     * @param aid
     * @return
     */
    public List<Api> getCountUnifiedTypeNorStatusOther(Integer aid,Integer tid) throws Exception;

    /**
     * 除了要恢复的产品筛选出统一类型价格最低做协作通道
     * @param aid
     * @return
     */
    public Api getUnifiedTypeNorStatusOtherLowCost(Integer aid,Integer tid) throws Exception;

    /**
     *查询请求日志
     * @param aid
     * @return
     */
    public List<ApiResponseLog> queryApiRequestLog(Integer aid, String begTime) throws Exception;

    /**
     * 根据ApiId查询预设配额和预设比例
     * @param apiId
     * @return
     * @throws Exception
     */
    public ApiExt queryDefProbDefPropByApiId(Integer apiId)throws Exception;

    /**
     * 判断此通道价格是否最低
     * @param tid
     * @return
     * @throws Exception
     */
    public Integer getCurrApiIsLowCost(Integer tid)throws Exception;

    /**
     * 插入恢复配额日志
     * @param recoverProbLog
     * @return
     * @throws Exception
     */
    public int addRecoverProbLog(RecoverProbLog recoverProbLog)throws Exception;


    /**
     * 修改恢复配额日志
     * @param recoverProbLog
     * @return
     * @throws Exception
     */
    public int updateRecoverProbLog(RecoverProbLog recoverProbLog)throws Exception;


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

    /**
     * 进行恢复检查记录是否存在
     * @return
     */
    public RecoverProbCheck queryRecoverProbCheck(Integer tid);

    /**
     * 进行恢复配额操作时修改执行标记
     * @return
     */
    public int updateRecoverProbCheck(RecoverProbCheck recoverProbCheck);

    /**
     * 进行恢复配额操作时插入执行标记
     * @param recoverProbCheck
     * @return
     */
    public int insertRecoverProbCheck(RecoverProbCheck recoverProbCheck);


}
