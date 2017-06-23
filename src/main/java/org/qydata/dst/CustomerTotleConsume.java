package org.qydata.dst;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/6/22.
 */
public class CustomerTotleConsume implements Serializable {

    private Integer customerId;
    private Integer chargeTotleAmount;
    private Integer consumeTotleAmount;
    private Integer currMonthAmount;
    private Integer currDayAmount;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getChargeTotleAmount() {
        return chargeTotleAmount;
    }

    public void setChargeTotleAmount(Integer chargeTotleAmount) {
        this.chargeTotleAmount = chargeTotleAmount;
    }

    public Integer getConsumeTotleAmount() {
        return consumeTotleAmount;
    }

    public void setConsumeTotleAmount(Integer consumeTotleAmount) {
        this.consumeTotleAmount = consumeTotleAmount;
    }

    public Integer getCurrMonthAmount() {
        return currMonthAmount;
    }

    public void setCurrMonthAmount(Integer currMonthAmount) {
        this.currMonthAmount = currMonthAmount;
    }

    public Integer getCurrDayAmount() {
        return currDayAmount;
    }

    public void setCurrDayAmount(Integer currDayAmount) {
        this.currDayAmount = currDayAmount;
    }
}
