package org.qydata.mapper;

import org.qydata.dst.ApiFinance;
import org.qydata.entity.ApiBalanceLog;
import org.qydata.entity.ApiRequestLog;
import org.qydata.entity.ApiType;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/12.
 */
public interface ApiFinanceMapper {

    /**
     *Api财务总览
     * @param map
     * @return
     * @throws Exception
     */
   public List<ApiFinance> queryApiOverAllFinance(Map<String,Object> map)throws Exception;

    /**
     * 查询供应商和api
     * @param map
     * @return
     * @throws Exception
     */
   public List<ApiFinance> queryApiVendorAndApi(Map<String,Object> map)throws Exception;

    /**
     *查询Api消费详情
     * @param map
     * @return
     * @throws Exception
     */
   public List<ApiRequestLog> queryApiDetailById(Map<String,Object> map) throws Exception;

    /**
     * Api充值

     * @return
     * @throws Exception
     */
   public boolean updateApiBalance(Integer apiId,Long balance ) throws Exception;

    /**
     * Api充值记录
     * @return
     * @throws Exception
     */
    public boolean insertApiBalanceLog(ApiBalanceLog apiBalanceLog) throws Exception;

    /**
     * 查询Api余额
     * @param apiId
     * @return
     * @throws Exception
     */
    public Long queryApiBalance(Integer apiId)throws Exception;

    /**
     * 查询Api类型
     * @return
     * @throws Exception
     */
    public List<ApiType> queryApiType()throws Exception;
}
