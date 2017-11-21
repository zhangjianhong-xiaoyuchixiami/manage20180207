package org.qydata.tools.customer;

import com.google.gson.Gson;
import net.sf.json.JSONObject;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jonhn on 2017/10/10.
 */
public class RequestData {

    private static String authId = ApiStaticConstants.AUTHID_03;
    private static String authPass = ApiStaticConstants.PASSWORD_03;

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

    }

    public static Map<String,Object> result(String tid,String mob,String name,String idCard,Integer apiId,String omit,String skip){
        try {
            String url = null;
            String mobile = null;
            String realName = null;
            String idNo = null;
            Integer aid = null;
            boolean skipSaveLd = false;
            boolean omitLocal = false;
            if (tid != null && tid != ""){
                url = tid.trim();
                if (tid.equals("/mobile/verify/3f")){
                    if (mob != null && mob != ""){
                        mobile = mob.trim();
                    }
                    if (name != null && name != ""){
                        realName = name.trim();
                    }
                    if (idCard != null && idCard != ""){
                        idNo = idCard.trim();
                    }
                }
                if (tid.equals("/mobile/query/duration") || tid.equals("/mobile/query/status")){
                    if (mob != null && mob != ""){
                        mobile = mob.trim();
                    }
                }
                if (tid.equals("/mobile/verify/2f-name")){
                    if (mob != null && mob != ""){
                        mobile = mob.trim();
                    }
                    if (name != null && name != ""){
                        realName = name.trim();
                    }
                }
                if (tid.equals("/id/verify/2f") || tid.equals("/id/query/photo")){
                    if (name != null && name != ""){
                        realName = name.trim();
                    }
                    if (idCard != null && idCard != ""){
                        idNo = idCard.trim();
                    }
                }
                if (apiId != null){
                    aid = apiId;
                }
                if (omit.trim().equals("true")){
                    omitLocal = true;
                }
                if (skip.trim().equals("true")){
                    skipSaveLd = true;
                }
            }
            String jsonObject = mobileProductApi(url,mobile,realName,idNo,aid,omitLocal,skipSaveLd);
            String result = CustomerDataParamUtil.respParam(jsonObject);
            Map<String,Object> map = new HashMap();
            map.put("result",result);
            map.put("photo",CustomerDataParamUtil.photoParam(jsonObject));
            map.put("respResult",CustomerDataParamUtil.deleteRespPhoto(jsonObject));
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 手机产品Api
     * @param mobile
     * @param realName
     * @param idNo
     * @param aid
     * @return
     * @throws IOException
     */
    public static String mobileProductApi(String url, String mobile, String realName, String idNo, Integer aid,boolean omitLocal,boolean skipSaveLd) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost request = new HttpPost(ApiStaticConstants.REQUEST_PREFIX_API + url);
            request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
            Req req = new Req();
            req.authId = authId;
            req.reqId = Long.toString(System.currentTimeMillis()).substring(1);
            req.ts = System.currentTimeMillis();
            req.sign = DigestUtils.md5Hex(req.authId + authPass + req.reqId + Long.toString(req.ts)).toUpperCase();
            req.omitLocal = omitLocal;
            req.skipSaveLd = skipSaveLd;
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
            request.setEntity(new StringEntity(new Gson().toJson(req), Charsets.UTF_8));
            CloseableHttpResponse execute = httpClient.execute(request);
            String result = EntityUtils.toString(execute.getEntity());
            JSONObject resultJo = JSONObject.fromObject(result);
            return resultJo.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

}
