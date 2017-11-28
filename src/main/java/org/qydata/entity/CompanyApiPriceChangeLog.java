package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jonhn on 2017/7/5.
 */
@Data
public class CompanyApiPriceChangeLog implements Serializable {

    private Integer id;
    private Integer companyId;
    private Integer apiTypeId;
    private Integer stid;
    private Integer price;
    private Date timeForce;
    private Date timeDead;
    private String timeRange;
    private Integer partnerId;
    private String partnerName;
    private String companyName;
    private String apiTypeName;
    private String stidName;
    private String apiType_stid;

}
