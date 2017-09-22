package org.qydata.dst.customer;

/**
 * Created by jonhn on 2017/9/22.
 */
public class CustomerCurrDayConsumeDetail {

    private Integer apiTypeId;
    private Integer stid;
    private Integer apiId;
    private Integer resultCount;
    private Integer resultCostCount;
    private Integer cacheCount;
    private String type_stid_name;
    private String vendorName;

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

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public Integer getResultCostCount() {
        return resultCostCount;
    }

    public void setResultCostCount(Integer resultCostCount) {
        this.resultCostCount = resultCostCount;
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

    public Integer getCacheCount() {
        return cacheCount;
    }

    public void setCacheCount(Integer cacheCount) {
        this.cacheCount = cacheCount;
    }
}

