package org.qydata.entity;

/**
 * Created by jonhn on 2017/8/31.
 */
public class VendorHistoryBillUpdateLog {

    private Integer id;
    private Integer vendorHistoryBillId;
    private Double beforData;
    private Double afterData;
    private String content;
    private Integer type;
    private String createTime;
    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVendorHistoryBillId() {
        return vendorHistoryBillId;
    }

    public void setVendorHistoryBillId(Integer vendorHistoryBillId) {
        this.vendorHistoryBillId = vendorHistoryBillId;
    }

    public Double getBeforData() {
        return beforData;
    }

    public void setBeforData(Double beforData) {
        this.beforData = beforData;
    }

    public Double getAfterData() {
        return afterData;
    }

    public void setAfterData(Double afterData) {
        this.afterData = afterData;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
