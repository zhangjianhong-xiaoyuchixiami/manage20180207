package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/8/7.
 */
@Data
public class VendorExt implements Serializable {

    private Integer vendorId;
    private String vendorName;
    private Integer isPrepay;
    private String isPrepayName;
    private Integer isBalWarn;
    private Integer partnerId;
    private String partnerName;
    private Double balance;
    private Double remaining;
    private Double totleCost;
    private Double currDayCost;

}
