package com.fx.manage.bean;

import java.io.Serializable;

/**
 * 学生用户
 * @author fx
 *
 */
public class Student implements Serializable{
    private static final long serialVersionUID = 1L;
    private int userid;
    private String account;//一卡通账号
    private String name;
    private String pd;
    private String sex;
    private String grade;
    private String banji;
    private String mailbox;
    private String major;
    private byte[] image;
    public int getUserid() {
        return userid;
    }
    public void setUserid(int id) {
        this.userid = id;
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
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getBanji() {
        return banji;
    }
    public void setBanji(String banji) {
        this.banji = banji;
    }
    public String getMailbox() {
        return mailbox;
    }
    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    public byte[] getImage() {return image;}
    public void setImage(byte[] image) {this.image = image;}

}
