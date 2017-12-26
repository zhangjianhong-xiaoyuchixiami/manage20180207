package org.qydata.dst;

import lombok.Data;

@Data
public class VendorCost {

    private Integer vendorId;
    private String vendorName;
    private Integer consumeAccount;
    private Double cost;
    private Double amount;
}
