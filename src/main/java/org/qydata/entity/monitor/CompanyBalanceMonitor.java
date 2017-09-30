package org.qydata.entity.monitor;

/**
 * Created by jonhn on 2017/8/23.
 */

//
//@Data //注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
//@Log4j //注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
//@NoArgsConstructor //注解在类上；为类提供一个无参的构造方法
//@AllArgsConstructor //注解在类上；为类提供一个全参的构造方法
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsPrepay() {
        return isPrepay;
    }

    public void setIsPrepay(Integer isPrepay) {
        this.isPrepay = isPrepay;
    }

    public String getPrepayName() {
        return prepayName;
    }

    public void setPrepayName(String prepayName) {
        this.prepayName = prepayName;
    }

    public Integer getIsAlarm() {
        return isAlarm;
    }

    public void setIsAlarm(Integer isAlarm) {
        this.isAlarm = isAlarm;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public Integer getIsRemindCustomer() {
        return isRemindCustomer;
    }

    public void setIsRemindCustomer(Integer isRemindCustomer) {
        this.isRemindCustomer = isRemindCustomer;
    }

    public String getRemindCustomerName() {
        return remindCustomerName;
    }

    public void setRemindCustomerName(String remindCustomerName) {
        this.remindCustomerName = remindCustomerName;
    }

    public Integer getAhead() {
        return ahead;
    }

    public void setAhead(Integer ahead) {
        this.ahead = ahead;
    }
}
