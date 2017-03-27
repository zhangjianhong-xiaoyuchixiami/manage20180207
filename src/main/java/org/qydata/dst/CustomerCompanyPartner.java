package org.qydata.dst;

import org.qydata.entity.Customer;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * Created by jonhn on 2017/2/10.
 */
public class CustomerCompanyPartner implements Serializable {

    private Integer companyId;
    private String companyName;
    private Long companyBalance;
    private Long floor;
    private Integer partnerId;
    private String partnerName;
    private Integer companyStatus;
    private Long surplusFloor;
    private Timestamp companyCreateTime;
    private List<Customer> customerList;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getCompanyBalance() {
        return companyBalance;
    }

    public void setCompanyBalance(Long companyBalance) {
        this.companyBalance = companyBalance;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public Timestamp getCompanyCreateTime() {
        return companyCreateTime;
    }

    public void setCompanyCreateTime(Timestamp companyCreateTime) {
        this.companyCreateTime = companyCreateTime;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public Long getFloor() {
        return floor;
    }

    public void setFloor(Long floor) {
        this.floor = floor;
    }

    public Integer getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(Integer companyStatus) {
        this.companyStatus = companyStatus;
    }

    public Long getSurplusFloor() {
        return surplusFloor;
    }

    public void setSurplusFloor(Long surplusFloor) {
        this.surplusFloor = surplusFloor;
    }
}
