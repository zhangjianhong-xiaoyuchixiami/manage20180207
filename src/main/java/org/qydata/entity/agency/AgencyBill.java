package org.qydata.entity.agency;

/**
 * Created by jonhn on 2017/9/14.
 */
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

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRebateEndPrice() {
        return rebateEndPrice;
    }

    public void setRebateEndPrice(Double rebateEndPrice) {
        this.rebateEndPrice = rebateEndPrice;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public String getTaxRateResult() {
        return taxRateResult;
    }

    public void setTaxRateResult(String taxRateResult) {
        this.taxRateResult = taxRateResult;
    }

    public Double getRebateRate() {
        return rebateRate;
    }

    public void setRebateRate(Double rebateRate) {
        this.rebateRate = rebateRate;
    }

    public Double getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(Double costMoney) {
        this.costMoney = costMoney;
    }

    public Double getPriceMoney() {
        return priceMoney;
    }

    public void setPriceMoney(Double priceMoney) {
        this.priceMoney = priceMoney;
    }

    public Double getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(Double grossProfit) {
        this.grossProfit = grossProfit;
    }

    public Double getFirstRebate() {
        return firstRebate;
    }

    public void setFirstRebate(Double firstRebate) {
        this.firstRebate = firstRebate;
    }

    public Double getTwiceRebate() {
        return twiceRebate;
    }

    public void setTwiceRebate(Double twiceRebate) {
        this.twiceRebate = twiceRebate;
    }

    public Double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Double netProfit) {
        this.netProfit = netProfit;
    }

    public Double getClearingMoney() {
        return clearingMoney;
    }

    public void setClearingMoney(Double clearingMoney) {
        this.clearingMoney = clearingMoney;
    }
}
