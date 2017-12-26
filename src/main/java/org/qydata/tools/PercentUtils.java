package org.qydata.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class PercentUtils {

    public static String getPercent(double curr, double yest){
        double percent = (curr - yest)/yest;

        DecimalFormat df = new DecimalFormat("##.00%");
        String format = df.format(percent);
        return format;
    }


    public static double getRound(double num){
        BigDecimal b = new BigDecimal(num);
        double round = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return round;
    }

}
