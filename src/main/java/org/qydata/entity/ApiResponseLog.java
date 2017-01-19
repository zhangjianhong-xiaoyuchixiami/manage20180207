package org.qydata.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jonhn on 2017/1/19.
 */
public class ApiResponseLog implements Serializable {

    private Long id;
    private Long requestLogId;
    private Integer httpStatus;
    private String content;
    private Integer cost;
    private Boolean ok;
    private String code;
    private Integer resTime;
    private Timestamp createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequestLogId() {
        return requestLogId;
    }

    public void setRequestLogId(Long requestLogId) {
        this.requestLogId = requestLogId;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getResTime() {
        return resTime;
    }

    public void setResTime(Integer resTime) {
        this.resTime = resTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
