package org.qydata.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/5/26.
 */
@Data
public class CustomerConsumeExcel implements Serializable {

    private Integer id;
    private byte [] excelCode;
    private Integer customerId;
    private String consuTime;
    private Integer year;
    private Integer month;


}
