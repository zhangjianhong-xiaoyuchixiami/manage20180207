package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/7/6.
 */
@Data
public class ApiFake implements Serializable {

    private Integer id;
    private Integer apiId;
    private Integer fakeV;

}
