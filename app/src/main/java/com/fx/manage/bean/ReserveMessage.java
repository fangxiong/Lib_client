package com.fx.manage.bean;

import java.io.Serializable;

/**
 * Created by fangxiong on 2017/12/15.
 */
public class ReserveMessage implements Serializable{
    private static final long serialVersionUID = 1L;
    private int reserveid;
    private String bookname;
    private byte[] image;
    private String reservetime;
    private int status;

    public int getReserveid() {
        return reserveid;
    }

    public void setReserveid(int reserveid) {
        this.reserveid = reserveid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getReservetime() {
        return reservetime;
    }

    public void setReservetime(String reservetime) {
        this.reservetime = reservetime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
