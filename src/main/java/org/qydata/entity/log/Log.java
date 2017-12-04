package org.qydata.entity.log;

import lombok.Data;
import org.qydata.entity.User;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

 
@Data
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
}
