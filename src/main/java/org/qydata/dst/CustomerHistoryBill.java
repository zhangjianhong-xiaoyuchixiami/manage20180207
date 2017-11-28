package org.qydata.dst;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/8/2.
 */
@Data
public class CustomerHistoryBill implements Serializable {

    private Integer customerId;
    private Double floor;
    private Double balance;
    private Double userFloor;
    private Integer companyId;
    private Integer partnerId;
    private String companyName;
    private String partnerName;
    private Double chargeAmount;
    private Double chargeCurrDayAmount;
    private Double consumeAmount;
    private Integer status;
    private Double staticConsumeAmount;
    private Double currMonthRealTimeConsume;
    private Double currDayRealTimeConsume;

}
