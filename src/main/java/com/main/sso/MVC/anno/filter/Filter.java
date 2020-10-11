package com.main.sso.MVC.anno.filter;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
/**
 * url为过滤器需要过滤的url
 */
public @interface Filter {
    String value() default "";
}
