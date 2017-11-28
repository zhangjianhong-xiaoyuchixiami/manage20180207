package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by jonhn on 2017/7/3.
 */
@Data
public class ApiPriceChanceLog implements Serializable {

    private Integer id;
    private Integer apiId;
    private Integer price;
    private Date timeForce;
    private Date timeDead;
    private String timeRange;
    private ApiType apiType;
    private ApiVendor apiVendor;
    private Partner partner;
    private List<MobileOperator> mobileOperatorList;

}
