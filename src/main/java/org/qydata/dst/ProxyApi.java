package org.qydata.dst;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/3/1.
 */
public class ProxyApi implements Serializable {

    private Integer proxyApiId;
    private Integer proxyApiTypeId;
    private String proxyApiTypeName;
    private String proxyMobileOperatorName;
    private Integer minCost;
    private Integer apiTypeCount;

    public Integer getProxyApiId() {
        return proxyApiId;
    }

    public void setProxyApiId(Integer proxyApiId) {
        this.proxyApiId = proxyApiId;
    }

    public Integer getProxyApiTypeId() {
        return proxyApiTypeId;
    }

    public void setProxyApiTypeId(Integer proxyApiTypeId) {
        this.proxyApiTypeId = proxyApiTypeId;
    }

    public String getProxyApiTypeName() {
        return proxyApiTypeName;
    }

    public void setProxyApiTypeName(String proxyApiTypeName) {
        this.proxyApiTypeName = proxyApiTypeName;
    }

    public String getProxyMobileOperatorName() {
        return proxyMobileOperatorName;
    }

    public void setProxyMobileOperatorName(String proxyMobileOperatorName) {
        this.proxyMobileOperatorName = proxyMobileOperatorName;
    }

    public Integer getMinCost() {
        return minCost;
    }

    public void setMinCost(Integer minCost) {
        this.minCost = minCost;
    }

    public Integer getApiTypeCount() {
        return apiTypeCount;
    }

    public void setApiTypeCount(Integer apiTypeCount) {
        this.apiTypeCount = apiTypeCount;
    }
}
