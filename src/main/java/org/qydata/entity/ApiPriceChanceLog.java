package org.qydata.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by jonhn on 2017/7/3.
 */
public class ApiPriceChanceLog implements Serializable {

    private Integer id;
    private Integer apiId;
    private Integer price;
    private Date timeForce;
    private Date timeDead;
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
