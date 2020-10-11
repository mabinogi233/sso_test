package com.main.sso.MVC.bean;

import com.main.sso.MVC.anno.filter.FilterOperator;
import com.main.sso.MVC.anno.mvc.ControllerRequestMappingOperator;
import com.main.sso.MVC.utils.*;

import java.util.Set;

//初始化beans
public class add_beans {
    /**
     * 框架初始化
     */
    public static void init() {
        //扫描包下的所有类
        System.out.println("开始注入beans");
        String packageName = "com";
        Set<Class<?>> classSet = packageClassScanner.getClasses(packageName);
        for(Class<?> item:classSet){
            //处理Controller和RequestMapping注解
            ControllerRequestMappingOperator.operator_Controller(item);
            //处理Filter注解的类
            FilterOperator.operator_filter(item);
        }
        System.out.println("结束初始化");
        beanFactory.printAll();
        methodFactory.printAll();
    }
}
