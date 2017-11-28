package org.qydata.dst;

import lombok.Data;

/**
 * Created by jonhn on 2017/9/7.
 */
@Data
public class ApiTurnoverBillTrend {

    private Integer apiId;
    private Integer vendorId;
    private Double cost;
    private Integer amount;
    private Double consume;
    private Integer apiTypeId;
    private Integer stid;
    private String vendorPartnerName;
    private Integer companyId;
    private String companyPartnerName;
    private Integer customerId;

}
