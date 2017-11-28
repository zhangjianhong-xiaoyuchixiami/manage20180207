package org.qydata.config.dataSource;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/1/3.
 */
@Aspect
@Component
public class DatabaseAop {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * 使用空方法定义切点表达式
     */
    @Pointcut("execution(* org.qydata.mapper.*.*(..))")
    public void declareJointPointExpression() {
    }

    /**
     * 使用定义切点表达式的方法进行切点表达式的引入
     */
    @Before("declareJointPointExpression()")
    public void setDataSourceKey(JoinPoint point) {
        // 连接点所属的类实例是ShopDao
        if (isSlave(point.getSignature().getName())) {
            DatabaseContextHolder.setDatabaseType(DatabaseType.slave);
        } else {// 连接点所属的类实例是UserDao（当然，这一步也可以不写，因为defaultTargertDataSource就是该类所用的mytestdb）
            DatabaseContextHolder.setDatabaseType(DatabaseType.master);
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
        return StringUtils.startsWithAny(methodName, "query", "find","get","list");
    }

}
