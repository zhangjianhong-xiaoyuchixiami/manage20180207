package org.qydata.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jonhn on 2016/11/8.
 */
@Data
public class CustomerBalanceLog implements Serializable{

    private Long id;
    private Integer reasonId;
    private Integer customerId;
    private Long amount;
    private Long reqId;
    private Timestamp createTime;
    private CustomerBalanceModifyReason customerBalanceModifyReason;
    private ApiType apiType;
    private MobileOperator mobileOperator;
    private ApiVendor apiVendor;

}
