package org.qydata.tools.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jonhn on 2017/3/8.
 */
public class CalendarUtil_2 {



    /**
     * 取得当前月的第一天
     * @return
     */
    public static String getCurrentMonthFirstDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);
        String firstDay = sdf.format(calendar.getTime());
        return firstDay;
    }

    /**
     * 取得当前月上一个月的第一天
     * @return
     */
    public static String getCurrentLastMonthFirstDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        String firstDay = sdf.format(calendar.getTime());
        return firstDay;
    }


    /**
     * 取得当前月上一个月，格式“yyyy-MM”
     * @return
     */
    public static String getCurrentLastMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        String firstDay = sdf.format(calendar.getTime());
        return firstDay;
    }

}
