package org.qydata.service.impl;

import org.qydata.entity.Partner;
import org.qydata.entity.PartnerIncomeExpenditureLog;
import org.qydata.mapper.mapper1.PartnerFinanceMapper;
import org.qydata.mapper.mapper2.PartnerFinanceSelectMapper;
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

    @Autowired 
    private PartnerFinanceMapper partnerFinanceMapper;
    @Autowired
    private PartnerFinanceSelectMapper partnerFinanceSelectMapper;

    @Override
    public Map<String,Object> queryPartnerOverFinance(Map<String, Object> map) {
        Map<String,Object> mapTran = new HashMap<>();
        try {
            mapTran.put("queryPartnerOverFinance",partnerFinanceSelectMapper.queryPartnerOverFinance(map));
            mapTran.put("getCountWeekIncomePartnerOverFinance",partnerFinanceSelectMapper.getCountWeekIncomePartnerOverFinance(map));
            mapTran.put("getCountWeekExpenditurePartnerOverFinance",partnerFinanceSelectMapper.getCountWeekExpenditurePartnerOverFinance(map));
            mapTran.put("getCountMonthIncomePartnerOverFinance",partnerFinanceSelectMapper.getCountMonthIncomePartnerOverFinance(map));
            mapTran.put("getCountMonthExpenditurePartnerOverFinance",partnerFinanceSelectMapper.getCountMonthExpenditurePartnerOverFinance(map));
            mapTran.put("getCountTotleIncomePartnerOverFinance",partnerFinanceSelectMapper.getCountTotleIncomePartnerOverFinance(map));
            mapTran.put("getCountTotleExpenditurePartnerOverFinance",partnerFinanceSelectMapper.getCountTotleExpenditurePartnerOverFinance(map));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapTran;
    }

    @Override
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
    public boolean addPartner(String partnerName) {
        try {
            return partnerFinanceMapper.addPartner(partnerName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Map<String,Object> queryPartnerDetailLog(Map<String, Object> map) {
        Map<String,Object> mapTran = new HashMap<>();
        try {
            mapTran.put("queryPartnerDetailLog",partnerFinanceSelectMapper.queryPartnerDetailLog(map));
            mapTran.put("getCountPartnerDetailLog",partnerFinanceSelectMapper.getCountPartnerDetailLog(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapTran;
    }

    @Override
    public List<Partner> queryPartnerBarChart(Map<String, Object> map) {
        try {
            return partnerFinanceSelectMapper.queryPartnerBarChart(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
