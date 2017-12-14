package org.qydata.mapper.mapper1;

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

}
