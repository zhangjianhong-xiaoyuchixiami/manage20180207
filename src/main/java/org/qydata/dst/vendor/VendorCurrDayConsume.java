package org.qydata.dst.vendor;

import lombok.Data;

@Data
public class VendorCurrDayConsume {
    //供应商id
    private Integer vendorId;
    //apiId
    private Integer apiId;
    //类型Id
    private Integer typeId;
    //运营商Id
    private Integer subTypeId;
    //扣费数量
    private Integer consumeAccount;
    //当天消费
    private Double total;
    //api类型
    private String apiTypeName;
    //api运营商
    private String subTypeName;
    //api消费总额
    private Double apiTypeConsume;
    //api单价
    private Double cost;
}
