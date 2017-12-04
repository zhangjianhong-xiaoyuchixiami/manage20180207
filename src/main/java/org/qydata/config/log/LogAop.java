package org.qydata.config.log;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.qydata.config.annotation.SystemControllerLog;
import org.qydata.config.annotation.SystemServiceLog;
import org.qydata.entity.User;
import org.qydata.entity.log.Log;
import org.qydata.service.LogOperatorDataBeforAfterService;
import org.qydata.service.LogService;
import org.qydata.service.PowerUserService;
import org.qydata.service.UserService;
import org.qydata.tools.ClientIpTools;
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

    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);

    private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<Date>("ThreadLocal beginTime");

    private static final ThreadLocal<Log> logThreadLocal = new NamedThreadLocal<Log>("ThreadLocal log");

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @Autowired
    private PowerUserService powerUserService;

    @Autowired
    private LogOperatorDataBeforAfterService logOperatorDataBeforAfterService;

    //Service层切点
    @Pointcut("@annotation(org.qydata.config.annotation.SystemServiceLog)")
    public void serviceAspect() {
    }

    //Controller层切点
    @Pointcut("@annotation(org.qydata.config.annotation.SystemControllerLog)")
    public void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Service层记录用户的操作的开始时间
     *
     * @param joinPoint 切点
     * @throws InterruptedException
     */
    @Before("serviceAspect()")
    @Order(value = 1)
    public void doBefore(JoinPoint joinPoint) throws InterruptedException {
        Date beginTime = new Date();
        beginTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）
    }

    /**
     * 前置通知 用于拦截Controller层 记录用户的操作
     *
     * @param joinPoint
     */
    @Before("controllerAspect()")
    public void controllerBefore(JoinPoint joinPoint) {
        //获取用户
        try {
            User user = powerUserService.findUserByEmail((String) SecurityUtils.getSubject().getPrincipal());
            if (user != null) {
                joinPoint.getArgs();
                //日志类型
                Integer typeId = 1;
                //获取ip
                String remoteAddr = ClientIpTools.getIpAddr(request);
                //获取请求的uri
                String requestURI = request.getRequestURI();
                //获取请求方法类型
                String method = request.getMethod();
                //获取请求参数
                Map<String, String[]> parameterMap = request.getParameterMap();

                Log log = new Log();
                log.setTitle(LogAop.getControllerMethodDescription(joinPoint));
                log.setBeginTime(new Date());
                log.setTypeId(typeId);
                log.setRemoteAddr(remoteAddr);
                log.setRequestUri(requestURI);
                log.setMethod(method);
                log.setUserId(user.getId());
                log.setParams(JSONObject.fromObject(parameterMap).toString());
                //调用存储日志线程
                new SaveLogThread(log, logService).start();
                logThreadLocal.set(log);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 后置通知 用于拦截Service层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @SuppressWarnings("unchecked")
    @After("serviceAspect()")
    @Order(value = 1)
    public void doAfter(JoinPoint joinPoint) {
        try {
            User user = powerUserService.findUserByEmail((String) SecurityUtils.getSubject().getPrincipal());
            if (user != null) {
                Object[] args = joinPoint.getArgs();
                Integer typeId = 1;                       //日志类型(info:入库,error:错误)
                String remoteAddr = ClientIpTools.getIpAddr(request); //请求的IP
                String requestUri = request.getRequestURI();//请求的Uri
                String method = request.getMethod();        //请求的方法类型(post/get)
                Map<String, String[]> params = request.getParameterMap(); //请求提交的参数

                Log log = new Log();
                //获取注解内容
                log.setTitle(getServiceMethodDescription(joinPoint));
                log.setTypeId(typeId);
                log.setRemoteAddr(remoteAddr);
                log.setRequestUri(requestUri);
                log.setMethod(method);
                log.setParams(JSONObject.fromObject(params).toString());
                log.setUserId(user.getId());
                //调用存储日志线程存储日志
                new SaveLogThread(log, logService).start();
                log.setId(log.getId());
                logThreadLocal.set(log);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异常通知 记录操作报错日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    @Order(value = 1)
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        System.out.println("*********************************异常*************************************");
        Log log = logThreadLocal.get();
        log.setTypeId(2);
        log.setError(e.toString());
        new UpdateLogThread(log, logService).start();
    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return description
     */
    public static String getServiceMethodDescription(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemServiceLog serviceLog = method.getAnnotation(SystemServiceLog.class);
        String description = serviceLog.description();
        return description;
    }


    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return description
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemControllerLog controllerLog = method.getAnnotation(SystemControllerLog.class);
        String description = controllerLog.description();
        return description;
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
