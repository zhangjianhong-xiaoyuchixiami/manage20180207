package org.qydata.service.impl;

import org.qydata.entity.agency.AgencyBill;
import org.qydata.mapper.AgencyMapper;
import org.qydata.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/9/14.
 */
@Service
public class AgencyServiceImpl implements AgencyService {

    @Autowired
    private AgencyMapper agencyMapper;

    @Override
    public Map<String, Object> queryAgencyBill(Map<String, Object> map) {
        map.put("noIncludeCache",-1);
        List<AgencyBill> billList = agencyMapper.queryAgencyBill(map);
        if (billList == null){
            return null;
        }
        Double netProfitTot = 0.0;
        for (AgencyBill bill : billList) {
            if (bill.getTaxRate() != null){
                bill.setTaxRateResult(bill.getTaxRate()+"%");
            }
            bill.setCostMoney((bill.getCost()/100.0) * bill.getResultCount());
            bill.setPriceMoney((bill.getPrice()/100.0) * bill.getResultCount());
            bill.setGrossProfit(bill.getPriceMoney() - bill.getCostMoney());
            if (bill.getRebateEndPrice() != null){
                bill.setFirstRebate(((bill.getPrice() - bill.getRebateEndPrice())/100.0) * bill.getResultCount());
            }
            if (bill.getFirstRebate() != null){
                bill.setTwiceRebate((bill.getGrossProfit() - bill.getFirstRebate())/2.0);
            }else {
                bill.setTwiceRebate((((bill.getPrice() - bill.getCostMoney())/100.0) * bill.getResultCount())/2.0 );
            }
            bill.setNetProfit(bill.getTwiceRebate());
            netProfitTot += bill.getNetProfit();
        }
        Map<String,Object> resu = new HashMap<>();
        resu.put("billList",billList);
        resu.put("netProfitTot",netProfitTot);
        return resu;
    }
}
