package org.qydata.entity;

import org.qydata.dst.ProxyApi;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jonhn on 2016/11/25.
 */
public class Api implements Serializable {

    private Integer id;
    private Integer apiTypeId;
    private Integer vendorId;
    private String name;
    private Integer cost;
    private Integer status;
    private Integer proxyApiId;
    private Timestamp createTime;
    private Integer prob;
    private Integer defProb;
    private Integer defProp;
    private String typeName;
    private String vendorName;
    private ApiVendor apiVendor;
    private ApiType apiType;
    private ProxyApi proxyApi;
    private MobileOperator mobileOperator;
    private ApiFake apiFake;
    private List<Customer> customerList;
    private List<MobileOperator> mobileOperatorList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApiTypeId() {
        return apiTypeId;
    }

    public void setApiTypeId(Integer apiTypeId) {
        this.apiTypeId = apiTypeId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProxyApiId() {
        return proxyApiId;
    }

    public void setProxyApiId(Integer proxyApiId) {
        this.proxyApiId = proxyApiId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getProb() {
        return prob;
    }

    public void setProb(Integer prob) {
        this.prob = prob;
    }

    public Integer getDefProb() {
        return defProb;
    }

    public void setDefProb(Integer defProb) {
        this.defProb = defProb;
    }

    public Integer getDefProp() {
        return defProp;
    }

    public void setDefProp(Integer defProp) {
        this.defProp = defProp;
    }

    public ApiVendor getApiVendor() {
        return apiVendor;
    }

    public void setApiVendor(ApiVendor apiVendor) {
        this.apiVendor = apiVendor;
    }

    public ApiType getApiType() {
        return apiType;
    }

    public void setApiType(ApiType apiType) {
        this.apiType = apiType;
    }

    public ProxyApi getProxyApi() {
        return proxyApi;
    }

    public void setProxyApi(ProxyApi proxyApi) {
        this.proxyApi = proxyApi;
    }

    public MobileOperator getMobileOperator() {
        return mobileOperator;
    }

    public void setMobileOperator(MobileOperator mobileOperator) {
        this.mobileOperator = mobileOperator;
    }

    public ApiFake getApiFake() {
        return apiFake;
    }

    public void setApiFake(ApiFake apiFake) {
        this.apiFake = apiFake;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<MobileOperator> getMobileOperatorList() {
        return mobileOperatorList;
    }

    public void setMobileOperatorList(List<MobileOperator> mobileOperatorList) {
        this.mobileOperatorList = mobileOperatorList;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
