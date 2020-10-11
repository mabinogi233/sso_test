package com.main.sso.myServlet;


import com.main.sso.MVC.bean.add_beans;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//监听器，实现注入
public class servletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //初始化框架
        add_beans.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }


}
