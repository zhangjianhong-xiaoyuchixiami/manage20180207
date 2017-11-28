package org.qydata.dst;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonhn on 2017/6/26.
 */
@Data
public class CustomerCacheConsume implements Serializable {

    private Integer customerId;
    private Integer companyId;
    private String companyName;
    private Integer partnerId;
    private String partnerName;
    private Integer costCount;
    private Integer costUpCount;
    private Integer cacheCount;
    private Integer currMonthCacheCount;
    private Integer currDayCacheCount;
    private List<CustomerCacheApiTypeConsume> customerCacheApiTypeConsumeList;

}
