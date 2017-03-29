package org.qydata.config.annotation;

import java.lang.annotation.*;

/**
 * Created by jonhn on 2017/3/28.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)

public @interface SystemServiceLog {

    String description()  default "";

}
