package org.qydata.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by jonhn on 2017/1/11.
 */
@Aspect
@Component
@Slf4j
public class ExportExcelAop {

    private final Logger log = Logger.getLogger(this.getClass());

    @Pointcut("execution(* org.qydata.controller.FinanceController.findAll*(..))")
    private void pointCutMethod(){}

    @Before("pointCutMethod()")
    public void doBefore() {
        System.out.println("前置通知");
    }

    @AfterReturning(pointcut = "pointCutMethod()",returning = "result"   )
    public void doAfterReturning(String result) {
        System.out.println("后置通知");
        System.out.println("---" + result + "---");
    }

    /**
     *  声明例外通知
     */
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        System.out.println("例外通知");
        System.out.println(e.getMessage());
    }

    @After("pointCutMethod()")
    public void doAfter() {
        System.out.println("最终通知");
    }


    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("进入方法---环绕通知");
        Object o = pjp.proceed();
        System.out.println("退出方法---环绕通知");
        return o;
    }

}
