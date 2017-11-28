package org.qydata.entity.agency;

import lombok.Data;

/**
 * Created by jonhn on 2017/9/14.
 */
@Data
public class AgencyBill {

    private Integer agencyId;
    private String agencyName;
    private Double cost;
    private Double price;
    private Double rebateEndPrice;
    private Integer resultCount;
    private Double taxRate;
    private String taxRateResult;
    private Double rebateRate;
    private Double costMoney;
    private Double priceMoney;
    private Double clearingMoney;
    private Double grossProfit;
    private Double firstRebate;
    private Double twiceRebate;
    private Double netProfit;

}
