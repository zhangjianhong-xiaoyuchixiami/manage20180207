package org.qydata.entity.agency;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/9/18.
 */
public class AgencyBillDetail implements Serializable {

    private Integer id;
    private Integer customerId;
    private String cycle;
    private Integer apiTypeId;
    private Integer stid;
    private Integer apiId;
    private Integer costCount;
    private Double cost;
    private Double price;
    private Double rebateBegPrice;
    private Double rebateEndPrice;
    private String type_stid_name;
    private String vendorName;
    private String companyName;
    private Double priceMoney;
    private Double costMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
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

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getCostCount() {
        return costCount;
    }

    public void setCostCount(Integer costCount) {
        this.costCount = costCount;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRebateBegPrice() {
        return rebateBegPrice;
    }

    public void setRebateBegPrice(Double rebateBegPrice) {
        this.rebateBegPrice = rebateBegPrice;
    }

    public Double getRebateEndPrice() {
        return rebateEndPrice;
    }

    public void setRebateEndPrice(Double rebateEndPrice) {
        this.rebateEndPrice = rebateEndPrice;
    }

    public String getType_stid_name() {
        return type_stid_name;
    }

    public void setType_stid_name(String type_stid_name) {
        this.type_stid_name = type_stid_name;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getPriceMoney() {
        return priceMoney;
    }

    public void setPriceMoney(Double priceMoney) {
        this.priceMoney = priceMoney;
    }

    public Double getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(Double costMoney) {
        this.costMoney = costMoney;
    }
}
