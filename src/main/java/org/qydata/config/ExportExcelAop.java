package org.qydata.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.qydata.util.ViewExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jonhn on 2017/1/11.
 */
@Aspect
@Component
@Slf4j
public class ExportExcelAop {

    private final Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private HttpServletRequest request;


    @Pointcut("execution(* org.qydata.controller.TestController.*(..))")
    private void pointCutMethod(){}

//    @Before("pointCutMethod()")
//    public void doBefore() {
//        String export = request.getParameter("export");
//        System.out.println("前置通知");
//        System.out.println(export);
//        if (export == null){
//
//        }
//        ViewExcel viewExcel = new ViewExcel();
//        ModelMap map = new ModelMap();
//        map.put("list",userList);
//        return new ModelAndView(viewExcel,map);
    //   }

//    @AfterReturning(pointcut = "pointCutMethod()",returning = "result"   )
//    public void doAfterReturning(String result) {
//        System.out.println("后置通知");
//        System.out.println("---" + result + "---");
//    }

//    /**
//     *  声明例外通知
//     */
//    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
//    public void doAfterThrowing(Exception e) {
//        System.out.println("例外通知");
//        System.out.println(e.getMessage());
//    }

//    @After("pointCutMethod()")
//    public void doAfter() {
//        System.out.println("最终通知");
//    }


    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        String export = request.getParameter("export");

        Object o = pjp.proceed();
        System.out.println(export);
        if (export != null){
            Object[] obj=   pjp.getArgs();
            for (int i=0; i<obj.length; i++){
                ViewExcel viewExcel = new ViewExcel();
                ModelMap map = new ModelMap();
                map.put("list",obj[0]);
                ModelAndView modelAndView = new ModelAndView(viewExcel,map);
            }
//            ViewExcel viewExcel = new ViewExcel();
//            ModelMap map = new ModelMap();
//            map.put("list",userList);
//            return new ModelAndView(viewExcel,map);
        }
        return o;
    }

}
