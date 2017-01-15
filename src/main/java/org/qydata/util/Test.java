package org.qydata.util;

import org.qydata.tools.CalendarTools;

/**
 * Created by jonhn on 2017/1/6.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        System.out.println(CalendarTools.getYearWeekFirstDay(2016,52));
        System.out.println(CalendarTools.getYearWeekEndDay(2016,52));
        System.out.println(CalendarTools.getYearMonthCount());
        System.out.println(CalendarTools.getMonthCount());
    }
}


