package org.qydata.dst;

import lombok.Data;

/**
 * Created by jonhn on 2017/9/5.
 */
@Data
public class ApiTurnoverBill {

    private Integer apiTypeId;
    private Integer stid;
    private String type_stid_name;
    private Integer vendorAmount;
    private Double vendorConsume;
    private Integer customerAmount;
    private Double customerConsume;
    private Double profit;
    private Integer status;
    private String typeId_stid;

}
