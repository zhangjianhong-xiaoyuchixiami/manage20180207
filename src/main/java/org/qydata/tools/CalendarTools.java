package org.qydata.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jonhn on 2017/1/6.
 */
public class CalendarTools {

    /**
     * 计算某年某周的开始日期
     * @param yearNum 格式 yyyy  ，必须大于1900年度 小于9999年
     * @param weekNum 1到52或者53
     * @return 日期，格式为yyyy-MM-dd
     */
    public static Date getYearWeekFirstDay(int yearNum, int weekNum)throws Exception  {
        if(yearNum<1900 || yearNum >9999){
            throw new NullPointerException("年度必须大于等于1900年小于等于9999年");
        }
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY); //设置每周的第一天为星期一
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//每周从周一开始
//       上面两句代码配合，才能实现，每年度的第一个周，是包含第一个星期一的那个周。
        cal.setMinimalDaysInFirstWeek(7);  //设置每周最少为7天
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);
        //分别取得当前日期的年、月、日

        return  cal.getTime();
    }

    /**
     * 计算某年某周的结束日期
     * @param yearNum 格式 yyyy  ，必须大于1900年度 小于9999年
     * @param weekNum 1到52或者53
     * @return 日期，格式为yyyy-MM-dd
     */
    public static Date getYearWeekEndDay(int yearNum, int weekNum)throws Exception  {
        if(yearNum<1900 || yearNum >9999){
            throw new NullPointerException("年度必须大于等于1900年小于等于9999年");
        }
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY); //设置每周的第一天为星期一
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//每周从周一开始
//       上面两句代码配合，才能实现，每年度的第一个周，是包含第一个星期一的那个周。
        cal.setMinimalDaysInFirstWeek(7);  //设置每周最少为7天
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);
        //分别取得当前日期的年、月、日

        return  cal.getTime();
    }

    public static Date getYearMonthEndDay(int yearNum,int monthNum) throws ParseException {
        if(yearNum<1900 || yearNum >9999){
            throw new NullPointerException("年度必须大于等于1900年小于等于9999年");
        }
        int days = 0;
        if((yearNum % 400 == 0)||(yearNum % 4 == 0)&&(yearNum % 100 != 0)){
            if(monthNum == 2){
                days=29;
            }
        }else {
            if(monthNum == 2){
                days=28;
            }
        }
        if(monthNum == 1||monthNum == 3||monthNum == 5 || monthNum == 7||monthNum == 8||monthNum == 10||monthNum == 12){
            days=31;
        }
        if(monthNum == 4||monthNum == 6||monthNum == 9 || monthNum == 11){
            days=30;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String yearMonthEndDay = yearNum+"-"+monthNum+"-"+days;
        return sdf.parse(yearMonthEndDay);
    }

    public static Date getYearMonthFirstDay(int yearNum,int monthNum) throws ParseException {
        if(yearNum<1900 || yearNum >9999){
            throw new NullPointerException("年度必须大于等于1900年小于等于9999年");
        }
        if(monthNum<1||monthNum>12){
            throw new NullPointerException("月份必须大于等于1月小于等于12月");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String yearMonthFirstDay = yearNum+"-"+monthNum+"-"+1;
        return sdf.parse(yearMonthFirstDay);
    }
}
