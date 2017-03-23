package org.qydata.entity.log;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/3/23.
 */
public class LogType implements Serializable {

    private Integer id;
    private String name;

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
}
