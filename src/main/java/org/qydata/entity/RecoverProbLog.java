package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jonhn on 2017/7/20.
 */
@Data
public class RecoverProbLog implements Serializable {

    private Integer id;
    private Integer rApiId;
    private Integer tApiId;
    private Date phaseOneBeginTime;
    private Date phaseOneEndTime;
    private Date phaseTwoBeginTime;
    private Date phaseTwoEndTime;
    private Integer statusCode;
    private Integer ok;
    private String statusName;
    private String createTime;

}
