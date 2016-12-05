package org.qydata.entity;

import java.io.Serializable;

/**
 * Created by jonhn on 2016/11/27.
 */
public class CustomerStatus implements Serializable {

    private Integer id;
    private String name;

    public CustomerStatus() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CustomerStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
