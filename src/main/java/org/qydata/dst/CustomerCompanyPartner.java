package org.qydata.dst;

import lombok.Data;
import org.qydata.entity.Customer;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * Created by jonhn on 2017/2/10.
 */
@Data
public class CustomerCompanyPartner implements Serializable {

    private Integer companyId;
    private String companyName;
    private Long companyBalance;
    private Long floor;
    private Integer partnerId;
    private String partnerName;
    private Integer companyStatus;
    private Long surplusFloor;
    private Timestamp companyCreateTime;
    private List<Customer> customerList;

}
