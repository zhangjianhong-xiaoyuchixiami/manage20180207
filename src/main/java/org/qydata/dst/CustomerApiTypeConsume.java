package org.qydata.dst;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/5/24.
 */
public class CustomerApiTypeConsume implements Serializable {

    private Integer customerId;
    private Integer companyId;
    private String companyName;
    private Integer apiTypeId;
    private Integer stid;
    private Integer sumAmount;
    private Integer countTotle;
    private Integer countSuccess;
    private Integer price;
    private String apiTypeName;
    private String subTypeName;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    public Integer getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(Integer sumAmount) {
        this.sumAmount = sumAmount;
    }

    public Integer getCountTotle() {
        return countTotle;
    }

    public void setCountTotle(Integer countTotle) {
        this.countTotle = countTotle;
    }

    public Integer getCountSuccess() {
        return countSuccess;
    }

    public void setCountSuccess(Integer countSuccess) {
        this.countSuccess = countSuccess;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getApiTypeName() {
        return apiTypeName;
    }

    public void setApiTypeName(String apiTypeName) {
        this.apiTypeName = apiTypeName;
    }

    public String getSubTypeName() {
        return subTypeName;
    }

    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
