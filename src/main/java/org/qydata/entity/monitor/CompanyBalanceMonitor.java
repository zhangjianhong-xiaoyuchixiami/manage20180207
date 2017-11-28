package org.qydata.entity.monitor;

import lombok.Data;

/**
 * Created by jonhn on 2017/8/23.
 */

//
//@Data //注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
//@Log4j //注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
//@NoArgsConstructor //注解在类上；为类提供一个无参的构造方法
//@AllArgsConstructor //注解在类上；为类提供一个全参的构造方法
@Data
public class CompanyBalanceMonitor {

    private Integer companyId;
    private String companyName;
    private Integer partnerId;
    private String partnerName;
    private Integer status;
    private Integer isPrepay;
    private String prepayName;
    private Integer isAlarm;
    private String alarmName;
    private Integer isRemindCustomer;
    private String remindCustomerName;
    private Integer ahead;

}
