package com.main.sso.MVC.anno.mvc;



import com.main.sso.MVC.bean.beanFactory;
import com.main.sso.MVC.bean.methodFactory;

import java.lang.reflect.Method;

//Controller注解和RequestMapping注解处理器
public class ControllerRequestMappingOperator {
    /**
     * 输入一个class实例，根据注解进行操作，控制器注入
     * @param item
     */
    public static void operator_Controller(Class<?> item){
        boolean isController = item.isAnnotationPresent(Controller.class);
        if (!isController) {
            return;
        }

        //加入beanFactory
        beanFactory.addBean(item.getSimpleName(),item);

        // 类方法为空不继续执行
        Method[] methods = item.getMethods();
        if (methods == null || methods.length <= 0) {
            return;
        }

        //获取类的RequestMapping注解信息
        String value_class="";
        if(item.isAnnotationPresent(RequestURI.class)) {
            value_class = item.getAnnotation(RequestURI.class).value();
        }
        for(Method method:methods){
            boolean annotationPresent = method.isAnnotationPresent(RequestURI.class);
            if (annotationPresent) {
                //获取方法注解的全部value
                String value_method = method.getAnnotation(RequestURI.class).value();
                //将url与method加入methodFactory
                String url = value_class + value_method;
                methodFactory.addURLMethod(url,method);
            }
        }
    }
}
