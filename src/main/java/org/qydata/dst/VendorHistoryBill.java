package org.qydata.dst;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/8/2.
 */
@Data
public class VendorHistoryBill implements Serializable {

    private Integer vendorId;
    private Double consumeAmount;
    private String vendorName;
    private Integer partnerId;
    private String partnerName;
    private Double chargeAmount;
    private Integer status;
    private Double balance;
    private Double currMonthRealTimeConsume;
    private Double currDayRealTimeConsume;
    private Double staticConsumeAmount;

}
