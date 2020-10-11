package com.main.sso.controller;


import com.main.sso.MVC.anno.mvc.Controller;
import com.main.sso.MVC.anno.mvc.RequestURI;
import com.main.sso.OAuth2.codeFactory;
import com.main.sso.entity.Client;
import com.main.sso.jdbc.clientMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@Controller
public class clientRegister {
    //获取页面
    @RequestURI("/client/getClientId")
    public void registerClient(HttpServletRequest request, HttpServletResponse response){
        try {
            Writer out = response.getWriter();
            out.write("<html>\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <title>getClientId</title>\n" +
                    "</head>\n" +
                    "\n" +
                    "\n" +
                    "<body>\n" +
                    "<h2>input client name to get client_id</h2>"+
                    "<form method='post' action='" +
                    "/client/check"
                    + "'>\n" +
                    "  <input type='text' name='clientName'><br/>\n" +
                    "  <input type=\"submit\" value=\"getClientId\">\n" + "<br/>" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>");
            out.flush();
            out.close();
        }catch (IOException e){
            //无法打开response输出流
            e.printStackTrace();
        }
    }
    //获取服务端id
    @RequestURI("/client/check")
    public void registerClientCheck(HttpServletRequest request, HttpServletResponse response){
        String clientName = request.getParameter("clientName");
        clientMapper mapper = new clientMapper();
        String clientID = codeFactory.getCode(clientName,"");
        if(mapper.select(clientID)==null){
            //注册成功，返回code
            Client client = new Client();
            client.setClientName(clientName);
            client.setClientID(clientID);
            mapper.insert(client);
            try {
                Writer out = response.getWriter();
                out.write(clientID);
                out.flush();
                out.close();
            }catch (IOException e){
                //无法打开response输出流
                e.printStackTrace();
            }
        }else{
            //直接返回code，已经注册
            try {
                Writer out = response.getWriter();
                out.write(clientID);
                out.flush();
                out.close();
            }catch (IOException e){
                //无法打开response输出流
                e.printStackTrace();
            }
        }
    }
}
