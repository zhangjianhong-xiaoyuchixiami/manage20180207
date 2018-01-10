package org.qydata.tools.https;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.qydata.constants.CustomerFinanceContants;
import org.qydata.mapper.mapper2.CompanySelectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by jonhn on 2017/4/13.
 */
public class HttpClientUtil {

    /**
     * 发送get请求
     * @param url       链接地址
     * @param charset   字符编码，若为null则默认utf-8
     * @param params   请求参数
     * @return
     */
    public static int doGet(String url, Map<String, Object> params, String charset){
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        if(null == charset){
            charset = "utf-8";
        }
        String result = null;
        int statusCode = 500;
        try {
            HttpClient httpClient = new SSLClient();
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpResponse response = httpClient.execute(httpGet);
            statusCode = response.getStatusLine().getStatusCode();
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                    System.out.println("**************************************");
                    System.out.println("https请求状态码statusCode="+statusCode);
                    System.out.println("https请求结果result="+result);
                    System.out.println("**************************************");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusCode;
    }


    /**
     * 客户消费量查询接口
     * @param customerId
     * @param date
     * @return
     */
    public static String httpRequest(String customerId, String date, String requestKey) {
        String s = null;
        try {
            HttpClient httpClient = new SSLClient();
            HttpGet httpGet = new HttpGet(CustomerFinanceContants.REQUEST_PREFIX_API + "/" + customerId+"/" + date + "?k=" + requestKey);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            s = EntityUtils.toString(entity);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == 200){
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
