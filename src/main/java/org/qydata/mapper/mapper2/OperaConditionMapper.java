package org.qydata.mapper.mapper2;

import org.qydata.dst.CustomerIncome;
import org.qydata.dst.NearlyWeekCondition;
import org.qydata.dst.OperaCondition;
import org.qydata.dst.VendorCost;

import java.util.List;
import java.util.Map;

public interface OperaConditionMapper {

    public List<OperaCondition> getIncomeAccount(Map<String, Object> map);

    public List<OperaCondition> getCostAccount(Map<String, Object> map);

    public List<OperaCondition> getCostAccountByType(Map<String, Object> map);

    public List<OperaCondition> getIncomeAccountByType(Map<String, Object> map);

    public List<OperaCondition> getApiType(Map<String, Object> map);

    public List<CustomerIncome> getCustomerIncomeCondition(Map<String, Object> map);

    public List<VendorCost>getVendorCostCondition(Map<String, Object> map);

    public List<NearlyWeekCondition>queryIncomeNearlyWeekTrend();

    public List<NearlyWeekCondition>queryCostNearlyWeekTrend();
}
