package org.qydata.dst;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/4/11.
 */
@Data
public class ApiTypeSubType implements Serializable {

    private Integer apiTypeId;
    private String apiTypeName;
    private Integer mobileOperatorId;
    private String mobileOperatorName;

}
