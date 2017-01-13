package org.qydata.annontation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jonhn on 2017/1/13.
 */
@Target(ElementType.METHOD)//这个注解是应用在方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface ExportAnnontation {
    /**
     * @return
     */
    String view();

    String value() default "";
}
