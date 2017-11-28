package org.qydata.dst.vendor;


import lombok.Data;
import org.qydata.entity.MobileOperator;

import java.util.List;

@Data
public class VendorTypeConsume {

    private Integer apiTypeId;
    private String apiTypeName;
    private Integer status;
    private Double cost;
    private Double consume;
    private Integer userCount;
    private Integer feeCount;
    private List<MobileOperator> mobileList;

}
