package com.main.sso.login.loginCheck;

import com.main.sso.entity.User;
import com.main.sso.jdbc.userMapper;
import com.main.sso.login.autoLogin.sessionUtils;
import com.main.sso.template.access;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class checkLogin {
    /**
     * 登录验证，成功则返回code，失败则返回null
     * @param request
     * @param response
     * @param username
     * @param password
     * @return
     */
    public static String check_Login(HttpServletRequest request, HttpServletResponse response,String username, String password){
        if(username!=null && password!=null){
            User user = new userMapper().select(username);
            if(user!=null){
                if(user.getPassword().equals(password)){
                    //保存cookie和session
                    HttpSession session = request.getSession(true);
                    Cookie newCookie = new Cookie("session_id", session.getId());
                    newCookie.setMaxAge(3600);
                    newCookie.setDomain("localhost");
                    newCookie.setPath("/");
                    newCookie.setSecure(false);
                    response.addCookie(newCookie);
                    session.setAttribute("username",user.getUsername());
                    session.setAttribute("password",user.getPassword());
                    session.setAttribute("code",user.getCode());
                    sessionUtils.addSession(session);
                    //登录成功
                    return user.getCode();

                }
            }
        }
        return null;
    }
}
