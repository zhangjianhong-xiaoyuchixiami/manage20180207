package org.qydata.dst;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/2/7.
 */
public class PartnerFinance implements Serializable {

    private Integer partnerId;
    private String partnerName;
    private Long totleIncomeAmount;
    private Long totleExpenditureAmount;
    private Long weekIncomeAmount;
    private Long monthIncomeAmount;
    private Long weekExpenditureAmount;
    private Long monthExpenditureAmount;

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

    public Long getTotleIncomeAmount() {
        return totleIncomeAmount;
    }

    public void setTotleIncomeAmount(Long totleIncomeAmount) {
        this.totleIncomeAmount = totleIncomeAmount;
    }

    public Long getTotleExpenditureAmount() {
        return totleExpenditureAmount;
    }

    public void setTotleExpenditureAmount(Long totleExpenditureAmount) {
        this.totleExpenditureAmount = totleExpenditureAmount;
    }

    public Long getWeekIncomeAmount() {
        return weekIncomeAmount;
    }

    public void setWeekIncomeAmount(Long weekIncomeAmount) {
        this.weekIncomeAmount = weekIncomeAmount;
    }

    public Long getMonthIncomeAmount() {
        return monthIncomeAmount;
    }

    public void setMonthIncomeAmount(Long monthIncomeAmount) {
        this.monthIncomeAmount = monthIncomeAmount;
    }

    public Long getWeekExpenditureAmount() {
        return weekExpenditureAmount;
    }

    public void setWeekExpenditureAmount(Long weekExpenditureAmount) {
        this.weekExpenditureAmount = weekExpenditureAmount;
    }

    public Long getMonthExpenditureAmount() {
        return monthExpenditureAmount;
    }

    public void setMonthExpenditureAmount(Long monthExpenditureAmount) {
        this.monthExpenditureAmount = monthExpenditureAmount;
    }
}
