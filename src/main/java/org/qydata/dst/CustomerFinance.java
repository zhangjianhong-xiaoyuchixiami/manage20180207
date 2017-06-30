package org.qydata.dst;

import org.qydata.entity.CompanyApi;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonhn on 2017/1/8.
 */
public class CustomerFinance implements Serializable {

    private Integer id;
    private Integer companyId;
    private String companyName;
    private Integer balance;
    private Integer partnerId;
    private String partnerName;
    private Integer floor;
    private Integer surplusFloor;  //可用额度
    private Integer usableFloor;   //剩余信用额度
    private Integer companyStatus;
    private Integer chargeWeekTotleAmount;
    private Integer chargeMonthTotleAmount;
    private Integer consumeWeekTotleAmount;
    private Integer consumeMonthTotleAmount;
    private Integer chargeTotleAmount;
    private Integer consumeTotleAmount;
    private Integer currMonthAmount;
    private Integer currDayAmount;
    private Integer currDayChargeAmount;
    private String email;
    private String consuTime;  //上月excel账单
    private List<CompanyApi> companyApiList;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getSurplusFloor() {
        return surplusFloor;
    }

    public void setSurplusFloor(Integer surplusFloor) {
        this.surplusFloor = surplusFloor;
    }

    public Integer getUsableFloor() {
        return usableFloor;
    }

    public void setUsableFloor(Integer usableFloor) {
        this.usableFloor = usableFloor;
    }

    public Integer getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(Integer companyStatus) {
        this.companyStatus = companyStatus;
    }

    public Integer getChargeWeekTotleAmount() {
        return chargeWeekTotleAmount;
    }

    public void setChargeWeekTotleAmount(Integer chargeWeekTotleAmount) {
        this.chargeWeekTotleAmount = chargeWeekTotleAmount;
    }

    public Integer getChargeMonthTotleAmount() {
        return chargeMonthTotleAmount;
    }

    public void setChargeMonthTotleAmount(Integer chargeMonthTotleAmount) {
        this.chargeMonthTotleAmount = chargeMonthTotleAmount;
    }

    public Integer getConsumeWeekTotleAmount() {
        return consumeWeekTotleAmount;
    }

    public void setConsumeWeekTotleAmount(Integer consumeWeekTotleAmount) {
        this.consumeWeekTotleAmount = consumeWeekTotleAmount;
    }

    public Integer getConsumeMonthTotleAmount() {
        return consumeMonthTotleAmount;
    }

    public void setConsumeMonthTotleAmount(Integer consumeMonthTotleAmount) {
        this.consumeMonthTotleAmount = consumeMonthTotleAmount;
    }

    public Integer getChargeTotleAmount() {
        return chargeTotleAmount;
    }

    public void setChargeTotleAmount(Integer chargeTotleAmount) {
        this.chargeTotleAmount = chargeTotleAmount;
    }

    public Integer getConsumeTotleAmount() {
        return consumeTotleAmount;
    }

    public void setConsumeTotleAmount(Integer consumeTotleAmount) {
        this.consumeTotleAmount = consumeTotleAmount;
    }

    public Integer getCurrMonthAmount() {
        return currMonthAmount;
    }

    public void setCurrMonthAmount(Integer currMonthAmount) {
        this.currMonthAmount = currMonthAmount;
    }

    public Integer getCurrDayAmount() {
        return currDayAmount;
    }

    public void setCurrDayAmount(Integer currDayAmount) {
        this.currDayAmount = currDayAmount;
    }

    public List<CompanyApi> getCompanyApiList() {
        return companyApiList;
    }

    public void setCompanyApiList(List<CompanyApi> companyApiList) {
        this.companyApiList = companyApiList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConsuTime() {
        return consuTime;
    }

    public void setConsuTime(String consuTime) {
        this.consuTime = consuTime;
    }

    public Integer getCurrDayChargeAmount() {
        return currDayChargeAmount;
    }

    public void setCurrDayChargeAmount(Integer currDayChargeAmount) {
        this.currDayChargeAmount = currDayChargeAmount;
    }
}
