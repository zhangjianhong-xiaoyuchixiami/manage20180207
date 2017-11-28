package org.qydata.dst;

import lombok.Data;
import org.qydata.entity.MobileOperator;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonhn on 2017/4/17.
 */
@Data
public class ApiTypeConsume implements Serializable {
    private Integer apiTypeId;
    private String apiTypeName;
    private Integer apiStatus;
    private Integer cost;
    private Integer apiTypeConsumeTotleAmount;
    private Integer apiTypeUsageAmount;
    private Integer apiTypefeeAmount;
    private Integer typeCurrDayCost;
    private Integer typeCurrDayUsageAmount;
    private Integer typeFeeCurrDayAmount;
    private List<MobileOperator> mobileOperatorList;

}
