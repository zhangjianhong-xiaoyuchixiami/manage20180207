package org.qydata.dst;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 */
public class CustomerApiType implements Serializable {

    private Integer apiTypeId;
    private String apiTypeName;
    private Long totlePrice;
    private List<CustomerApiVendor> customerApiVendors;

    public Integer getApiTypeId() {
        return apiTypeId;
    }

    public void setApiTypeId(Integer apiTypeId) {
        this.apiTypeId = apiTypeId;
    }

    public String getApiTypeName() {
        return apiTypeName;
    }

    public void setApiTypeName(String apiTypeName) {
        this.apiTypeName = apiTypeName;
    }

    public Long getTotlePrice() {
        return totlePrice;
    }

    public void setTotlePrice(Long totlePrice) {
        this.totlePrice = totlePrice;
    }

    public List<CustomerApiVendor> getCustomerApiVendors() {
        return customerApiVendors;
    }

    public void setCustomerApiVendors(List<CustomerApiVendor> customerApiVendors) {
        this.customerApiVendors = customerApiVendors;
    }
}
