package org.qydata.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    /**
     * 获取当月的第一天
     * @return
     */

    public static String formatLastMonth(){
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date strDateTo = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String firstDayOfMonth = sdf.format(strDateTo);
        System.out.println(firstDayOfMonth);
        return firstDayOfMonth;
    }

    /**
     * 任一月的的第一天 yyyy-MM-01 00:00:00
     * @param date
     * @return
     * @throws ParseException
     */
    public static String firstDayOfMonth(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sdfy = new SimpleDateFormat("yyyy-MM-01 00:00:00");
        Date parse = sdf.parse(date);
        String parsey = sdfy.format(parse);
        return parsey;
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String currentDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentDate = sdf.format(date);
        return currentDate;
    }


}
