package org.qydata.dst.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class BackGroundCustomerBalanceLog implements Serializable {

    private Integer id;
    private Integer customerId;
    private Integer typeId;
    private Integer amount;
    private Integer userId;

}
