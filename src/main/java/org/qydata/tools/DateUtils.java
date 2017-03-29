package org.qydata.tools;

/**
 * Created by jonhn on 2017/3/28.
 */
public class DateUtils {

    /**
     * 获取两日期时间差
     * @param between
     * @return
     */
    public static String formatDateTime(long between){
        long day = between  / (24 * 60 * 60 * 1000);
        long hour = (between  / (60 * 60 * 1000) - day * 24);
        long min = ((between  / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between  / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long ms = (between  - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return  hour+":"+min+":"+s+"."+ms;
    }
}
