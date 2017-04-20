package org.qydata.dst;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private Long balance;
    private Integer partnerId;
    private String partnerName;
    private Long floor;
    private Long surplusFloor;
    private Integer companyStatus;
    private Long chargeWeekTotleAmount;
    private Long chargeMonthTotleAmount;
    private Long consumeWeekTotleAmount;
    private Long consumeMonthTotleAmount;
    private Long chargeTotleAmount;
    private Long consumeTotleAmount;
    private Long currMonthAmount;
    private Long currDayAmount;
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

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getChargeWeekTotleAmount() {
        return chargeWeekTotleAmount;
    }

    public void setChargeWeekTotleAmount(Long chargeWeekTotleAmount) {
        this.chargeWeekTotleAmount = chargeWeekTotleAmount;
    }

    public Long getChargeMonthTotleAmount() {
        return chargeMonthTotleAmount;
    }

    public void setChargeMonthTotleAmount(Long chargeMonthTotleAmount) {
        this.chargeMonthTotleAmount = chargeMonthTotleAmount;
    }

    public Long getConsumeWeekTotleAmount() {
        return consumeWeekTotleAmount;
    }

    public void setConsumeWeekTotleAmount(Long consumeWeekTotleAmount) {
        this.consumeWeekTotleAmount = consumeWeekTotleAmount;
    }

    public Long getConsumeMonthTotleAmount() {
        return consumeMonthTotleAmount;
    }

    public void setConsumeMonthTotleAmount(Long consumeMonthTotleAmount) {
        this.consumeMonthTotleAmount = consumeMonthTotleAmount;
    }

    public Long getChargeTotleAmount() {
        return chargeTotleAmount;
    }

    public void setChargeTotleAmount(Long chargeTotleAmount) {
        this.chargeTotleAmount = chargeTotleAmount;
    }

    public Long getConsumeTotleAmount() {
        return consumeTotleAmount;
    }

    public void setConsumeTotleAmount(Long consumeTotleAmount) {
        this.consumeTotleAmount = consumeTotleAmount;
    }

    public List<CompanyApi> getCompanyApiList() {
        return companyApiList;
    }

    public void setCompanyApiList(List<CompanyApi> companyApiList) {
        this.companyApiList = companyApiList;
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

    public Long getCurrMonthAmount() {
        return currMonthAmount;
    }

    public void setCurrMonthAmount(Long currMonthAmount) {
        this.currMonthAmount = currMonthAmount;
    }

    public Long getCurrDayAmount() {
        return currDayAmount;
    }

    public void setCurrDayAmount(Long currDayAmount) {
        this.currDayAmount = currDayAmount;
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
