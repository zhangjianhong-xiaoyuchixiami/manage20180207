package org.qydata.mapper;

import org.qydata.dst.PartnerFinance;
import org.qydata.entity.Partner;
import org.qydata.entity.PartnerIncomeExpenditureLog;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/2/6.
 */
public interface PartnerFinanceMapper {

    /**
     * 查询合作公司的财务总览
     * @param map
     * @return
     * @throws Exception
     */
    public List<PartnerFinance> queryPartnerOverFinance(Map<String,Object> map)throws Exception;

    /**
     * 付款和收款
     * @param partnerIncomeExpenditureLog
     * @return
     * @throws Exception
     */
    public boolean addPartnerIncomeExpenditureLog(PartnerIncomeExpenditureLog partnerIncomeExpenditureLog)throws Exception;

    /**
     * 新增合作公司
     * @param partnerName
     * @return
     */
    public boolean addPartner(String partnerName)throws Exception;

    /**
     * 合作公司明细
     * @return
     * @throws Exception
     */
    public List<PartnerIncomeExpenditureLog> queryPartnerDetailLog(Map<String,Object> map)throws Exception;

    /**
     * 收入支出走势
     * @param map
     * @return
     * @throws Exception
     */
   public List<Partner> queryPartnerBarChart(Map<String,Object> map) throws Exception;
}
