package org.qydata.entity;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/7/19.
 */
public class ApiExt implements Serializable{

    private Integer id;
    private Integer apiId;
    private Integer defProb;
    private Integer defProp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getDefProb() {
        return defProb;
    }

    public void setDefProb(Integer defProb) {
        this.defProb = defProb;
    }

    public Integer getDefProp() {
        return defProp;
    }

    public void setDefProp(Integer defProp) {
        this.defProp = defProp;
    }
}
