package org.qydata.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jonhn on 2016/11/22.
 */
@Data
public class Dept implements Serializable {

    private Integer id;
    private String deptName;
    private Timestamp createTime;
    private  Timestamp timestamp;
    private List<User> user;


}
