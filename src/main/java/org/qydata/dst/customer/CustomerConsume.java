package org.qydata.dst.customer;

import lombok.Data;

/**
 * Created by Administrator on 2017/11/11.
 */
@Data
public class CustomerConsume {

    private Integer customerId;
    private Double lastWeekCharge;
    private Double lastWeekConsume;
    private Double lastMonthCharge;
    private Double yesterdayConsume;
    private Double lastMonthConsume;
    private Double historyConsume;
    private Double cost;
    private Double amount;
    private String consuTime;
    private Double consume;
    private Integer rate;

}
