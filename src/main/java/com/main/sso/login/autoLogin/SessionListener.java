package com.main.sso.login.autoLogin;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//监听session的创建和销毁
public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se) {
        sessionUtils.addSession(se.getSession());
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        sessionUtils.deleteSession(se.getSession());
    }
}

