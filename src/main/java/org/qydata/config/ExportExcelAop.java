package org.qydata.config;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        String export = request.getParameter("export");
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        System.out.println(export);
        System.out.println(url);
        System.out.println(method);
        System.out.println(uri);
        System.out.println(queryString);
        Object result = pjp.proceed();
        Object [] objects = pjp.getArgs();
        //System.out.println(objects);
//        for (int i=0; i<objects.length; i++){
//            JSONObject obj = (JSONObject) objects[i];
//           if (obj.get("name").equals("userList")){
//               List list = (List) obj.get("value");
//               System.out.println(list);
//           }
//        }
//        Gson gson= new Gson();
//        String list = gson.toJson(result);
//        System.out.println(list);
        JSONArray jsonArray = JSONArray.fromObject(objects);
        for (int i=0; i<jsonArray.size(); i++){
            if(jsonArray.get(i) != null) {
                System.out.println(jsonArray.get(i));
            }
        }
        return result;
    }

}
