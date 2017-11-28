package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jonhn on 2017/1/24.
 */
@Data
public class ApiVendorBalance implements Serializable {

    private Integer id;
    private Integer vendorId;
    private Double balance;
    private Timestamp createTime;

}
