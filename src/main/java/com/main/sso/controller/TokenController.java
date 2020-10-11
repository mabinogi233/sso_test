package com.main.sso.controller;


import com.main.sso.MVC.anno.mvc.Controller;
import com.main.sso.MVC.anno.mvc.RequestURI;
import com.main.sso.OAuth2.Token;
import com.main.sso.OAuth2.tokenFactory;
import com.main.sso.checkClient.AdmClient;
import com.main.sso.entity.User;
import com.main.sso.jdbc.userMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@Controller
public class TokenController {
    @RequestURI("/getToken")
    public void getToken(HttpServletRequest request, HttpServletResponse response){
        //code，必选参数
        String code = request.getParameter("code");
        //授权模式，恒定为 authorization_code，必选
        String grant_type = request.getParameter("grant_type");
        //服务端id参数，必选
        String client_id = request.getParameter("client_id");
        // grant_type验证

        if(grant_type.equals("authorization_code")) {
            //client_id验证
            if(AdmClient.checkClient(client_id)) {
                //code验证
                userMapper mapper = new userMapper();
                User user = mapper.selectByCode(code);
                if (user != null) {
                    //返回token
                    Token token = tokenFactory.createTokenByCode(code);
                    String jsonToken="{\"access_token\":\""+token.getToken()+"\"," +
                            "\"refresh_token\":\""+token.getReflesh_token()+"\"," +
                            "\"token_type\":\"test\"," +
                            "\"expires_in\":\"36000\"}";
                    try {
                        Writer out = response.getWriter();
                        out.write(jsonToken);
                        out.flush();
                        out.close();
                    }catch (IOException e){
                        //无法打开response输出流
                        e.printStackTrace();
                    }
                    return;
                }
            }
        }
        //获取失败
        String jsonToken="{\"access_token\":\""+"error"+"\"," +
                "\"refresh_token\":\""+"error"+"\"," +
                "\"token_type\":\"test\"," +
                "\"expires_in\":\"36000\"}";
        try {
            Writer out = response.getWriter();
            out.write(jsonToken);
            out.flush();
            out.close();
        }catch (IOException e){
            //无法打开response输出流
            e.printStackTrace();
        }
    }
    @RequestURI("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response){
        //授权模式，恒定为 authorization_code，必选
        String refresh_token = request.getParameter("refresh_token");
        //服务端id参数，必选
        String client_id = request.getParameter("client_id");
        //id验证
        if(AdmClient.checkClient(client_id)) {
            //refresh_token验证
            Token token = tokenFactory.reflesh_token(refresh_token);
            if(token!=null) {
                //返回token
                String jsonToken = "{\"access_token\":\"" + token.getToken() + "\"," +
                        "\"refresh_token\":\"" + token.getReflesh_token() + "\"," +
                        "\"token_type\":\"test\"," +
                        "\"expires_in\":\"36000\"}";
                try {
                    Writer out = response.getWriter();
                    out.write(jsonToken);
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    //无法打开response输出流
                    e.printStackTrace();
                }
                return;
            }
        }
        //刷新失败
        String jsonToken="{\"access_token\":\""+"error"+"\"," +
                "\"refresh_token\":\""+"error"+"\"," +
                "\"token_type\":\"test\"," +
                "\"expires_in\":\"36000\"}";
        try {
            Writer out = response.getWriter();
            out.write(jsonToken);
            out.flush();
            out.close();
        }catch (IOException e){
            //无法打开response输出流
            e.printStackTrace();
        }
    }
}
