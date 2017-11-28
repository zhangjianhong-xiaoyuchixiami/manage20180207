package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/7/19.
 */
@Data
public class ApiExt implements Serializable{

    private Integer id;
    private Integer apiId;
    private Integer defProb;
    private Integer defProp;

}
