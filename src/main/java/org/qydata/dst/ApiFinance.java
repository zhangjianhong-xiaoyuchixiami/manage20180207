package org.qydata.dst;

import org.qydata.entity.ApiType;
import org.qydata.entity.MobileOperator;

import java.io.Serializable;
import java.util.List;

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
    private Integer status;
    private Double balance;
    private Double weekTotleCost;
    private Double monthTotleCost;
    private Double consumeTotleAmount;
    private Integer usageAmount;
    private Integer feeUsageAmount;
    private Double currMonthCost;
    private Double currDayCost;
    private Integer currMonthUsageAmount;
    private Integer currDayUsageAmount;
    private Integer currDayFeeAmount;
    private Integer partnerId;
    private String partnerName;
    private List<MobileOperator> mobileOperatorList;
    private List<ApiType> apiTypeList;
    private List<ApiTypeConsume> apiTypeConsumeList;
    private Double chargeAmount;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getWeekTotleCost() {
        return weekTotleCost;
    }

    public void setWeekTotleCost(Double weekTotleCost) {
        this.weekTotleCost = weekTotleCost;
    }

    public Double getMonthTotleCost() {
        return monthTotleCost;
    }

    public void setMonthTotleCost(Double monthTotleCost) {
        this.monthTotleCost = monthTotleCost;
    }

    public Double getConsumeTotleAmount() {
        return consumeTotleAmount;
    }

    public void setConsumeTotleAmount(Double consumeTotleAmount) {
        this.consumeTotleAmount = consumeTotleAmount;
    }

    public Integer getUsageAmount() {
        return usageAmount;
    }

    public void setUsageAmount(Integer usageAmount) {
        this.usageAmount = usageAmount;
    }

    public Integer getFeeUsageAmount() {
        return feeUsageAmount;
    }

    public void setFeeUsageAmount(Integer feeUsageAmount) {
        this.feeUsageAmount = feeUsageAmount;
    }

    public Double getCurrMonthCost() {
        return currMonthCost;
    }

    public void setCurrMonthCost(Double currMonthCost) {
        this.currMonthCost = currMonthCost;
    }

    public Double getCurrDayCost() {
        return currDayCost;
    }

    public void setCurrDayCost(Double currDayCost) {
        this.currDayCost = currDayCost;
    }

    public Integer getCurrMonthUsageAmount() {
        return currMonthUsageAmount;
    }

    public void setCurrMonthUsageAmount(Integer currMonthUsageAmount) {
        this.currMonthUsageAmount = currMonthUsageAmount;
    }

    public Integer getCurrDayUsageAmount() {
        return currDayUsageAmount;
    }

    public void setCurrDayUsageAmount(Integer currDayUsageAmount) {
        this.currDayUsageAmount = currDayUsageAmount;
    }

    public Integer getCurrDayFeeAmount() {
        return currDayFeeAmount;
    }

    public void setCurrDayFeeAmount(Integer currDayFeeAmount) {
        this.currDayFeeAmount = currDayFeeAmount;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public List<MobileOperator> getMobileOperatorList() {
        return mobileOperatorList;
    }

    public void setMobileOperatorList(List<MobileOperator> mobileOperatorList) {
        this.mobileOperatorList = mobileOperatorList;
    }

    public List<ApiType> getApiTypeList() {
        return apiTypeList;
    }

    public void setApiTypeList(List<ApiType> apiTypeList) {
        this.apiTypeList = apiTypeList;
    }

    public List<ApiTypeConsume> getApiTypeConsumeList() {
        return apiTypeConsumeList;
    }

    public void setApiTypeConsumeList(List<ApiTypeConsume> apiTypeConsumeList) {
        this.apiTypeConsumeList = apiTypeConsumeList;
    }

    public Double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }
}
