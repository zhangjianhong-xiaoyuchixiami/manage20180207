package org.qydata.tools.customer;

import com.google.gson.Gson;
import net.sf.json.JSONObject;
import netscape.javascript.JSObject;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.qydata.constants.ApiStaticConstants;
import org.qydata.dst.valid.ReqParam;
import org.qydata.dst.valid.ValidResult;
import org.qydata.service.ValidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/10/10.
 */
@Component
public class RequestData {

    @Autowired private ValidService validService;

    private static class ReqBody{

        public String mobile;
        public String realName;
        public String idNo;
        public String bankcard;

    }

    /**
     * 核验结果
     * @param paramList
     * @return
     */
    public List<ValidResult> result(List<ReqParam> paramList){
        List<ValidResult> resultList = new ArrayList<>();
        if (paramList != null){
            for (ReqParam param : paramList) {
                if (param != null){
                    ReqBody reqBody = new ReqBody();
                    ValidResult validResult = new ValidResult();
                    Map<String,String> map = matches(param);
                    if (map != null){
                        for (Map.Entry<String,String> me : map.entrySet()) {
                            if ("result".equals(me.getKey())){
                                validResult.setResult(me.getValue());
                            }
                            if ("responseBody".equals(me.getKey())){
                                validResult.setResponseBody(CustomerDataParamUtil.deleteRespPhoto(me.getValue()));
                                validResult.setPhoto(CustomerDataParamUtil.photoParam(me.getValue()));
                            }
                        }
                    }
                    reqBody.mobile = param.mob;
                    reqBody.idNo = param.id;
                    reqBody.realName = param.name;
                    reqBody.bankcard = param.bankId;
                    validResult.setRequestBody(new Gson().toJson(reqBody));
                    validResult.setDataSource(validService.queryApiVendorByAid(param.aid));
                    validResult.setApiType(validService.queryApiTypeByTypeIdAndStid(param.tid,param.stid));
                    resultList.add(validResult);
                }
            }
        }
        return resultList;
    }

    /**
     * 核验数据
     * @param param
     * @return
     */
    public static Map<String,String> matches(ReqParam param) {
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i < 3 ; i++) {
            JSONObject jsonObject = mobileProductApi(param.address,param.mob,param.name,param.id,param.bankId,param.aid,param.omit,param.skip);
            String code = resultParam(jsonObject);
            if (code.contains("range")){
                String codes [] = code.split(":");
                map.put("result",codes[1]);
                if (jsonObject != null){
                    map.put("responseBody", String.valueOf(jsonObject));
                }
                return map;
            }
            if (!CodeMessageList.isTry(code)){
                map.put("result",CodeMessageList.message(code));
                if (jsonObject != null){
                    map.put("responseBody", String.valueOf(jsonObject));
                }
                return map;
            }
        }
        map.put("result","未知错误发生");
        return map;
    }

    /**
     * 解析返回code
     * @param jsonObject
     * @return
     */
    public static String resultParam(JSONObject jsonObject){
        if (jsonObject == null){
            return null;
        }
        try{
            if (jsonObject.containsKey("code")){
                if (jsonObject.getInt("code") == 0){
                    if (jsonObject.containsKey("result")){
                        JSONObject result = jsonObject.getJSONObject("result");
                        if (result.containsKey("resultCode")){
                            return "resultCode:" + result.getString("resultCode");
                        }
                        if (result.containsKey("status")){
                            return "status:" + result.getString("status");
                        }
                        if (result.containsKey("rangeStart") && result.containsKey("rangeEnd")){
                            return "range:" + result.getString("rangeStart") + "," + result.getString("rangeEnd");
                        }
                    }
                }
                return jsonObject.getString("code");
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    private static class Req{
        public String authId;
        public long ts;
        public String reqId;
        public String sign;
        public boolean omitLocal;
        public int aid;
        public boolean skipSaveLd;

        public String mobile;
        public String realName;
        public String idNo;
        public String bankcard;

    }


    /**
     * 通用Api接口
     * @param mobile
     * @param realName
     * @param idNo
     * @param aid
     * @return
     * @throws IOException
     */
    public static JSONObject mobileProductApi(String url, String mobile, String realName, String idNo, String bankcard , Integer aid, String omitLocal, String skipSaveLd) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost request = new HttpPost(url);
            request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
            Req req = new Req();
            req.authId = ApiStaticConstants.AUTHID;
            req.reqId = Long.toString(System.currentTimeMillis()).substring(1);
            req.ts = System.currentTimeMillis();
            req.sign = DigestUtils.md5Hex(req.authId + ApiStaticConstants.PASSWORD + req.reqId + Long.toString(req.ts)).toUpperCase();
            if ("true".equals(omitLocal)){
                req.omitLocal = true;
            }else {
                req.omitLocal = false;
            }
            if ("true".equals(skipSaveLd)){
                req.skipSaveLd = true;
            }else {
                req.skipSaveLd = false;
            }
            if (aid != null){
                req.aid = aid;
            }
            if (mobile != null){
                req.mobile = mobile;
            }
            if (realName != null){
                req.realName = realName;
            }
            if (idNo != null){
                req.idNo = idNo;
            }
            if (bankcard != null){
                req.bankcard = bankcard;
            }
            System.out.println(new Gson().toJson(req).toString());
            request.setEntity(new StringEntity(new Gson().toJson(req), Charsets.UTF_8));
            CloseableHttpResponse execute = httpClient.execute(request);
            String result = EntityUtils.toString(execute.getEntity());
            JSONObject resultJo = JSONObject.fromObject(result);
            return resultJo;
            //return JSONObject.fromObject("\t{\"code\":0,\"message\":\"成功\",\"result\":{\"resultCode\":1,\"unmatched\":null},\"l\":null}");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
