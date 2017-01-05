package org.qydata.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2016/12/10.
 */
public class Test {

    public static void main(String[] args)throws Exception {
//        String s = "2016-";
//        for (int i = 1; i <= 12; i++) {
//            System.out.println("*******************************************");
//            System.out.println("月份:" + i);
//            printfWeeks(s + i);
//            System.out.println("*******************************************");
//        }
        String weekFirstDay = Test1.getYearWeekFirstDay(2016,52);
        String weekEndDay = Test1.getYearWeekEndDay(2016,52);
        String monthFirstDay = Test1.getYearMonthFirstDay(2017,2);
        String monthEndDay = Test1.getYearMonthEndDay(2017,2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(sdf.parse(weekFirstDay));
        System.out.println(sdf.parse(weekEndDay));
        System.out.println(sdf.parse(monthFirstDay));
        System.out.println(sdf.parse(monthEndDay));
        System.out.println(weekFirstDay+" "+"00:00:00");
        System.out.println(weekEndDay+" "+"23:59:59");
        System.out.println(monthFirstDay+" "+"00:00:00");
        System.out.println(monthEndDay+" "+"23:59:59");
        System.out.println(sdf1.parse(weekFirstDay+" "+"00:00:00"));
        System.out.println(sdf1.parse(weekEndDay+" "+"23:59:59"));
        System.out.println(sdf1.parse(monthFirstDay+" "+"00:00:00"));
        System.out.println(sdf1.parse(monthEndDay+" "+"23:59:59"));



    }

    public static void printfWeeks(String date) throws Exception {
        // String date = "2013-09";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date date1 = dateFormat.parse(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date1);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println("days:" + days);
        int count = 0;
        for (int i = 1; i <= days; i++) {
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = dateFormat1.parse(date + "-" + i);
            calendar.clear();
            calendar.setTime(date2);
            int k = new Integer(calendar.get(Calendar.DAY_OF_WEEK));
            if (k == 1) {// 若当天是周日
                count++;
                System.out.println("-----------------------------------");
                System.out.println("第" + count + "周");
                if (i - 6 <= 1) {
                    System.out.println("本周开始日期:" + date + "-" + 1);
                } else {
                    System.out.println("本周开始日期:" + date + "-" + (i - 6));
                }
                System.out.println("本周结束日期:" + date + "-" + i);
                System.out.println("-----------------------------------");
            }
            if (k != 1 && i == days) {// 若是本月最好一天，且不是周日
                count++;
                System.out.println("-----------------------------------");
                System.out.println("第" + count + "周");
                System.out.println("本周开始日期:" + date + "-" + (i - k + 2));
                System.out.println("本周结束日期:" + date + "-" + i);
                System.out.println("-----------------------------------");
            }
        }
    }


}
