package com.main.sso.MVC.anno.mvc;

import java.lang.annotation.*;

//控制层
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Controller {
    String value() default "";
}
