package org.qydata.tools.finance;

import org.qydata.entity.MobileOperator;

import java.util.List;

/**
 * Created by jonhn on 2017/7/4.
 */
public class ApiTypeMobileOperatorNameUtils {


    public static String apiTypeMobileOperatorName(String name, List<MobileOperator> list){
        String resuName = name;
        if (list != null && list.size() > 0){
            resuName = resuName + "--";
            for (int i = 0; i < list.size() ; i++) {
                MobileOperator mobileOperator = list.get(i);
                if (mobileOperator != null && mobileOperator.getName() != null){
                    resuName = resuName + mobileOperator.getName() + "，";
                }else {
                    resuName = resuName.replace("--", "");
                }
            }
            if (resuName.lastIndexOf("，") != -1) {
                resuName = resuName.substring(0, resuName.lastIndexOf("，"));
            }
        }
        return resuName;
    }

    public static String apiTypeVendorMobileOperatorName(String name,String vendorName, List<MobileOperator> list){
        String resuName = name;
        if (list != null && list.size() > 0){
            resuName = resuName + "--";
            for (int i = 0; i < list.size() ; i++) {
                MobileOperator mobileOperator = list.get(i);
                if (mobileOperator != null && mobileOperator.getName() != null){
                    resuName = resuName + mobileOperator.getName() + "，";
                }
            }
            if (resuName.lastIndexOf("，") != -1) {
                resuName = resuName.substring(0, resuName.lastIndexOf("，"));
            }
        }
        if (vendorName != null){
            resuName = resuName + "@" + vendorName;
        }
        return resuName;
    }

}
