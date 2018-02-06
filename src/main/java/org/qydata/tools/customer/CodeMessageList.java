package org.qydata.tools.customer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/15.
 */
public class CodeMessageList {

    public final static Map<String,String> codeMap = new HashMap<String,String>();

    public final static HashSet<String> tryCodeMap = new HashSet<String>();

    static {

        codeMap.put("resultCode:0","成功");
        codeMap.put("resultCode:-1","无记录");
        codeMap.put("resultCode:1","匹配");
        codeMap.put("resultCode:4","不匹配");

        codeMap.put("status:-2","异常（号码状态非正常）");
        codeMap.put("status:-1","不在网");
        codeMap.put("status:0","未知");
        codeMap.put("status:1","未启用");
        codeMap.put("status:2","正常");
        codeMap.put("status:3","欠费停机");
        codeMap.put("status:4","其它停机");
        codeMap.put("status:5","已销号");
        codeMap.put("status:6","在网但不可用");
        codeMap.put("status:7","停机（原因未知）");




        codeMap.put("-301","账号不存在或已停用");
        codeMap.put("-302","账号余额不足");
        codeMap.put("-303","IP不在许可范围内");
        codeMap.put("-304","账号未开通接口权限");
        codeMap.put("-400","请求的数据异常");
        codeMap.put("-401","请求的必填参数为空");
        codeMap.put("-402","请求的sign(签名)无效");
        codeMap.put("-403","请求的ts(时间戳)无效");
        codeMap.put("-404","请求的reqId(请求标识)无效");
        codeMap.put("-500","请求失败，请联系管理员");
        codeMap.put("-502","请求失败，请重试");


        tryCodeMap.add("-402");
        tryCodeMap.add("-403");
        tryCodeMap.add("-404");
        tryCodeMap.add("-500");
        tryCodeMap.add("-502");

    }

    /**
     * 返回对应中文解释
     * @param code
     * @return
     */
    public static String message(String code){
        if (CodeMessageList.codeMap.containsKey(code)){
            return CodeMessageList.codeMap.get(code);
        }
        return "NULL";
    }

    /**
     * 判断是否重试
     * @param code
     * @return
     */
    public static boolean isTry(String code){
        if (CodeMessageList.tryCodeMap.contains(code)){
            return true;
        }
        return false;
    }

}
