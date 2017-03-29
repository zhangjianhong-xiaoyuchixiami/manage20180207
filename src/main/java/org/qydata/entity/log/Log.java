package org.qydata.entity.log;

import org.qydata.entity.User;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

 

public class Log implements Serializable {

    private Integer id;           //日志主键
    private Integer typeId;            //日志类型
    private String title;           //日志标题
    private String remoteAddr;          //请求地址(Ip)
    private String requestUri;          //URI
    private String method;          //请求方式
    private String params;          //提交参数
    private String operationBeforData; //操作前数据
    private String operationAfterData;
    private String error;        //错误
    private Date beginTime;           //开始时间
    private String timeOut;    //请求超时
    private Integer userId;          //用户邮箱
    private Timestamp createTime;
    private User user;
    private LogType logType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getOperationBeforData() {
        return operationBeforData;
    }

    public void setOperationBeforData(String operationBeforData) {
        this.operationBeforData = operationBeforData;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getOperationAfterData() {
        return operationAfterData;
    }

    public void setOperationAfterData(String operationAfterData) {
        this.operationAfterData = operationAfterData;
    }
}
