package com.main.sso.template;

import java.util.Map;

//生成登录界面
public class login extends template{
    @Override
    public String createPage(Map<String,Object> map) {
        String page;
        Object object = map.get("fail");
        if(object!=null){
            page= "<html>\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <title>login WebService</title>\n" +
                    "</head>\n" +
                    "\n" +
                    "\n" +
                    "<body>\n" +
                    "<h2>login fail,check username and password</h2>"+
                    "<form method='post' action='" +
                    (String) map.get("uri")
                    + "'>\n" +
                    "  <input type='text' name='username'><br/>\n" +
                    "  <input type='password' name='password'><br/>\n" +
                    "  <input type=\"hidden\" name='client_id' value=\"" + (String) map.get("client_id") + "\">\n" +
                    "  <input type=\"hidden\" name='response_type' value=\"" + (String) map.get("response_type") + "\">\n" +
                    "  <input type=\"hidden\" name='redirect_uri' value=\"" + (String) map.get("redirect_uri") + "\">\n" +
                    "  <input type=\"hidden\" name='state' value=\"" + (String) map.get("state") + "\">\n" +
                    "  <input type=\"submit\" value=\"login\">\n" +
                    "</form>\n" +
                    "<a href=\""+
                    (String) map.get("register_uri")+
                    "\">\n" +
                    "    <button>register</button>\n" +
                    "</a>"+
                    "</body>\n" +
                    "</html>";
        }else {
            page = "<html>\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <title>login WebService</title>\n" +
                    "</head>\n" +
                    "\n" +
                    "\n" +
                    "<body>\n" +
                    "<form method='post' action='" +
                    (String) map.get("uri")
                    + "'>\n" +
                    "  <input type='text' name='username'><br/>\n" +
                    "  <input type='password' name='password'><br/>\n" +
                    "  <input type=\"hidden\" name='client_id' value=\"" + (String) map.get("client_id") + "\">\n" +
                    "  <input type=\"hidden\" name='response_type' value=\"" + (String) map.get("response_type") + "\">\n" +
                    "  <input type=\"hidden\" name='redirect_uri' value=\"" + (String) map.get("redirect_uri") + "\">\n" +
                    "  <input type=\"hidden\" name='state' value=\"" + (String) map.get("state") + "\">\n" +
                    "  <input type=\"submit\" value=\"login\">\n" + "<br/>"+
                    "</form>\n" +
                    "<a href=\""+
                    (String) map.get("register_uri")+
                    "\">\n" +
                    "    <button>register</button>\n" +
                    "</a>"+
                    "</body>\n" +
                    "</html>";
        }
        return page;
    }
}
