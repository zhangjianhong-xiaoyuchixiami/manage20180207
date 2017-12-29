package org.qydata.service;


import org.qydata.dst.CustomerIncome;
import org.qydata.dst.OperaCondition;
import org.qydata.dst.VendorCost;

import java.util.List;
import java.util.Map;

public interface OperaConditionService {

    public  Map<String, Object> getConsumeConditionAccount();

    public List<OperaCondition> getApiOperaCondition();

    public List<CustomerIncome> getCurrCustomerIncomeCondition(Map<String, Object>map);

    public List<CustomerIncome> getYesterCustomerIncomeCondition(Map<String, Object>map);

    public List<CustomerIncome> getYesterHourCustomerIncomeCondition(Map<String, Object>map);

    public List<VendorCost> getYesterVendorCostCondition(Map<String, Object>map);

    public List<VendorCost> getYesterHourVendorCostCondition(Map<String, Object>map);

    public List<VendorCost> getCurrVendorCostCondition(Map<String, Object>map);


}
