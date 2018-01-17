package org.qydata.service;

import org.qydata.dst.ApiResponseCondition;
import org.qydata.dst.ApiTags;

import java.util.List;
import java.util.Map;

public interface ApiResponseConditionService {

    public Map<String, Object> queryApiResponseCondition(String apiId);

    public List<ApiResponseCondition> queryAllApiResponse();

    public ApiTags findApiTags(String apiId);

    public Integer submitApiTag(String apiId, String apiTag) throws Exception;

}
