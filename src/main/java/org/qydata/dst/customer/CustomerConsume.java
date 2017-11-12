package org.qydata.dst.customer;

/**
 * Created by Administrator on 2017/11/11.
 */
public class CustomerConsume {

    private Integer customerId;
    private Double lastWeekCharge;
    private Double lastWeekConsume;
    private Double lastMonthCharge;
    private Double yesterdayConsume;
    private Double lastMonthConsume;
    private Double cost;
    private Double amount;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getLastWeekCharge() {
        return lastWeekCharge;
    }

    public void setLastWeekCharge(Double lastWeekCharge) {
        this.lastWeekCharge = lastWeekCharge;
    }

    public Double getLastWeekConsume() {
        return lastWeekConsume;
    }

    public void setLastWeekConsume(Double lastWeekConsume) {
        this.lastWeekConsume = lastWeekConsume;
    }

    public Double getLastMonthCharge() {
        return lastMonthCharge;
    }

    public void setLastMonthCharge(Double lastMonthCharge) {
        this.lastMonthCharge = lastMonthCharge;
    }

    public Double getLastMonthConsume() {
        return lastMonthConsume;
    }

    public void setLastMonthConsume(Double lastMonthConsume) {
        this.lastMonthConsume = lastMonthConsume;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getYesterdayConsume() {
        return yesterdayConsume;
    }

    public void setYesterdayConsume(Double yesterdayConsume) {
        this.yesterdayConsume = yesterdayConsume;
    }
}
