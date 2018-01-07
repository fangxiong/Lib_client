package com.fx.manage.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * ���ı?
 * @author fx
 *
 */
public class Borrow implements Serializable{
	private static final long serialVersionUID = 1L;
	private int borrowid;
	private int bookid;
	private int userid;
	private String loantime;
	private String returntime;
	private String expiretime;
	private int isoverdue;//1Ϊ���ڣ�0Ϊδ����
	private double fine;
	public int getBorrowid() {
		return borrowid;
	}
	public void setBorrowid(int borrowid) {
		this.borrowid = borrowid;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getLoantime() {
		return loantime;
	}
	public void setLoantime(String loantime) {
		this.loantime = loantime;
	}
	public String getReturntime() {
		return returntime;
	}
	public void setReturntime(String returntime) {
		this.returntime = returntime;
	}
	public String getExpiretime() {
		return expiretime;
	}
	public void setExpiretime(String expiretime) {
		this.expiretime = expiretime;
	}
	public int getIsoverdue() {
		return isoverdue;
	}
	public void setIsoverdue(int isoverdue) {
		this.isoverdue = isoverdue;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
}
