package org.qydata.entity;

/**
 * Created by jonhn on 2017/8/31.
 */
public class VendorHistoryBillUpdateLog {

    private Integer id;
    private Integer vendorHistoryBillId;
    private Integer beforData;
    private Integer afterData;
    private String content;
    private Integer type;
    private String createTime;

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

    public Integer getBeforData() {
        return beforData;
    }

    public void setBeforData(Integer beforData) {
        this.beforData = beforData;
    }

    public Integer getAfterData() {
        return afterData;
    }

    public void setAfterData(Integer afterData) {
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
}
