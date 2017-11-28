package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jonhn on 2016/11/22.
 */
@Data
public class CustomerType implements Serializable{

    private Integer id;
    private String name;
    private Timestamp timestamp;


}
