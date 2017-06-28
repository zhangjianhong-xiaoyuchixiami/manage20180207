package org.qydata.dst;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonhn on 2017/6/26.
 */
public class CustomerCacheConsume implements Serializable {

    private Integer customerId;
    private Integer companyId;
    private String companyName;
    private Integer partnerId;
    private String partnerName;
    private Integer costCount;
    private Integer costUpCount;
    private Integer cacheCount;
    private Integer currMonthCacheCount;
    private Integer currDayCacheCount;
    private List<CustomerCacheApiTypeConsume> customerCacheApiTypeConsumeList;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCacheCount() {
        return cacheCount;
    }

    public void setCacheCount(Integer cacheCount) {
        this.cacheCount = cacheCount;
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

    public List<CustomerCacheApiTypeConsume> getCustomerCacheApiTypeConsumeList() {
        return customerCacheApiTypeConsumeList;
    }

    public void setCustomerCacheApiTypeConsumeList(List<CustomerCacheApiTypeConsume> customerCacheApiTypeConsumeList) {
        this.customerCacheApiTypeConsumeList = customerCacheApiTypeConsumeList;
    }

    public Integer getCurrMonthCacheCount() {
        return currMonthCacheCount;
    }

    public void setCurrMonthCacheCount(Integer currMonthCacheCount) {
        this.currMonthCacheCount = currMonthCacheCount;
    }

    public Integer getCurrDayCacheCount() {
        return currDayCacheCount;
    }

    public void setCurrDayCacheCount(Integer currDayCacheCount) {
        this.currDayCacheCount = currDayCacheCount;
    }

    public Integer getCostCount() {
        return costCount;
    }

    public void setCostCount(Integer costCount) {
        this.costCount = costCount;
    }

    public Integer getCostUpCount() {
        return costUpCount;
    }

    public void setCostUpCount(Integer costUpCount) {
        this.costUpCount = costUpCount;
    }
}
