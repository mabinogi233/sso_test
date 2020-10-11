package com.main.sso.controller;


import com.main.sso.MVC.anno.mvc.Controller;
import com.main.sso.MVC.anno.mvc.RequestURI;
import com.main.sso.OAuth2.tokenFactory;
import com.main.sso.checkClient.AdmClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@Controller
public class ResourcesController {
    @RequestURI("/getResources")
    public void getResources(HttpServletRequest request, HttpServletResponse response){
        //后端测试
        tokenFactory.printRTmap();
        tokenFactory.printTTmap();

        //access_token，必选参数
        String access_token = request.getParameter("access_token");
        //服务端id参数，必选
        String client_id = request.getParameter("client_id");
        //验证id
        if(AdmClient.checkClient(client_id)){
            System.out.println(access_token);
            if(tokenFactory.checkToken(access_token)){
                //资源获取成功
                try {
                    Writer out = response.getWriter();
                    out.write("resources get success");
                    out.flush();
                    out.close();
                }catch (IOException e){
                    //无法打开response输出流
                    e.printStackTrace();
                }
                return;
            }
        }
        //资源获取失败
        try {
            Writer out = response.getWriter();
            out.write("resources get fail");
            out.flush();
            out.close();
        }catch (IOException e){
            //无法打开response输出流
            e.printStackTrace();
        }
    }
}
