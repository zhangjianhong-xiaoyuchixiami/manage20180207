package org.qydata.dst;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/5/24.
 */
@Data
public class CustomerApiTypeConsume implements Serializable {

    private Integer customerId;
    private Integer companyId;
    private String companyName;
    private Integer apiTypeId;
    private Integer stid;
    private Integer sumAmount;
    private Integer countTotle;
    private Integer countSuccess;
    private Integer price;
    private String apiTypeName;
    private String subTypeName;


}
