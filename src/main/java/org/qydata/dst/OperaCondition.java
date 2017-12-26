package org.qydata.dst;

import lombok.Data;

@Data
public class OperaCondition {

    private Integer customerId;
    private Integer vendorId;
    private Integer apiId;
    private Integer apiTypeId;
    private Integer subTypeId;
    private String apiTypeName;
    private String subTypeName;
    private Double incomeAccount;
    private Double currIncomeAccount;
    private Double yesterIncomeAccount;
    private Double yesterTotalIncomeAccount;
    private Double costAccount;
    private Double currCostAccount;
    private Double yesterCostAccount;
    private Double yesterTotalCostAccount;
    private Double currProfit;
    private Double yesterProfit;
    private Double yesterTotalProfit;
    private Double profit;
    private String profitPercent;
    private String incomePercent;
    private String costPercent;


}
