package org.qydata.config.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.qydata.config.annotation.SystemControllerLog;
import org.qydata.entity.User;
import org.qydata.entity.log.Log;
import org.qydata.service.LogService;
import org.qydata.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * Created by jonhn on 2017/3/20.
 */
@Aspect
@Component
@Slf4j
public class LogAop {

    private  static  final Logger logger = LoggerFactory.getLogger(LogAop.class);

    private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<Date>("ThreadLocal beginTime");

    private static final ThreadLocal<Log> logThreadLocal = new NamedThreadLocal<Log>("ThreadLocal log");

    @Autowired private HttpServletRequest request;

    @Autowired private UserService userService;

    @Autowired private LogService logService;

    /**
     * 前置通知 用于拦截Controller层记录用户的操作的开始时间
     * @param joinPoint 切点
     * @throws InterruptedException
     */
    @Before("execution(* org.qydata.controller.ApiFinanceController.chargeApiVendorBalance(..))")
    @Order(value = 1)
    public void doBefore(JoinPoint joinPoint) throws InterruptedException{
        Date beginTime=new Date();
        beginTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）
    }

    /**
     * 后置通知 用于拦截Controller层记录用户的操作
     * @param joinPoint 切点
     */
    @SuppressWarnings("unchecked")
    @After("execution(* org.qydata.controller.ApiFinanceController.chargeApiVendorBalance(..))")
    @Order(value = 1)
    public void doAfter(JoinPoint joinPoint) {
        try {
            User user = userService.findUserByEmail((String) SecurityUtils.getSubject().getPrincipal());
            if(user !=null){
                Integer typeId=1;                       //日志类型(info:入库,error:错误)
                String remoteAddr=request.getRemoteAddr();//请求的IP
                String requestUri=request.getRequestURI();//请求的Uri
                String method=request.getMethod();        //请求的方法类型(post/get)
                Map<String,String[]> params=request.getParameterMap(); //请求提交的参数
                String operationBeforData = "";
                String operationAfterData = "";
                String error = "";
                long beginTime = beginTimeThreadLocal.get().getTime();//得到线程绑定的局部变量（开始时间）
                long endTime = System.currentTimeMillis();  //2、结束时间

                Log log=new Log();
                log.setTitle(getControllerMethodDescription2(joinPoint));
                log.setTypeId(typeId);
                log.setRemoteAddr(remoteAddr);
                log.setRequestUri(requestUri);
                log.setMethod(method);
                log.setParams("111");
                log.setOperationBeforData(operationBeforData);
                log.setOperationAfterData(operationAfterData);
                log.setError(error);
                log.setUserId(user.getId());
                log.setBeginTime(new Date((beginTime)));
                log.setEndTime(new Date(endTime));
                new SaveLogThread(log, logService).start();
                logThreadLocal.set(log);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  异常通知 记录操作报错日志
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "execution(* org.qydata.controller.ApiFinanceController.chargeApiVendorBalance(..))", throwing = "e")
    @Order(value = 10)
    public  void doAfterThrowing(JoinPoint joinPoint, Exception  e) {
        System.out.println("*********************************异常*************************************");
        Log log = logThreadLocal.get();
        log.setTypeId(2);
        log.setError(e.toString());
        new UpdateLogThread(log, logService).start();
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return discription
     */
    public static String getControllerMethodDescription2(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemControllerLog controllerLog = method.getAnnotation(SystemControllerLog.class);
        String discription = controllerLog.description();
        return discription;
    }

    /**
     * 保存日志线程
     */
    private static class SaveLogThread extends Thread {
        private Log log;
        private LogService logService;

        public SaveLogThread(Log log, LogService logService) {
            this.log = log;
            this.logService = logService;
        }

        @Override
        public void run() {
            logService.createLog(log);
        }
    }

    /**
     * 日志更新线程
     */
    private static class UpdateLogThread extends Thread {
        private Log log;
        private LogService logService;

        public UpdateLogThread(Log log, LogService logService) {
            super(UpdateLogThread.class.getSimpleName());
            this.log = log;
            this.logService = logService;
        }

        @Override
        public void run() {
            this.logService.updateLog(log);
        }
    }

}
