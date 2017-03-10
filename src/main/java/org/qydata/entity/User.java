package org.qydata.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jonhn on 2016/11/23.
 */
public class User implements Serializable {

    private Integer id;
    private String email;
    private String password;
    private Timestamp createTime;
    private Integer status;
    private Integer typeId;
    private List<Dept> dept;
    private List<Role> roles;
    public User() {}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Dept> getDept() {
        return dept;
    }

    public void setDept(List<Dept> dept) {
        this.dept = dept;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
