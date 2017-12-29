package org.qydata.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PercentUtils {

    public static String getPercent(double curr, double yest){
        double percent = ((float)curr - (float)yest)/(float)yest * 100;
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format(percent);
        String p = result + "%";
        return p;
    }


    public static double getRound(double num){
        BigDecimal b = new BigDecimal(num);
        double round = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return round;
    }
}
