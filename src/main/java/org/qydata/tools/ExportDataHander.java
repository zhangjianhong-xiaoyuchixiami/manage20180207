package org.qydata.tools;


/**
 * Created by jonhn on 2017/3/15.
 */
public class ExportDataHander {

    /**
     * 处理导出Excel操作的分和元装换
     * @param data
     * @return
     */
    public static Double pointsIntoRMB(Integer data){
        if (data == null){
            return 0.0;
        }
        return (data/100.0);
    }

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
    /*public static List<ApiFinance> processApiFinance(List<ApiFinance> apiFinanceList){
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
    }*/




}
