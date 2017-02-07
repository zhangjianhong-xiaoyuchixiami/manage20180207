package org.qydata.service;

import org.qydata.dst.PartnerFinance;
import org.qydata.entity.Partner;
import org.qydata.entity.PartnerIncomeExpenditureLog;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/2/7.
 */
public interface PartnerFinanceService {

    /**
     * 查询合作公司的财务总览
     * @param map
     * @return
     * @throws Exception
     */
    public List<PartnerFinance> queryPartnerOverFinance(Map<String,Object> map);

    /**
     * 付款和收款
     * @param partnerId
     * @param amount
     * @param remark
     * @param date
     * @param reasonId
     * @return
     */
    @Transactional
    public boolean addPartnerIncomeExpenditureLog(Integer partnerId,String amount,String remark,String date,Integer reasonId);

    /**
     * 新增合作公司
     * @param partnerName
     * @return
     */
    public boolean addPartner(String partnerName);

    /**
     * 合作公司明细
     * @param map
     * @return
     * @throws Exception
     */
    public List<PartnerIncomeExpenditureLog> queryPartnerDetailLog(Map<String,Object> map);

    /**
     * 收入支出走势
     * @param map
     * @return
     * @throws Exception
     */
    public List<Partner> queryPartnerBarChart(Map<String,Object> map);
}
