package org.qydata.dst;

/**
 * Created by jonhn on 2017/9/5.
 */
public class ApiTurnoverBill {

    private Integer apiTypeId;
    private Integer stid;
    private String type_stid_name;
    private Integer vendorAmount;
    private Double vendorConsume;
    private Integer customerAmount;
    private Double customerConsume;
    private Double profit;
    private Integer status;
    private String typeId_stid;

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

    public String getType_stid_name() {
        return type_stid_name;
    }

    public void setType_stid_name(String type_stid_name) {
        this.type_stid_name = type_stid_name;
    }

    public Integer getVendorAmount() {
        return vendorAmount;
    }

    public void setVendorAmount(Integer vendorAmount) {
        this.vendorAmount = vendorAmount;
    }

    public Double getVendorConsume() {
        return vendorConsume;
    }

    public void setVendorConsume(Double vendorConsume) {
        this.vendorConsume = vendorConsume;
    }

    public Integer getCustomerAmount() {
        return customerAmount;
    }

    public void setCustomerAmount(Integer customerAmount) {
        this.customerAmount = customerAmount;
    }

    public Double getCustomerConsume() {
        return customerConsume;
    }

    public void setCustomerConsume(Double customerConsume) {
        this.customerConsume = customerConsume;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTypeId_stid() {
        return typeId_stid;
    }

    public void setTypeId_stid(String typeId_stid) {
        this.typeId_stid = typeId_stid;
    }
}
