package com.main.sso.login.registerCheck;

import com.main.sso.entity.User;
import com.main.sso.jdbc.userMapper;

public class registerCheck {
    /**
     * 是否接受注册
     * @param username
     * @param password
     * @param re_password
     * @return
     */
    public static boolean register(String username,String password,String re_password){
        userMapper mapper = new userMapper();
        if(username!=null&&password!=null&&re_password!=null){
            if(password.equals(re_password)) {
                User user = mapper.select(username);
                return user == null;
            }
        }
        return false;
    }
}
