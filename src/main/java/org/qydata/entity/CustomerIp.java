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
public class CustomerIp implements Serializable{

    private Integer id;
    private Integer customerId;
    private Long beginIp;
    private Long endIp;
    private String beginIpRaw;
    private String endIpRaw;
    private Timestamp timestamp;

}
