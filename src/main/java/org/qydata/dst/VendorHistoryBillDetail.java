package org.qydata.dst;

import lombok.Data;
import org.qydata.entity.MobileOperator;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonhn on 2017/8/2.
 */
@Data
public class VendorHistoryBillDetail implements Serializable {

    private Integer id;
    private Integer year;
    private Integer month;
    private String yearMonth;
    private Integer vendorId;
    private Integer apiId;
    private Integer apiTypeId;
    private Integer stid;
    private Double cost;
    private Integer amount;
    private String apiTypeName;
    private String stidName;
    private Double consumeAmount;
    private Integer isLock;
    private String isLockName;
    private List<MobileOperator> mobileList;

}
