package org.qydata.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonhn on 2016/12/14.
 */
public class ApiType implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private List<CustomerApi> customerApiList;
    private List<ApiVendor> apiVendorList;

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

    public List<CustomerApi> getCustomerApiList() {
        return customerApiList;
    }

    public void setCustomerApiList(List<CustomerApi> customerApiList) {
        this.customerApiList = customerApiList;
    }

    public List<ApiVendor> getApiVendorList() {
        return apiVendorList;
    }

    public void setApiVendorList(List<ApiVendor> apiVendorList) {
        this.apiVendorList = apiVendorList;
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
