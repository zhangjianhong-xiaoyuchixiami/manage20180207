package org.qydata.dst;

import org.qydata.entity.MobileOperator;

import java.util.List;

/**
 * Created by jonhn on 2017/8/31.
 */
public class ApiWeb {

    private Integer apiId;
    private Integer apiTypeId;
    private Integer vendorId;
    private String apiTypeName;
    private List<MobileOperator> mobileList;

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getApiTypeId() {
        return apiTypeId;
    }

    public void setApiTypeId(Integer apiTypeId) {
        this.apiTypeId = apiTypeId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getApiTypeName() {
        return apiTypeName;
    }

    public void setApiTypeName(String apiTypeName) {
        this.apiTypeName = apiTypeName;
    }

    public List<MobileOperator> getMobileList() {
        return mobileList;
    }

    public void setMobileList(List<MobileOperator> mobileList) {
        this.mobileList = mobileList;
    }
}
