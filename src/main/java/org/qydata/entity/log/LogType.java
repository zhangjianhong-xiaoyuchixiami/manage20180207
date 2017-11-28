package org.qydata.entity.log;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/3/23.
 */
@Data
public class LogType implements Serializable {

    private Integer id;
    private String name;

}
