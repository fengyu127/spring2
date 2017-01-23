package com.admin;

import java.util.Date;

/**
 * Created by Administrator on 2017/1/23.
 */
public class Admin {
    private int id;
    private String user;
    private  String password;
    private  Date createtime;
    private Date Logintime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLogintime() {
        return Logintime;
    }

    public void setLogintime(Date logintime) {
        Logintime = logintime;
    }
}
