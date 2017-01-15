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

    /**
     * 计算某年某月的起始日期
     * @param yearNum
     * @param monthNum
     * @return
     * @throws ParseException
     */
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

    /**
     * 计算某年某月的结束日期
     * @param yearNum
     * @param monthNum
     * @return
     * @throws ParseException
     */
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

    /**
     * 根据系统时间获取上一周属于第几周
     * @return
     */
    public static Integer getYearWeekCount(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//可以方便地修改日期格
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        c.add(Calendar.WEEK_OF_MONTH,-1);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int date = c.get(Calendar.DATE);
        String today = year + "/" + month + "/" + date;
        //String today = "2017/01/01";
        System.out.println(today);
        Date data = null;
        try {
            data = dateFormat.parse(today);
            c.setFirstDayOfWeek(Calendar.MONDAY);
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//每周从周一开始
            c.setMinimalDaysInFirstWeek(7);  //设置每周最少为7天
            c.setTime(data);
            System.out.println(c.get(Calendar.WEEK_OF_YEAR));
            System.out.println(c.get(Calendar.YEAR));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 根据系统时间获取上一周的年份
     * @return
     */
    public static Integer getYearCount(){
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        c.add(Calendar.WEEK_OF_MONTH,-1);
        String str = c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONDAY)+1)+"-"+c.get(Calendar.DATE);
        if (str.equals(c.get(Calendar.YEAR)+"-"+1+"-"+1)){
            if (!CalendarTools.booleanWeek(str)){
                return (c.get(Calendar.YEAR)-1);
            }
        }
        System.out.println(str);
        System.out.println(c.get(Calendar.YEAR)+"-"+1+"-"+1);
        return c.get(Calendar.YEAR);
    }

    /**
     * 判断每年一月一号是周几
     * @param str
     * @return
     */
    public static boolean booleanWeek(String str){
        Calendar c = Calendar.getInstance(java.util.Locale.CHINA);
        String[] sp = str.split("-");
        c.set(Calendar.YEAR,Integer.parseInt(sp[0]));
        c.set(Calendar.MONTH,Integer.parseInt(sp[1])-1);
        c.set(Calendar.DATE,Integer.parseInt(sp[2]));
        int wd = c.get(Calendar.DAY_OF_WEEK);
        if (wd == 2){
            return true;
        }
        return false;
    }

    /**
     * 根据系统时间获取上一月的月份
     * @return
     */
    public static Integer getYearMonthCount(){
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        c.add(Calendar.MONTH,-1);
        Integer year = c.get(Calendar.YEAR);
       return year;
    }

    /**
     * 根据系统时间获取上一月的年份
     * @return
     */
    public static Integer getMonthCount(){
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        c.add(Calendar.MONTH,-1);
        Integer month = c.get(Calendar.MONTH)+1;
        return month;
    }
}
