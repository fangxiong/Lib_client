package com.fx.manage.bean;

import java.io.Serializable;

/**
 * 管理员
 * @author fx
 *
 */
public class Administrator implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String account;
    private String name;
    private String pd;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPd() {
        return pd;
    }
    public void setPd(String pd) {
        this.pd = pd;
    }


}
