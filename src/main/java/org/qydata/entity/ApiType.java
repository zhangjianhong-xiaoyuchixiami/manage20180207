package org.qydata.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.qydata.dst.CustomerApiVendor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonhn on 2016/12/14.
 */
public class ApiType implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private List<CustomerApiVendor> customerApiVendorList;
    private List<MobileOperator> mobileOperatorList;
    private Api api;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CustomerApiVendor> getCustomerApiVendorList() {
        return customerApiVendorList;
    }

    public void setCustomerApiVendorList(List<CustomerApiVendor> customerApiVendorList) {
        this.customerApiVendorList = customerApiVendorList;
    }

    public List<MobileOperator> getMobileOperatorList() {
        return mobileOperatorList;
    }

    public void setMobileOperatorList(List<MobileOperator> mobileOperatorList) {
        this.mobileOperatorList = mobileOperatorList;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public String toString(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
