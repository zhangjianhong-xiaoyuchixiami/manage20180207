package org.qydata.entity;

import lombok.Data;
import org.qydata.dst.ProxyApi;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jonhn on 2016/11/25.
 */
@Data
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

}
