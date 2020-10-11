package com.main.sso.myServlet;



import com.main.sso.MVC.bean.beanFactory;
import com.main.sso.MVC.bean.methodFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


//核心Servlet

public class coreServlet extends HttpServlet {
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{


        String requestPath = request.getServletPath();
        System.out.println("请求地址："+requestPath);
        methodFactory.printAll();
        //获取所有类
        Method method = methodFactory.getMethod(requestPath);
        if(method!=null){
            Class<?> item = method.getDeclaringClass();
            String select_name = item.getSimpleName();
            //获取类实例的对象
            Object object = null;
            try {
                object = beanFactory.getBean(select_name);
                method.invoke(object,request,response);

            }  catch (IllegalAccessException | InvocationTargetException e) {
                //方法没有权限调用
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request,response);
    }
}
