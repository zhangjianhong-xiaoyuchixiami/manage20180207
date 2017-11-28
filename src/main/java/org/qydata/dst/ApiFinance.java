package org.qydata.dst;

import lombok.Data;
import org.qydata.entity.ApiType;
import org.qydata.entity.MobileOperator;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonhn on 2017/1/18.
 */
@Data
public class ApiFinance implements Serializable {

    private Integer apiId;
    private Integer apiTypeId;
    private String apiTypeName;
    private Integer vendorId;
    private String vendorName;
    private String apiName;
    private Integer status;
    private List<MobileOperator> mobileList;

    private Double lastWeekConsume;
    private Double lastMonthConsume;
    private Double currMonthConsume;
    private Double currDayConsume;
    private Integer currDayUserCount;
    private Integer currDayFeeCount;

    private Integer userCount;
    private Integer feeCount;
    private Double consume;




}
