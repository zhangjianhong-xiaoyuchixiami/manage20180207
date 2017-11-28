package org.qydata.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2016/12/3.
 */
@Data
public class UserDept implements Serializable {

    private Integer id;
    private Integer userId;
    private Integer deptId;

    private Timestamp createTime;
    private Timestamp timestamp;

}
