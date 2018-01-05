package org.qydata.service.impl;

import net.sf.json.JSONArray;
import org.qydata.dst.*;
import org.qydata.dst.customer.CustomerConsume;
import org.qydata.mapper.mapper2.OperaConditionMapper;
import org.qydata.service.OperaConditionService;
import org.qydata.tools.CalendarTools;
import org.qydata.tools.DateUtils;
import org.qydata.tools.PercentUtils;
import org.qydata.tools.chartdate.ChartCalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OperaConditionServiceImpl implements OperaConditionService {
    @Autowired
    private OperaConditionMapper operaConditionMapper;

    /**
     * 当天经营状况
     *
     * @return
     */
    @Override
    public Map<String, Object> getConsumeConditionAccount() {

        String currTime = DateUtils.currHour();
        String currDawn = DateUtils.currDawn();
        String yesterDawn = DateUtils.yesterDawn();
        String yesterHour = DateUtils.yesterHour();

        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        Map<String, Object> map3 = new HashMap<String, Object>();
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
        if (yesterIncomeAccount != 0.0) {
            incomePercent = PercentUtils.getPercent(currIncomeAccount, yesterIncomeAccount);
        } else {
            incomePercent = "昨日同时段无收入";
        }
        String costPercent = null;
        if (yesterCostAccount != 0.0) {
            costPercent = PercentUtils.getPercent(currCostAccount, yesterCostAccount);
        } else {
            costPercent = "昨日同时段无支出";
        }

        String profitPercent = null;
        if (yesterProfit != 0.0) {
            profitPercent = PercentUtils.getPercent(currProfit, yesterProfit);
        } else {
            profitPercent = "昨日同时段无利润";
        }

        Map<String, Object> map = new HashMap<String, Object>();
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
     *
     * @return
     */
    @Override
    public List<OperaCondition> getApiOperaCondition() {
        String currTime = DateUtils.currHour();
        String currDawn = DateUtils.currDawn();
        String yesterDawn = DateUtils.yesterDawn();
        String yesterHour = DateUtils.yesterHour();

        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        Map<String, Object> map3 = new HashMap<String, Object>();
        map1.put("Dawn", currDawn);
        map1.put("currTime", currTime);
        map2.put("Dawn", yesterDawn);
        map2.put("currTime", yesterHour);
        map3.put("Dawn", yesterDawn);
        map3.put("currTime", currDawn);
        //今天产品消费状况
        List<OperaCondition> currConditionList = getConditionByType(map1);
        //昨天产品消费状况
        List<OperaCondition> yestConditionList = getConditionByType(map2);
        //昨天全天的消费情况
        List<OperaCondition> yesterdayConditionList = getConditionByType(map3);
        //今天
        if (currConditionList != null) {
            for (OperaCondition operaCondition : currConditionList) {
                Integer ati = operaCondition.getApiTypeId();
                Integer sti = operaCondition.getSubTypeId();
                //成本
                if (operaCondition.getCostAccount() != null) {
                    operaCondition.setCurrCostAccount(operaCondition.getCostAccount());
                } else {
                    operaCondition.setCurrCostAccount(0.0);
                }
                //收入
                if (operaCondition.getIncomeAccount() != null) {
                    operaCondition.setCurrIncomeAccount(operaCondition.getIncomeAccount());
                } else {
                    operaCondition.setCurrIncomeAccount(0.0);
                }
                operaCondition.setCurrProfit(operaCondition.getCurrIncomeAccount() - operaCondition.getCurrCostAccount());
                //请求上游的数量
                if (operaCondition.getCostCount() != null) {
                    operaCondition.setCurrCostCount(operaCondition.getCostCount());
                } else {
                    operaCondition.setCurrCostCount(0);
                }
                //下游请求的数量
                if (operaCondition.getIncomeCount() != null) {
                    operaCondition.setCurrIncomeCount(operaCondition.getIncomeCount());
                } else {
                    operaCondition.setCurrIncomeCount(0);
                }

                //昨天全天
                if (yesterdayConditionList != null) {
                    for (OperaCondition condition : yesterdayConditionList) {
                        if (ati == condition.getApiTypeId() && sti == condition.getSubTypeId()) {
                            //成本
                            if (condition.getCostAccount() != null) {
                                operaCondition.setYesterTotalCostAccount(condition.getCostAccount());
                            } else {
                                operaCondition.setYesterTotalCostAccount(0.0);
                            }

                            //收入
                            if (condition.getIncomeAccount() != null) {
                                operaCondition.setYesterTotalIncomeAccount(condition.getIncomeAccount());
                            } else {
                                operaCondition.setYesterTotalIncomeAccount(0.0);
                            }
                            operaCondition.setYesterTotalProfit(operaCondition.getYesterTotalIncomeAccount() - operaCondition.getYesterTotalCostAccount());
                            //请求上有的数量
                            if (condition.getCostCount() != null) {
                                operaCondition.setYesterdayCostCount(condition.getCostCount());
                            } else {
                                operaCondition.setYesterdayCostCount(0);
                            }

                            //下游请求的数量
                            if (condition.getIncomeCount() != null) {
                                operaCondition.setYesterdayIncomeCount(condition.getIncomeCount());
                            } else {
                                operaCondition.setYesterdayIncomeCount(0);
                            }
                        }
                    }
                }

                //昨天
                if (yestConditionList != null) {
                    for (OperaCondition yestcondition : yestConditionList) {
                        if (ati == yestcondition.getApiTypeId() && sti == yestcondition.getSubTypeId()) {
                            //成本
                            if (yestcondition.getCostAccount() != null) {
                                operaCondition.setYesterCostAccount(yestcondition.getCostAccount());
                            } else {
                                operaCondition.setYesterCostAccount(0.0);
                            }
                            //收入
                            if (yestcondition.getIncomeAccount() != null) {
                                operaCondition.setYesterIncomeAccount(yestcondition.getIncomeAccount());
                            } else {
                                operaCondition.setYesterIncomeAccount(0.0);
                            }
                            operaCondition.setYesterProfit(operaCondition.getYesterIncomeAccount() - operaCondition.getYesterCostAccount());

                            //成本同比
                            if (operaCondition.getYesterCostAccount() != 0.0) {
                                operaCondition.setCostPercent(PercentUtils.getPercent(operaCondition.getCurrCostAccount(), operaCondition.getYesterCostAccount()));
                            }

                            //收入同比
                            if (operaCondition.getYesterIncomeAccount() != 0.0) {
                                operaCondition.setIncomePercent(PercentUtils.getPercent(operaCondition.getCurrIncomeAccount(), operaCondition.getYesterIncomeAccount()));
                            }

                            //利润同比
                            if (operaCondition.getYesterProfit() != 0.0) {
                                operaCondition.setProfitPercent(PercentUtils.getPercent(operaCondition.getCurrProfit(), operaCondition.getYesterProfit()));
                            }

                            //请求上游的数量
                            if (yestcondition.getCostCount() != null) {
                                operaCondition.setYestCostCount(yestcondition.getCostCount());
                            } else {
                                operaCondition.setYestCostCount(0);
                            }

                            //下游请求的数量
                            if (yestcondition.getIncomeCount() != null) {
                                operaCondition.setYestIncomeCount(yestcondition.getIncomeCount());
                            } else {
                                operaCondition.setYestIncomeCount(0);
                            }
                        }
                    }
                }
            }

        }
        return currConditionList;
    }


    /**
     * 获取今天此时的收入
     *
     * @return
     */
    @Override
    public List<CustomerIncome> getCurrCustomerIncomeCondition(Map<String, Object> map) {
        List<CustomerIncome> customerIncomeCondition = getCustomerIncomeCondition(map);

        return customerIncomeCondition;
    }

    /**
     * 获取昨天的收入
     *
     * @return
     */
    @Override
    public List<CustomerIncome> getYesterCustomerIncomeCondition(Map<String, Object> map) {
        List<CustomerIncome> customerIncomeCondition = getCustomerIncomeCondition(map);
        return customerIncomeCondition;

    }

    /**
     * 昨天同时段收入
     *
     * @param map
     * @return
     */
    @Override
    public List<CustomerIncome> getYesterHourCustomerIncomeCondition(Map<String, Object> map) {
        List<CustomerIncome> customerIncomeCondition = getCustomerIncomeCondition(map);
        return customerIncomeCondition;
    }

    /**
     * 昨天支出
     *
     * @param map
     * @return
     */
    @Override
    public List<VendorCost> getYesterVendorCostCondition(Map<String, Object> map) {
        List<VendorCost> vendorCostCondition = getVendorCostCondition(map);
        return vendorCostCondition;
    }


    /**
     * 昨天同时段的支出
     *
     * @param map
     * @return
     */
    @Override
    public List<VendorCost> getYesterHourVendorCostCondition(Map<String, Object> map) {

        List<VendorCost> vendorCostCondition = getVendorCostCondition(map);
        return vendorCostCondition;
    }

    /**
     * 今天支出
     *
     * @param map
     * @return
     */
    @Override
    public List<VendorCost> getCurrVendorCostCondition(Map<String, Object> map) {

        List<VendorCost> vendorCostCondition = getVendorCostCondition(map);
        return vendorCostCondition;
    }


    /**
     * 获取今天凌晨到现在前一小时的收入
     *
     * @param map
     * @return
     */
    public double getIncomeAccount(Map<String, Object> map) {
        List<OperaCondition> yesterIncomeAccountList = operaConditionMapper.getIncomeAccount(map);
        double amount = 0.0;
        if (yesterIncomeAccountList != null) {

            for (OperaCondition operaCondition : yesterIncomeAccountList) {
                Double mount = operaCondition.getIncomeAccount() / 100;
                amount += mount;
            }
            return amount;
        }
        return amount;
    }

    /**
     * 获取今天凌晨到当前前一小时的成本
     *
     * @param map
     * @return
     */
    public double getCostAccount(Map<String, Object> map) {
        List<OperaCondition> currCostAccountList = operaConditionMapper.getCostAccount(map);
        double amount = 0.0;
        if (currCostAccountList != null) {

            for (OperaCondition operaCondition : currCostAccountList) {
                Double mount = operaCondition.getCostAccount() / 100;
                amount += mount;
            }
            return amount;
        }
        return amount;
    }

    /**
     * 按时间和产品获取成本
     *
     * @param map
     * @return
     */
    public List<VendorCost> getVendorCostCondition(Map<String, Object> map) {
        List<VendorCost> vendorCostCondition = operaConditionMapper.getVendorCostCondition(map);

        if (vendorCostCondition != null && !vendorCostCondition.contains(null)) {
            for (VendorCost vendorCost : vendorCostCondition) {
                if (vendorCost.getCost() != null) {
                    vendorCost.setCost(vendorCost.getCost() / 100);
                }
                if (vendorCost.getAmount() != null) {
                    vendorCost.setAmount(vendorCost.getAmount() / 100);
                }
            }
        } else {
            return new ArrayList<>();
        }
        return vendorCostCondition;
    }

    /**
     * 按时间产品获取收入
     *
     * @param map
     * @return
     */
    public List<CustomerIncome> getCustomerIncomeCondition(Map<String, Object> map) {
        List<CustomerIncome> customerIncomeCondition = operaConditionMapper.getCustomerIncomeCondition(map);

        if (customerIncomeCondition != null && !customerIncomeCondition.contains(null)) {
            for (CustomerIncome customerIncome : customerIncomeCondition) {
                if (customerIncome.getPrice() != null) {
                    customerIncome.setPrice(customerIncome.getPrice() / 100);
                }
                if (customerIncome.getAmount() != null) {
                    customerIncome.setAmount(customerIncome.getAmount() / 100);
                }
            }
        } else {
            return new ArrayList<>();
        }
        return customerIncomeCondition;
    }

    /**
     * 成本和收入
     *
     * @param map
     * @return
     */
    public List<OperaCondition> getConditionByType(Map<String, Object> map) {
        String currTime = DateUtils.currHour();
        String yesterDawn = DateUtils.yesterDawn();
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("Dawn", yesterDawn);
        map3.put("currTime", currTime);
        //获取最近两天所有消费的类别
        List<OperaCondition> apiTypeList = operaConditionMapper.getApiType(map3);
        //按类别获取收入和成本
        List<OperaCondition> costAccountByTypeList = operaConditionMapper.getCostAccountByType(map);
        List<OperaCondition> incomeAccountByTypeList = operaConditionMapper.getIncomeAccountByType(map);
//        List<OperaCondition>operaConditionList = new ArrayList<OperaCondition>();

        if (apiTypeList != null) {
            for (OperaCondition operaCondition : apiTypeList) {

                Integer ati = operaCondition.getApiTypeId();
                Integer sti = operaCondition.getSubTypeId();

                if (incomeAccountByTypeList != null) {
                    for (OperaCondition ioc : incomeAccountByTypeList) {
                        if (ati == ioc.getApiTypeId() && sti == ioc.getSubTypeId()) {
                            if (ioc.getIncomeAccount() != null) {
                                operaCondition.setIncomeAccount(ioc.getIncomeAccount() / 100.0);
                            }
                            if (ioc.getIncomeCount() != null) {
                                operaCondition.setIncomeCount(ioc.getIncomeCount());
                            }
                        }
                    }
                }

                if (costAccountByTypeList != null) {
                    for (OperaCondition ooc : costAccountByTypeList) {
                        if (ati == ooc.getApiTypeId() && sti == ooc.getSubTypeId()) {
                            if (ooc.getCostAccount() != null) {
                                operaCondition.setCostAccount(ooc.getCostAccount() / 100);
                            }

                            if (ooc.getCostCount() != null) {
                                operaCondition.setCostCount(ooc.getCostCount());
                            }
                        }
                    }
                }
            }
            return apiTypeList;
        } else {
            return null;
        }
    }

    /**
     * 近期经营走势折线图
     * @return
     */
    @Override
    public Map<String, Object> queryOperationNearlyWeekTrend() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<NearlyWeekCondition> nearlyWeekCostConditions = operaConditionMapper.queryCostNearlyWeekTrend();
        List<NearlyWeekCondition> nearlyWeekIncomeConditions = operaConditionMapper.queryIncomeNearlyWeekTrend();
        List<LineEntity> dataList = new ArrayList<>();
        List<String> xList = new ArrayList<>();

        LineEntity lineEntity = new LineEntity();

        List<Double> costList = new ArrayList<>();
        if (nearlyWeekCostConditions != null && nearlyWeekCostConditions.size() > 0){
            for (NearlyWeekCondition costCondition : nearlyWeekCostConditions) {
                costList.add(costCondition.getTotalCost()/100);
            }
            Collections.reverse(costList);
            lineEntity.setData(costList);
            lineEntity.setName("总成本");
            dataList.add(lineEntity);
        }

        LineEntity lineEntity1 = new LineEntity();

        List<Double> costList1 = new ArrayList<>();
        if (nearlyWeekIncomeConditions != null && nearlyWeekIncomeConditions.size() > 0){
            for (NearlyWeekCondition incomeCondition : nearlyWeekIncomeConditions) {
                costList1.add(incomeCondition.getTotalIncome()/100);
            }
            Collections.reverse(costList1);
            lineEntity1.setData(costList1);
            lineEntity1.setName("销售额");
            dataList.add(lineEntity1);
        }

        LineEntity lineEntity2 = new LineEntity();

        List<Double> costList2 = new ArrayList<>();
        if (nearlyWeekCostConditions != null && nearlyWeekCostConditions.size() > 0){
            for (NearlyWeekCondition costCondition : nearlyWeekCostConditions) {
                if (nearlyWeekIncomeConditions != null && nearlyWeekIncomeConditions.size() > 0){
                    for (NearlyWeekCondition incomeCondition : nearlyWeekIncomeConditions) {
                        if (costCondition.getCycle() == incomeCondition.getCycle() || costCondition.getCycle().equals(incomeCondition.getCycle())){
                            costList2.add(PercentUtils.getRound(incomeCondition.getTotalIncome()/100 - costCondition.getTotalCost()/100));
                        }
                    }
                }
            }
            Collections.reverse(costList2);
            lineEntity2.setData(costList2);
            lineEntity2.setName("毛利润");
            dataList.add(lineEntity2);
        }

        for (int i = 15; i >= 0 ; i--) {
            String day = ChartCalendarUtil.getStatetime(i);
            String weekOfDate = CalendarTools.getWeekOfDate(day);
            xList.add(day + weekOfDate);
        }

        JSONArray jsonArrayX = JSONArray.fromObject(xList);
        JSONArray jsonArrayS = JSONArray.fromObject(dataList);
        Map<String,Object> resu = new HashMap();
        resu.put("jsonArrayX",jsonArrayX);
        resu.put("jsonArrayS",jsonArrayS);

        return resu;
    }
}