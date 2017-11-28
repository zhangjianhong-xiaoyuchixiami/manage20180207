package org.qydata.dst;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/1/9.
 */
@Data
public class CustomerApiVendor implements Serializable {

    private Integer apiId;
    private String apiName;
    private Long totlePrice;
    private String vendorName;
    private Integer vendorId;
    private Integer reasonId;
    private String reasonName;

}
