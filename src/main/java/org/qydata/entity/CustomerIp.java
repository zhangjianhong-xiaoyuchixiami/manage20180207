package org.qydata.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jonhn on 2016/11/8.
 */

public class CustomerIp implements Serializable{

    private Integer id;
    private Integer customerId;
    private Long beginIp;
    private Long endIp;
    private String beginIpRaw;
    private String endIpRaw;
    private Timestamp timestamp;
    public CustomerIp(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Long getBeginIp() {
        return beginIp;
    }

    public void setBeginIp(Long beginIp) {
        this.beginIp = beginIp;
    }

    public Long getEndIp() {
        return endIp;
    }

    public void setEndIp(Long endIp) {
        this.endIp = endIp;
    }

    public String getBeginIpRaw() {
        return beginIpRaw;
    }

    public void setBeginIpRaw(String beginIpRaw) {
        this.beginIpRaw = beginIpRaw;
    }

    public String getEndIpRaw() {
        return endIpRaw;
    }

    public void setEndIpRaw(String endIpRaw) {
        this.endIpRaw = endIpRaw;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    @Override
    public String toString(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
