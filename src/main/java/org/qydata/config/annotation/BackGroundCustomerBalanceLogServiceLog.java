package org.qydata.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jonhn on 2017/3/28.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BackGroundCustomerBalanceLogServiceLog {

    /**
     * 描述客户业务层余额变动操作
     * @return
     */
   public String description()  default "";

}
