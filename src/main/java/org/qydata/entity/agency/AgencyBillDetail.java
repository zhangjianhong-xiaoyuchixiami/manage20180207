package org.qydata.entity.agency;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/9/18.
 */
@Data
public class AgencyBillDetail implements Serializable {

    private Integer id;
    private Integer customerId;
    private String cycle;
    private Integer apiTypeId;
    private Integer stid;
    private Integer apiId;
    private Integer costCount;
    private Double cost;
    private Double price;
    private Double rebateBegPrice;
    private Double rebateEndPrice;
    private String type_stid_name;
    private String vendorName;
    private String companyName;
    private Double priceMoney;
    private Double costMoney;

}
