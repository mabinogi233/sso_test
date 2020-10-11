package com.main.sso.MVC.utils;


import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

//找出包中所有的类
public class packageClassScanner {
    /**
     *
     * @param packagePath 包名
     * @return Set<Class<?>> 包中的类的实例
     */

    //从包路径下扫描
    public static Set<Class<?>> getClasses(String packagePath)  {
        //类的集合
        Set<Class<?>> res = new HashSet<Class<?>>();
        //将包的路径.转化为实际的路径/
        String path = packagePath.replace(".", "/");
        //获取当前路径的URI表示
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        //包不存在
        if (url == null) {
            return res;
        }
        //判断是外部jar包还是文件
        String protocol = url.getProtocol();
        if ("jar".equalsIgnoreCase(protocol)) {

            try {
                res.addAll(getJarClasses(url, packagePath));
            } catch (IOException e) {
                e.printStackTrace();
                return res;
            }

        } else if ("file".equalsIgnoreCase(protocol)) {
            res.addAll(getFileClasses(url, packagePath));
        }
        return res;
    }
    //获取file路径下的class文件
    /**
     * 获取file路径下的class文件
     * @param url
     * @param packagePath
     * @return
     */
    private static Set<Class<?>> getFileClasses(URL url, String packagePath) {
        Set<Class<?>> res = new HashSet<Class<?>>();
        String filePath = url.getFile();
        File dir = new File(filePath);
        String[] list = dir.list();
        if (list == null) return res;
        for (String classPath : list) {
            if (classPath.endsWith(".class")) {
                classPath = classPath.replace(".class", "");
                try {
                    System.out.println(packagePath + "." + classPath);
                    if((packagePath + "." + classPath).equals("com.myMVC.controller.connect_mybatis.mybatisSession")){
                        continue;
                    }
                    Class<?> aClass = Class.forName(packagePath + "." + classPath);
                    res.add(aClass);
                } catch (ClassNotFoundException e) {
                    //找不到类
                    e.printStackTrace();
                }
            } else {
                res.addAll(getClasses(packagePath + "." + classPath));
            }
        }
        return res;
    }
    //使用JarURLConnection类获取路径下的所有类

    /**
     * 使用JarURLConnection类获取路径下的所有类
     * @param url
     * @param packagePath
     * @return
     * @throws IOException
     */
    private static Set<Class<?>> getJarClasses(URL url, String packagePath) throws  IOException {
        Set<Class<?>> res = new HashSet<Class<?>>();
        JarURLConnection conn = (JarURLConnection) url.openConnection();
        if (conn != null) {
            JarFile jarFile = conn.getJarFile();
            Enumeration<JarEntry> entries = jarFile.entries();
            while (((Enumeration) entries).hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                String name = jarEntry.getName();
                if (name.contains(".class") && name.replaceAll("/", ".").startsWith(packagePath)) {
                    String className = name.substring(0, name.lastIndexOf(".")).replace("/", ".");
                    try {
                        Class clazz = Class.forName(className);
                        res.add(clazz);
                    } catch (ClassNotFoundException e) {
                        //找不到类
                        e.printStackTrace();
                    }
                }
            }
        }
        return res;
    }
}
