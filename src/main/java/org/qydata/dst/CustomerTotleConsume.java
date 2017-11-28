package org.qydata.dst;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/6/22.
 */
@Data
public class CustomerTotleConsume implements Serializable {

    private Integer customerId;
    private Integer chargeTotleAmount;
    private Integer consumeTotleAmount;
    private Integer currMonthAmount;
    private Integer currDayAmount;


}
