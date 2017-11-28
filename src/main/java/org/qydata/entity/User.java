package org.qydata.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jonhn on 2016/11/23.
 */
@Data
public class User implements Serializable {

    private Integer id;
    private String email;
    private String password;
    private Timestamp createTime;
    private Integer status;
    private Integer typeId;
    private List<Dept> dept;
    private List<Role> roles;

}
