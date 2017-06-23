package org.qydata.tools.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jonhn on 2017/3/8.
 */
public class CalendarUtil {


    /**
     * 取得当前时间的前一小时
     * @return
     */
    public static String getCurrentLastHour(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) - 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(c.getTime());
    }

    /**
     * 取得当前时间的上一个月份的年份
     * @return
     */
    public static int getCurrentDateLastMonthYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        return year ;
    }

    /**
     * 取得当前时间的上一个月份的月份
     * @return
     */
    public static int getCurrentDateLastMonthMonth() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        return month;
    }


}
