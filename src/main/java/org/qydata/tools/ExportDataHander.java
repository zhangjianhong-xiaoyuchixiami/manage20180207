package org.qydata.tools;



/**
 * Created by jonhn on 2017/3/15.
 */
public class ExportDataHander {

    public static Double pointsIntoRMB(Long data){
        if (data == null){
            return 0.0;
        }
        return (data/100.0);
    }



}
