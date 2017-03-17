package org.qydata.tools;


import org.qydata.dst.ApiFinance;

import java.util.ArrayList;
import java.util.List;

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

    public static List<ApiFinance> processApiFinance(List<ApiFinance> apiFinanceList){
        List<ApiFinance> apiFinances = new ArrayList<>();
        if (apiFinanceList != null){
            for (int i=0; i<apiFinanceList.size(); i++){
                ApiFinance apiFinance = apiFinanceList.get(i);
                ApiFinance apiFinanceOne = new ApiFinance();
                apiFinanceOne.setVendorId(apiFinance.getVendorId());
                apiFinanceOne.setVendorName(apiFinance.getVendorName());
                apiFinanceOne.setApiTypeList(apiFinance.getApiTypeList());
                apiFinanceOne.setPartnerName(apiFinance.getPartnerName());
                apiFinanceOne.setPartnerId(apiFinance.getPartnerId());
                apiFinanceOne.setWeekTotleCost(apiFinance.getWeekTotleCost());
                apiFinanceOne.setMonthTotleCost(apiFinance.getMonthTotleCost());
                apiFinanceOne.setConsumeTotleAmount(apiFinance.getConsumeTotleAmount());
                if(apiFinance.getBalance() != null && apiFinance.getConsumeTotleAmount() != null){
                    apiFinanceOne.setBalance(apiFinance.getBalance()-apiFinance.getConsumeTotleAmount());
                }else if (apiFinance.getBalance() != null && apiFinance.getConsumeTotleAmount() == null){
                    apiFinanceOne.setBalance(apiFinance.getBalance());
                }else if (apiFinance.getBalance() == null && apiFinance.getConsumeTotleAmount() == null){
                    apiFinanceOne.setBalance(0L);
                }else {
                    apiFinanceOne.setBalance(-apiFinance.getConsumeTotleAmount());
                }
                apiFinances.add(apiFinanceOne);
            }
        }
        return apiFinances;
    }


}
