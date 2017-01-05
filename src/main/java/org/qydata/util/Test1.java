package org.qydata.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2017/1/5.
 */
public class Test1 {

//    public static List<String[]> getWeeksByYear(final int year){
//        if(year<1900 || year >9999){
//            throw new NullPointerException("年度必须大于等于1900年小于等于9999年");
//        }
//        //实现思路，首先计算当年有多少个周，然后找到每个周的开始日期和结束日期
////      Calendar calendar = new GregorianCalendar();
////      // 在具有默认语言环境的默认时区内使用当前时间构造一个默认的 GregorianCalendar。
////      calendar.setFirstDayOfWeek(Calendar.MONDAY); //设置每周的第一天为星期一
////      calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //每周从周一开始
////      上面两句代码配合，才能实现，每年度的第一个周，是包含第一个星期一的那个周。
////      calendar.setMinimalDaysInFirstWeek(7);  //设置每周最少为7天
////      calendar.set(Calendar.YEAR, year); // 设置年度为指定的年
////      //首先计算当年有多少个周,每年都至少有52个周，个别年度有53个周
//        int weeks = getWeekNumByYear(year);
////      System.out.println(year+"共有"+weeks+"个周");
//        List<String[]> result = new ArrayList<String[]>(weeks);
//        for(int i=1;i<=weeks;i++){
//            String[] tempWeek = new String[2];
//            tempWeek[0] = getYearWeekFirstDay(year,i);
//            tempWeek[1] = getYearWeekEndDay (year,i);
////或者使用下面的代码，不过发现效率更低
////          tempWeek[0] = getDateAdd(firstWeekDay,(i-1)*7+0);
////          tempWeek[1] = getDateAdd(firstWeekDay,(i-1)*7+6);
//            result.add(tempWeek);
////          System.out.println(i+"="+tempWeek[0]+"_"+tempWeek[1]);
//        }
//        return result;
//    }

//    /**
//     * 计算指定年度共有多少个周。
//     * @param year 格式 yyyy  ，必须大于1900年度 小于9999年
//     * @return
//     */
//    public static int getWeekNumByYear(final int year){
//        if(year<1900 || year >9999){
//            throw new NullPointerException("年度必须大于等于1900年小于等于9999年");
//        }
//        int result = 52;//每年至少有52个周 ，最多有53个周。
//        String date = getYearWeekFirstDay(year,53);
//        if(date.substring(0, 4).equals(year+"")){ //判断年度是否相符，如果相符说明有53个周。
//            result = 53;
//        }
//        return result;
//    }

    /**
     * 计算某年某周的开始日期
     * @param yearNum 格式 yyyy  ，必须大于1900年度 小于9999年
     * @param weekNum 1到52或者53
     * @return 日期，格式为yyyy-MM-dd
     */
    public static String getYearWeekFirstDay(int yearNum, int weekNum)  {
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //分别取得当前日期的年、月、日
        return sdf.format(cal.getTime());
    }

    /**
     * 计算某年某周的结束日期
     * @param yearNum 格式 yyyy  ，必须大于1900年度 小于9999年
     * @param weekNum 1到52或者53
     * @return 日期，格式为yyyy-MM-dd
     */
    public static String getYearWeekEndDay(int yearNum, int weekNum)  {
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //分别取得当前日期的年、月、日
        return sdf.format(cal.getTime());
    }

    public static String getYearMonthEndDay(int yearNum,int monthNum)  {
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
        return yearNum+"-"+monthNum+"-"+days;
    }

    public static String getYearMonthFirstDay(int yearNum,int monthNum)  {
        if(yearNum<1900 || yearNum >9999){
            throw new NullPointerException("年度必须大于等于1900年小于等于9999年");
        }
        if(monthNum<1||monthNum>12){
            throw new NullPointerException("月份必须大于等于1月小于等于12月");
        }
        return yearNum+"-"+monthNum+"-"+1;
    }
}
