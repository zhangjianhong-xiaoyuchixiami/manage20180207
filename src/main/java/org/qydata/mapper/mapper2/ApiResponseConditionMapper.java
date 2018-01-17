package org.qydata.mapper.mapper2;

import org.qydata.dst.ApiResponseCondition;
import org.qydata.dst.ApiTags;

import java.util.List;
import java.util.Map;

public interface ApiResponseConditionMapper {

    public List<ApiResponseCondition>queryApiResponseCondition(String apiId);

    public List<ApiResponseCondition>queryAllApiResponse(Map<String, Object>map);

    public ApiTags queryApiTags(String apiId);


}
