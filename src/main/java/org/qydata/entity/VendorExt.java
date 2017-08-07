package org.qydata.entity;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/8/7.
 */
public class VendorExt implements Serializable {

    private Integer vendorId;
    private String vendorName;
    private Integer isPrepay;
    private String isPrepayName;
    private Integer isBalWarn;
    private Integer partnerId;
    private String partnerName;
    private Double balance;
    private Double remaining;
    private Double totleCost;
    private Double currDayCost;

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

    public Integer getIsPrepay() {
        return isPrepay;
    }

    public void setIsPrepay(Integer isPrepay) {
        this.isPrepay = isPrepay;
    }

    public String getIsPrepayName() {
        return isPrepayName;
    }

    public void setIsPrepayName(String isPrepayName) {
        this.isPrepayName = isPrepayName;
    }

    public Integer getIsBalWarn() {
        return isBalWarn;
    }

    public void setIsBalWarn(Integer isBalWarn) {
        this.isBalWarn = isBalWarn;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getRemaining() {
        return remaining;
    }

    public void setRemaining(Double remaining) {
        this.remaining = remaining;
    }

    public Double getTotleCost() {
        return totleCost;
    }

    public void setTotleCost(Double totleCost) {
        this.totleCost = totleCost;
    }

    public Double getCurrDayCost() {
        return currDayCost;
    }

    public void setCurrDayCost(Double currDayCost) {
        this.currDayCost = currDayCost;
    }
}
