package org.qydata.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.qydata.config.DataSourceContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/1/3.
 */
@Aspect
@Component
@Slf4j
public class DataSourceAop {

    private final Logger log = Logger.getLogger(this.getClass());

    //Service层切点
    @Pointcut("@annotation(org.qydata.config.annotation.DataSourceService)")
    public  void serviceAspect() {}
    /**
     * 在进入Service方法之前执行
     * @param point 切面对象
     */
    @Before("serviceAspect()")
    @Order(-1)
    public void before(JoinPoint point) {
        // 获取到当前执行的方法名
        String methodName = point.getSignature().getName();
        log.info("方法名methodName="+methodName);
        log.info("*****************开始切入************");
        if (isSlave(methodName)) {
            // 标记为读库
            log.info("***********切换到slave************");
            DataSourceContextHolder.markSlave();
        } else {
            // 标记为写库
            log.info("***********切换到master************");
            DataSourceContextHolder.markMaster();
        }
    }
    /**
     * 判断是否为读库
     *
     * @param methodName
     * @return
     */
    private boolean isSlave(String methodName) {
        // 方法名以query、find、get开头的方法名走从库
        return StringUtils.startsWithAny(methodName, "query", "find");
    }




}
