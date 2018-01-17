package org.qydata.controller;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.qydata.config.annotation.RolePermission;
import org.qydata.dst.ApiResponseCondition;
import org.qydata.dst.ApiTags;
import org.qydata.service.ApiResponseConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api-response-condition")
public class ApiResponseConditionController {

    @Autowired
    private ApiResponseConditionService apiResponseConditionServiceImpl;

    @RequestMapping("/all-api-response")
    public String queryAllApiResponse(Model model){
        List<ApiResponseCondition> apiResponseConditions = apiResponseConditionServiceImpl.queryAllApiResponse();
        model.addAttribute("apiResponseConditions", apiResponseConditions);
        return  "/api/api-response-condition";
    }

    @RequestMapping("/api-response-time-trends")
    public ModelAndView apiResponseChartName(String apiId, String vendorName, String apiName, Model model){
        model.addAttribute("apiId",apiId);
        model.addAttribute("vendorName",vendorName);
        model.addAttribute("apiName",apiName);
        return new ModelAndView("/api/api-response-trends");
    }

    @RequestMapping("/api-response-nearly-week-thread/data")
    @ResponseBody
    public String apiResponseNearlyTimeThread(HttpServletRequest request){
        String apiId = request.getParameter("apiId");
        Map<String, Object> map = apiResponseConditionServiceImpl.queryApiResponseCondition(apiId);
        JSONArray jsonArrayX = null;
        JSONArray jsonArrayData = null;
        if (map != null){
            for (Map.Entry<String,Object> me : map.entrySet()){
                if (me.getKey().equals("jsonArrayX")) {
                    jsonArrayX = (JSONArray) me.getValue();
                }
                if (me.getKey().equals("jsonArrayData")) {
                    jsonArrayData = (JSONArray) me.getValue();
                }
            }
        }
        JSONObject getObj = new JSONObject();
        getObj.put("xList", jsonArrayX);
        getObj.put("seriesData", jsonArrayData);
        return getObj.toString();

    }

    @RequestMapping("/show-api-tags")
    @ResponseBody
    public String findApiTags(HttpServletRequest request){
        String apiId = request.getParameter("apiId");
        ApiTags apiTags = apiResponseConditionServiceImpl.findApiTags(apiId);
        String s = new Gson().toJson(apiTags).toString();
        System.out.println(s);
        return s;
    }

    /**
     * 提交修改的标签
     * @param apiTag
     * @param apiId
     * @return
     */
    @RequestMapping("/submitApiTag")
    @ResponseBody
    public String addApiTag(String apiTag, String apiId){
        Map<String,Object> map = new HashMap<>();
        Gson gson = new Gson();
        int result = 0;
        if (apiTag.length() == 0){
            apiTag = null;
        }
        try {
            result = apiResponseConditionServiceImpl.submitApiTag(apiId, apiTag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (200 == result){
            map.put("success","操作成功");
            return gson.toJson(map);
        }
        map.put("fail","添加失败，请检查你的操作");
        return gson.toJson(map);
    }

   /* @RequestMapping("/deleteApiTag")
    @ResponseBody
    public String deleteApiTag(String id){
        Map<String,Object> map = new HashMap<>();
        Gson gson = new Gson();
        int result = 0;
        try {
            result = apiResponseConditionServiceImpl.deleteApiTag(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (200 == result){
            map.put("success","操作成功");
            return gson.toJson(map);
        }
        map.put("fail","添加失败，请检查你的操作");
        return gson.toJson(map);
    }*/
}
