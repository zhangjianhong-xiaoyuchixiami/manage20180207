package org.qydata.dst;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/31.
 */
@Data
public class CustomerWeekMonthConsume  implements Serializable{

    private Integer customerId;
    private Integer chargeWeekTotleAmount;
    private Integer chargeMonthTotleAmount;
    private Integer consumeWeekTotleAmount;
    private Integer consumeMonthTotleAmount;

}
