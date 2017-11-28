package org.qydata.dst;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/6/26.
 */
@Data
public class CustomerCacheApiTypeConsume implements Serializable {

    private Integer customerId;
    private Integer apiTypeId;
    private Integer stid;
    private String apiTypeName;
    private String stidName;
    private String apiTypeName_stidName;
    private Integer costCount;
    private Integer costUpCount;
    private Integer cacheCount;

}
