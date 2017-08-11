package org.qydata.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jonhn on 2017/8/8.
 */

@Target({ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RolePermission {

    /**
     * 描述业务操作 例:执行Xxx操作
     * @return
     */
    String description() default "";

}
