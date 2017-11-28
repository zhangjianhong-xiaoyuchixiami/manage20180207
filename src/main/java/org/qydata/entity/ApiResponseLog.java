package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jonhn on 2017/1/19.
 */
@Data
public class ApiResponseLog implements Serializable {

    private Long id;
    private Long requestLogId;
    private Integer httpStatus;
    private String content;
    private Integer cost;
    private Integer ok;
    private String code;
    private Integer resTime;
    private Timestamp createTime;

}
