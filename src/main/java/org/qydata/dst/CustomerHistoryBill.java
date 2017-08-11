package org.qydata.dst;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/8/2.
 */
public class CustomerHistoryBill implements Serializable {

    private Integer customerId;
    private Double floor;
    private Double balance;
    private Double userFloor;
    private Integer companyId;
    private Integer partnerId;
    private String companyName;
    private String partnerName;
    private Double chargeAmount;
    private Double chargeCurrDayAmount;
    private Double consumeAmount;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getFloor() {
        return floor;
    }

    public void setFloor(Double floor) {
        this.floor = floor;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getUserFloor() {
        return userFloor;
    }

    public void setUserFloor(Double userFloor) {
        this.userFloor = userFloor;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Double getChargeCurrDayAmount() {
        return chargeCurrDayAmount;
    }

    public void setChargeCurrDayAmount(Double chargeCurrDayAmount) {
        this.chargeCurrDayAmount = chargeCurrDayAmount;
    }

    public Double getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Double consumeAmount) {
        this.consumeAmount = consumeAmount;
    }
}
