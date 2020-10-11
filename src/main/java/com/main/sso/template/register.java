package com.main.sso.template;

import java.util.Map;

public class register extends template {

    @Override
    public String createPage(Map<String, Object> map) {
        String page;
        Object object = map.get("fail");
        if(object!=null) {
            page = "<html>\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <title>login WebService</title>\n" +
                    "</head>\n" +
                    "\n" +
                    "\n" +
                    "<body>\n" +
                    "<h2>register fail</h2>"+
                    "<form method='post' action='" +
                    (String) map.get("uri")
                    + "'>\n" +
                    "  <input type='text' name='username'><br/>\n" +
                    "  <input type='password' name='password'><br/>\n" +
                    "  <input type='password' name='re_password'><br/>\n" +
                    "  <input type=\"hidden\" name='client_id' value=\"" + (String) map.get("client_id") + "\">\n" +
                    "  <input type=\"hidden\" name='response_type' value=\"" + (String) map.get("response_type") + "\">\n" +
                    "  <input type=\"hidden\" name='redirect_uri' value=\"" + (String) map.get("redirect_uri") + "\">\n" +
                    "  <input type=\"hidden\" name='state' value=\"" + (String) map.get("state") + "\">\n" +
                    "  <input type=\"submit\" value=\"register\">\n" + "<br/>" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>";
            return page;
        }else{
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
                    "  <input type='password' name='re_password'><br/>\n" +
                    "  <input type=\"hidden\" name='client_id' value=\"" + (String) map.get("client_id") + "\">\n" +
                    "  <input type=\"hidden\" name='response_type' value=\"" + (String) map.get("response_type") + "\">\n" +
                    "  <input type=\"hidden\" name='redirect_uri' value=\"" + (String) map.get("redirect_uri") + "\">\n" +
                    "  <input type=\"hidden\" name='state' value=\"" + (String) map.get("state") + "\">\n" +
                    "  <input type=\"submit\" value=\"register\">\n" + "<br/>" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>";
            return page;
        }
    }
}
