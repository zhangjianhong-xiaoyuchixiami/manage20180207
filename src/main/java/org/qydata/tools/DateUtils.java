package org.qydata.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

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


    /**
     * 将日期格式转换为字符串
     *
     * @return
     */
    public static Integer formatCurrentDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateTime = sdf.format(date);
        return Integer.valueOf(dateTime);
    }
}
