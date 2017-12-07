package org.qydata.dst;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/3/2.
 */
@Data
public class CompanyApiCount implements Serializable {

    private Integer sumAmount;
    private Integer countTotle;
    private Integer countSuccess;
    private Integer currDaySumAmount;
    private Integer currDayCountTotle;
    private Integer currDayCountSuccess;

}
