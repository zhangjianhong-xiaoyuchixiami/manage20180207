package org.qydata.entity;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/7/6.
 */
public class ApiFake implements Serializable {

    private Integer id;
    private Integer apiId;
    private Integer fakeV;

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

    public Integer getFakeV() {
        return fakeV;
    }

    public void setFakeV(Integer fakeV) {
        this.fakeV = fakeV;
    }
}
