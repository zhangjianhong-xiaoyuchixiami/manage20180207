package org.qydata.entity;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/6/22.
 */
public class CustomerCompanyEmail implements Serializable {

    private Integer id;
    private Integer companyId;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
