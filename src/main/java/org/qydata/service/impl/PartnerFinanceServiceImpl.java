package org.qydata.service.impl;

import org.qydata.config.annotation.DataSourceService;
import org.qydata.entity.Partner;
import org.qydata.entity.PartnerIncomeExpenditureLog;
import org.qydata.mapper.PartnerFinanceMapper;
import org.qydata.service.PartnerFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/2/7.
 */
@Service
public class PartnerFinanceServiceImpl implements PartnerFinanceService {

    @Autowired private PartnerFinanceMapper partnerFinanceMapper;

    @Override
    @DataSourceService
    public Map<String,Object> queryPartnerOverFinance(Map<String, Object> map) {
        Map<String,Object> mapTran = new HashMap<>();
        try {
            mapTran.put("queryPartnerOverFinance",partnerFinanceMapper.queryPartnerOverFinance(map));
            mapTran.put("getCountWeekIncomePartnerOverFinance",partnerFinanceMapper.getCountWeekIncomePartnerOverFinance(map));
            mapTran.put("getCountWeekExpenditurePartnerOverFinance",partnerFinanceMapper.getCountWeekExpenditurePartnerOverFinance(map));
            mapTran.put("getCountMonthIncomePartnerOverFinance",partnerFinanceMapper.getCountMonthIncomePartnerOverFinance(map));
            mapTran.put("getCountMonthExpenditurePartnerOverFinance",partnerFinanceMapper.getCountMonthExpenditurePartnerOverFinance(map));
            mapTran.put("getCountTotleIncomePartnerOverFinance",partnerFinanceMapper.getCountTotleIncomePartnerOverFinance(map));
            mapTran.put("getCountTotleExpenditurePartnerOverFinance",partnerFinanceMapper.getCountTotleExpenditurePartnerOverFinance(map));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapTran;
    }

    @Override
    @DataSourceService
    public boolean addPartnerIncomeExpenditureLog(Integer partnerId,String amount,String remark,String date,Integer reasonId) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            PartnerIncomeExpenditureLog partnerIncomeExpenditureLog = new PartnerIncomeExpenditureLog();
            partnerIncomeExpenditureLog.setPartnerId(partnerId);
            partnerIncomeExpenditureLog.setAmount(Long.parseLong(amount)*100);
            partnerIncomeExpenditureLog.setReasonId(reasonId);
            if (remark != "" || remark != null){
                partnerIncomeExpenditureLog.setRemark(remark);
            }
            if (date == "" || date == null){
                partnerIncomeExpenditureLog.setCreateTime(new Date());
            }else {
                partnerIncomeExpenditureLog.setCreateTime(sdf.parse(date));
            }
            return partnerFinanceMapper.addPartnerIncomeExpenditureLog(partnerIncomeExpenditureLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @DataSourceService
    public boolean addPartner(String partnerName) {
        try {
            return partnerFinanceMapper.addPartner(partnerName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @DataSourceService
    public Map<String,Object> queryPartnerDetailLog(Map<String, Object> map) {
        Map<String,Object> mapTran = new HashMap<>();
        try {
            mapTran.put("queryPartnerDetailLog",partnerFinanceMapper.queryPartnerDetailLog(map));
            mapTran.put("getCountPartnerDetailLog",partnerFinanceMapper.getCountPartnerDetailLog(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapTran;
    }

    @Override
    @DataSourceService
    public List<Partner> queryPartnerBarChart(Map<String, Object> map) {
        try {
            return partnerFinanceMapper.queryPartnerBarChart(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
