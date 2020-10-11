package com.main.sso.MVC.anno.mvc;

import java.lang.annotation.*;

//路由映射
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface RequestURI {
    String value() default "";
}
