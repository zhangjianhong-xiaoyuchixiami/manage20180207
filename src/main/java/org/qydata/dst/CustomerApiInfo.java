package org.qydata.dst;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.qydata.entity.Customer;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonhn on 2016/12/20.
 */
@Data
public class CustomerApiInfo implements Serializable{
    private String apiTypeName;
    private Integer apiId;
    private Integer price;
    private Boolean enabled;
    private String mobileOperatorName;
    private Integer companyId;
    private List<Customer> customerList;

}
