package org.qydata.dst;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/6/26.
 */
public class CustomerCacheApiTypeConsume implements Serializable {

    private Integer customerId;
    private Integer apiTypeId;
    private Integer stid;
    private String apiTypeName;
    private String stidName;
    private String apiTypeName_stidName;
    private Integer costCount;
    private Integer costUpCount;
    private Integer cacheCount;

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

    public String getApiTypeName_stidName() {
        return apiTypeName_stidName;
    }

    public void setApiTypeName_stidName(String apiTypeName_stidName) {
        this.apiTypeName_stidName = apiTypeName_stidName;
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

    public Integer getCacheCount() {
        return cacheCount;
    }

    public void setCacheCount(Integer cacheCount) {
        this.cacheCount = cacheCount;
    }
}
