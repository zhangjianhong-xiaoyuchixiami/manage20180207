package org.qydata.dst;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/3/8.
 */
@Data
public class CustomerApiPartner implements Serializable {

    private Integer id;
    private Integer companyId;
    private String companyName;
    private Integer apiTypeId;
    private String apiTypeName;
    private Integer subTypeId;
    private String subTypeName;
    private Integer price;
    private Integer partnerId;
    private String partnerName;
    private Integer enabled;
    private Integer companyStatus;

}
