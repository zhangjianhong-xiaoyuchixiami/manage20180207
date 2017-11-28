package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jonhn on 2016/11/23.
 */
@Data
public class Role implements Serializable {

    private Integer id;
    private String name;
    private String flag;
    private Timestamp createTime;
    private Timestamp timestamp;
    private List<User> users;


}
