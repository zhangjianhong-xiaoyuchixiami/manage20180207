package org.qydata.entity.agency;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/9/18.
 */
@Data
public class RebateAgency implements Serializable {

    private Integer id;
    private String name;
    private Integer taxRate;
    private String taxRateName;
    private String rule;

}
