package com.main.sso.login.autoLogin;


import javax.servlet.http.HttpSession;
import java.util.HashMap;

//session统一管理
public class sessionUtils {
    //session map
    private static HashMap<String, HttpSession> sessionMap = new HashMap<String, HttpSession>();

    /**
     * 加入session
     * @param session
     */
    public static synchronized void addSession(HttpSession session) {
        if (session != null) {
            if(sessionMap.get(session.getId())!=null){
                sessionMap.replace(session.getId(), session);
            }else{
                sessionMap.put(session.getId(), session);
            }
        }
    }

    /**
     * 删除session
     * @param session
     */
    public static synchronized void deleteSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    /**
     * 获取session
     * @param sessionID
     * @return
     */
    public static HttpSession getSession(String sessionID) {
        if (sessionID == null) {
            return null;
        }
        return sessionMap.get(sessionID);
    }

    public static HashMap<String, HttpSession> getSessionMap() {
        return sessionMap;
    }
}

