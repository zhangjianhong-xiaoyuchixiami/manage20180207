package org.qydata.entity;

import lombok.Data;

/**
 * Created by jonhn on 2017/8/31.
 */
@Data
public class CustomerHistoryBillUpdateLog {

    private Integer id;
    private Integer customerHistoryBillId;
    private Double beforData;
    private Double afterData;
    private String content;
    private Integer typeId;
    private String createTime;
    private String typeName;

}
