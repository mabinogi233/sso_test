package com.main.sso.login.autoLogin;

import com.main.sso.entity.User;
import com.main.sso.jdbc.userMapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class autoLogin {
    /**
     * 自动登录检测，满足则返回用户的code，不满足则返回null
     * @param request
     * @return
     */
    public static String check_auto_login(HttpServletRequest request){
        System.out.println("检测自动登录");
        Cookie[] cookies = request.getCookies();
        for(Cookie c:cookies){
            if("session_id".contentEquals(c.getName())){
                if(c.getValue()!=null){
                    String session_id = c.getValue();
                    if(session_id!=null) {
                        //获取Session
                        HttpSession session = sessionUtils.getSession(session_id);
                        if (session != null) {
                            //检验session的信息是否过期，不过期则返回授权界面
                            String username = (String) session.getAttribute("username");
                            String password = (String) session.getAttribute("password");
                            if(username!=null&&password!=null) {
                                User user = new userMapper().select(username);
                                if(user!=null){
                                    if(user.getPassword().equals(password)){
                                        return (String) session.getAttribute("code");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
