package com.fx.manage.bean;

import java.io.Serializable;
/**
 * 出版社
 * @author fx
 *
 */
public class Publish implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String publishname;
    private String contact;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPublishname() {
        return publishname;
    }
    public void setPublishname(String publishname) {
        this.publishname = publishname;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

}
