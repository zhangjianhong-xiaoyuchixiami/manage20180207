package org.qydata.dst;

import lombok.Data;
import org.qydata.entity.MobileOperator;

import java.util.List;

/**
 * Created by jonhn on 2017/8/31.
 */
@Data
public class ApiWeb {

    private Integer apiId;
    private Integer apiTypeId;
    private Integer vendorId;
    private String apiTypeName;
    private List<MobileOperator> mobileList;

}
