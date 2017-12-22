package org.qydata.mapper.mapper2;

import org.qydata.dst.OperaCondition;

import java.util.List;
import java.util.Map;

public interface OperaConditionMapper {

    public List<OperaCondition> getIncomeAccount(Map<String, Object> map);

    public List<OperaCondition> getCostAccount(Map<String, Object> map);
}
