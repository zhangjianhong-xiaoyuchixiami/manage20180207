package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/6/22.
 */
@Data
public class CustomerCompanyEmail implements Serializable {

    private Integer id;
    private Integer companyId;
    private String email;


}
