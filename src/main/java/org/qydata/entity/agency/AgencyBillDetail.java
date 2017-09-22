package org.qydata.entity.agency;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/9/18.
 */
public class AgencyBillDetail implements Serializable {

    private Integer id;
    private Integer companyId;
    private Integer customerId;
    private String cycle;
    private Integer apiTypeId;
    private Integer stid;
    private Integer apiId;
    private Double cost;
    private Double price;
    private Double rebateBegPrice;
    private Double rebateEndPrice;
    private Integer resultCount;
    private String companyName;
    private String type_stid_name;
    private String vendor_partner_name;

    private String typeName;
    private Double priceMoney;
    private Double costMoney;
    private Double grossProfit;
    private Double firstRebate;
    private Double twiceRebate;
    private Double netProfit;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getType_stid_name() {
        return type_stid_name;
    }

    public void setType_stid_name(String type_stid_name) {
        this.type_stid_name = type_stid_name;
    }

    public String getVendor_partner_name() {
        return vendor_partner_name;
    }

    public void setVendor_partner_name(String vendor_partner_name) {
        this.vendor_partner_name = vendor_partner_name;
    }

    public Double getPriceMoney() {
        return priceMoney;
    }

    public void setPriceMoney(Double priceMoney) {
        this.priceMoney = priceMoney;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Double getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(Double costMoney) {
        this.costMoney = costMoney;
    }

    public Double getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(Double grossProfit) {
        this.grossProfit = grossProfit;
    }

    public Double getFirstRebate() {
        return firstRebate;
    }

    public void setFirstRebate(Double firstRebate) {
        this.firstRebate = firstRebate;
    }

    public Double getTwiceRebate() {
        return twiceRebate;
    }

    public void setTwiceRebate(Double twiceRebate) {
        this.twiceRebate = twiceRebate;
    }

    public Double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Double netProfit) {
        this.netProfit = netProfit;
    }
}
