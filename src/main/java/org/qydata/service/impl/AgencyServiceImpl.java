package org.qydata.service.impl;

import org.qydata.entity.agency.AgencyBill;
import org.qydata.entity.agency.AgencyBillDetail;
import org.qydata.entity.agency.AgencyCustomer;
import org.qydata.entity.agency.RebateAgency;
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
        Map<String,Object> param = new HashMap<>();
        boolean rate = false;
        if (map != null){
            for (Map.Entry<String,Object> me : map.entrySet()) {
                if (me.getKey().equals("rate")){
                    rate = true;
                }
                if (me.getKey().equals("cyc")){
                    param.put("cycList",me.getValue());
                }
                if (me.getKey().equals("agency")){
                    param.put("agency",me.getValue());
                }
                if (me.getKey().equals("cid")){
                    param.put("cidList",me.getValue());
                }
            }
        }
        map.put("noIncludeCache",-1);
        List<AgencyBill> billList = agencyMapper.queryAgencyBill(param);
        if (billList == null){
            return null;
        }
        Double netProfitTot = 0.0;
        for (AgencyBill bill : billList) {
            if (bill.getTaxRate() != null){
                bill.setTaxRateResult(bill.getTaxRate()+"%");
            }
            if (bill.getPriceMoney() != null){
                bill.setPriceMoney(bill.getPriceMoney()/100.0);
            }
            if (bill.getCostMoney() != null){
                bill.setCostMoney(bill.getCostMoney()/100.0);
            }
            if (bill.getClearingMoney() != null){
                bill.setClearingMoney(bill.getClearingMoney()/100.0);
            }
            if (bill.getPriceMoney() != null && bill.getCostMoney() != null){
                bill.setGrossProfit(bill.getPriceMoney() - bill.getCostMoney());
            }
            if (bill.getPriceMoney() != null && bill.getCostMoney() == null){
                bill.setGrossProfit(bill.getPriceMoney());
            }
            if (bill.getPriceMoney() == null && bill.getCostMoney() != null){
                bill.setGrossProfit(-bill.getCostMoney());
            }

            if (bill.getPriceMoney() != null && bill.getClearingMoney() != null){
                bill.setFirstRebate(bill.getPriceMoney() - bill.getClearingMoney());
            }
            if (rate){
                if (bill.getFirstRebate() != null && bill.getTaxRate() != null){
                    bill.setFirstRebate(bill.getFirstRebate() * (1 - (bill.getTaxRate()/100.0)));
                }
            }
            if (bill.getFirstRebate() != null){
                bill.setTwiceRebate((bill.getGrossProfit() - bill.getFirstRebate())/2.0);
            }else {
                bill.setTwiceRebate(bill.getGrossProfit()/2.0 );
            }
            bill.setNetProfit(bill.getTwiceRebate());
            netProfitTot += bill.getNetProfit();
        }
        Map<String,Object> resu = new HashMap<>();
        resu.put("billList",billList);
        resu.put("netProfitTot",netProfitTot);
        return resu;
    }

    @Override
    public List<RebateAgency> queryRebateAgency() {
        return agencyMapper.queryRebateAgency();
    }

    @Override
    public List<AgencyCustomer> queryAgencyCustomer(Map<String,Object> map) {
        List<AgencyCustomer> list = agencyMapper.queryAgencyCustomer(map);
        if (list == null){
            return null;
        }
        for (AgencyCustomer customer : list) {
            if (customer.getCompanyName() != null && customer.getAgencyName() != null){
                customer.setCompanyName(customer.getCompanyName() + "@" + customer.getAgencyName());
            }
        }
        return list;
    }

    @Override
    public List<String> queryConsumeCycle() {
        return agencyMapper.queryConsumeCycle();
    }

    @Override
    public Map<String, Object> queryAgencyBillDetail(Map<String, Object> map) {
        List<AgencyBillDetail> detailList = agencyMapper.queryAgencyBillDetail(map);
        if (detailList == null){
            return null;
        }
        for (AgencyBillDetail detail : detailList) {
            if (detail.getCost() != null){
                detail.setCost(detail.getCost()/100.0);
                detail.setTypeName("上游");
                detail.setCostMoney(detail.getCost() * detail.getResultCount());
            }else {
                detail.setTypeName("缓存");
                detail.setCostMoney(0.0);
            }
            if (detail.getPrice() != null){
                detail.setPrice(detail.getPrice()/100.0);
                detail.setPriceMoney(detail.getPrice() * detail.getResultCount());
                detail.setGrossProfit(detail.getPriceMoney() - detail.getCostMoney());
            }
            if (detail.getRebateBegPrice() != null){
                detail.setRebateBegPrice(detail.getRebateBegPrice()/100.0);
            }
            if (detail.getRebateEndPrice() != null){
                detail.setRebateEndPrice(detail.getRebateEndPrice()/100.0);
                detail.setFirstRebate(detail.getPriceMoney() - (detail.getRebateEndPrice() * detail.getResultCount()));
            }
            if (detail.getFirstRebate() != null){
                detail.setTwiceRebate((detail.getGrossProfit() - detail.getFirstRebate())/2.0);
                detail.setNetProfit((detail.getGrossProfit() - detail.getFirstRebate())/2.0);
            }else {
                detail.setTwiceRebate(detail.getGrossProfit()/2.0);
                detail.setNetProfit(detail.getGrossProfit()/2.0);
            }
        }
        Map<String,Object> resu = new HashMap<>();
        resu.put("detailList",detailList);
        return resu;
    }

    @Override
    public boolean updateRebateBillAmount(Integer id, Integer amount) {
        return false;
    }
}
