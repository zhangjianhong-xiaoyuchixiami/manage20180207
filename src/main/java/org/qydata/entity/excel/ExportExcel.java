package org.qydata.entity.excel;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/7/7.
 */
public class ExportExcel implements Serializable {

    private String apiTypeName_stidName;
    private String vendorName;
    private Integer sumCount;
    private String yearMonth;
    private String yearMonthDay;
    private String years;
    private String months;
    private String days;
    private String consuTime;
    private Double cost;
    private Double sumCost;

    public String getApiTypeName_stidName() {
        return apiTypeName_stidName;
    }

    public void setApiTypeName_stidName(String apiTypeName_stidName) {
        this.apiTypeName_stidName = apiTypeName_stidName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Integer getSumCount() {
        return sumCount;
    }

    public void setSumCount(Integer sumCount) {
        this.sumCount = sumCount;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getYearMonthDay() {
        return yearMonthDay;
    }

    public void setYearMonthDay(String yearMonthDay) {
        this.yearMonthDay = yearMonthDay;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getConsuTime() {
        return consuTime;
    }

    public void setConsuTime(String consuTime) {
        this.consuTime = consuTime;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getSumCost() {
        return sumCost;
    }

    public void setSumCost(Double sumCost) {
        this.sumCost = sumCost;
    }
}
