package org.qydata.dst.vendor;

import lombok.Data;
import org.qydata.entity.MobileOperator;

import java.util.List;

@Data
public class VendorBill {
    private String apiName;
    private String subTypeName;
    private String apiTypeName;
    private Double cost;
    private Double amount;
    private Integer totalCount;
    private Integer apiTypeId;
    private Integer subTypeId;
    private List<MobileOperator> mobileList;
    private String consuTime;
}
