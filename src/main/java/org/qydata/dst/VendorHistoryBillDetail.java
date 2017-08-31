package org.qydata.dst;

import org.qydata.entity.MobileOperator;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonhn on 2017/8/2.
 */
public class VendorHistoryBillDetail implements Serializable {

    private Integer id;
    private Integer year;
    private Integer month;
    private String yearMonth;
    private Integer vendorId;
    private Integer apiId;
    private Integer apiTypeId;
    private Integer stid;
    private Double cost;
    private Integer amount;
    private String apiTypeName;
    private String stidName;
    private Double consumeAmount;
    private Integer isLock;
    private String isLockName;
    private List<MobileOperator> mobileList;

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

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

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

    public List<MobileOperator> getMobileList() {
        return mobileList;
    }

    public void setMobileList(List<MobileOperator> mobileList) {
        this.mobileList = mobileList;
    }
}
