package org.qydata.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jonhn on 2017/1/4.
 */
@Data
public class CustomerRequestLog implements Serializable {

    private Long id;
    private Integer apiTypeId;
    private Integer stid;
    private Integer chosenApiId;
    private String clientIp;
    private Integer customerId;
    private String reqId;
    private String k;
    private String content;
    private Timestamp createTime;
    private ApiType apiType;
    private MobileOperator mobileOperator;
    private Company company;


}
