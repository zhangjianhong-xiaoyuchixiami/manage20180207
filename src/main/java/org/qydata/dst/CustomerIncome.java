package org.qydata.dst;

import lombok.Data;

@Data
public class CustomerIncome {
    private Integer customerId;
    private String customerName;
    private Integer consumeAccount;
    private Double price;
    private Double amount;
}
