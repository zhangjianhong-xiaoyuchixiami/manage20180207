package org.qydata.dst;

import lombok.Data;
import org.qydata.entity.CompanyApi;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonhn on 2017/1/8.
 */
@Data
public class CustomerFinance implements Serializable {

    private Integer id;
    private Integer companyId;
    private String companyName;
    private Double balance;
    private Integer cost;
    private Integer amount;
    //历史总消费(至上月)
    private Double historyTotalAmount;
    private Double systemBalance;
    private Integer partnerId;
    private String partnerName;
    private Double floor;
    private Double surplusFloor;  //可用额度
    private Double usableFloor;   //剩余信用额度
    private Integer companyStatus;
    private Double chargeTotleAmount;
    private Double consumeTotleAmount;
    private Double currMonthAmount;
    private Double currDayAmount;
    private Double currDayChargeAmount;
    private String email;
    private String consuTime;  //上月excel账单
    private Double lastWeekCharge;
    private Double lastWeekConsume;
    private Double lastMonthCharge;
    private Double yesterdayConsume;
    private Double lastMonthConsume;
    private List<CompanyApi> companyApiList;
    private Integer rate;

}
