package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2016/12/15.
 */
@Data
public class Company implements Serializable {

    private Integer id;
    private String name;
    private Integer partnerId;
    private String partnerName;
    private Partner partner;

}
