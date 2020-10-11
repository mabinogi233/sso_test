package com.main.sso.MVC.bean;


public class beans {
    // id，唯一标识符
    String id;
    Object object = null;
    Class<?> object_class;


    public Class<?> getObject_class() {
        return object_class;
    }

    public Object getObject() {
        return object;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void setObject_class(Class<?> object_class) {
        this.object_class = object_class;
    }

}
