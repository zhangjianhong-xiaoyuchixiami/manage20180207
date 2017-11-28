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
public class CustomerResponseLog implements Serializable {

    private Long id;
    private Long reqId;
    private String content;
    private Integer apiId;
    private Long dataSourceId;
    private Boolean hasCost;
    private Timestamp createTime;
    private Timestamp timestamp;


}
