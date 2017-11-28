package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jonhn on 2017/2/7.
 */
@Data
public class PartnerIncomeExpenditureLog implements Serializable {

    private Integer id;
    private Integer partnerId;
    private Long amount;
    private Integer reasonId;
    private String remark;
    private Date createTime;
    private PartnerIncomeExpenditureReason partnerIncomeExpenditureReason;

}
