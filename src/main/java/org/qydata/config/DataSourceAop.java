package org.qydata.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/1/3.
 */
@Aspect
@Component
@Slf4j
public class DataSourceAop {

    private final Logger log = Logger.getLogger(this.getClass());

    @Pointcut("execution(* org.qydata.mapper.*.query*(..))" +
              "or execution(* org.qydata.mapper.*.get*(..))" +
              "or execution(* org.qydata.mapper.*.find*(..))")
    public void readPointcut(){}

    @Pointcut("execution(* org.qydata.mapper.*.insert*(..))" +
              "or execution(* org.qydata.mapper.*.update*(..))" +
              "or execution(* org.qydata.mapper.*.delete*(..))" +
              "or execution(* org.qydata.mapper.*.remove*(..))" +
              "or execution(* org.qydata.mapper.*.add*(..))")
    public void writePointcut(){}

    @Before("readPointcut()")
    public void setReadDataSourceType() {
        DataSourceContextHolder.slave();
        System.out.println("-------------------------------------------------------------");
        log.info("dataSource切换到：slave");
    }

    @Before("writePointcut()")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.master();
        System.out.println("-------------------------------------------------------------");
        log.info("dataSource切换到：master");
    }
}
