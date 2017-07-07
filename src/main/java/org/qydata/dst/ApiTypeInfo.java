package org.qydata.dst;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/7/5.
 */
public class ApiTypeInfo implements Serializable {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
