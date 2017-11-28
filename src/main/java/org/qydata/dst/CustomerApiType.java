package org.qydata.dst;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 */
@Data
public class CustomerApiType implements Serializable {

    private Integer apiTypeId;
    private String apiTypeName;
    private Long totlePrice;
    private List<CustomerApiVendor> customerApiVendors;


}
