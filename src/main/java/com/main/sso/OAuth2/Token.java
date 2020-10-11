package com.main.sso.OAuth2;

public class Token {
    //code
    String code;
    //token
    String token;
    //刷新token
    String reflesh_token;
    //失效时间
    Long end_time;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getEnd_time() {
        return end_time;
    }

    public String getReflesh_token() {
        return reflesh_token;
    }

    public String getToken() {
        return token;
    }

    public void setEnd_time(Long end_time) {
        this.end_time = end_time;
    }

    public void setReflesh_token(String reflesh_token) {
        this.reflesh_token = reflesh_token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
