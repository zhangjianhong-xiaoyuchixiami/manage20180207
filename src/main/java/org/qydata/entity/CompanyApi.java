package org.qydata.entity;

import lombok.Data;
import org.qydata.dst.CompanyApiCount;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jonhn on 2017/1/21.
 */
@Data
public class CompanyApi implements Serializable {

    private Integer id;
    private Integer companyId;
    private Integer apiTypeId;
    private Integer subTypeId;
    private Integer price;
    private Integer enabled;
    private Timestamp createTime;
    private String type_stid;
    private String type_stid_name;
    private String btypeName;
    private String cvendorName;
    private Integer apiId;
    private Double minCost;
    private Double profit;
    private ApiType apiType;
    private MobileOperator mobileOperator;
    private CompanyApiCount companyApiCount;

}
