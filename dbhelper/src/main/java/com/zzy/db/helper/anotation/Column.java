package com.zzy.db.helper.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author zzy
 * @Date 2018/12/26 3:30 PM
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface Column {

    String name() default "";
    boolean isPK() default false;
}
