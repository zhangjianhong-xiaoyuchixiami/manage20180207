package org.qydata.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jonhn on 2016/11/8.
 */

public class CustomerBalanceLog implements Serializable{

    private Long id;
    private Integer reasonId;
    private Integer customerId;
    private Long amount;
    private Long reqId;
    private Timestamp createTime;
    private CustomerBalanceModifyReason customerBalanceModifyReason;
    private Api api;
    public CustomerBalanceLog(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReasonId() {
        return reasonId;
    }

    public void setReasonId(Integer reasonId) {
        this.reasonId = reasonId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public CustomerBalanceModifyReason getCustomerBalanceModifyReason() {
        return customerBalanceModifyReason;
    }

    public void setCustomerBalanceModifyReason(CustomerBalanceModifyReason customerBalanceModifyReason) {
        this.customerBalanceModifyReason = customerBalanceModifyReason;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public String toString(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
