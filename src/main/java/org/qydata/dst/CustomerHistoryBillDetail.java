package org.qydata.dst;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/8/3.
 */
public class CustomerHistoryBillDetail implements Serializable {

    private Integer id;
    private Integer year;
    private Integer month;
    private String yearMonth;
    private Integer customerId;
    private Integer apiTypeId;
    private Integer stid;
    private Double cost;
    private Integer amount;
    private String apiTypeName;
    private String stidName;
    private Double consumeAmount;
    private Integer isLock;
    private String isLockName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getApiTypeId() {
        return apiTypeId;
    }

    public void setApiTypeId(Integer apiTypeId) {
        this.apiTypeId = apiTypeId;
    }

    public Integer getStid() {
        return stid;
    }

    public void setStid(Integer stid) {
        this.stid = stid;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getApiTypeName() {
        return apiTypeName;
    }

    public void setApiTypeName(String apiTypeName) {
        this.apiTypeName = apiTypeName;
    }

    public String getStidName() {
        return stidName;
    }

    public void setStidName(String stidName) {
        this.stidName = stidName;
    }

    public Double getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Double consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public String getIsLockName() {
        return isLockName;
    }

    public void setIsLockName(String isLockName) {
        this.isLockName = isLockName;
    }
}
