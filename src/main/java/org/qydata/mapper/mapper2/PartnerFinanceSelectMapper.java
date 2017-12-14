package org.qydata.mapper.mapper2;

import org.qydata.dst.PartnerFinance;
import org.qydata.entity.Partner;
import org.qydata.entity.PartnerIncomeExpenditureLog;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/2/6.
 */
public interface PartnerFinanceSelectMapper {

    /**
     * 查询合作公司的财务总览
     * @param map
     * @return
     * @throws Exception
     */
    public List<PartnerFinance> queryPartnerOverFinance(Map<String, Object> map)throws Exception;

    /**
     * 合作公司的财务总览-周收入总额
     * @param map
     * @return
     * @throws Exception
     */
    public Integer getCountWeekIncomePartnerOverFinance(Map<String, Object> map)throws Exception;

    /**
     * 合作公司的财务总览-周支出总额
     * @param map
     * @return
     * @throws Exception
     */
    public Integer getCountWeekExpenditurePartnerOverFinance(Map<String, Object> map)throws Exception;

    /**
     * 合作公司的财务总览-月收入总额
     * @param map
     * @return
     * @throws Exception
     */
    public Integer getCountMonthIncomePartnerOverFinance(Map<String, Object> map)throws Exception;

    /**
     * 合作公司的财务总览-月支出总额
     * @param map
     * @return
     * @throws Exception
     */
    public Integer getCountMonthExpenditurePartnerOverFinance(Map<String, Object> map)throws Exception;

    /**
     * 合作公司的财务总览-收入总额
     * @param map
     * @return
     * @throws Exception
     */
    public Integer getCountTotleIncomePartnerOverFinance(Map<String, Object> map)throws Exception;

    /**
     * 合作公司的财务总览-支出总额
     * @param map
     * @return
     * @throws Exception
     */
    public Integer getCountTotleExpenditurePartnerOverFinance(Map<String, Object> map)throws Exception;

    /**
     * 合作公司明细
     * @return
     * @throws Exception
     */
    public List<PartnerIncomeExpenditureLog> queryPartnerDetailLog(Map<String, Object> map)throws Exception;

    /**
     * 合作公司明细-金额总计
     * @param map
     * @return
     * @throws Exception
     */
    public Integer getCountPartnerDetailLog(Map<String, Object> map)throws Exception;


    /**
     * 收入支出走势
     * @param map
     * @return
     * @throws Exception
     */
   public List<Partner> queryPartnerBarChart(Map<String, Object> map) throws Exception;
}
