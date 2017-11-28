package org.qydata.dst;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/8/3.
 */
@Data
public class CustomerHistoryBillDetail implements Serializable {

    private Integer id;
    private Integer year;
    private Integer month;
    private String yearMonth;
    private Integer customerId;
    private Integer apiTypeId;
    private Integer stid;
    private Double cost;
    private Integer amount;
    private String apiTypeName;
    private String stidName;
    private Double consumeAmount;
    private Integer isLock;
    private String isLockName;

}
