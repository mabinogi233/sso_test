package com.main.sso.MVC.bean;


import java.util.HashMap;
import java.util.Map;

//bean的工厂，存储全部的bean
public class beanFactory  {
    private static Map<String, beans> beanMap = new HashMap<String, beans>();

    /**
     * 将bean加入map
     * @param beanClass
     */
    public static void addBean(Class<?> beanClass) {
        beans add_bean = new beans();
        add_bean.setId(beanClass.getSimpleName());
        add_bean.setObject_class(beanClass);
        add_bean.setObject(null);
        beanMap.putIfAbsent(beanClass.getSimpleName(), add_bean);
    }

    /**
     * 默认方式注入bean，使用无参数构造函数
     * @param name
     * @param beanClass
     */
    public static void addBean(String name,Class<?> beanClass) {
        beans add_bean = new beans();
        add_bean.setId(name);
        add_bean.setObject_class(beanClass);
        add_bean.setObject(null);
        beanMap.putIfAbsent(name, add_bean);
    }

    /**
     * 根据名称返回实例对象
     * @param name
     * @return
     */
    public static Object getBean(String name)  {
        try {
            if (beanMap.get(name) != null) {
                beans re_bean = beanMap.get(name);
                //生成对象.无参数构造函数
                if (re_bean.getObject() == null) {
                    //不存在实例对象时
                    Object bean = re_bean.getObject_class().getDeclaredConstructor().newInstance();
                    re_bean.setObject(bean);
                    beanMap.replace(name, re_bean);
                    return bean;
                } else {
                    //存在实例对象，直接返回
                    return re_bean.getObject();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 输出全部bean
     */
    public static void printAll(){
        System.out.println("*****************************************");
        for(beans item:beanMap.values()){
            if(item.getObject()!=null) {
                System.out.println(item.getObject().toString() + " " + item.getObject_class().toString());
            }else{
                System.out.println("null" + " " + item.getObject_class().toString());
            }
        }
        System.out.println("*****************************************");
    }
}
