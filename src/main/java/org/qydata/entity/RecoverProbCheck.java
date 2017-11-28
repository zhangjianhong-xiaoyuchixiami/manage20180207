package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/7/24.
 */
@Data
public class RecoverProbCheck implements Serializable {

    private Integer tid;
    private Integer value;


}
