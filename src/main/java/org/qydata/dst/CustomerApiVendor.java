package org.qydata.dst;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/1/9.
 */
public class CustomerApiVendor implements Serializable {

    private Integer apiId;
    private String apiName;
    private Long totlePrice;
    private String vendorName;
    private Integer vendorId;
    private Integer reasonId;
    private String reasonName;

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Long getTotlePrice() {
        return totlePrice;
    }

    public void setTotlePrice(Long totlePrice) {
        this.totlePrice = totlePrice;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getReasonId() {
        return reasonId;
    }

    public void setReasonId(Integer reasonId) {
        this.reasonId = reasonId;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }
}
