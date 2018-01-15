package org.qydata.service;

import org.qydata.dst.ApiResponseCondition;
import org.qydata.dst.ApiTags;

import java.util.List;
import java.util.Map;

public interface ApiResponseConditionService {

    public Map<String, Object> queryApiResponseCondition(String apiId);

    public List<ApiResponseCondition> queryAllApiResponse();

    public List<ApiTags> findApiTags(String apiId);

    public Integer addApiTag(String apiId, String apiTag) throws Exception;

    public Integer deleteApiTag(String id) throws Exception;
}
