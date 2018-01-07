package com.fx.manage.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 预订表单
 * @author 15934
 *
 */
public class Reserve implements Serializable{
	private static final long serialVersionUID = 1L;
	private int reserveid;
	private int userid;
	private int bookid;
	private String reservetime;
	private int staus;
	public int getReserveid() {
		return reserveid;
	}
	public void setReserveid(int reserveid) {
		this.reserveid = reserveid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getReservetime() {
		return reservetime;
	}
	public void setReservetime(String reservetime) {
		this.reservetime = reservetime;
	}

	public int getStaus() {
		return staus;
	}

	public void setStaus(int staus) {
		this.staus = staus;
	}
}
