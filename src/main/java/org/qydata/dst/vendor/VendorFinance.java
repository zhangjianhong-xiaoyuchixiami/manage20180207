package org.qydata.dst.vendor;


import lombok.Data;

import java.util.List;

@Data
public class VendorFinance {

    private Integer vendorId;
    private String vendorName;
    private Integer partnerId;
    private String partnerName;
    private Integer status;
    private Double balance;
    private Double charge;
    private Double consume;
    private Double staticConsume;
    private Double lastWeekConsume;
    private Double lastMonthConsume;
    private Double currMonthConsume;
    private Double currDayConsume;
    private List<VendorTypeConsume> typeConsumeList;

}
