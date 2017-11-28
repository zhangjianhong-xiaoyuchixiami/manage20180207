package org.qydata.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jonhn on 2016/12/14.
 */
@Data
public class MobileOperator implements Serializable {

    private Integer id;
    private String name;
    private String rules;
    private Timestamp timestamp;

}
