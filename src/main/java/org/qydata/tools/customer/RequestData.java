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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jonhn on 2017/10/10.
 */
public class RequestData {

    private static String authId = "qydata03";
    private static String authPass = "a54cc70444ea4618ad8d586194ba1572";

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
        String url = null;
        String mobile = null;
        String realName = null;
        String idNo = null;
        Integer aid = null;
        boolean skipSaveLd = false;
        boolean omitLocal = false;
        if (tid != null){
            url = tid;
        }
        if (mob != null){
            mobile = mob;
        }
        if (name != null){
            realName = name;
        }
        if (idCard != null){
            idNo = idCard;
        }
        if (apiId != null){
            aid = apiId;
        }
        if (omit != null){
            omitLocal = Boolean.getBoolean(omit);
        }
        if (skip != null){
            skipSaveLd = Boolean.getBoolean(skip);
        }
        try {
            JSONObject jsonObject = mobileProductApi(url,mobile,realName,idNo,aid,omitLocal,skipSaveLd);
            String result = CustomerDataParamUtil.respParam(jsonObject.toString());
            Map<String,Object> map = new HashMap();
            map.put("result",result);
            map.put("respResult",jsonObject.toString());
        } catch (IOException e) {
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
    public static JSONObject mobileProductApi(String url, String mobile, String realName, String idNo, Integer aid,boolean omitLocal,boolean skipSaveLd) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("https://api.qydata.org:9000" + url);
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
        System.out.println(result);
        JSONObject resultJo = JSONObject.fromObject(result);
        return resultJo;
    }

}
