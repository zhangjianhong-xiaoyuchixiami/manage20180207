package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jonhn on 2017/2/7.
 */
@Data
public class PartnerIncomeExpenditureReason implements Serializable {

    private Integer id;
    private String name;
    private Timestamp createTime;


}
