package org.qydata.entity;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/7/24.
 */
public class RecoverProbCheck implements Serializable {

    private Integer tid;
    private Integer value;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
