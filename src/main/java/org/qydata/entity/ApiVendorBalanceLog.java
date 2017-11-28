package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jonhn on 2017/1/20.
 */
@Data
public class ApiVendorBalanceLog implements Serializable {

    private Integer id;
    private Integer vendorId;
    private String vendorName;
    private Double amount;
    private String remark;
    private Date createTime;
    private String chargeTime;
    private Integer reasonId;
    private String reasonName;

}
