package org.qydata.service.impl;

import net.sf.json.JSONArray;
import org.qydata.constants.GlobalStaticConstants;
import org.qydata.dst.ApiLineEntity;
import org.qydata.dst.ApiResponseCondition;
import org.qydata.dst.ApiTags;
import org.qydata.dst.LineEntity;
import org.qydata.mapper.mapper1.ApiTagsMapper;
import org.qydata.mapper.mapper2.ApiResponseConditionMapper;
import org.qydata.service.ApiResponseConditionService;
import org.qydata.tools.CalendarTools;
import org.qydata.tools.DateUtils;
import org.qydata.tools.PercentUtils;
import org.qydata.tools.finance.ApiTypeMobileOperatorNameUtils;
import org.qydata.tools.https.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiResponseConditionServiceImpl implements ApiResponseConditionService{

    @Autowired
    private ApiResponseConditionMapper apiResponseConditionMapper;
    @Autowired
    private ApiTagsMapper apiTagsMapper;

    /**
     * 响应时间的走势
     * @param apiId
     * @return
     */
    @Override
    public Map<String, Object> queryApiResponseCondition(String apiId) {
        List<ApiLineEntity> dataList = new ArrayList<ApiLineEntity>();
        List<String> xList = new ArrayList<>();

        ApiLineEntity apilineEntity1 = new ApiLineEntity();
        ApiLineEntity apilineEntity2 = new ApiLineEntity();
        ApiLineEntity apilineEntity3 = new ApiLineEntity();
        ApiLineEntity apilineEntity4 = new ApiLineEntity();
        ApiLineEntity apilineEntity5 = new ApiLineEntity();
        ApiLineEntity apilineEntity6 = new ApiLineEntity();
        ApiLineEntity apilineEntity7 = new ApiLineEntity();
        List<Double> list1 = new ArrayList<Double>();
        List<Double> list2 = new ArrayList<Double>();
        List<Double> list3 = new ArrayList<Double>();
        List<Double> list4 = new ArrayList<Double>();
        List<Double> list5 = new ArrayList<Double>();
        List<Double> list6 = new ArrayList<Double>();
        List<Double> list7 = new ArrayList<Double>();
        List<ApiResponseCondition> apiResponseConditions = apiResponseConditionMapper.queryApiResponseCondition(apiId);
        if (apiResponseConditions != null && apiResponseConditions.size() > 0){
            for (ApiResponseCondition apiResponseCondition : apiResponseConditions) {
                String dateTime = apiResponseCondition.getDateTime();
                String weekOfDate = CalendarTools.getWeekOfDate(dateTime);
                xList.add(dateTime + weekOfDate);
                if (apiResponseCondition.getSuccessCount() != 0) {
                    list1.add(PercentUtils.getPercent2(apiResponseCondition.getCount1(), apiResponseCondition.getSuccessCount()));
                    list2.add(PercentUtils.getPercent2(apiResponseCondition.getCount2(), apiResponseCondition.getSuccessCount()));
                    list3.add(PercentUtils.getPercent2(apiResponseCondition.getCount3(), apiResponseCondition.getSuccessCount()));
                    list4.add(PercentUtils.getPercent2(apiResponseCondition.getCount4(), apiResponseCondition.getSuccessCount()));
                    list5.add(PercentUtils.getPercent2(apiResponseCondition.getCount5(), apiResponseCondition.getSuccessCount()));
                    list6.add(PercentUtils.getPercent2(apiResponseCondition.getCount6(), apiResponseCondition.getSuccessCount()));
                    list7.add(PercentUtils.getPercent2(apiResponseCondition.getCount7(), apiResponseCondition.getSuccessCount()));
                }else {
                    list1.add(0.0);
                    list2.add(0.0);
                    list3.add(0.0);
                    list4.add(0.0);
                    list5.add(0.0);
                    list6.add(0.0);
                    list7.add(0.0);
                }
            }
            apilineEntity1.setName("0-500ms");
            apilineEntity1.setData(list1);

            apilineEntity2.setName("500-1000ms");
            apilineEntity2.setData(list2);

            apilineEntity3.setName("1000-1500ms");
            apilineEntity3.setData(list3);

            apilineEntity4.setName("1500-2000ms");
            apilineEntity4.setData(list4);

            apilineEntity5.setName("2000-2500ms");
            apilineEntity5.setData(list5);

            apilineEntity6.setName("2500-3000ms");
            apilineEntity6.setData(list6);

            apilineEntity7.setName("3000ms-+∞");
            apilineEntity7.setData(list7);

            dataList.add(apilineEntity1);
            dataList.add(apilineEntity2);
            dataList.add(apilineEntity3);
            dataList.add(apilineEntity4);
            dataList.add(apilineEntity5);
            dataList.add(apilineEntity6);
            dataList.add(apilineEntity7);
        }
        JSONArray jsonArrayX = JSONArray.fromObject(xList);
        JSONArray jsonArrayData = JSONArray.fromObject(dataList);
        Map<String,Object> resu = new HashMap();
        resu.put("jsonArrayX",jsonArrayX);
        resu.put("jsonArrayData",jsonArrayData);

        return resu;
    }

    /**
     * 请求详情
     * @return
     */
    @Override
    public List<ApiResponseCondition> queryAllApiResponse() {

        Map<String, Object> map = new HashMap<String, Object>();
        String currHour = DateUtils.currHour();
        String currDawn = DateUtils.currDawn();
        map.put("currTime", currHour);
        map.put("currDawn", currDawn);

        List<ApiResponseCondition> apiResponseConditions = apiResponseConditionMapper.queryAllApiResponse(map);
        if (apiResponseConditions != null && apiResponseConditions.size() > 0){
            for (ApiResponseCondition apiResponseCondition : apiResponseConditions) {
                if (apiResponseCondition.getApiTypeId() != null){
                    String apiTypeName_stidName = ApiTypeMobileOperatorNameUtils.apiTypeMobileOperatorName(apiResponseCondition.getApiTypeName(),apiResponseCondition.getMobileList());
                    apiResponseCondition.setApiName(apiTypeName_stidName);
                }

                if (apiResponseCondition.getTotalCount() != 0 ){
                    String percent = PercentUtils.getPercent1(apiResponseCondition.getFailCount(), apiResponseCondition.getTotalCount());
                    apiResponseCondition.setFailPercent(percent);
                }else{
                    apiResponseCondition.setFailPercent("当前请求数为零");
                }
            }
        }

        return apiResponseConditions;
    }

    /**
     * 查询产品标签
     * @param apiId
     * @return
     */
    @Override
    public List<ApiTags> findApiTags(String apiId) {
        List<ApiTags> list = apiResponseConditionMapper.queryApiTags(apiId);
        return list;
    }

    /**
     * 增加标签
     * @param apiId
     * @param apiTag
     * @return
     * @throws Exception
     */
    @Override
    public Integer addApiTag(String apiId, String apiTag) throws Exception {
        String uri = GlobalStaticConstants.CUSTOMER_ADD_IP;
        Map<String,Object> map = new HashMap<>();
        map.put("apiId", apiId);
        map.put("apiTag", apiTag);
        Integer integer = apiTagsMapper.addApiTag(map);
        Integer code = null;
        if (integer > 0 ){
            return code = 200;
        }
        throw new Exception("保存失败");
    }

    /**
     * 删除标签
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Integer deleteApiTag(String id) throws Exception {
        Integer integer = apiTagsMapper.deleteApiTag(id);
        Integer code = null;
        if (integer > 0 ){
            return code = 200;
        }
        throw new Exception("删除失败");
    }
}
