package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/2/7.
 */
@Data
public class Partner implements Serializable {

    private Integer id;
    private String name;
    private PartnerIncomeExpenditureLog partnerIncomeExpenditureLog;

}
