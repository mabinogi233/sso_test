package com.main.sso.template;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public abstract class template {
    /**
     * 主方法，页面写入输出流
     * @param response
     */
    public void wirteInResponse(HttpServletResponse response,Map<String,Object> map){
        try {
            Writer out = response.getWriter();
            out.write(createPage(map));
            out.flush();
            out.close();
        }catch (IOException e){
            //无法打开response输出流
            e.printStackTrace();
        }
    }

    /**
     * 子类重写，生成模板，map为参数map
     * @param map
     * @return
     */
    public abstract String createPage(Map<String,Object> map);
}
