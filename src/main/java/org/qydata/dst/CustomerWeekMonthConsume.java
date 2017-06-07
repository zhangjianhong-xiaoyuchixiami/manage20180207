package org.qydata.dst;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/31.
 */
public class CustomerWeekMonthConsume  implements Serializable{

    private Integer customerId;
    private Integer chargeWeekTotleAmount;
    private Integer chargeMonthTotleAmount;
    private Integer consumeWeekTotleAmount;
    private Integer consumeMonthTotleAmount;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getChargeWeekTotleAmount() {
        return chargeWeekTotleAmount;
    }

    public void setChargeWeekTotleAmount(Integer chargeWeekTotleAmount) {
        this.chargeWeekTotleAmount = chargeWeekTotleAmount;
    }

    public Integer getChargeMonthTotleAmount() {
        return chargeMonthTotleAmount;
    }

    public void setChargeMonthTotleAmount(Integer chargeMonthTotleAmount) {
        this.chargeMonthTotleAmount = chargeMonthTotleAmount;
    }

    public Integer getConsumeWeekTotleAmount() {
        return consumeWeekTotleAmount;
    }

    public void setConsumeWeekTotleAmount(Integer consumeWeekTotleAmount) {
        this.consumeWeekTotleAmount = consumeWeekTotleAmount;
    }

    public Integer getConsumeMonthTotleAmount() {
        return consumeMonthTotleAmount;
    }

    public void setConsumeMonthTotleAmount(Integer consumeMonthTotleAmount) {
        this.consumeMonthTotleAmount = consumeMonthTotleAmount;
    }
}
