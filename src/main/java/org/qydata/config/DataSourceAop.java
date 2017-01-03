package org.qydata.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/1/3.
 */
@Aspect
@Component
@Slf4j
public class DataSourceAop {

    private final Logger log = Logger.getLogger(this.getClass());

    @Before("execution(* org.qydata.mapper.*.find*(..)) " +
            "or execution(* org.qydata.mapper.*.get*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.read();
        log.info("dataSource切换到：Read");
    }

    @Before("execution(* org.qydata.mapper.*.insert*(..)) " +
            "or execution(* org.qydata.mapper.*.update*(..))" +
            "or execution(* org.qydata.mapper.*.delete*(..)) " +
            "or execution(* org.qydata.mapper.*.remove*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
        log.info("dataSource切换到：write");
    }
}
