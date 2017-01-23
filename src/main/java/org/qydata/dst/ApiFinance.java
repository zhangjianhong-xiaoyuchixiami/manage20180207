package org.qydata.dst;

import org.qydata.entity.MobileOperator;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/1/18.
 */
public class ApiFinance implements Serializable {

    private Integer apiId;
    private Integer apiTypeId;
    private String apiTypeName;
    private Integer vendorId;
    private String vendorName;
    private String apiName;
    private Long balance;
    private Long weekTotleCost;
    private Long monthTotleCost;
    private Long consumeTotleAmount;
    private MobileOperator mobileOperator;

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getApiTypeId() {
        return apiTypeId;
    }

    public void setApiTypeId(Integer apiTypeId) {
        this.apiTypeId = apiTypeId;
    }

    public String getApiTypeName() {
        return apiTypeName;
    }

    public void setApiTypeName(String apiTypeName) {
        this.apiTypeName = apiTypeName;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getWeekTotleCost() {
        return weekTotleCost;
    }

    public void setWeekTotleCost(Long weekTotleCost) {
        this.weekTotleCost = weekTotleCost;
    }

    public Long getMonthTotleCost() {
        return monthTotleCost;
    }

    public void setMonthTotleCost(Long monthTotleCost) {
        this.monthTotleCost = monthTotleCost;
    }

    public Long getConsumeTotleAmount() {
        return consumeTotleAmount;
    }

    public void setConsumeTotleAmount(Long consumeTotleAmount) {
        this.consumeTotleAmount = consumeTotleAmount;
    }

    public MobileOperator getMobileOperator() {
        return mobileOperator;
    }

    public void setMobileOperator(MobileOperator mobileOperator) {
        this.mobileOperator = mobileOperator;
    }
}
