package org.qydata.dst;

import lombok.Data;
import org.qydata.entity.MobileOperator;

import java.util.List;

@Data
public class ApiResponseCondition {
    private Integer apiId;
    private Integer apiTypeId;
    private Integer subTypeId;
    private String apiName;
    private String apiTypeName;
    private String subTypeName;
    private String vendorName;
    private Integer totalCount;
    private Integer successCount;
    private Integer failCount;
    private String failPercent;
    private Integer count1;
    private Integer count2;
    private Integer count3;
    private Integer count4;
    private Integer count5;
    private Integer count6;
    private Integer count7;
    private String dateTime;
    private List<MobileOperator>mobileList;

}
