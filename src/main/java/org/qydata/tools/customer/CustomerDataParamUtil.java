package org.qydata.tools.customer;

import com.google.gson.Gson;
import net.sf.json.JSONObject;

/**
 * Created by jonhn on 2017/11/16.
 */
public class CustomerDataParamUtil {

    private static class Req{
        public String mobile;
        public String idNo;
        public String realName;
        public String bankcard;
        public String longitude;
        public String latitude;
        public String oppoMobile;
        public String areaCode;
    }

    //解析请求数据
    public static String reqParam(String str){
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.fromObject(str);
        }catch (Exception e){
            return str;
        }
        if (jsonObject == null){
            return str;
        }
        Req req = new Req();
        req.mobile = mobileParam(str);
        req.idNo = idNoParam(str);
        req.realName = realNameParam(str);
        req.bankcard = bankCardParam(str);
        req.longitude = longitudeParam(str);
        req.latitude = latitudeParam(str);
        req.oppoMobile = oppoMobileParam(str);
        req.areaCode = areaCodeParam(str);
        return new Gson().toJson(req).toString();
    }

    //解析mobile
    public static String mobileParam(String str){
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.fromObject(str);
        }catch (Exception e){
            return null;
        }
        if (jsonObject == null){
            return null;
        }
        try{
            return jsonObject.getString("mobile");
        }catch (Exception e){
            return null;
        }
    }

    //解析idNo
    public static String idNoParam(String str){
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.fromObject(str);
        }catch (Exception e){
            return null;
        }
        if (jsonObject == null){
            return null;
        }
        try{
            return jsonObject.getString("idNo");
        }catch (Exception e){
            return null;
        }
    }

    //解析realName
    public static String realNameParam(String str){
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.fromObject(str);
        }catch (Exception e){
            return null;
        }
        if (jsonObject == null){
            return null;
        }
        try{
            return jsonObject.getString("realName");
        }catch (Exception e){
            return null;
        }
    }

    //解析bankcard
    public static String bankCardParam(String str){
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.fromObject(str);
        }catch (Exception e){
            return null;
        }
        if (jsonObject == null){
            return null;
        }
        try{
            return jsonObject.getString("bankcard");
        }catch (Exception e){
            return null;
        }
    }

    //解析longitude
    public static String longitudeParam(String str){
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.fromObject(str);
        }catch (Exception e){
            return null;
        }
        if (jsonObject == null){
            return null;
        }
        try{
            return jsonObject.getString("longitude");
        }catch (Exception e){
            return null;
        }
    }

    //解析latitude
    public static String latitudeParam(String str){
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.fromObject(str);
        }catch (Exception e){
            return null;
        }
        if (jsonObject == null){
            return null;
        }
        try{
            return jsonObject.getString("latitude");
        }catch (Exception e){
            return null;
        }
    }

    //解析oppoMobile
    public static String oppoMobileParam(String str){
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.fromObject(str);
        }catch (Exception e){
            return null;
        }
        if (jsonObject == null){
            return null;
        }
        try{
            return jsonObject.getString("oppoMobile");
        }catch (Exception e){
            return null;
        }
    }

    //解析areaCode
    public static String areaCodeParam(String str){
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.fromObject(str);
        }catch (Exception e){
            return null;
        }
        if (jsonObject == null){
            return null;
        }
        try{
            return jsonObject.getString("areaCode");
        }catch (Exception e){
            return null;
        }
    }

    private static class Resp{
        public String code;
        public String message;
        public Result result;
    }

    private static class Result{
        public String resultCode;
        public String rangeStart;
        public String rangeEnd;
        public String status;
        public String balance;
        public String photo;
        public String star;
    }

    //解析响应数据
    public static String respParam(String str){
        try{
            return resultParam(str);
        }catch (Exception e){
            return null;
        }

    }

    public static String resultParam(String str){
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.fromObject(str);
        }catch (Exception e){
            return null;
        }
        if (jsonObject == null){
            return null;
        }
        try{
            if (jsonObject.getJSONObject("result") == null || jsonObject.get("result") instanceof net.sf.json.JSONNull){
                return null;
            }
            JSONObject result = jsonObject.getJSONObject("result");
            if (result.getString("resultCode") != null || !(jsonObject.get("resultCode") instanceof net.sf.json.JSONNull)){
                return resultCodeParam(result);
            }
            if (result.getString("rangeStart") != null || !(jsonObject.get("rangeStart") instanceof net.sf.json.JSONNull)){
                 if (result.getString("rangeEnd") != null || !(jsonObject.get("rangeEnd") instanceof net.sf.json.JSONNull)) {
                     return rangeParam(result);
                 }
            }
            if (result.getString("status") != null || !(jsonObject.get("status") instanceof net.sf.json.JSONNull)){
                return statusParam(result);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String resultCodeParam(JSONObject jsonObject){
        try {
            if (jsonObject.getString("resultCode") == null || jsonObject.get("resultCode") instanceof net.sf.json.JSONNull || "".equals(jsonObject.getString("resultCode"))){
                return null;
            }
            String resultCode = jsonObject.getString("resultCode");
            if ("-1".equals(resultCode)) return "无记录";
            if ("1".equals(resultCode)) return "匹配";
            if ("2".equals(resultCode)) return "部分匹配";
            if ("3".equals(resultCode)) return "无匹配";
            if ("4".equals(resultCode)) return "不匹配";
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String rangeParam(JSONObject jsonObject){
        try {
            if (jsonObject.getString("rangeStart") == null || jsonObject.get("rangeStart") instanceof net.sf.json.JSONNull || "".equals(jsonObject.getString("rangeStart"))){
                return null;
            }
            if (jsonObject.getString("rangeEnd") == null || jsonObject.get("rangeEnd") instanceof net.sf.json.JSONNull || "".equals(jsonObject.getString("rangeEnd"))){
                return null;
            }
            String rangeStart = jsonObject.getString("rangeStart");
            String rangeEnd = jsonObject.getString("rangeEnd");
            return "起始值：" + rangeStart + ",结束值：" + rangeEnd;
        }catch (Exception e){
            return null;
        }
    }

    public static String statusParam(JSONObject jsonObject){
        try {
            if (jsonObject.getString("status") == null || jsonObject.get("status") instanceof net.sf.json.JSONNull || "".equals(jsonObject.getString("status"))){
                return null;
            }
            String status = jsonObject.getString("status");
            if ("-2".equals(status)) return "异常（号码状态非正常）";
            if ("-1".equals(status)) return "不在网";
            if ("0".equals(status)) return "未知";
            if ("1".equals(status)) return "未启用";
            if ("2".equals(status)) return "正常";
            if ("3".equals(status)) return "欠费停机";
            if ("4".equals(status)) return "其它停机";
            if ("5".equals(status)) return "已销号";
            if ("6".equals(status)) return "在网但不可用";
            if ("7".equals(status)) return "停机（原因未知";
            return null;
        }catch (Exception e){
            return null;
        }
    }

}
