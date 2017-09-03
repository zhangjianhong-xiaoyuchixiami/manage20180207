package org.qydata.entity;

/**
 * Created by jonhn on 2017/8/31.
 */
public class CustomerHistoryBillUpdateLog {

    private Integer id;
    private Integer customerHistoryBillId;
    private Integer beforData;
    private Integer afterData;
    private String content;
    private Integer typeId;
    private String createTime;
    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerHistoryBillId() {
        return customerHistoryBillId;
    }

    public void setCustomerHistoryBillId(Integer customerHistoryBillId) {
        this.customerHistoryBillId = customerHistoryBillId;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
