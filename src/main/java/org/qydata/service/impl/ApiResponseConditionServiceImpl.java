package org.qydata.service.impl;

import org.qydata.dst.ApiResponseCondition;
import org.qydata.mapper.mapper2.ApiResponseConditionMapper;
import org.qydata.service.ApiResponseConditionService;
import org.qydata.tools.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiResponseConditionServiceImpl implements ApiResponseConditionService{

    @Autowired
    private ApiResponseConditionMapper apiResponseConditionMapper;

    @Override
    public List<ApiResponseCondition> queryApiResponseCondition(Map<String, Object> map) {
        List<ApiResponseCondition> apiResponseConditions = apiResponseConditionMapper.queryApiResponseCondition(map);






        return apiResponseConditions;
    }

    @Override
    public List<ApiResponseCondition> queryAllApiResponse() {

        Map<String, Object> map = new HashMap<String, Object>();
        String currHour = DateUtils.currHour();
        String monthBeforeCurrent = DateUtils.monthBeforeCurrent();
        map.put("currTime", currHour);
        map.put("monthBeforeCurrent", monthBeforeCurrent);

        List<ApiResponseCondition> apiResponseConditions = apiResponseConditionMapper.queryAllApiResponse(map);
        return apiResponseConditions;
    }
}
