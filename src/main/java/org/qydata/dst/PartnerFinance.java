package org.qydata.dst;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/2/7.
 */
@Data
public class PartnerFinance implements Serializable {

    private Integer partnerId;
    private String partnerName;
    private Long totleIncomeAmount;
    private Long totleExpenditureAmount;
    private Long weekIncomeAmount;
    private Long monthIncomeAmount;
    private Long weekExpenditureAmount;
    private Long monthExpenditureAmount;

}
