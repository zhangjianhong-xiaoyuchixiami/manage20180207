package org.qydata.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jonhn on 2017/2/7.
 */
public class PartnerIncomeExpenditureLog implements Serializable {

    private Integer id;
    private Integer partnerId;
    private Long amount;
    private Integer reasonId;
    private String remark;
    private Date createTime;
    private PartnerIncomeExpenditureReason partnerIncomeExpenditureReason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getReasonId() {
        return reasonId;
    }

    public void setReasonId(Integer reasonId) {
        this.reasonId = reasonId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PartnerIncomeExpenditureReason getPartnerIncomeExpenditureReason() {
        return partnerIncomeExpenditureReason;
    }

    public void setPartnerIncomeExpenditureReason(PartnerIncomeExpenditureReason partnerIncomeExpenditureReason) {
        this.partnerIncomeExpenditureReason = partnerIncomeExpenditureReason;
    }
}
