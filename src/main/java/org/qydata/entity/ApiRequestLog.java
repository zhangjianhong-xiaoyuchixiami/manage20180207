package org.qydata.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jonhn on 2017/1/19.
 */
public class ApiRequestLog implements Serializable {

    private Long id;
    private Integer customerId;
    private Integer apiId;
    private Integer customerReqLogId;
    private String url;
    private String k;
    private String content;
    private Timestamp createTime;
    private Timestamp timestamp;
    private ApiResponseLog apiResponseLog;
    private Company company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getCustomerReqLogId() {
        return customerReqLogId;
    }

    public void setCustomerReqLogId(Integer customerReqLogId) {
        this.customerReqLogId = customerReqLogId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public ApiResponseLog getApiResponseLog() {
        return apiResponseLog;
    }

    public void setApiResponseLog(ApiResponseLog apiResponseLog) {
        this.apiResponseLog = apiResponseLog;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
