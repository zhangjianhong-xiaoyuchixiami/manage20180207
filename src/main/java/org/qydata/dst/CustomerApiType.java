package org.qydata.dst;

import org.qydata.entity.ApiVendor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonhn on 2017/1/9.
 */
public class CustomerApiType implements Serializable {

    private Integer apiTypeId;
    private Long totlePrice;
    private String typeName;
    private List<ApiVendor> vendorList;

    public Integer getApiTypeId() {
        return apiTypeId;
    }

    public void setApiTypeId(Integer apiTypeId) {
        this.apiTypeId = apiTypeId;
    }

    public Long getTotlePrice() {
        return totlePrice;
    }

    public void setTotlePrice(Long totlePrice) {
        this.totlePrice = totlePrice;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<ApiVendor> getVendorList() {
        return vendorList;
    }

    public void setVendorList(List<ApiVendor> vendorList) {
        this.vendorList = vendorList;
    }
}
