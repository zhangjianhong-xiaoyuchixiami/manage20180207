package org.qydata.config.aop;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/8.
 */
@Aspect
@Component
@Slf4j
public class RolePermissionAop {

    //扫描“RolePermission”注解的Controller层切点
    @Pointcut("@annotation(org.qydata.config.annotation.RolePermission)")
    public  void rolePermissionAspect() {

    }

    /**
     * 权限检查
     * @param point
     */
    @Around("rolePermissionAspect()")
    public Object rolePermissionCheck(ProceedingJoinPoint point) throws Throwable {
        System.out.println("@Before：****************** 角色权限检查 ******************");
        System.out.println("@Before：目标方法为：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        System.out.println("@Before：角色权限检查之前参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@Before：****************** 开始角色权限检查 ******************");
        Object args [] = point.getArgs();
        Map<String,Object> mapParam = new HashMap<>();
        mapParam.put("value",1);
        Subject subject = SecurityUtils.getSubject();
        if (!subject.hasRole("backAdmin")){
            Map<String,Object> map = new HashMap<>();
            map.put("role_warning","对不起，您没有操作此功能的权限哦！");
            return new Gson().toJson(map);
        }
        System.out.println("@Before：****************** 未进行角色权限检查 ******************");
        System.out.println("@Before：角色权限检查之后参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
        return point.proceed(args);
    }


}
