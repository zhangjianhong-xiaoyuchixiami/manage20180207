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
public class CustomerApi implements Serializable {

    private Integer id;
    private Integer apiId;
    private Integer customerId;
    private Integer price;
    private Boolean enabled;
    private Timestamp createTime;
    private Timestamp timestamp;
    private Api api;
    private Customer customer;


}
