package com.bawei.myapplication.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MyBean {
    private String jsonStr;

    @Generated(hash = 1064746827)
    public MyBean(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    @Generated(hash = 1281580447)
    public MyBean() {
    }

    public String getJsonStr() {
        return this.jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "jsonStr='" + jsonStr + '\'' +
                '}';
    }
}
