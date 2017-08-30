package org.qydata.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jonhn on 2017/1/24.
 */
public class ApiVendorBalance implements Serializable {

    private Integer id;
    private Integer vendorId;
    private Double balance;
    private Timestamp createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
