package org.qydata.dst;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/8/2.
 */
public class VendorHistoryBill implements Serializable {

    private Integer vendorId;
    private Double consumeAmount;
    private String vendorName;
    private Integer partnerId;
    private String partnerName;
    private Double chargeAmount;
    private Integer status;
    private Double balance;
    private Double currMonthRealTimeConsume;
    private Double currDayRealTimeConsume;
    private Double staticConsumeAmount;

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Double getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Double consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
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

    public Double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Double chargeAmount) {
        this.chargeAmount = chargeAmount;
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

    public Double getCurrMonthRealTimeConsume() {
        return currMonthRealTimeConsume;
    }

    public void setCurrMonthRealTimeConsume(Double currMonthRealTimeConsume) {
        this.currMonthRealTimeConsume = currMonthRealTimeConsume;
    }

    public Double getCurrDayRealTimeConsume() {
        return currDayRealTimeConsume;
    }

    public void setCurrDayRealTimeConsume(Double currDayRealTimeConsume) {
        this.currDayRealTimeConsume = currDayRealTimeConsume;
    }

    public Double getStaticConsumeAmount() {
        return staticConsumeAmount;
    }

    public void setStaticConsumeAmount(Double staticConsumeAmount) {
        this.staticConsumeAmount = staticConsumeAmount;
    }
}
