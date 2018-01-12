package org.qydata.service;

import org.qydata.dst.ApiResponseCondition;

import java.util.List;
import java.util.Map;

public interface ApiResponseConditionService {

    public Map<String, Object> queryApiResponseCondition(String apiId);

    public List<ApiResponseCondition> queryAllApiResponse();
}
