package org.qydata.dst.customer;

/**
 * Created by jonhn on 2017/9/22.
 */
public class CustomerCurrDayConsume {

    private Integer customerId;
    private Integer companyId;
    private String companyName;
    private Integer countTotle;


    private Integer apiTypeId;
    private Integer stid;
    //客户单个产品消费总额
    private Double sumAmount;
    private Integer countSuccess;
    private Double price;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Double getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(Double sumAmount) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
}
