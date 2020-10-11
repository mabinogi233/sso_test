package com.main.sso.controller;

import com.main.sso.MVC.anno.mvc.Controller;
import com.main.sso.MVC.anno.mvc.RequestURI;
import com.main.sso.OAuth2.codeFactory;
import com.main.sso.checkClient.AdmClient;
import com.main.sso.entity.User;
import com.main.sso.jdbc.userMapper;
import com.main.sso.login.autoLogin.autoLogin;
import com.main.sso.login.loginCheck.checkLogin;
import com.main.sso.login.registerCheck.registerCheck;
import com.main.sso.template.access;
import com.main.sso.template.login;
import com.main.sso.template.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CodeController {
    /**
     * 接受服务端跳转请求，处理登录请求
     * @param request
     * @param response
     */
    @RequestURI("/login")
    public void login(HttpServletRequest request, HttpServletResponse response){
        System.out.println("开始程序");
        //必须参数，服务端id
        String client_id = request.getParameter("client_id");
        //必须参数，目前只支持code
        String response_type = request.getParameter("response_type");
        //必须参数，回调uri
        String redirect_uri = request.getParameter("redirect_uri");
        //可选参数，回调返回相同的state
        String state = request.getParameter("state");
        //验证模式和服务端id
        if(response_type.equals("code")&& AdmClient.checkClient(client_id)){
            //自动登录认证
            String code = autoLogin.check_auto_login(request);
            if(code==null){
                //跳转到登录界面
                System.out.println("自动登录失败");
                //跳转到登录界面
                //生成from表单地址
                String URI = "/check";
                //参数填充
                Map<String,Object> map= new HashMap<String,Object>();
                map.put("uri",URI);
                map.put("register_uri","/register?client_id="+client_id+"&response_type="+
                        response_type+"&redirect_uri="+redirect_uri+"&state="+state);
                //以下参数使用隐藏域hidden，传输加密后的信息
                map.put("client_id",client_id);
                map.put("response_type",response_type);
                map.put("redirect_uri",redirect_uri);
                map.put("state",state);
                login page1 = new login();
                page1.wirteInResponse(response,map);
            }else{
                //跳转到授权界面
                System.out.println("自动登录成功");
                //生成回调链接
                String URI=redirect_uri+"?code=" + code +"&state="+state;
                //生成页面（JSP1.0）
                access page2 = new access();
                //填充参数
                Map<String,Object> map= new HashMap<String,Object>();
                map.put("uri",URI);
                //返回页面
                page2.wirteInResponse(response,map);
            }
        }


    }
    @RequestURI("/check")
    public void check(HttpServletRequest request,HttpServletResponse response)  {
        System.out.println("开始验证");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //必须参数，服务端id
        String client_id = request.getParameter("client_id");
        //必须参数，目前只支持code
        String response_type = request.getParameter("response_type");
        //必须参数，回调uri
        String redirect_uri = request.getParameter("redirect_uri");
        //可选参数，回调返回相同的state
        String state = request.getParameter("state");
        //登录验证，存储cookie和session
        String code = checkLogin.check_Login(request,response,username,password);
        if(code==null){
            //登录失败，跳转到登录界面
            String URI = "/check";
            Map<String,Object> map= new HashMap<String,Object>();
            map.put("uri",URI);
            map.put("fail",true);
            map.put("client_id",client_id);
            map.put("response_type",response_type);
            map.put("redirect_uri",redirect_uri);
            map.put("register_uri","/register?client_id="+client_id+"&response_type="+
                    response_type+"&redirect_uri="+redirect_uri+"&state="+state);
            map.put("state",state);
            login page1 = new login();
            page1.wirteInResponse(response,map);
        }else{
            //跳转到授权界面
            String URI=redirect_uri+"?code="+code+"&state="+state;
            access page2 = new access();
            Map<String,Object> map= new HashMap<String,Object>();
            map.put("uri",URI);
            page2.wirteInResponse(response,map);
        }
    }

    @RequestURI("/register")
    public void registerPage(HttpServletRequest request,HttpServletResponse response){
        //注册
        //必须参数，服务端id
        String client_id = request.getParameter("client_id");
        //必须参数，目前只支持code
        String response_type = request.getParameter("response_type");
        //必须参数，回调uri
        String redirect_uri = request.getParameter("redirect_uri");
        //必选参数，回调返回相同的state
        String state = request.getParameter("state");
        //生成注册页面
        String URI = "/registerCheck";
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("uri",URI);
        map.put("client_id",client_id);
        map.put("response_type",response_type);
        map.put("redirect_uri",redirect_uri);
        map.put("state",state);
        register page1 = new register();
        page1.wirteInResponse(response,map);
    }
    @RequestURI("/registerCheck")
    public void checkRegister(HttpServletRequest request,HttpServletResponse response){
        String client_id = request.getParameter("client_id");
        String response_type = request.getParameter("response_type");
        String redirect_uri = request.getParameter("redirect_uri");
        String state = request.getParameter("state");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String re_password = request.getParameter("re_password");
        //判断可以注册
        if(registerCheck.register(username,password,re_password)){
            //生成code
            String code = codeFactory.getCode(username,password);
            //存储code
            User user = new User();
            user.setCode(code);
            user.setPassword(password);
            user.setUsername(username);
            new userMapper().insert(user);
            //跳转到登录界面
            //生成from表单地址
            String URI = "/check";
            //参数填充
            Map<String,Object> map= new HashMap<String,Object>();
            map.put("uri",URI);
            map.put("register_uri","/register?client_id="+client_id+"&response_type="+
                    response_type+"&redirect_uri="+redirect_uri+"&state="+state);
            //以下参数使用隐藏域hidden，传输加密后的信息
            map.put("client_id",client_id);
            map.put("response_type",response_type);
            map.put("redirect_uri",redirect_uri);
            map.put("state",state);
            login page1 = new login();
            page1.wirteInResponse(response,map);
        }
        //注册失败，跳转到注册界面
        String URI = "/registerCheck";
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("uri",URI);
        map.put("client_id",client_id);
        map.put("response_type",response_type);
        map.put("redirect_uri",redirect_uri);
        map.put("state",state);
        map.put("fail",true);
        register page1 = new register();
        page1.wirteInResponse(response,map);
    }

}
