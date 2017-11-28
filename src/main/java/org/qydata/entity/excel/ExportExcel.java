package org.qydata.entity.excel;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/7/7.
 */
@Data
public class ExportExcel implements Serializable {

    private String apiTypeName_stidName;
    private String vendorName;
    private Integer sumCount;
    private String yearMonth;
    private String yearMonthDay;
    private String years;
    private String months;
    private String days;
    private String consuTime;
    private Double cost;
    private Double sumCost;

}
