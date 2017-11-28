package org.qydata.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jonhn on 2016/11/8.
 */
@Data
public class Customer implements Serializable{
    private Integer id;
    private Integer typeId;
    private String name;
    private String authId;
    private String authPass;
    private Long balance;
    private Timestamp createTime;
    private Timestamp timestamp;
    private Integer status;
    private CustomerType customerType;
    private CustomerStatus customerStatus;
    private User user;
    private Dept dept;
    private Company company;
    private Integer companyId;
    private List<Api> apiList;
    private List<CustomerIp> customerIpList;

}
