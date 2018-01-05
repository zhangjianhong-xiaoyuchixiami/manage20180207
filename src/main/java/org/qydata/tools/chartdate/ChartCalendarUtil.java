package org.qydata.tools.chartdate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jonhn on 2017/3/8.
 */
public class ChartCalendarUtil {

//    public static void main(String[] args) {
//        for (int i = 0; i <= 5; i++){
//            System.out.println(getCurrentDateAssignYearMonth(i));
//        }
//    }

    /**
     * 取得当前时间的指定月的时间，格式“yyyy-MM”
     * @return
     */
    public static String getCurrentDateAssignYearMonth(int amount) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,- amount-1);
        Date monday = calendar.getTime();
        String preMonday = sdf.format(monday);
        return preMonday;
    }

    /**
     * 取得当前时间的指定天的时间，格式“yyyy-MM-dd”
     * @return
     */
    public static String getCurrentDateAssignYearMonthDay(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,1);
        int year = calendar.get(Calendar.YEAR);
        calendar.add(Calendar.DATE,amount);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        return year +"-"+ month + "-" + day ;
    }

    public static String getStatetime(int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, - i);
        Date monday = c.getTime();
        String preMonday = sdf.format(monday);
        return preMonday;
    }


}
