package org.qydata.service.impl;

import org.qydata.dst.OperaCondition;
import org.qydata.mapper.mapper2.OperaConditionMapper;
import org.qydata.service.OperaConditionService;
import org.qydata.tools.DateUtils;
import org.qydata.tools.PercentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        String incomePercent = PercentUtils.getPercent(currIncomeAccount, yesterIncomeAccount);
        String costPercent = PercentUtils.getPercent(currCostAccount, yesterCostAccount);
        String profitPercent = PercentUtils.getPercent(currProfit, yesterProfit);

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
}
