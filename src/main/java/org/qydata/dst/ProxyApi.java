package org.qydata.dst;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/3/1.
 */
@Data
public class ProxyApi implements Serializable {

    private Integer proxyApiId;
    private Integer proxyApiTypeId;
    private String proxyApiTypeName;
    private String proxyMobileOperatorName;
    private Integer minCost;
    private Integer apiTypeCount;

}
