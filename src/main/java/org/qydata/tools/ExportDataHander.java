package org.qydata.tools;


import org.qydata.dst.ApiFinance;
import org.qydata.dst.CustomerFinance;

import java.util.*;

/**
 * Created by jonhn on 2017/3/15.
 */
public class ExportDataHander {

    /**
     * 处理导出Excel操作的分和元装换
     * @param data
     * @return
     */
    public static Double pointsIntoRMB(Long data){
        if (data == null){
            return 0.0;
        }
        return (data/100.0);
    }

    /**
     * 处理以APIVendor统计消费信息的集合封装
     * @param apiFinanceList
     * @return
     */
    public static List<ApiFinance> processApiFinance(List<ApiFinance> apiFinanceList){
        List<ApiFinance> apiFinances = new ArrayList<>();
        if (apiFinanceList != null){
            for (int i=0; i<apiFinanceList.size(); i++){
                ApiFinance apiFinance = apiFinanceList.get(i);
                ApiFinance apiFinanceOne = new ApiFinance();
                apiFinanceOne.setVendorId(apiFinance.getVendorId());
                apiFinanceOne.setVendorName(apiFinance.getVendorName());
                apiFinanceOne.setStatus(apiFinance.getStatus());
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


    public static Map<String,Object> processCompanyFinanceData(List<CustomerFinance> list){
        long weekChargeAmount = 0L;
        long weekConsumeAmount = 0L;
        long monthChargeAmount = 0L;
        long monthConsumeAmount = 0L;
        long totleChargeAmount = 0L;
        long totleConsumeAmount = 0L;
        List<CustomerFinance> customerFinanceList = new ArrayList<>();

        long weekChargeAmountDead = 0L;
        long weekConsumeAmountDead = 0L;
        long monthChargeAmountDead = 0L;
        long monthConsumeAmountDead = 0L;
        long totleChargeAmountDead = 0L;
        long totleConsumeAmountDead = 0L;
        List<CustomerFinance> customerFinanceListDead = new ArrayList<>();
        if (list != null){
            Iterator<CustomerFinance> it = list.iterator();
            while (it.hasNext()){
                CustomerFinance customerFinance = it.next();
                if (customerFinance.getCompanyStatus() == 0){
                    if (customerFinance.getChargeWeekTotleAmount() != null){
                        weekChargeAmount += customerFinance.getChargeWeekTotleAmount();
                    }
                    if (customerFinance.getConsumeWeekTotleAmount() != null){
                        weekConsumeAmount += customerFinance.getConsumeWeekTotleAmount();
                    }
                    if (customerFinance.getChargeMonthTotleAmount() != null){
                        monthChargeAmount += customerFinance.getChargeMonthTotleAmount();
                    }
                    if (customerFinance.getConsumeMonthTotleAmount() != null){
                        monthConsumeAmount += customerFinance.getConsumeMonthTotleAmount();
                    }
                    if (customerFinance.getChargeTotleAmount() != null){
                        totleChargeAmount += customerFinance.getChargeTotleAmount();
                    }
                    if (customerFinance.getConsumeTotleAmount() != null){
                        totleConsumeAmount = totleConsumeAmount + customerFinance.getConsumeTotleAmount();
                    }
                    customerFinanceList.add(customerFinance);
                }else {
                    if (customerFinance.getChargeWeekTotleAmount() != null){
                        weekChargeAmountDead += customerFinance.getChargeWeekTotleAmount();
                    }
                    if (customerFinance.getConsumeWeekTotleAmount() != null){
                        weekConsumeAmountDead += customerFinance.getConsumeWeekTotleAmount();
                    }
                    if (customerFinance.getChargeMonthTotleAmount() != null){
                        monthChargeAmountDead += customerFinance.getChargeMonthTotleAmount();
                    }
                    if (customerFinance.getConsumeMonthTotleAmount() != null){
                        monthConsumeAmountDead += customerFinance.getConsumeMonthTotleAmount();
                    }
                    if (customerFinance.getChargeTotleAmount() != null){
                        totleChargeAmountDead += customerFinance.getChargeTotleAmount();
                    }
                    if (customerFinance.getConsumeTotleAmount() != null){
                        totleConsumeAmountDead += customerFinance.getConsumeTotleAmount();
                    }
                    customerFinanceListDead.add(customerFinance);
                }

            }
        }
        System.out.println(weekChargeAmount);
        System.out.println(weekConsumeAmount);
        System.out.println(monthChargeAmount);
        System.out.println(monthConsumeAmount);
        System.out.println(totleChargeAmount);
        System.out.println(totleConsumeAmount);

        System.out.println(weekChargeAmountDead);
        System.out.println(weekConsumeAmountDead);
        System.out.println(monthChargeAmountDead);
        System.out.println(monthConsumeAmountDead);
        System.out.println(totleChargeAmountDead);
        System.out.println(totleConsumeAmountDead);
        Map<String,Object> mapTran = new HashMap<>();
        mapTran.put("weekChargeAmount",weekChargeAmount);
        mapTran.put("weekConsumeAmount",weekConsumeAmount);
        mapTran.put("monthChargeAmount",monthChargeAmount);
        mapTran.put("monthConsumeAmount",monthConsumeAmount);
        mapTran.put("totleChargeAmount",totleChargeAmount);
        mapTran.put("totleConsumeAmount",totleConsumeAmount);
        mapTran.put("customerFinanceList",customerFinanceList);

        mapTran.put("weekChargeAmountDead",weekChargeAmountDead);
        mapTran.put("weekConsumeAmountDead",weekConsumeAmountDead);
        mapTran.put("monthChargeAmountDead",monthChargeAmountDead);
        mapTran.put("monthConsumeAmountDead",monthConsumeAmountDead);
        mapTran.put("totleChargeAmountDead",totleChargeAmountDead);
        mapTran.put("totleConsumeAmountDead",totleConsumeAmountDead);
        mapTran.put("customerFinanceListDead",customerFinanceListDead);
        return mapTran;
    }


}
