package org.qydata.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.qydata.entity.User;
import org.qydata.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jonhn on 2017/3/13.
 */
@Aspect
@Component
@Slf4j
public class CustomerPermissionAop {

    @Autowired private HttpServletRequest request;
    @Autowired private CustomerService customerService;

    /**
     * 权限检查
     * @param point
     */
    @Around("execution(* org.qydata.controller.FinanceController.find*(..))")
    public Object permissionCheck(ProceedingJoinPoint point) throws Throwable {
        System.out.println("@Before：****************** 权限检查 ******************");
        System.out.println("@Before：目标方法为：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        System.out.println("@Before：权限检查之前参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@Before：****************** 开始权限检查 ******************");
        Object args [] = point.getArgs();
        if (args != null && args.length > 0 && args[0].getClass() == Integer.class) {
            Subject subject = SecurityUtils.getSubject();
            if (subject.hasRole("sell")){
                User user = (User)request.getSession().getAttribute("userInfo");
                List deptIdList = new ArrayList();
                for(int i =0;i<user.getDept().size();i++){
                    deptIdList.add(user.getDept().get(i).getId());
                }
                if (!customerService.findAllCustomerIdByDeptId(deptIdList).contains(args[0])){
                    //args[0] = -100;
                    return "/view/role";
                }
            }
        }
        System.out.println("@Before：权限检查之后参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
        return point.proceed(args);
    }


//    @Around("execution(* org.qydata.controller.FinanceController.findAllCustomerRechargeLogByCustomerId(..))")
//    public Object process(ProceedingJoinPoint point) throws Throwable {
//        System.out.println("@Around：执行目标方法之前...");
//        //访问目标方法的参数：
//        Object[] args = point.getArgs();
//        if (args != null && args.length > 0 && args[0].getClass() == String.class) {
//            args[0] = 1111111;
//        }
//        //用改变后的参数执行目标方法
//        Object returnValue = point.proceed(args);
//        System.out.println("@Around：执行目标方法之后...");
//        System.out.println("@Around：被织入的目标对象为：" + point.getTarget());
//        return "原返回值：" + returnValue + "，这是返回结果的后缀";
//    }

//    @AfterReturning(pointcut="execution(* org.qydata.controller.FinanceController.findAllCustomerRechargeLogByCustomerId(..))",
//            returning="returnValue")
//    public void log(JoinPoint point, Object returnValue) {
//        System.out.println("@AfterReturning：模拟日志记录功能...");
//        System.out.println("@AfterReturning：目标方法为：" +
//                point.getSignature().getDeclaringTypeName() +
//                "." + point.getSignature().getName());
//        System.out.println("@AfterReturning：参数为：" +
//                Arrays.toString(point.getArgs()));
//        System.out.println("@AfterReturning：返回值为：" + returnValue);
//        System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
//    }

//    @After("execution(* org.qydata.controller.FinanceController.findAllCustomerRechargeLogByCustomerId(..))")
//    public void releaseResource(JoinPoint point) {
//        System.out.println("@After：模拟释放资源...");
//        System.out.println("@After：目标方法为：" +
//                point.getSignature().getDeclaringTypeName() +
//                "." + point.getSignature().getName());
//        System.out.println("@After：参数为：" + Arrays.toString(point.getArgs()));
//        System.out.println("@After：被织入的目标对象为：" + point.getTarget());
//    }


}
