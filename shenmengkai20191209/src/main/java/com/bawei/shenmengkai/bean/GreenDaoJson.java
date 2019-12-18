package com.bawei.shenmengkai.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GreenDaoJson {
    private String jsonStr;

    @Generated(hash = 956113598)
    public GreenDaoJson(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    @Generated(hash = 903362959)
    public GreenDaoJson() {
    }

    @Override
    public String toString() {
        return "GreenDaoJson{" +
                "jsonStr='" + jsonStr + '\'' +
                '}';
    }

    public String getJsonStr() {
        return this.jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }
}
