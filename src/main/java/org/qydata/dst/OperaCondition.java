package org.qydata.dst;

import lombok.Data;

@Data
public class OperaCondition {

    private Integer customerId;
    private Integer vendorId;
    private Integer apiId;
    private Integer apiTypeId;
    private Integer subTypeId;
    private Double incomeAccount;
    private Double currIncomeAccount;
    private Double yesterIncomeAccount;
    private Double costAccount;
    private Double currCostAccount;
    private Double yesterCostAccount;
    private Double currProfit;
    private Double yesterProfit;

}
