package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jonhn on 2017/1/19.
 */
@Data
public class ApiRequestLog implements Serializable {

    private Long id;
    private Integer customerId;
    private Integer apiId;
    private Integer customerReqLogId;
    private String url;
    private String k;
    private String content;
    private Timestamp createTime;
    private Timestamp timestamp;
    private ApiResponseLog apiResponseLog;
    private Company company;

}
