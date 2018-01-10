package org.qydata.service;

import org.qydata.dst.ApiResponseCondition;

import java.util.List;
import java.util.Map;

public interface ApiResponseConditionService {

    public List<ApiResponseCondition> queryApiResponseCondition(Map<String, Object> map);

    public List<ApiResponseCondition>queryAllApiResponse();
}
