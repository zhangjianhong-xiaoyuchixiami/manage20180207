package org.qydata.entity;

import lombok.Data;

/**
 * Created by jonhn on 2017/8/31.
 */
@Data
public class VendorHistoryBillUpdateLog {

    private Integer id;
    private Integer vendorHistoryBillId;
    private Double beforData;
    private Double afterData;
    private String content;
    private Integer type;
    private String createTime;
    private String typeName;

}
