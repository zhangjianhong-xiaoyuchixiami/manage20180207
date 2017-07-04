package org.qydata.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jonhn on 2017/7/3.
 */
public class ApiPriceChanceLog implements Serializable {

    private Integer id;
    private Integer apiId;
    private Integer price;
    private Timestamp timeForce;
    private Timestamp timeDead;
    private String timeRange;
    private ApiType apiType;
    private ApiVendor apiVendor;
    private Partner partner;
    private List<MobileOperator> mobileOperatorList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Timestamp getTimeForce() {
        return timeForce;
    }

    public void setTimeForce(Timestamp timeForce) {
        this.timeForce = timeForce;
    }

    public Timestamp getTimeDead() {
        return timeDead;
    }

    public void setTimeDead(Timestamp timeDead) {
        this.timeDead = timeDead;
    }

    public ApiType getApiType() {
        return apiType;
    }

    public void setApiType(ApiType apiType) {
        this.apiType = apiType;
    }

    public ApiVendor getApiVendor() {
        return apiVendor;
    }

    public void setApiVendor(ApiVendor apiVendor) {
        this.apiVendor = apiVendor;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public List<MobileOperator> getMobileOperatorList() {
        return mobileOperatorList;
    }

    public void setMobileOperatorList(List<MobileOperator> mobileOperatorList) {
        this.mobileOperatorList = mobileOperatorList;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }
}
