package org.qydata.tools.customer;

import com.google.gson.Gson;
import net.sf.json.JSONObject;

/**
 * Created by jonhn on 2017/11/16.
 */
public class CustomerDataParamUtil {

    private static class Req{
        public String reqId;
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
        req.reqId = reqIdParam(str);
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

    //解析reqId
    public static String reqIdParam(String str){
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
            return jsonObject.getString("reqId");
        }catch (Exception e){
            return null;
        }
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

//    private static class Resp{
//        public String code;
//        public String message;
//        public Result result;
//    }
//
//    private static class Result{
//        public String resultCode;
//    }
//
//    //解析响应数据
//    public static String respParam(String str){
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = JSONObject.fromObject(str);
//        }catch (Exception e){
//            return str;
//        }
//        if (jsonObject == null){
//            return str;
//        }
//    }

}
