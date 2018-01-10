package org.qydata.mapper.mapper2;

import org.qydata.dst.ApiResponseCondition;

import java.util.List;
import java.util.Map;

public interface ApiResponseConditionMapper {

    public List<ApiResponseCondition>queryApiResponseCondition(Map<String, Object>map);

    public List<ApiResponseCondition>queryAllApiResponse(Map<String, Object>map);
}
