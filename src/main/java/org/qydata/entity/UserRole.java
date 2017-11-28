package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jonhn on 2016/11/30.
 */
@Data
public class UserRole implements Serializable {

    private Integer id;
    private Integer roleId;
    private Integer userId;
    private Timestamp createTime;
    private Timestamp timestamp;

}
