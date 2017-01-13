package org.qydata.annontation;

import java.lang.reflect.Method;

/**
 * Created by jonhn on 2017/1/13.
 */
public class ExportAnnontationParse {

    /**
     * 解析注解
     * @param targetClass　目标类的class形式
     * @param methodName　在客户端调用哪个方法,methodName就代表哪个方法　
     * @return
     * @throws Exception
     */
    public static String parse(Class targetClass, String methodName) throws Exception {
        String methodAccess = "";
        /*
         * 为简单起见，这里考虑该方法没有参数
         */
        Method method = targetClass.getMethod(methodName);
        //判断方法上是否有Privilege注解
        if (method.isAnnotationPresent(ExportAnnontation.class)) {
            //得到方法上的注解
            ExportAnnontation exportAnnontation = method.getAnnotation(ExportAnnontation.class);
            methodAccess = exportAnnontation.value();
        }
        return methodAccess;
    }
}
