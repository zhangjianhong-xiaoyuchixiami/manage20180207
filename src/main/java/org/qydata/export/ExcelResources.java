package org.qydata.export;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by jonhn on 2017/3/14.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelResources {

    /**
     * 属性的标题名称
     * @return
     */
    String title();
    /**
     * 在excel的顺序
     * @return
     */
    int order() default 9999;

}
