package org.qydata.entity;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/2/7.
 */
public class Partner implements Serializable {

    private Integer id;
    private String name;
    private PartnerIncomeExpenditureLog partnerIncomeExpenditureLog;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PartnerIncomeExpenditureLog getPartnerIncomeExpenditureLog() {
        return partnerIncomeExpenditureLog;
    }

    public void setPartnerIncomeExpenditureLog(PartnerIncomeExpenditureLog partnerIncomeExpenditureLog) {
        this.partnerIncomeExpenditureLog = partnerIncomeExpenditureLog;
    }
}
