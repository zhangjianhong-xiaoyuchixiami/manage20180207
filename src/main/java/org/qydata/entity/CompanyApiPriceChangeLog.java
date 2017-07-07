package org.qydata.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jonhn on 2017/7/5.
 */
public class CompanyApiPriceChangeLog implements Serializable {

    private Integer id;
    private Integer companyId;
    private Integer apiTypeId;
    private Integer stid;
    private Integer price;
    private Date timeForce;
    private Date timeDead;
    private String timeRange;
    private Integer partnerId;
    private String partnerName;
    private String companyName;
    private String apiTypeName;
    private String stidName;
    private String apiType_stid;

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getTimeForce() {
        return timeForce;
    }

    public void setTimeForce(Date timeForce) {
        this.timeForce = timeForce;
    }

    public Date getTimeDead() {
        return timeDead;
    }

    public void setTimeDead(Date timeDead) {
        this.timeDead = timeDead;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getApiType_stid() {
        return apiType_stid;
    }

    public void setApiType_stid(String apiType_stid) {
        this.apiType_stid = apiType_stid;
    }
}
