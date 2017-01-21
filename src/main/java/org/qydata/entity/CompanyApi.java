package org.qydata.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jonhn on 2017/1/21.
 */
public class CompanyApi implements Serializable {

    private Integer id;
    private Integer companyId;
    private Integer apiTypeId;
    private Integer subTypeId;
    private Integer price;
    private Integer enabled;
    private Timestamp createTime;
    private ApiType apiType;
    private MobileOperator mobileOperator;

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

    public Integer getApiTypeId() {
        return apiTypeId;
    }

    public void setApiTypeId(Integer apiTypeId) {
        this.apiTypeId = apiTypeId;
    }

    public Integer getSubTypeId() {
        return subTypeId;
    }

    public void setSubTypeId(Integer subTypeId) {
        this.subTypeId = subTypeId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public ApiType getApiType() {
        return apiType;
    }

    public void setApiType(ApiType apiType) {
        this.apiType = apiType;
    }

    public MobileOperator getMobileOperator() {
        return mobileOperator;
    }

    public void setMobileOperator(MobileOperator mobileOperator) {
        this.mobileOperator = mobileOperator;
    }
}
