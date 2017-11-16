package org.qydata.dst.customer;

import lombok.Data;

/**
 * Created by jonhn on 2017/11/16.
 */
@Data
public class CustomerReqLog {

    private Integer apiTypeId;
    private Integer stid;
    private Integer customerId;
    private String reqId;
    private String k;
    private String reqContent;
    private String createTime;
    private String respContent;
    private Integer apiId;
    private Integer dataSourceId;
    private Integer c0cacheId;
    private Integer dur;
    private Double amount;
    private String type_stid_name;
    private String cname_pname_authId;
    private String vname_pname_aname;
    private String isCache;
    private String isCost;

}
