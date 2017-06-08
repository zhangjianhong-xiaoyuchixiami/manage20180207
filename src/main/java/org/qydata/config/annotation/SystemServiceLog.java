package org.qydata.config.annotation;

import java.lang.annotation.*;

/**
 * Created by jonhn on 2017/3/28.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemServiceLog {

    /**
     * 描述业务操作 例:Xxx管理-执行Xxx操作
     * @return
     */
   public String description()  default "";

}
