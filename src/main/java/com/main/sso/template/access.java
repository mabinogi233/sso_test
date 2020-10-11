package com.main.sso.template;

import java.util.Map;

//生成授权界面
public class access extends template{

    @Override
    public String createPage(Map<String,Object> map) {
        String page="<html>\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <title>authorize and jump</title>\n" +
                "</head>\n" +
                "\n" +
                "\n" +
                "<body>\n" +
                "<a href=\""+
                (String) map.get("uri")+
                "\">\n" +
                "    <button>authorize and login</button>\n" +
                "</a>"+
                "</body>\n" +
                "</html>";
        return page;
    }
}
