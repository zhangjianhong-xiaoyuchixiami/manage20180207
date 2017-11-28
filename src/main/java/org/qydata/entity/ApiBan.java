package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jonhn on 2017/6/20.
 */
@Data
public class ApiBan implements Serializable {

    private Integer apiId;
    private Integer totleCount;
    private Integer failCount;
    private Long failRate;
    private Integer fc;
    private Timestamp ts;
    private Integer apiTypeId;
    private String apiTypeName;
    private Integer vendorId;
    private String vendorName;
    private Integer status;
    private Integer partnerId;
    private String partnerName;
    private List<MobileOperator> mobileOperatorList;


}
