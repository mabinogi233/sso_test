package com.main.sso.myServlet;


import com.main.sso.MVC.bean.beanFactory;
import com.main.sso.MVC.filter.afterFilterFactory;
import com.main.sso.MVC.filter.beforeFilterFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class coreFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //request过滤器

        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //按照过滤器url找出method
        Method method = beforeFilterFactory.getMethod(request.getServletPath(),true);
        if(method!=null){
            Object obj1 = null;
            obj1 = beanFactory.getBean(method.getDeclaringClass().getSimpleName());
            //执行方法
            try {
                method.invoke(obj1,req,resp);
            } catch (IllegalAccessException e) {
                //方法没有权限调用
                e.printStackTrace();
            } catch (InvocationTargetException e){
                //方法内部错误
                e.printStackTrace();
            }
        }

        chain.doFilter(req, resp);

        //response过滤器
        //按照过滤器url找出method
        Method method_after = afterFilterFactory.getMethod(request.getServletPath(),true);
        if(method_after!=null){
            Object obj2 = null;
            obj2 = beanFactory.getBean(method_after.getDeclaringClass().getSimpleName());
            //执行方法
            try {
                method_after.invoke(obj2,req,resp);
            } catch (IllegalAccessException e) {
                //方法没有权限调用
                e.printStackTrace();
            } catch (InvocationTargetException e){
                //方法内部错误
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
