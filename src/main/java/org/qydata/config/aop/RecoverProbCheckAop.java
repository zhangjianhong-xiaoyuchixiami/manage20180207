package org.qydata.config.aop;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.qydata.entity.RecoverProbCheck;
import org.qydata.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/7/24.
 */

@Aspect
@Component
@Slf4j
public class RecoverProbCheckAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ApiService apiService;

    //扫描“RecoverProbController”注解的Controller层切点
    @Pointcut("@annotation(org.qydata.config.annotation.RecoverProbController)")
    public  void controllerRecoverProbAspect() {

    }

    /**
     * 权限检查
     * @param point
     */
    @Around("controllerRecoverProbAspect()")
    public Object recoverProbCheck(ProceedingJoinPoint point) throws Throwable {
        System.out.println("@Before：****************** 恢复配额检查 ******************");
        System.out.println("@Before：目标方法为：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        System.out.println("@Before：恢复配额之前参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@Before：****************** 开始恢复配额检查 ******************");
        Object args [] = point.getArgs();
        Map<String,Object> mapParam = new HashMap<>();
        mapParam.put("value",1);
        List<RecoverProbCheck> recoverProbCheckList = apiService.queryAllRecoverProbCheck(mapParam);
        if (recoverProbCheckList != null && recoverProbCheckList.size() > 0) {
            System.out.println("@Before：****************** 进行配额恢复 ******************");
            Map<String,Object> map = new HashMap<>();
            map.put("warning","正在进行恢复配额操作，请稍后重试！");
            return new Gson().toJson(map);
        }
        System.out.println("@Before：****************** 未进行配额恢复 ******************");
        System.out.println("@Before：权限检查之后参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
        return point.proceed(args);
    }

}


