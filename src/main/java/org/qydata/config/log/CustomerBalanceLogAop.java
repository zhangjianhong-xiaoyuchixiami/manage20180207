package org.qydata.config.log;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.qydata.dst.customer.BackGroundCustomerBalanceLog;
import org.qydata.entity.User;
import org.qydata.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CustomerBalanceLogAop {

    private static final ThreadLocal<BackGroundCustomerBalanceLog> balanceLogThreadLocal = new NamedThreadLocal<BackGroundCustomerBalanceLog>("ThreadLocal log");


    @Autowired
    private BackGroundCustomerBalanceLogService logService;

    @Autowired
    private PowerUserService powerUserService;


    //Service层切点
    @Pointcut("@annotation(org.qydata.config.annotation.BackGroundCustomerBalanceLogServiceLog)")
    public  void serviceAspect() {
    }

    /**
     * 后置通知 用于拦截Service层记录用户的操作
     * @param joinPoint 切点
     */
    @SuppressWarnings("unchecked")
    @After("serviceAspect()")
    @Order(value = 1)
    public void doAfter(JoinPoint joinPoint) {
        try {
            User user = powerUserService.findUserByEmail((String) SecurityUtils.getSubject().getPrincipal());
            if(user !=null){
                Object[] args = joinPoint.getArgs();
                Integer companyId = (Integer) args[0];
                Integer reasonId = (Integer) args[1];
                String amount = (String) args[2];
                BackGroundCustomerBalanceLog log=new BackGroundCustomerBalanceLog();
                log.setCustomerId(logService.queryCustomerIdByCompanyId(companyId));
                log.setTypeId(reasonId);
                log.setAmount( (int) Double.parseDouble(amount) * 100 );
                log.setUserId(user.getId());
               // new CustomerBalanceLogAop.SaveLogThread(log, logService).start();
                logService.insertBackGroundCustomerBalanceLog(log);
                log.setTypeId(log.getId());
                balanceLogThreadLocal.set(log);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  异常通知 删除记录的客户余额变动日志
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    @Order(value = 1)
    public  void doAfterThrowing(JoinPoint joinPoint, Exception  e) {
        System.out.println(e.getMessage());
        System.out.println("**********执行删除操作**************");
        BackGroundCustomerBalanceLog log = balanceLogThreadLocal.get();
        new CustomerBalanceLogAop.UpdateLogThread(log, logService).start();
    }


//    /**
//     * 保存日志线程
//     */
//    private static class SaveLogThread extends Thread {
//        private BackGroundCustomerBalanceLog log;
//        private BackGroundCustomerBalanceLogService logService;
//
//        public SaveLogThread(BackGroundCustomerBalanceLog log, BackGroundCustomerBalanceLogService logService) {
//            this.log = log;
//            this.logService = logService;
//        }
//
//        @Override
//        public void run() {
//            logService.insertBackGroundCustomerBalanceLog(log);
//            log.setId(log.getId());
//            System.out.println(log.getId());
//            balanceLogThreadLocal.set(log);
//        }
//    }

    /**
     * 日志更新线程
     */
    private static class UpdateLogThread extends Thread {
        private BackGroundCustomerBalanceLog log;
        private BackGroundCustomerBalanceLogService logService;

        public UpdateLogThread(BackGroundCustomerBalanceLog log, BackGroundCustomerBalanceLogService logService) {
            this.log = log;
            this.logService = logService;
        }

        @Override
        public void run() {
            System.out.println("删除**********************");
            this.logService.deleteBackGroundCustomerBalanceLog(log);
        }
    }
}
