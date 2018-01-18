package org.qydata.tools.customer;

import com.google.gson.Gson;
import org.qydata.tools.RegexUtil;
import sun.security.krb5.internal.PAData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static void main(String[] args){
        //要提前号码的字符串
        String str="15846585301";
        System.out.println(check(str));
    }

    private static class Param{
        public String mobile;
        public String name;
        public String idNo;
        public String bankcard;

    }

    public static String check(String str){
        System.out.println(str);
        if (str.contains("|")){
            List<Param> paramList = new ArrayList<>();
            String strs [] = str.split("\\|");
            if (strs != null && strs.length > 0){
                for (int i = 0; i < strs.length ; i++) {
                    System.out.println(strs[i]);
                    Param param  = new Param();
                    //提取手机号
                    param.mobile = checkNumOne(strs[i]);
                    //提取身份证
                    param.idNo = checkIdNoOne(strs[i]);
                    //提取银行卡号
                    param.bankcard = checkBackIdOne(strs[i]);
                    //提取姓名
                    param.name = checkNameOne(strs[i]);
                    paramList.add(param);
                }
            }
            return new Gson().toJson(paramList).toString();
        }else {
            List<Param> paramList = new ArrayList<>();
            List<Integer> sizeList = new ArrayList<>();
            //提取手机号
            List<String> list = checkNum(str);
            //提取身份证
            List<String> list_1 = checkIdNo(str);
            //提取银行卡号
            List<String> list_2 = checkBackId(str);
            //提取银行卡号
            List<String> list_3 = checkName(str);
            sizeList.add(list.size());
            sizeList.add(list_1.size());
            sizeList.add(list_2.size());
            sizeList.add(list_3.size());
            for (int i = 0; i < Collections.max(sizeList) ; i++) {
                Param param  = new Param();
                if (list.size() > i){
                    param.mobile= list.get(i);
                }
                if (list_1.size() > i){
                    param.idNo= list_1.get(i);
                }
                if (list_2.size() > i){
                    param.bankcard= list_2.get(i);
                }
                if (list_3.size() > i){
                    param.name= list_3.get(i);
                }
                paramList.add(param);
            }
            return new Gson().toJson(paramList).toString();
        }
    }

    public static boolean isCharAt(String num,int index){
        try {
            String s = String.valueOf(num.charAt(index));
            return true;
        }catch (Exception e){

        }
        return false;
    }

    /**
     * 提取字符串中符合的手机号码
     * @param num
     */
    public static  List<String> checkNum(String num){
        if(num == null || num.length() == 0){return null;}
        Pattern pattern = Pattern.compile("(13|14|15|16|17|18|19)\\d{9}");
        Matcher matcher = pattern.matcher(num);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            int index = num.indexOf(matcher.group());
            if (isCharAt(num,index + 11)){
                if (!RegexUtil.isInteger(String.valueOf(num.charAt(index+11)))){
                    if (isCharAt(num,index - 1)){
                        if (!RegexUtil.isInteger(String.valueOf(num.charAt(index-1)))){
                            list.add(matcher.group());
                        }
                    }else {
                        list.add(matcher.group());
                    }
                }
            }else {
                if (isCharAt(num,index - 1)){
                    if (!RegexUtil.isInteger(String.valueOf(num.charAt(index-1)))){
                        list.add(matcher.group());
                    }
                }else {
                    list.add(matcher.group());
                }
            }
        }
        return list;
    }

    /**
     * 提取字符串中符合的身份证号码
     * @param num
     */
    public static  List<String> checkIdNo(String num){
        if(num == null || num.length() == 0){return null;}
        Pattern pattern = Pattern.compile("[1-9]\\d{17}");
        Matcher matcher = pattern.matcher(num);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            System.out.println(matcher.group());
            int index = num.indexOf(matcher.group());
            if (isCharAt(num,index + 18)){
                if (!RegexUtil.isInteger(String.valueOf(num.charAt(index+18)))){
                    if (isCharAt(num,index - 1)){
                        if (!RegexUtil.isInteger(String.valueOf(num.charAt(index-1)))){
                            if (IdcardValidator.isValidate18Idcard(matcher.group())){
                                list.add(matcher.group());
                            }
                        }
                    }else {
                        if (IdcardValidator.isValidate18Idcard(matcher.group())){
                            list.add(matcher.group());
                        }
                    }
                }
            }else {
                if (isCharAt(num,index - 1)){
                    if (!RegexUtil.isInteger(String.valueOf(num.charAt(index-1)))){
                        if (IdcardValidator.isValidate18Idcard(matcher.group())){
                            list.add(matcher.group());
                        }
                    }
                }else {
                    if (IdcardValidator.isValidate18Idcard(matcher.group())){
                        list.add(matcher.group());
                    }
                }
            }
        }
        return list;
    }

    /**
     * 提取字符串中符合的银行卡号码
     * @param num
     */
    public static  List<String> checkBackId(String num){
        if(num == null || num.length() == 0){return null;}
        Pattern pattern = Pattern.compile("[1-9]\\d+");
        Matcher matcher = pattern.matcher(num);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            if (StringUtils.checkBankCard(matcher.group())){
                list.add(matcher.group());
            }
        }
        return list;
    }

    /**
     * 提取字符串中符合的姓名
     * @param num
     */
    public static List<String> checkName(String num){
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(num);
        StringBuffer buffer = new StringBuffer();
        List<String> list = new ArrayList<>();
        while (m.find()) {
            buffer.append(m.group());
            int index = num.indexOf(m.group());
            if (isCharAt(num,index + 1)){
                if (!isChinese(num.charAt(index+1))){
                    buffer.append(",");
                }
            }
        }
        String strs [] = buffer.toString().split(",");
        if (strs == null){
            return null;
        }
        for (String s : strs) {
            list.add(s);
        }
        return list;
    }


    /**
     * 提取字符串中符合的手机号码
     * @param num
     */
    public static  String checkNumOne(String num){
        if(num == null || num.length() == 0){return null;}
        Pattern pattern = Pattern.compile("(13|14|15|16|17|18|19)\\d{9}");
        Matcher matcher = pattern.matcher(num);
        while (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    /**
     * 提取字符串中符合的身份证号码
     * @param num
     */
    public static  String checkIdNoOne(String num){
        if(num == null || num.length() == 0){return null;}
        Pattern pattern = Pattern.compile("[1-9]\\d{17}");
        Matcher matcher = pattern.matcher(num);
        while (matcher.find()) {
            if (IdcardValidator.isValidate18Idcard(matcher.group())){
                return matcher.group();
            }
        }
        return "";
    }

    /**
     * 提取字符串中符合的银行卡号码
     * @param num
     */
    public static  String checkBackIdOne(String num){
        if(num == null || num.length() == 0){return null;}
        Pattern pattern = Pattern.compile("[1-9]\\d+");
        Matcher matcher = pattern.matcher(num);
        while (matcher.find()) {
            if (StringUtils.checkBankCard(matcher.group())){
                return matcher.group();
            }
        }
        return "";
    }

    /**
     * 提取字符串中符合的姓名
     * @param num
     */
    public static String checkNameOne(String num){
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(num);
        StringBuffer buffer = new StringBuffer();
        while (m.find()) {
            buffer.append(m.group());
        }
        return buffer.toString();
    }



    /*
    校验过程：
    1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。
    2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，将个位十位数字相加，即将其减去9），再求和。
    3、将奇数位总和加上偶数位总和，结果应该可以被10整除。
    */
    /**
     * 校验银行卡卡号
     */
    public static boolean checkBankCard(String bankCard) {
        if(bankCard.length() < 15 || bankCard.length() > 19) {
            return false;
        }
        char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
        if(bit == 'N'){
            return false;
        }
        return bankCard.charAt(bankCard.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     * @param nonCheckCodeBankCard
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeBankCard){
        if(nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0
                || !nonCheckCodeBankCard.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeBankCard.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }

    // 根据Unicode编码完美的判断中文汉字和符号
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

}
