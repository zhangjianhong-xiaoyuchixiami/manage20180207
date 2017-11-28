package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jonhn on 2016/11/22.
 */
@Data
public class CustomerDept implements Serializable {

    private Integer id;
    private Integer customerId;
    private Integer deptId;
    private Integer createId;
    private Timestamp createTime;
    private Timestamp timestamp;


}
