package org.qydata.mapper.mapper1;

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
     * 修改产品失效时间
     * @param aid
     * @return
     */
    public boolean updateApiDeadTimeByApiId(Integer aid, Date date);

    /**
     * 新增产品价格记录
     * @param apiPriceChanceLog
     * @return
     */
    public boolean addApiPriceChangeLog(ApiPriceChanceLog apiPriceChanceLog) throws Exception;


    /**
     * 修改客户同一产品失效时间
     * @param cid
     * @param tid
     * @param stid
     * @param date
     * @return
     */
    public boolean updateCompanyApiDeadTimeByCidTidStid(Integer cid, Integer tid, Integer stid, Date date);

    /**
     * 新增产品价格记录
     * @param companyApiPriceChangeLog
     * @return
     */
    public boolean addCompanyApiPriceChangeLog(CompanyApiPriceChangeLog companyApiPriceChangeLog) throws Exception;


    /**
     * 添加api已改价记录
     * @param apiFake
     * @return
     */
    public boolean addApiFake(ApiFake apiFake);

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
