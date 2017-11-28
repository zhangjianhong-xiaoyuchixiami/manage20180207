package org.qydata.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by jonhn on 2016/12/14.
 */
@Data
public class ApiMobileOperator {

    private Integer id;
    private Integer apiId;
    private Integer mobileOperatorId;
    private Timestamp timestamp;
    private MobileOperator mobileOperator;

}
