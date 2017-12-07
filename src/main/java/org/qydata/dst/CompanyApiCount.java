package org.qydata.dst;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/3/2.
 */
@Data
public class CompanyApiCount implements Serializable {
    //单个客户所有产品消费总额
    private Integer sumAmount;
    //请求次数
    private Integer countTotle;
    //请求成功次数
    private Integer countSuccess;
    //单个客户当天消费的总额
    private Integer currDaySumAmount;
    //当天请求总数
    private Integer currDayCountTotle;
    //当天请求成功总数
    private Integer currDayCountSuccess;

}
