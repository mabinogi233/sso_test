package com.main.sso.entity;

//实体层,user实体POJO
public class User {

    String username;

    String password;

    String code;

    public String getCode() {
        return code;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
