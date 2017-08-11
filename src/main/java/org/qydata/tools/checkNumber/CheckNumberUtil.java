package org.qydata.tools.checkNumber;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by jonhn on 2017/8/8.
 */
public class CheckNumberUtil {

    /**
     * 正则表达式匹配
     *
     * @param text 待匹配的文本
     * @param reg 正则表达式
     * @return
     * @author jiqinlin
     */
    private final static boolean match(String text, String reg) {
        if (StringUtils.isBlank(text) || StringUtils.isBlank(reg))
            return false;
        return Pattern.compile(reg).matcher(text).matches();
    }

    public final static boolean isStipulateNumeric(Object str) {
        if(match(str.toString(),"^(\\d|[1-9]\\d+)(\\.\\d+)?$")){
            if (Double.parseDouble(str.toString()) <= 5){
                return true;
            }
        }
        return false;
    }


}
