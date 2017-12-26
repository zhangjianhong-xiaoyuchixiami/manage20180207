package org.qydata.service.impl;

import org.qydata.dst.CustomerIncome;
import org.qydata.dst.OperaCondition;
import org.qydata.dst.VendorCost;
import org.qydata.mapper.mapper2.OperaConditionMapper;
import org.qydata.service.OperaConditionService;
import org.qydata.tools.DateUtils;
import org.qydata.tools.PercentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OperaConditionServiceImpl implements OperaConditionService {
    @Autowired
    private OperaConditionMapper operaConditionMapper;

    /**
     *当天经营状况
     * @return
     */
    @Override
    public Map<String, Object> getConsumeConditionAccount() {

        String currTime = DateUtils.currHour();
        String currDawn = DateUtils.currDawn();
        String yesterDawn = DateUtils.yesterDawn();
        String yesterHour = DateUtils.yesterHour();

        Map<String, Object>map1 = new HashMap<String, Object>();
        Map<String, Object>map2 = new HashMap<String, Object>();
        Map<String, Object>map3 = new HashMap<String, Object>();
        map1.put("Dawn", currDawn);
        map1.put("currTime", currTime);
        map2.put("Dawn", yesterDawn);
        map2.put("currTime", yesterHour);
        map3.put("Dawn", yesterDawn);
        map3.put("currTime", currDawn);

        //当天消费
        double currCostAccount = PercentUtils.getRound(getCostAccount(map1));
        //昨天消费
        double yesterCostAccount = PercentUtils.getRound(getCostAccount(map2));
        //当天收入
        double currIncomeAccount = PercentUtils.getRound(getIncomeAccount(map1));
        //昨天收入
        double yesterIncomeAccount = PercentUtils.getRound(getIncomeAccount(map2));
        //昨天全天消费
        double yesterTotalCostAccount = PercentUtils.getRound(getCostAccount(map3));
        //昨天全天收入
        double yesterTotalIncomeAccount = PercentUtils.getRound(getIncomeAccount(map3));

        //今日当前利润
        double currProfit = PercentUtils.getRound(currIncomeAccount - currCostAccount);
        //昨日当前利润
        double yesterProfit = PercentUtils.getRound(yesterIncomeAccount - yesterCostAccount);
        //昨日全天利润
        double yesterTotalProfit = PercentUtils.getRound(yesterTotalIncomeAccount - yesterTotalCostAccount);
        String incomePercent = null;
        if (yesterIncomeAccount != 0.0){
            incomePercent = PercentUtils.getPercent(currIncomeAccount, yesterIncomeAccount);
        }else{
            incomePercent = "昨日同时段无收入";
        }
        String costPercent = null;
        if (yesterCostAccount != 0.0){
            costPercent = PercentUtils.getPercent(currCostAccount, yesterCostAccount);
        }else{
            costPercent = "昨日同时段无支出";
        }

        String profitPercent = null;
        if (yesterProfit != 0.0) {
            profitPercent = PercentUtils.getPercent(currProfit, yesterProfit);
        }else{
            profitPercent = "昨日同时段无利润";
        }

        Map<String, Object>map = new HashMap<String, Object>();
        map.put("currCostAccount", currCostAccount);
        map.put("yesterCostAccount", yesterCostAccount);
        map.put("yesterTotalCostAccount", yesterTotalCostAccount);
        map.put("currIncomeAccount", currIncomeAccount);
        map.put("yesterIncomeAccount", yesterIncomeAccount);
        map.put("yesterTotalIncomeAccount", yesterTotalIncomeAccount);
        map.put("currProfit", currProfit);
        map.put("yesterProfit", yesterProfit);
        map.put("yesterTotalProfit", yesterTotalProfit);
        map.put("incomePercent", incomePercent);
        map.put("costPercent", costPercent);
        map.put("profitPercent", profitPercent);

        return map;
    }

    /**
     * 获取最近两天的经营状况
     * @return
     */
    @Override
    public List<OperaCondition> getApiOperaCondition() {
        String currTime = DateUtils.currHour();
        String currDawn = DateUtils.currDawn();
        String yesterDawn = DateUtils.yesterDawn();
        String yesterHour = DateUtils.yesterHour();

        Map<String, Object>map1 = new HashMap<String, Object>();
        Map<String, Object>map2 = new HashMap<String, Object>();
        map1.put("Dawn", currDawn);
        map1.put("currTime", currTime);
        map2.put("Dawn", yesterDawn);
        map2.put("currTime", yesterHour);
        //今天产品消费状况
        List<OperaCondition> currConditionList = getConditionByType(map1);
        //昨天产品消费状况
        List<OperaCondition> yestConditionList = getConditionByType(map2);

//        List<OperaCondition> OperaConditionList = new ArrayList<OperaCondition>();
        if (currConditionList != null){
            for (OperaCondition operaCondition : currConditionList) {
//                OperaCondition oc = new OperaCondition();
                Integer ati = operaCondition.getApiTypeId();
                Integer sti = operaCondition.getSubTypeId();
                if (operaCondition.getCostAccount() != null){
                    operaCondition.setCurrCostAccount(operaCondition.getCostAccount());
                }else{
                    operaCondition.setCurrCostAccount(0.0);
                }
                if (operaCondition.getIncomeAccount() != null){
                    operaCondition.setCurrIncomeAccount(operaCondition.getIncomeAccount());
                }else{
                    operaCondition.setCurrIncomeAccount(0.0);
                }
                operaCondition.setCurrProfit(operaCondition.getCurrIncomeAccount() - operaCondition.getCurrCostAccount());

                if (yestConditionList != null){
                    for (OperaCondition condition : yestConditionList) {
                        Integer apiTypeId = condition.getApiTypeId();
                        Integer subTypeId = condition.getSubTypeId();
                        if (ati == apiTypeId && sti == subTypeId){
                            if (condition.getCostAccount() != null){
                                operaCondition.setYesterCostAccount(condition.getCostAccount());
                            }else{
                                operaCondition.setYesterCostAccount(0.0);
                            }
                            if (condition.getIncomeAccount() != null){
                                operaCondition.setYesterIncomeAccount(condition.getIncomeAccount());
                            }else{
                                operaCondition.setYesterIncomeAccount(0.0);
                            }
                            operaCondition.setYesterProfit(operaCondition.getYesterIncomeAccount() - operaCondition.getYesterCostAccount());

                            if (operaCondition.getYesterProfit() != 0.0){
                                operaCondition.setProfitPercent(PercentUtils.getPercent(operaCondition.getCurrProfit(), operaCondition.getYesterProfit()));
                            }
                            if (operaCondition.getYesterCostAccount() != 0.0){
                                operaCondition.setCostPercent(PercentUtils.getPercent(operaCondition.getCurrCostAccount(), operaCondition.getYesterCostAccount()));
                                System.out.println(operaCondition.getCostPercent());
                            }
                            if (operaCondition.getYesterIncomeAccount() != 0.0){
                                operaCondition.setIncomePercent(PercentUtils.getPercent(operaCondition.getCurrIncomeAccount(), operaCondition.getYesterIncomeAccount()));
                            }

                        }
                    }
                }else{
                    operaCondition.setYesterIncomeAccount(0.0);
                    operaCondition.setYesterCostAccount(0.0);
                    operaCondition.setYesterProfit(0.0);
                }
            }
            return  currConditionList;
        }else{

            if (yestConditionList != null){
                for (OperaCondition condition : yestConditionList) {
                    condition.setCurrProfit(0.0);
                    condition.setCurrIncomeAccount(0.0);
                    condition.setCurrCostAccount(0.0);
                    if (condition.getCostAccount() != null){
                        condition.setYesterCostAccount(condition.getCostAccount());
                    }else{
                        condition.setYesterCostAccount(0.0);
                    }
                    if (condition.getIncomeAccount() != null){
                        condition.setYesterIncomeAccount(condition.getIncomeAccount());
                    }else{
                        condition.setYesterIncomeAccount(0.0);
                    }
                    condition.setYesterProfit(condition.getYesterIncomeAccount() - condition.getYesterCostAccount());
                    if (condition.getYesterProfit() != 0.0){
                        condition.setProfitPercent(PercentUtils.getPercent(condition.getCurrProfit(), condition.getYesterProfit()));
                    }
                    if (condition.getYesterCostAccount() != 0.0){
                        condition.setCostPercent(PercentUtils.getPercent(condition.getCurrCostAccount(), condition.getYesterCostAccount()));
                    }
                    if (condition.getYesterIncomeAccount() != 0.0){
                        condition.setIncomePercent(PercentUtils.getPercent(condition.getCurrIncomeAccount(), condition.getYesterIncomeAccount()));
                    }

                }
                return yestConditionList;
            }else{
                return null;
            }
        }
    }


    /**
     * 获取此时的收入
     * @return
     */
    @Override
    public List<CustomerIncome> getCurrCustomerIncomeCondition(Map<String, Object>map) {
        String currTime = DateUtils.currHour();
        String currDawn = DateUtils.currDawn();
        map.put("Dawn", currDawn);
        map.put("currTime", currTime);
        List<CustomerIncome> customerIncomeCondition = operaConditionMapper.getCustomerIncomeCondition(map);
        if (customerIncomeCondition != null && !customerIncomeCondition.contains(null)){
            for (CustomerIncome customerIncome : customerIncomeCondition) {
                if (customerIncome.getPrice() != null){
                    customerIncome.setPrice(customerIncome.getPrice()/100);
                }
                if (customerIncome.getAmount() != null){
                    customerIncome.setAmount(customerIncome.getAmount()/100);
                }
            }
        }else{
            return new ArrayList<>();
        }
        return customerIncomeCondition;
    }

    /**
     * 获取昨天此时的收入
     * @return
     */
    @Override
    public List<CustomerIncome> getYesterCustomerIncomeCondition(Map<String, Object>map) {
        String yesterDawn = DateUtils.yesterDawn();
        String yesterHour = DateUtils.yesterHour();
        map.put("Dawn", yesterDawn);
        map.put("currTime", yesterHour);
        List<CustomerIncome> customerIncomeCondition = operaConditionMapper.getCustomerIncomeCondition(map);
        if (customerIncomeCondition != null && !customerIncomeCondition.contains(null)){
            for (CustomerIncome customerIncome : customerIncomeCondition) {
                if (customerIncome.getPrice() != null){
                    customerIncome.setPrice(customerIncome.getPrice()/100);
                }
                if (customerIncome.getAmount() != null){
                    customerIncome.setAmount(customerIncome.getAmount()/100);
                }
            }
        }else{
            return new ArrayList<>();
        }
        return customerIncomeCondition;

    }

    /**
     * 昨天支出
     * @param map
     * @return
     */
    @Override
    public List<VendorCost> getYesterVendorCostCondition(Map<String, Object> map) {
        String yesterDawn = DateUtils.yesterDawn();
        String yesterHour = DateUtils.yesterHour();
        map.put("Dawn", yesterDawn);
        map.put("currTime", yesterHour);
        List<VendorCost> vendorCostCondition = operaConditionMapper.getVendorCostCondition(map);
        if (vendorCostCondition != null && !vendorCostCondition.contains(null)){
            for (VendorCost vendorCost : vendorCostCondition) {
                if (vendorCost.getCost() != null){
                    vendorCost.setCost(vendorCost.getCost()/100);
                }
                if (vendorCost.getAmount() != null){
                    vendorCost.setAmount(vendorCost.getAmount()/100);
                }
            }
        }else{
            return new ArrayList<>();
        }
        return vendorCostCondition;
    }

    /**
     * 今天支出
     * @param map
     * @return
     */
    @Override
    public List<VendorCost> getCurrVendorCostCondition(Map<String, Object> map) {
        String currTime = DateUtils.currHour();
        String currDawn = DateUtils.currDawn();
        map.put("Dawn", currDawn);
        map.put("currTime", currTime);
        List<VendorCost> vendorCostCondition = operaConditionMapper.getVendorCostCondition(map);
        if (vendorCostCondition != null && !vendorCostCondition.contains(null)){
            for (VendorCost vendorCost : vendorCostCondition) {
                if (vendorCost.getCost() != null){
                    vendorCost.setCost(vendorCost.getCost()/100);
                }
                if (vendorCost.getAmount() != null){
                    vendorCost.setAmount(vendorCost.getAmount()/100);
                }
            }
        }else{
            return new ArrayList<>();
        }
        return vendorCostCondition;
    }


    /**
     * 收入
     * @param map
     * @return
     */
    public double getIncomeAccount(Map<String, Object> map) {
        List<OperaCondition> yesterIncomeAccountList = operaConditionMapper.getIncomeAccount(map);
        double amount = 0.0;
        if (yesterIncomeAccountList != null){

            for (OperaCondition operaCondition : yesterIncomeAccountList) {
                Double mount = operaCondition.getIncomeAccount()/100;
                amount += mount;
            }
            return amount;
        }
        return amount;
    }

    /**
     * 消费
     * @param map
     * @return
     */
    public double getCostAccount(Map<String, Object> map) {
        List<OperaCondition> currCostAccountList = operaConditionMapper.getCostAccount(map);
        double amount = 0.0;
        if (currCostAccountList != null){

            for (OperaCondition operaCondition : currCostAccountList) {
                Double mount = operaCondition.getCostAccount()/100;
                amount += mount;
            }
            return amount;
        }
        return amount;
    }

    /**
     * 成本和收入
     * @param map
     * @return
     */
    public List<OperaCondition> getConditionByType(Map<String, Object>map){
        String currTime = DateUtils.currHour();
        String yesterDawn = DateUtils.yesterDawn();
        Map<String, Object>map3 = new HashMap<String, Object>();
        map3.put("Dawn", yesterDawn);
        map3.put("currTime", currTime);
        //获取最近两天所有消费的类别
        List<OperaCondition> apiTypeList = operaConditionMapper.getApiType(map3);
        //按类别获取收入和成本
        List<OperaCondition> costAccountByTypeList = operaConditionMapper.getCostAccountByType(map);
        List<OperaCondition> incomeAccountByTypeList = operaConditionMapper.getIncomeAccountByType(map);
//        List<OperaCondition>operaConditionList = new ArrayList<OperaCondition>();

        if (apiTypeList != null){
            for (OperaCondition operaCondition : apiTypeList) {

                Integer ati = operaCondition.getApiTypeId();
                Integer sti = operaCondition.getSubTypeId();

                if (incomeAccountByTypeList != null){
                    for (OperaCondition ioc : incomeAccountByTypeList) {
                        if (ati == ioc.getApiTypeId() && sti == ioc.getSubTypeId()){
                            if (ioc.getIncomeAccount() != null){
                                operaCondition.setIncomeAccount(ioc.getIncomeAccount()/100.0);
                            }
                        }
                    }
                }

                if (costAccountByTypeList != null){
                    for (OperaCondition ooc : costAccountByTypeList) {
                        if (ati == ooc.getApiTypeId() && sti == ooc.getSubTypeId()) {
                            if (ooc.getCostAccount() != null) {
                                operaCondition.setCostAccount(ooc.getCostAccount() / 100);
                            }
                        }
                    }
                }
            }
            return apiTypeList;
        }else{
            return null;
        }
    }


}
