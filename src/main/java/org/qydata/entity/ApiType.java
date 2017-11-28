package org.qydata.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.qydata.dst.CustomerApiVendor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonhn on 2016/12/14.
 */
@Data
public class ApiType implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private List<CustomerApiVendor> customerApiVendorList;
    private List<MobileOperator> mobileOperatorList;
    private Api api;

}
